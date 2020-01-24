package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.document.PymeEnterpriseAccount;
import com.rafalazar.bootcamp.app.dto.PymeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PymeEnterpriseAccountService {
	
	public Flux<PymeEnterpriseAccount> findAll();
	
	public Mono<PymeEnterpriseAccount> findById(String id);
	
	//Este save es experimental - sólo para este microservicio.
	public Mono<PymeEnterpriseAccount> save(PymeEnterpriseAccount pea);
	
	//Este update es experimental - solo para este microservicio.
	public Mono<PymeEnterpriseAccount> update(PymeDto dto, String ruc);
	
	public Mono<Void> delete(PymeEnterpriseAccount pea);

	//Este findByRuc es experimental - sólo para este microservicio.
	public Mono<PymeEnterpriseAccount> findByRuc(String ruc);
	
	//Este método es experimental - sólo es para este microservicio.
	public Mono<PymeEnterpriseAccount> savePymeDto(PymeDto dto);
}
