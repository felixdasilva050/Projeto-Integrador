package com.wiprobootcamp.classeA.ProjetoFinal.service;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.LegalEntity;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.LegalRepository;

@Service
public class LegalService {

	Logger logger = Logger.getLogger(LegalService.class.getName());

	@Autowired
	private LegalRepository legalRepository;

	public LegalEntity createLegalCustomer(LegalEntity obj) {
		return legalRepository.save(obj);
	}

	public void createLegalEntityCustomer(LegalEntity legalEntity) throws Exception {
		// Verifica se já existe um CNPJ no banco de dados.
		Optional<LegalEntity> findCNPJ = legalRepository.findByCnpj(legalEntity.getCnpj());

		// Retornando umA Empresa já cadastrada, jogamos uma exception.
		if (findCNPJ.isPresent()) {
			logger.info("Empresa já existe no banco de dados.");
			throw new Exception("Empresa já cadastrada");
		}
		LegalEntity newLegalEntity = new LegalEntity();
		newLegalEntity.setCompanyName(legalEntity.getCompanyName());
		newLegalEntity.setAddress(legalEntity.getAddress());
		newLegalEntity.setCnpj(legalEntity.getCnpj());
		newLegalEntity.setCostumerType(CustomerType.LEGAL_ENTITY);

		legalRepository.save(newLegalEntity);
	}

	public LegalEntity findLegalCustomerById(Integer idCustomer) {
		Optional<LegalEntity> findIndCustRepo = legalRepository.findById(idCustomer);
		return findIndCustRepo.orElse(null);
	}

	public LegalEntity updateLegalCustomer(LegalEntity legalEntenty) throws Exception {
		Optional<LegalEntity> findCustomer = legalRepository.findByCnpj(legalEntenty.getCnpj());
		if (findCustomer.isEmpty()) {
			logger.info("Empresa não encontrada no banco de dados");
			throw new Exception("Empresa não existe!");
		}

		LegalEntity upLegal = findLegalCustomerById(findCustomer.get().getIdCustomer());
		upLegal.setCompanyName(legalEntenty.getCompanyName());
		upLegal.setCnpj(legalEntenty.getCnpj());
		upLegal.setAddress(legalEntenty.getAddress());
		upLegal.setCostumerType(CustomerType.LEGAL_ENTITY);
		return legalRepository.save(upLegal);
	}

	public Iterable<LegalEntity> findAllLegalCustomer() {
		return legalRepository.findAll();
	}

	public void deleteLegalCustomer(Integer idCustomer) {
		findLegalCustomerById(idCustomer);
		legalRepository.deleteById(idCustomer);
	}

}
