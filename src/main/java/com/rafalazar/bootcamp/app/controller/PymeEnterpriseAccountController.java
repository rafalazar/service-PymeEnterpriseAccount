package com.rafalazar.bootcamp.app.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.bootcamp.app.document.PymeEnterpriseAccount;
import com.rafalazar.bootcamp.app.dto.PymeDto;
import com.rafalazar.bootcamp.app.service.PymeEnterpriseAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pymeEnterpriseAccount")
public class PymeEnterpriseAccountController {
	
	@Autowired
	private PymeEnterpriseAccountService service;
	
	@GetMapping("/findAll")
	public Flux<PymeEnterpriseAccount> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/findById/{id}")
	Mono<PymeEnterpriseAccount> findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@PostMapping("/save")
	public Mono<ResponseEntity<PymeEnterpriseAccount>> save(@RequestBody PymeEnterpriseAccount pea) {

		return service.save(pea).map(p->ResponseEntity.created(URI.create("/pymeEnterpriseAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(p));

	}
	
	@PutMapping("/update/{id}")
	Mono<ResponseEntity<PymeEnterpriseAccount>> update(@RequestBody PymeDto pyme, @PathVariable String ruc){
		return service.update(pyme, ruc).map(p -> ResponseEntity
				.created(URI.create("/pymeEnterpriseAccount".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
		return service.findById(id).flatMap(p -> {
			return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/saveP")
	public Mono<ResponseEntity<PymeEnterpriseAccount>> savePymeDto(@RequestBody PymeDto dto) {

		return service.savePymeDto(dto).map(p->ResponseEntity.created(URI.create("/pymeEnterpriseAccount"))
				.contentType(MediaType.APPLICATION_JSON).body(p));
	}
	
	
}
