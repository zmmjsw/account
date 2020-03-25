package com.zmm.account.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.zmm.account.entity.Account;


public class SubjectUtil {
	protected static HttpServletRequest req;
	protected static HttpServletResponse res;

	public static void  addSession(HttpServletRequest req, HttpServletResponse res) {
		SubjectUtil.req=req;
		SubjectUtil.res=res;
	};

	public static boolean addSessionValue(String key, String value) {
		HttpSession session = req.getSession();
		if (null == session) {
			return false;
		} else {
			session.setAttribute("user", value);
			return true;
		}
	}

	public static Account getUser() {
		String user =getSessionValue("user").toString();
		return JSON.parseObject(user, Account.class);
	}

	public static Object getSessionValue(String key) {
		HttpSession session = req.getSession();
		if (null == session) {
			return null;
		} else {
			Object ret = session.getAttribute(key);
			return ret;
		}
	}

}
