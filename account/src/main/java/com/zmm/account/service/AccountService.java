package com.zmm.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zmm.account.common.SubjectUtil;
import com.zmm.account.common.enums.ResponseStatusEnum;
import com.zmm.account.common.exception.MyException;
import com.zmm.account.entity.Account;
import com.zmm.account.mapper.AccountMapper;
import com.zmm.account.util.JwtUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @ClassName: AccountService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhumingming
 * @date 2020年3月25日
 *
 */
@Service
@Transactional
public class AccountService {
	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private JwtUtils jwtUtils;

	public String login(Account account) {
		Example andEqualTo = new Example(Account.class);
		andEqualTo.and().andEqualTo("userName", account.getUserName());
		Account user = accountMapper.selectOneByExample(andEqualTo);
		if (user == null) {
			throw new MyException(ResponseStatusEnum.LOGIN_ERROR);
		}
		String token = jwtUtils.createToken(user);
		SubjectUtil.addSessionValue("user", JSON.toJSONString(user));
		return token;

	}

	public Account getUser() {
		Account user = SubjectUtil.getUser();
		return user;

	}

}
