package com.book.main;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class LoginUITest {
    @Autowired
    UserRepository urep;
    
    
    DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();           
    UserDetailsService userDet= Mockito.mock(UserService.class);    
    VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);
    
    private LoginUI ui;

    @Mock
    private Page page;
    
    @Before
    public void setup() throws Exception {
        initUi();
        mockPage();
    }
    
    private void initUi() {
        ui = new LoginUI(urep);
        ui.init(vaadinRequest);
    }
    
    private void mockPage() throws NoSuchFieldException, IllegalAccessException {
        Field pageField = UI.class.getDeclaredField("page");
        pageField.setAccessible(true);
        pageField.set(ui, page);
    }
    
    @Test
    public void addUser() {
        Users cus=new Users();
        cus.setUsername("username2");
        cus.setPassword("password2");
        cus.setAccountNonLocked(true);
        cus.setAccountNonExpired(true);
        cus.setCredentialsNonExpired(true);
        cus.setEnabled(true);
        cus.setEmail("test@test.com");
        urep.save(cus);
        Users nam=urep.findByUsername("username2");
        System.out.println("---> "+nam.getPassword());
        assertTrue(nam.getEmail().equals("test@test.com"));
    }
    
    @Test
    public void loginTest() {
        Users cus=new Users();
        cus.setUsername("username3");
        cus.setPassword("password3");
        cus.setAccountNonLocked(true);
        cus.setAccountNonExpired(true);
        cus.setCredentialsNonExpired(true);
        cus.setEnabled(true);
        cus.setEmail("test@test.com");
        urep.save(cus);
        ui.getUserName().setValue("username3");
        ui.getPasswordField().setValue("password3");
//        ui.getLogin().click();
        UserService usr= new UserService(urep);
        daoAuthenticationProvider.setUserDetailsService(usr);        
        Authentication auth = new UsernamePasswordAuthenticationToken("username3","password3",new ArrayList<>());
        System.out.println("auth <--> "+auth.toString());
        System.out.println("auth <--> "+auth.getCredentials()); 
        Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        assertTrue(authenticated.isAuthenticated());

    }
    
    @Test
    public void loginTest_no_user() {
        try {
//            Users cus=new Users();
//            cus.setUsername("username2");
//            cus.setPassword("password2");
//            cus.setAccountNonLocked(true);
//            cus.setAccountNonExpired(true);
//            cus.setCredentialsNonExpired(true);
//            cus.setEnabled(true);
//            cus.setEmail("test@test.com");
//            urep.save(cus);
            ui.getUserName().setValue("username4");
            ui.getPasswordField().setValue("password4");
            UserService usr= new UserService(urep);
            daoAuthenticationProvider.setUserDetailsService(usr);
            Authentication auth = new UsernamePasswordAuthenticationToken("username4","password4");
            System.out.println("auth <--> "+auth.toString());           
            daoAuthenticationProvider.authenticate(auth);
            assertTrue(false);
//            SecurityContextHolder.getContext().setAuthentication(authenticated);
//            assertTrue(authenticated==null || !(authenticated.isAuthenticated()));
        }
        catch(InternalAuthenticationServiceException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void loginTest_login_failure() {
        try {
            Users cus=new Users();
            cus.setUsername("username5");
            cus.setPassword("password5");
            cus.setAccountNonLocked(true);
            cus.setAccountNonExpired(true);
            cus.setCredentialsNonExpired(true);
            cus.setEnabled(true);
            cus.setEmail("test@test.com");
            urep.save(cus);
            ui.getUserName().setValue("username5");
            ui.getPasswordField().setValue("password0");
            UserService usr= new UserService(urep);
            daoAuthenticationProvider.setUserDetailsService(usr);
            Authentication auth = new UsernamePasswordAuthenticationToken("username5","password0");
            daoAuthenticationProvider.authenticate(auth);
            assertTrue(false);
            
//            SecurityContextHolder.getContext().setAuthentication(authenticated);
//            assertTrue(authenticated==null || !(authenticated.isAuthenticated()));
        }
        catch(BadCredentialsException e) {
            assertTrue(true);
        }
    }
    
    
    
}
