package com.wiprobootcamp.classeA.ProjetoFinal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.TransactionsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.SpecialAccountRepository;

@Service
public class SpecialAccountService {

	Logger logger = Logger.getLogger(SpecialAccountService.class.getName());

	@Autowired
	private SpecialAccountRepository specialAccountRepository;
	
	//método que busca uma conta corrente pelo seu ID
	public SpecialAccount findById(Integer id) {
		Optional<SpecialAccount> object = specialAccountRepository.findById(id);
		return object.orElse(null);
	}
	
	//Método que lista todas as contas correntes
	public List<SpecialAccount> findAll(){
		return specialAccountRepository.findAll();
	}
	
	//Método que atualiza uma conta corrente dado um ID e uma conta corrente
	public SpecialAccount update(SpecialAccount specialAccount) throws Exception {

		Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.findByAccountNumber(specialAccount.getAccountNumber());
		if(findSpecialAccountInDb.isEmpty()){
			logger.info("Conta não localizada no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		SpecialAccount newSpecialAccount = findById(specialAccount.getIdAccount());
		newSpecialAccount.setAccountNumber(specialAccount.getAccountNumber());
		newSpecialAccount.setAccountType(specialAccount.getAccountType());
		newSpecialAccount.setBalance(specialAccount.getBalance());
		newSpecialAccount.setCreditCard(specialAccount.getCreditCard());
		newSpecialAccount.setLimitAmount(specialAccount.getLimitAmount());
		
		return specialAccountRepository.save(newSpecialAccount);
	}
	
	//Método que cria uma conta corrente
	public SpecialAccount create(SpecialAccount specialAccount) throws Exception {
		Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.findByAccountNumber(specialAccount.getAccountNumber());

			if(findSpecialAccountInDb.isPresent()) {
				logger.info("Conta já cadastrada no Banco de dados!");
				throw new Exception("Conta especial já cadastrada!");
			}

			SpecialAccount newSpecialAccount = new SpecialAccount();
			newSpecialAccount.setAccountNumber(specialAccount.getAccountNumber());
			newSpecialAccount.setCreditCard(specialAccount.getCreditCard());
			newSpecialAccount.setAccountType(AccountType.ESPECIAL_ACCOUNT);
			newSpecialAccount.setLimitAmount(specialAccount.getLimitAmount());
			newSpecialAccount.setBalance(specialAccount.getBalance());
			return specialAccountRepository.save(newSpecialAccount);
	}
	
	//Método que deleta uma conta corrente dado um ID
	public void delete(Integer id) {
		findById(id);
		specialAccountRepository.deleteById(id);
	}

	public void specialWithdraw(TransactionsRequest transactionsRequest) throws Exception {

		verifyWithdraw(transactionsRequest);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
		String todayFormated = sdf.format(new Date());

		transactionsRequest.setTransactionDate(todayFormated);

	}

	public void depositMoney(TransactionsRequest transactionsRequest) throws Exception {
		Optional<SpecialAccount> findAccountInDb = specialAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());
		if(findAccountInDb.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		Double defineBalance = findAccountInDb.get().getBalance() + transactionsRequest.getDebitValue();
		findAccountInDb.get().setBalance(defineBalance);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
		String todayFormated = sdf.format(new Date());

		transactionsRequest.setTransactionDate(todayFormated);

		specialAccountRepository.save(findAccountInDb.get());
	}

	private void verifyWithdraw(TransactionsRequest transactionsRequest) throws Exception {
		Optional<SpecialAccount> findAccountInDb = specialAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());

		//verifica se a conta existe no DB, caso não retorna exception
		if(findAccountInDb.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new Exception("Conta não localizada!");

			//verifica se possui saldo em conta para sacar, caso possuia apenas realiza o saque
		} else if (transactionsRequest.getDebitValue() <= findAccountInDb.get().getBalance()) {
			findAccountInDb.get().setBalance(findAccountInDb.get().getBalance() - transactionsRequest.getDebitValue());
			specialAccountRepository.save(findAccountInDb.get());

			//verifica se o cliente possui saldo, caso não ele debita o valor restante do saque do limite especial
		} else if (transactionsRequest.getDebitValue() > findAccountInDb.get().getBalance() && transactionsRequest.getDebitValue() <= findAccountInDb.get().getLimitAmount()) {
			Double defineLimit = (findAccountInDb.get().getBalance() - transactionsRequest.getDebitValue()) + findAccountInDb.get().getLimitAmount();
			findAccountInDb.get().setLimitAmount(defineLimit);
			findAccountInDb.get().setBalance(0.00);
			specialAccountRepository.save(findAccountInDb.get());
		}
	}

}
