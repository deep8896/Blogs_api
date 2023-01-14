package com.blog_app_apis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog_app_apis.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {

	  @Autowired
		private UserRepo userRepo;
	@Test
	void contextLoads() {
	}
	

	/*public void repoTest() {
		String className = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}
*/
}
