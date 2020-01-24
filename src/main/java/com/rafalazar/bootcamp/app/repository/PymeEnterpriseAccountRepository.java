package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.PymeEnterpriseAccount;

import reactor.core.publisher.Mono;

public interface PymeEnterpriseAccountRepository extends ReactiveMongoRepository<PymeEnterpriseAccount,String>{
	public Mono<PymeEnterpriseAccount> findByRuc(String ruc);
}
