package com.webshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CaffFile {
	
	@GeneratedValue
	@Id
	private long id;
	
	public CaffFile() {
	}
}
