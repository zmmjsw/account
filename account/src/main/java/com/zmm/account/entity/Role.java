package com.zmm.account.entity;



import java.util.List;

import lombok.Data;

@Data
public class Role {
	private Long id;
	private String name;
	private List<Account> accounts;
	private List<Authority> menus;
	private List<Authority> buttons;

}
