package com.rafalazar.bootcamp.app.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rafalazar.bootcamp.app.document.Account;
import com.rafalazar.bootcamp.app.document.PymeEnterpriseAccount;
import com.rafalazar.bootcamp.app.dto.PymeDto;

@Component
public class Convert {
	
	public PymeEnterpriseAccount convertToPyme(PymeDto dto) {
		Account a = new Account();
		a.setIdAccount(dto.getIdAccount());
		a.setNameAccount(dto.getNameAccount());
		a.setNumberAccount(dto.getNumberAccount());
		
		List<Account> alist = new ArrayList<>();
		
		alist.add(a);
		
		PymeEnterpriseAccount pyme = new PymeEnterpriseAccount();
		pyme.setRuc(dto.getRuc());
		pyme.setRazonSocial(dto.getRazonSocial());
		pyme.setAddress(dto.getAddress());
		pyme.setCreateAt(new Date());
		pyme.setListAccount(alist);
		
		return pyme;
	}

}
 