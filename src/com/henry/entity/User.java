package com.henry.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Hibernate使用注解
 * Hibernate Validator
 */
@Entity
@Table(name="user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

	private Integer id;
	private String name;
	private String password;
	
	@Id
	@GeneratedValue //相当于xml中配置native,交给数据库管理,在MySQL就是自增
	//@GeneratedValue(strategy=GenerationType.IDENTITY) //指定MySQL,SQLserver的自增
	//@GeneratedValue(strategy=GenerationType.SEQUENCE) //Oracle的自增
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name")
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="password")
	@NotBlank
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
