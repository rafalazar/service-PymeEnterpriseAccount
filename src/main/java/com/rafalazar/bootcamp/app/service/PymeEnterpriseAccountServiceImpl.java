package com.rafalazar.bootcamp.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.document.Account;
import com.rafalazar.bootcamp.app.document.PymeEnterpriseAccount;
import com.rafalazar.bootcamp.app.dto.PymeDto;
import com.rafalazar.bootcamp.app.repository.PymeEnterpriseAccountRepository;
import com.rafalazar.bootcamp.app.util.Convert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PymeEnterpriseAccountServiceImpl implements PymeEnterpriseAccountService{
	
	@Autowired
	private PymeEnterpriseAccountRepository repo;
	
	@Autowired
	private Convert convert;
	
	@Override
	public Flux<PymeEnterpriseAccount> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<PymeEnterpriseAccount> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<PymeEnterpriseAccount> save(PymeEnterpriseAccount pea) {
		pea.setCreateAt(new Date());
		pea.setListAccount(new ArrayList<Account>());
		
		return repo.save(pea);
	}

	//Método EXPERIMENTAL para este microservicio!
	@Override
	public Mono<PymeEnterpriseAccount> update(PymeDto dto, String ruc) {
		return repo.findByRuc(ruc).flatMap(p -> {
			List<Account> alist = p.getListAccount();
			Account a = new Account();
			a.setIdAccount(dto.getIdAccount());
			a.setNumberAccount(dto.getNumberAccount());
			a.setNameAccount(dto.getNameAccount());
			
			alist.add(a);
			
			p.setRuc(dto.getRuc());
			p.setRazonSocial(dto.getRazonSocial());
			p.setAddress(dto.getAddress());
			p.setCreateAt(new Date());
			p.setListAccount(alist);
			
			return repo.save(p);
		});
	}

	@Override
	public Mono<Void> delete(PymeEnterpriseAccount pea) {
		return repo.delete(pea);
	}

	//Exposición de servicios.
	
	@Override
	public Mono<PymeEnterpriseAccount> findByRuc(String ruc) {
		return repo.findByRuc(ruc);
	}

	@Override
	public Mono<PymeEnterpriseAccount> savePymeDto(PymeDto dto) {
		return save(convert.convertToPyme(dto));
	}

}
