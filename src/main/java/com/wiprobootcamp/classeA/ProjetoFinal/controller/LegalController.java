package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiprobootcamp.classeA.ProjetoFinal.model.LegalEntity;
import com.wiprobootcamp.classeA.ProjetoFinal.service.LegalService;

@RestController
@RequestMapping("/legal")
@CrossOrigin("*")
public class LegalController {

	@Autowired
	private LegalService service;

	@GetMapping("/{idCustomer}")
	public ResponseEntity<LegalEntity> getLegalById(@PathVariable Integer idCustomer) {
		LegalEntity obj = this.service.findLegalCustomerById(idCustomer);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/findall")
	public Iterable<LegalEntity> getAllLegalEntity() {
		return this.service.findAllLegalCustomer();
	}

	@PostMapping("/create")
	public ResponseEntity<String> createLegalEntityCustomer(@RequestBody LegalEntity legal) {

		try {
			service.createLegalEntityCustomer(legal);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Empresa " + legal.getCompanyName() + " criado com sucesso!");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateLegalEntity(@RequestBody LegalEntity legal) {
		try {
			service.updateLegalCustomer(legal);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(legal.getCompanyName() + " Updated!");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

    @DeleteMapping("/delete/{idCustomer}")
    public ResponseEntity<Void> deleteLegalEntity(@PathVariable Integer idCustomer) {
        this.service.deleteLegalCustomer(idCustomer);
        return ResponseEntity.noContent().build();
    }
}
