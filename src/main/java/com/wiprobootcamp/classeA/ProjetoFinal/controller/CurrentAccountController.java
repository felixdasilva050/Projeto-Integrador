package com.wiprobootcamp.classeA.ProjetoFinal.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.service.CurrentAccountService;

@RestController
@RequestMapping("/contaCorrente")
@CrossOrigin("*")
public class CurrentAccountController {
	
	@Autowired
	private CurrentAccountService currentAccountService;
	
	@GetMapping("/{idAccount}")
	public ResponseEntity<CurrentAccount> GetById(@PathVariable Integer idAccount){
		CurrentAccount currentAccount = currentAccountService.findById(idAccount);
		return ResponseEntity.ok().body(currentAccount);
	}
	
	@GetMapping
	public ResponseEntity<List<CurrentAccount>> GetAll(){
		List<CurrentAccount> currentAccounts = currentAccountService.findAll();
		return ResponseEntity.ok().body(currentAccounts);
	}
	
	@PostMapping("/create")
	public ResponseEntity<CurrentAccount> Post(@RequestBody CurrentAccount currentAccount){
		CurrentAccount newCurrentAccount = currentAccountService.create(currentAccount);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idAccount}")
				.buildAndExpand(newCurrentAccount.getIdAccount()).toUri();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCurrentAccount);
	}
	
	@PutMapping("/update/{idAccount}")
	public ResponseEntity<CurrentAccount> Put(@PathVariable Integer idAccount, @RequestBody CurrentAccount currentAccount) {
		CurrentAccount newCurrentAccount = currentAccountService.update(idAccount, currentAccount);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCurrentAccount);
	}
	
	@DeleteMapping("/delete/{idAccount}")
	public ResponseEntity<Void> Delete(@PathVariable Integer idAccount) {
		currentAccountService.delete(idAccount);
		return ResponseEntity.noContent().build();
	}
}
