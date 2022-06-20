package com.bk.test;

import com.bk.rest.UserEntityControllerIncorrectDef;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 12/04/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserEntityControllerIncorrectDef.class)
@AutoConfigureMockMvc
@ActiveProfiles({"NEGATIVE_TEST"})
@TestPropertySource(locations = "classpath:test-application.properties")
public class AuthorizationNegativeTest {

    @Autowired
    private MockMvc mockMvc;

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Before
    public void setup() {
        DispatcherServlet dispatcherServlet = mockMvc.getDispatcherServlet();
        List<HandlerMapping> handlerMappings = dispatcherServlet.getHandlerMappings();
        requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMappings.stream().filter(handlerMapping -> handlerMapping instanceof RequestMappingHandlerMapping).collect(Collectors.toList()).get(0);
    }

    @Test
    public void testSetup() {
        Assert.assertNotNull(requestMappingHandlerMapping);
    }
   /* @Test(expected = ResourceDefinitionException.class)
    public void testInvalidResourceDef() {
        AuthorizationModel authorizationModel = new AuthorizationModel(requestMappingHandlerMapping);
    }*/
}
