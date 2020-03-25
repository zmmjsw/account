package com.zmm.account.entity.dto;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class MenuDTO {
	private String name;
	private String url;
	private String seq;
	private List<ButtonDTO> buttons=Lists.newArrayList();

}
