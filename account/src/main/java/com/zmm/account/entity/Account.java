package com.zmm.account.entity;


import java.util.List;

import lombok.Data;

@Data
public class Account{
	private Long id;
	private String userName;
	private String passWord;
	private List<Role> roles;
	
	

}
