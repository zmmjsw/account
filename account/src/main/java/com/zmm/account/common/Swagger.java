package com.zmm.account.common;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 
* @ClassName: Swagger 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhumingming 
* @date 2017年12月7日 下午6:25:02 
*
 */
@Configuration
@EnableSwagger2
public class Swagger {
	  @Value("${swagger.description}")
	   private String description;
	  
	  @Value("${swagger.basePackage}")
	   private String basePackage;
	
	@Bean
	public Docket createRestApi() {
		//添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("认证token").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors
						.basePackage(basePackage))
				.paths(PathSelectors.any())
				.build()
                .globalOperationParameters(pars);
	}

	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Spring Boot2中使用Swagger2构建RESTful APIs")
	                .description(description)
	                .termsOfServiceUrl("www.baidu.com")
	                .version("2.8.0")
	                .build();
	    }

	 
	 
	 
}
