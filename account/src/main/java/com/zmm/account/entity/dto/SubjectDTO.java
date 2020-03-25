package com.zmm.account.entity.dto;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;
@Data
public class SubjectDTO {
	private Long accountId;
	private String accountName;
	private List<MenuDTO> Menus=Lists.newArrayList();

}
