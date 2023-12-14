package com.jtspringproject.JtSpringProject;
import com.jtspringproject.JtSpringProject.controller.AdminController;
import com.jtspringproject.JtSpringProject.controller.UserController;
import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class JtSpringProjectApplicationTests {
	@Test
	public void testAdminLogout() {
		AdminController adminController = new AdminController();
		String loginPage = adminController.returnIndex();

		assertEquals("adminlogin", loginPage);
	}
	@Test
	public void testAdminHome() {
		AdminController adminController = new AdminController();
		String result = adminController.adminHome(new ConcurrentModel());

		assertEquals("redirect:/admin/login", result);
	}

	@Test
	public void TestUserLogout() {
		UserController userController = new UserController();
		String logoutPage = userController.userlogout(new ConcurrentModel());
		assertEquals("redirect:/userLogin", logoutPage);
	}
	@Test
	public void testProfileDisplay() {
		UserController userController = new UserController();

		String profileDisplay = userController.updateProfile(new ConcurrentModel());

		assertEquals("displayProfile", profileDisplay);
	}
	@Test
	public void testProfileupdate(){

		UserController userController = new UserController();

		String profileDisplay = userController.updateUser("123","hehe","no@","632");

		assertEquals("displayProfile", profileDisplay);
	}


	@Test
	public void testInvalidUsernameAndPassword_user() {
		UserController userController = new UserController();

		// Assuming you have a HttpServletResponse instance
		HttpServletResponse response = new MockHttpServletResponse();

		ModelAndView modelAndView = userController.userlogin("123", "hehe", new ConcurrentModel(), response);
		// Assert that the view name is "userLogin"
		assertEquals("userLogin", modelAndView.getViewName());
	}
	@Test
	public void testInvalidUsernameAndPass_admin() {
		AdminController Controller = new AdminController();
		// Assuming you have a HttpServletResponse instance
		ModelAndView modelAndView = Controller.adminlogin("admin", "123");
		// Assert that the view name is "userLogin"
		assertEquals("adminHome", modelAndView.getViewName());
	}
	@Test
	public void testCart() {
		UserController userController = new UserController();
		ModelAndView modelAndView = userController.getCartDetail();
		// Assert that the view name is "userLogin"
		assertEquals("Cart", modelAndView.getViewName());
	}

	@Test
	public void testdelcategory() {
		AdminController adminController = new AdminController();
		String result = adminController.removeProduct(2);

		assertEquals( "redirect:/admin/products", result);
	}

	@Test
	public void testUpdateproduct() {
		AdminController Controller = new AdminController();
		// Assuming you have a HttpServletResponse instance
		ModelAndView mv= Controller.updateproduct(2);
		// Assert that the view name is "userLogin"
		assertEquals("productsUpdate", mv.getViewName());
	}
	@Test
	public void testaddproduct() {
		AdminController Controller = new AdminController();
		// Assuming you have a HttpServletResponse instance
		ModelAndView mv= Controller.addProduct();
		// Assert that the view name is "userLogin"
		assertEquals("categories", mv.getViewName());
	}


}
