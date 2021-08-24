package com.spring.mvc.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("diConnectionEx")
public class DiConnectionEx {
	
	@Autowired(required = false)
	@Qualifier("diex")
	private DiContext diex;
	
	public DiConnectionEx() {
	}

	public DiConnectionEx(DiContext diex) {
		super();
		this.diex = diex;
	}
	
	public DiContext getDiex() {
		return diex;
	}
	
	public void setDiex(DiContext diex) {
		this.diex = diex;
	}

	@Override
	public String toString() {
		if(diex == null) {
			return "";
		}else {
			return diex.diConnection();
		}
	}
	
}
