package com.zmm.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zmm.account.common.plugin.BaseMapper;
import com.zmm.account.entity.Account;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
	@Select("SELECT CONCAT(#{path},a.url) url	 FROM authority a "
			+ "LEFT JOIN role_authority ra ON ra.authority_id = a.id "
			+ "LEFT JOIN account_role ar ON ar.role_id = ra.role_id "
			+ "WHERE	ar.account_id =#{accountId}")
	List<String> findUrlByAccountId(@Param("accountId") Long accountId,@Param("path") String path);

}
