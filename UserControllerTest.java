package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.controller.UserController;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private userService userService;

    @InjectMocks
    private UserController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserLogin() {
        Model model = mock(Model.class);

        String result = controller.userlogin(model);

        assertEquals("userLogin", result);
    }

    @Test
    public void testUserLoginValidateSuccess() {
        String username = "testUser";
        String password = "testPassword";
        Model model = mock(Model.class);
        ModelAndView expectedView = new ModelAndView("index");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userService.checkLogin(username, password)).thenReturn(user);

        ModelAndView result = controller.userlogin(username, password, model, null);

        assertEquals(expectedView.getViewName(), result.getViewName());
        assertEquals(user, result.getModel().get("user"));
    }

    @Test
    public void testUserLoginValidateFailure() {
        String username = "invalidUser";
        String password = "invalidPassword";
        Model model = mock(Model.class);
        ModelAndView expectedView = new ModelAndView("userLogin");

        when(userService.checkLogin(username, password)).thenReturn(new User());

        ModelAndView result = controller.userlogin(username, password, model, null);

        assertEquals(expectedView.getViewName(), result.getViewName());
        assertEquals("Please enter correct email and password", result.getModel().get("msg"));
    }

    // Additional tests for other methods in UserController can be added here
}
