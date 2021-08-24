package com.spring.mvc.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("diex1")
public class DiEx1 implements DiContext {
	
	@Value("최종원")
	private String name;
	@Value("010-0000-0000")
	private String phone;
	
	public DiEx1() {
	}
	
	public DiEx1(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String diConnection() {
		return "성명: " + name + " 연락처: " + phone;
	}
}
