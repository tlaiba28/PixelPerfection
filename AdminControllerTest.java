package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.controller.AdminController;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @Mock
    private userService userService;

    @Mock
    private categoryService categoryService;

    @Mock
    private productService productService;

    @InjectMocks
    private AdminController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testIndex() {
        // Mocking the Model
        Model model = mock(Model.class);

        // Stubbing the userService.checkLogin() method
        when(userService.checkLogin(anyString(), anyString())).thenReturn(new User());

        // Executing the controller method
        String result = controller.index(model);

        // Asserting the result
        assertEquals("index", result);

        // Verifying model interactions
        verify(model, times(1)).addAttribute(eq("userid"), anyString());
    }

}