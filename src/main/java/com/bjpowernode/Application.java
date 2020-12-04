package com.bjpowernode;

import com.bjpowernode.bean.User;
import com.bjpowernode.service.SqlService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext=SpringApplication.run(Application.class, args);
		SqlService sqlService= (SqlService) applicationContext.getBean("sqlServiceImpl");
		System.out.println("-------------新增前--------------");
		System.out.println(sqlService.selectAll());
		User user=new User();
		user.setName("springboog动态资源");
		System.out.println("新增结果:"+sqlService.add(user));
		System.out.println("-------------新增后--------------");
		System.out.println(sqlService.selectAll());
	}

}
