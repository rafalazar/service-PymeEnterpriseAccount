package com.rafalazar.bootcamp.app.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection="pyme_enterprise_account")
public class PymeEnterpriseAccount {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String ruc;
	private String razonSocial;
	private String address;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	//Experimental!
	private List<Account> listAccount;
	
	public PymeEnterpriseAccount() {
		
	}
}
