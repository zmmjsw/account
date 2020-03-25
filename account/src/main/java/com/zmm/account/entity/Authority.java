package com.zmm.account.entity;



import java.util.List;

import lombok.Data;

@Data
public class Authority  {
	public final static String MENU="menu";
	public final static String BUTTON="button";
	private Long id;
	private String name;
	private Long faterId;
	private String seq;//排序 
	private String buttonValue;//按钮标识
	private String url;//资源路径
	private String relation;//资源类型 该权限是菜单权限  还是按钮权限  
	private List<Role> roles;
	private List<Authority> buttons;

}
