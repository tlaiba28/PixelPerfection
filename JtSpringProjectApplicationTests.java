package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.controller.AdminController;
import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JtSpringProjectApplicationTests {
	@Test
	public void testLogout() {
		AdminController adminController = new AdminController();
		String loginPage = adminController.returnIndex();

		assertEquals("adminlogin", loginPage);
	}



}
