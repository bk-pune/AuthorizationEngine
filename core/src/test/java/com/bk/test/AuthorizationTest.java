package com.bk.test;

import com.bk.cache.PrincipalCache;
import com.bk.engine.AuthorizationEngine;
import com.bk.engine.RESTAuthorizationEngine;
import com.bk.exception.AuthorizationException;
import com.bk.identity.Principal;
import com.bk.model.TestUtils;
import com.bk.policy.AuthorizationPolicy;
import com.bk.registry.AuthorizationModel;
import com.bk.resource.ResourceOperationMetadata;
import com.bk.rest.UserEntityController;
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
@TestPropertySource(locations = "classpath:test-application.properties")
public class AuthorizationTest {

    @Autowired
    private MockMvc mockMvc;
    public static final String PACKAGE_COM_BK = "com.bk";
    private PrincipalCache principalCache;
    private AuthorizationModel authorizationModel;
    AuthorizationEngine authorizationEngine;

    @Before
    public void setup() {
        DispatcherServlet dispatcherServlet = mockMvc.getDispatcherServlet();
        List<HandlerMapping> handlerMappings = dispatcherServlet.getHandlerMappings();
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMappings.stream().filter(handlerMapping -> handlerMapping instanceof RequestMappingHandlerMapping).collect(Collectors.toList()).get(0);
        authorizationModel = new AuthorizationModel(PACKAGE_COM_BK, requestMappingHandlerMapping);
        principalCache = new PrincipalCache();
        authorizationEngine = new RESTAuthorizationEngine(authorizationModel);
    }

    @Test
    public void testPolicyDef() {
        Assert.assertNotNull(authorizationModel);
        Assert.assertNotNull(authorizationModel.getResourceMetadataFromOperationName("getUser"));
    }

    @Test
    public void testResourceOperation() {
        ResourceOperationMetadata resourceOperationMetadata = new ResourceOperationMetadata();
        resourceOperationMetadata.setName("getUser");
        Assert.assertTrue(authorizationModel.getResourceMetadata("user").getResourceOperationMetadata().contains(resourceOperationMetadata));

        resourceOperationMetadata.setName("updateUser");
        Assert.assertTrue(authorizationModel.getResourceMetadata("user").getResourceOperationMetadata().contains(resourceOperationMetadata));
    }

    @Test
    public void testAdminPrincipal() throws AuthorizationException {
        Principal adminPrincipal = principalCache.getPrincipal(PrincipalCache.ADMIN_USERNAME);

        boolean authorized = authorizationEngine.isAuthorized(adminPrincipal, "/getUser");
        Assert.assertTrue(authorized);

        authorized = authorizationEngine.isAuthorized(adminPrincipal, "/updateUser");
        Assert.assertTrue(authorized);
    }

    @Test
    public void testGuestPrincipal() throws AuthorizationException {
        Principal guestPrincipal = principalCache.getPrincipal(PrincipalCache.GUEST_USERNAME);
        boolean authorized = false;

        try {
            authorized = authorizationEngine.isAuthorized(guestPrincipal, "/getUser");
        } catch (AuthorizationException e) {
            Assert.assertTrue(true);
        }

        try {
            authorized = authorizationEngine.isAuthorized(guestPrincipal, "/updateUser");
        } catch (AuthorizationException e) {
            Assert.assertTrue(true);
        }

        authorized = authorizationEngine.isAuthorized(guestPrincipal, "/hello");
        Assert.assertTrue(authorized);
    }

    @Test
    public void testElevatedPrincipal() throws AuthorizationException {
        Principal adminPrincipal = principalCache.getPrincipal(PrincipalCache.GUEST_ELEVATED_USERNAME);

        boolean authorized = authorizationEngine.isAuthorized(adminPrincipal, "/getUser");
        Assert.assertTrue(authorized);

        authorized = authorizationEngine.isAuthorized(adminPrincipal, "/updateUser");
        Assert.assertTrue(authorized);
    }

    @Test
    public void testDisabledPolicy() throws AuthorizationException {
        AuthorizationEngine authorizationEngine = new RESTAuthorizationEngine(authorizationModel);
        AuthorizationPolicy samplePolicy = TestUtils.getPolicy(TestUtils.ADMIN_POLICY);
        samplePolicy.setEnabled(Boolean.FALSE);

        Principal adminPrincipal = principalCache.getPrincipal(PrincipalCache.ADMIN_USERNAME);
        try{
            authorizationEngine.isAuthorized(adminPrincipal, "/getUser");
        } catch (AuthorizationException e) {
            Assert.assertTrue(true);
        } finally {
            samplePolicy.setEnabled(Boolean.TRUE); // must
        }
    }

    @Test
    public void testInvalidURI() throws AuthorizationException {
        AuthorizationEngine authorizationEngine = new RESTAuthorizationEngine(authorizationModel);
        Principal adminPrincipal = principalCache.getPrincipal(PrincipalCache.GUEST_USERNAME);
        boolean authorized = authorizationEngine.isAuthorized(adminPrincipal, "/nonExistingRestUrl");
        Assert.assertTrue(authorized); // for uris not having ResourceOperation, the access is allowed
    }
}
