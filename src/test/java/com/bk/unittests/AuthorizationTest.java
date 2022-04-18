package com.bk.unittests;

import com.bk.engine.AuthorizationEngine;
import com.bk.engine.AuthorizationEngineImpl;
import com.bk.model.TestUtils;
import com.bk.policy.AuthorizationPolicy;
import com.bk.registry.AuthorizationModel;
import com.bk.resource.ResourceOperationMetadata;
import com.bk.rest.UserEntityController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@SpringBootTest(classes = UserEntityController.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
//@ActiveProfiles({"POSITIVE_TEST"}) // not working currently
@TestPropertySource(locations = "classpath:test-application.properties")
public class AuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    private AuthorizationModel authorizationModel;

    @Before
    public void setup() {
        DispatcherServlet dispatcherServlet = mockMvc.getDispatcherServlet();
        List<HandlerMapping> handlerMappings = dispatcherServlet.getHandlerMappings();
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMappings.stream().filter(handlerMapping -> handlerMapping instanceof RequestMappingHandlerMapping).collect(Collectors.toList()).get(0);
        authorizationModel = new AuthorizationModel(requestMappingHandlerMapping);
    }

    @Test
    public void testPolicyDef() {
        Assert.assertNotNull(authorizationModel);
        Assert.assertNotNull(authorizationModel.getResourceMetadataFromOperationName("getUser"));
    }

    @Test
    public void testResourceOperation() throws NoSuchMethodException {
        ResourceOperationMetadata resourceOperationMetadata = new ResourceOperationMetadata();
        resourceOperationMetadata.setName("getUser");
        Assert.assertTrue(authorizationModel.getResourceMetadata("user").getResourceOperationMetadata().contains(resourceOperationMetadata));

        resourceOperationMetadata.setName("updateUser");
        Assert.assertTrue(authorizationModel.getResourceMetadata("user").getResourceOperationMetadata().contains(resourceOperationMetadata));
    }

    @Test
    public void testAuthorizationEngine() throws JsonProcessingException {
        AuthorizationEngine authorizationEngine = new AuthorizationEngineImpl(authorizationModel);
        AuthorizationPolicy samplePolicy = TestUtils.getSamplePolicy();

        boolean authorized = authorizationEngine.isAuthorized(samplePolicy, "/getUser");
        Assert.assertTrue(authorized);

        authorized = authorizationEngine.isAuthorized(samplePolicy, "/updateUser");
        Assert.assertFalse(authorized);
    }

    @Test
    public void testDisabledPolicy() throws JsonProcessingException {
        AuthorizationEngine authorizationEngine = new AuthorizationEngineImpl(authorizationModel);
        AuthorizationPolicy samplePolicy = TestUtils.getSamplePolicy();
        samplePolicy.setEnabled(Boolean.FALSE);

        boolean authorized = authorizationEngine.isAuthorized(samplePolicy, "/getUser");
        Assert.assertFalse(authorized);
    }

    @Test
    public void testInvalidURI() throws JsonProcessingException {
        AuthorizationEngine authorizationEngine = new AuthorizationEngineImpl(authorizationModel);
        AuthorizationPolicy samplePolicy = TestUtils.getSamplePolicy();
        boolean authorized = authorizationEngine.isAuthorized(samplePolicy, "/nonExistingRestUrl");
        Assert.assertTrue(authorized); // for uris not having ResourceOperation, the access is allowed
    }
}
