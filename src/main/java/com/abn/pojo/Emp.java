package com.abn.pojo;

import javax.persistence.*;

@Entity
@Table(name = "emp")
public class Emp {
	
	@Column(name = "name")
	String name;
	
	@Id @GeneratedValue
	@Column(name = "id")
	int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
