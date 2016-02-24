package com.henry.entity;

import javax.persistence.EmbeddedId;

public class Student {
/*	private int id;
	private String name;*/
	private StudentPK pk;
	
	//联合主键 有三种方法
	@EmbeddedId
	public StudentPK getPk() {
		return pk;
	}
	public void setPk(StudentPK pk) {
		this.pk = pk;
	}
	private int status;
	private String content;
	
/*	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}*/
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
