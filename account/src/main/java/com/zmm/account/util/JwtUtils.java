package com.zmm.account.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zmm.account.common.enums.ResponseStatusEnum;
import com.zmm.account.common.exception.MyException;
import com.zmm.account.entity.Account;
import com.zmm.account.mapper.AccountMapper;

/**
 * 
 * @ClassName: JwtUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhumingming
 * @date 2020年3月25日
 *
 */
@Component
@Transactional
public class JwtUtils {
	// 密钥
	public static final String SECRET = "sdjhakdhajdklsl;o653632";
	// 过期时间
	public static final long EXPIRE = 30l;

	public static final String PATHS = "paths";

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Autowired
	private AccountMapper accountMapper;

	/**
	 * 生成Token
	 * 
	 * @param userId
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public String createToken(Account account) {
		LocalDateTime begin = LocalDateTime.now();
		LocalDateTime end = begin.plusMinutes(EXPIRE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		List<String> urlsList = accountMapper.findUrlByAccountId(account.getId(), contextPath);
		String urls = JSONObject.toJSONString(urlsList);
		String subject = JSONObject.toJSONString(account);
		String token = JWT.create().withHeader(map)// 头
				.withIssuer("")// 签发者
				.withSubject(subject)// 面向的用户
				.withAudience("")// 接收方
				.withIssuedAt(DateUtils.asDate(begin))// 签名时间
				.withExpiresAt(DateUtils.asDate(end))// 过期时间
				.withClaim(PATHS, urls)// 权限列表
				.sign(Algorithm.HMAC256(SECRET));// 签名
		return token;
	}

	/**
	 * 验证Token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Claim> verifyToken(String token) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
		} catch (Exception e) {
			throw new MyException(ResponseStatusEnum.INVALID_ACCESS);
		}
		return jwt.getClaims();
	}

	/**
	 * 解析Token
	 * 
	 * @param token
	 * @return
	 */
	public static Map<String, Claim> parseToken(String token) {
		DecodedJWT decodedJWT = JWT.decode(token);
		return decodedJWT.getClaims();
	}

}
