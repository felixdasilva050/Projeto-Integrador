package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.request.SpecialAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.SpecialAccountRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransactionsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SpecialAccountService {

	Logger logger = Logger.getLogger(SpecialAccountService.class.getName());

	@Autowired
	private SpecialAccountRepository specialAccountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private  ReceiptService receiptService;
	
	//método que busca uma conta corrente pelo seu ID
	public SpecialAccount findById(Integer id) {
		Optional<SpecialAccount> object = specialAccountRepository.findById(id);
		return object.orElse(null);
	}
	
	//Método que lista todas as contas correntes
	public List<SpecialAccount> findAll(){
		return specialAccountRepository.findAll();
	}
	
//	Método que atualiza uma conta corrente dado um ID e uma conta corrente
	public SpecialAccount updateSpecialAccount(SpecialAccount specialAccount) throws Exception {

		Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.findByAccountNumber(specialAccount.getAccountNumber());
		if(findSpecialAccountInDb.isEmpty()){
			logger.info("Conta não localizada no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		SpecialAccount newSpecialAccount = new SpecialAccount();
		newSpecialAccount.setIdAccount(findSpecialAccountInDb.get().getIdAccount());
		newSpecialAccount.setAccountNumber(specialAccount.getAccountNumber());
		newSpecialAccount.setAccountType(AccountType.ESPECIAL_ACCOUNT);
		newSpecialAccount.setBalance(specialAccount.getBalance());
		newSpecialAccount.setLimitAmount(specialAccount.getLimitAmount());
		newSpecialAccount.setCustomer(findSpecialAccountInDb.get().getCustomer());

		return specialAccountRepository.save(newSpecialAccount);
	}
	
	//Método que cria uma conta corrente
	public SpecialAccount create(SpecialAccountRequest specialAccountRequest) throws Exception {
		Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.
				findByAccountNumber(specialAccountRequest.getAccountNumber());

			if(findSpecialAccountInDb.isPresent()) {
				logger.info("Conta já cadastrada no Banco de dados!");
				throw new Exception("Conta especial já cadastrada!");
			}

		Optional<Customer> findCustomer = customerRepository.findByDocumentNumber(specialAccountRequest.getDocumentNumber());
		if(findCustomer.isEmpty()) {
			logger.info("Cliente não localizado no banco de dados!");
			throw new Exception("Cliente não localizado!");
		}

			SpecialAccount newSpecialAccount = new SpecialAccount();
			newSpecialAccount.setAccountNumber(specialAccountRequest.getAccountNumber());
			newSpecialAccount.setAccountType(AccountType.ESPECIAL_ACCOUNT);
			newSpecialAccount.setLimitAmount(specialAccountRequest.getLimitAmount());
			newSpecialAccount.setBalance(specialAccountRequest.getBalance());
			newSpecialAccount.setCustomer(findCustomer.get());
			newSpecialAccount.setTotalLimitAmount(specialAccountRequest.getLimitAmount());
			newSpecialAccount.setInDebt(0.0);


			return specialAccountRepository.save(newSpecialAccount);
	}

	//Método que deleta uma conta corrente informando o número da conta
	public void delete(Integer id) throws Exception {
		Optional<SpecialAccount> findSpecialAccount = specialAccountRepository.
				findById(id);
		if(findSpecialAccount.isEmpty()) {
			logger.info("Conta não existe no banco de dados!");
			throw new Exception("Conta inexistente!");
		}
		specialAccountRepository.deleteById(findSpecialAccount.get().getIdAccount());
	}

	public String specialWithdraw(TransactionsRequest transactionsRequest) throws Exception {

		verifyWithdraw(transactionsRequest);

		return receiptService.createReceipt(transactionsRequest);
	}

	public String depositMoney(TransactionsRequest transactionsRequest) throws Exception {
		Optional<SpecialAccount> verifyAccountInDb = specialAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());
		if(verifyAccountInDb.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
			Double defineBalance = verifyAccountInDb.get().getBalance() + transactionsRequest.getValue();
			verifyAccountInDb.get().setBalance(defineBalance);

		specialAccountRepository.save(verifyAccountInDb.get());
		return receiptService.createReceipt(transactionsRequest);
	}

	private void verifyWithdraw(TransactionsRequest transactionsRequest) throws Exception {
		Optional<SpecialAccount> findAccountInDb = specialAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());

		//verifica se a conta existe no DB, caso não retorna exception
		if(findAccountInDb.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new Exception("Conta não localizada!");

			//verifica se possui saldo em conta para sacar, caso possuia apenas realiza o saque
		} else if (transactionsRequest.getValue() <= findAccountInDb.get().getBalance()) {
			findAccountInDb.get().setBalance(findAccountInDb.get().getBalance() - transactionsRequest.getValue());
			specialAccountRepository.save(findAccountInDb.get());

			//verifica se o cliente possui saldo, caso não ele debita o valor restante do saque do limite especial
		} else if (transactionsRequest.getValue() > findAccountInDb.get().getBalance() && transactionsRequest.getValue() <= findAccountInDb.get().getLimitAmount()) {
			Double defineLimit = (findAccountInDb.get().getBalance() - transactionsRequest.getValue()) + findAccountInDb.get().getLimitAmount();
			if(findAccountInDb.get().getLimitAmount() <= findAccountInDb.get().getTotalLimitAmount() && findAccountInDb.get().getLimitAmount() != 0){
				Double debt = transactionsRequest.getValue() + findAccountInDb.get().getInDebt();
				findAccountInDb.get().setInDebt(debt);
			} else {
				logger.info("Limite excedido do total disponível");
				throw new Exception("Limite excedido");
			}
			findAccountInDb.get().setLimitAmount(defineLimit);
			findAccountInDb.get().setBalance(0.00);
			specialAccountRepository.save(findAccountInDb.get());
		}
	}
	public String payDebt(TransactionsRequest transactionsRequest) throws Exception {
		Optional<SpecialAccount> verifyAccountInDb = specialAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());
		if(verifyAccountInDb.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		if(verifyAccountInDb.get().getInDebt() > 0){
			Double payDebt = verifyAccountInDb.get().getInDebt() - transactionsRequest.getValue();
			verifyAccountInDb.get().setInDebt(payDebt < 0 ? 0 : payDebt);
			if (payDebt<0){
				Double cash = (verifyAccountInDb.get().getBalance() + (payDebt * (-1.0)));
				verifyAccountInDb.get().setBalance(cash);
			}
			Double paidAmount =  verifyAccountInDb.get().getLimitAmount() + transactionsRequest.getValue();
			Double totalLimit =  verifyAccountInDb.get().getTotalLimitAmount();
			verifyAccountInDb.get().setLimitAmount(paidAmount >= totalLimit ? totalLimit : paidAmount);
		}
		specialAccountRepository.save(verifyAccountInDb.get());
		return receiptService.createReceipt(transactionsRequest);
	}

}
