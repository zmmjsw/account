package com.zmm.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zmm.account.annotation.Login;
import com.zmm.account.common.CommonResponse;
import com.zmm.account.common.enums.ResponseStatusEnum;
import com.zmm.account.entity.Account;
import com.zmm.account.service.AccountService;
/**
 * 
 * @ClassName: AccountController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhumingming
 * @date 2020年3月25日
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@Login
	@PostMapping("/login")
	public CommonResponse<String> login(@RequestBody Account account) {
		 return CommonResponse.build(accountService.login(account),ResponseStatusEnum.LOGIN_SUCCESS);
	}
	@PostMapping("/test")
	public CommonResponse<Account> test() {
		Account user = accountService.getUser();
		return CommonResponse.build(user);
	}
	@PostMapping("/delete")
	public CommonResponse<Account> delete() {
		Account user = accountService.getUser();
		return CommonResponse.build(user);
	}
}
