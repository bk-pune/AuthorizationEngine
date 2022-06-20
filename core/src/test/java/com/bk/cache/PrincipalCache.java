package com.bk.cache;

import com.bk.identity.Principal;
import com.bk.identity.Role;
import com.bk.impl.TestPrincipal;
import com.bk.model.TestUtils;
import com.bk.policy.AuthorizationPolicy;

import java.util.*;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 14/06/22
 */
public class PrincipalCache {
    public static final String ADMIN_USERNAME = "ADMIN";
    public static final String GUEST_USERNAME = "GUEST";
    public static final String GUEST_ELEVATED_USERNAME = "GUEST_ELEVATED";


    public static final String ADMIN_ROLE = "ADMIN_ROLE";
    public static final String GUEST_ROLE = "GUEST_ROLE";


    private Map<String, Principal> principalCache;
    private Map<String, Role> predefinedRoles;
    private Map<String, AuthorizationPolicy> policyMap;

    public PrincipalCache() {
        initRoles();
        initPrincipalCache();
    }

    public Principal getPrincipal(String name) {
        return principalCache.get(name);
    }

    private void initRoles() {
        predefinedRoles = new HashMap<String, Role>();
        Role roleAdmin = new Role();
        roleAdmin.setName(ADMIN_ROLE);
        roleAdmin.setId(new Random().nextLong());
        roleAdmin.setPolicy(TestUtils.getPolicy(TestUtils.ADMIN_POLICY));
        predefinedRoles.put(ADMIN_ROLE, roleAdmin);

        Role roleGuest = new Role();
        roleGuest.setName(GUEST_ROLE);
        roleGuest.setId(new Random().nextLong());
        roleGuest.setPolicy(TestUtils.getPolicy(TestUtils.GUEST_POLICY));
        predefinedRoles.put(GUEST_ROLE, roleGuest);
    }

    private void initPrincipalCache() {
        principalCache = new HashMap<>();
        // 1. Principal for an Admin User
        TestPrincipal principal = new TestPrincipal(new Random().nextLong(),
                ADMIN_USERNAME,
                null,
                TestUtils.getPolicy(TestUtils.ADMIN_POLICY));
        principalCache.put(principal.getName(), principal);

        // 2. Principal for a Guest User
        principal = new TestPrincipal(new Random().nextLong(),
                GUEST_USERNAME,
                null,
                TestUtils.getPolicy(TestUtils.GUEST_POLICY));
        principalCache.put(principal.getName(), principal);

        // 3. Principal with Guest user but Elevated to Admin by assigning ROLE_ADMIN
        Set<Role> rolesForElevatedPrivileges = new HashSet<>();
        rolesForElevatedPrivileges.add(predefinedRoles.get(ADMIN_ROLE));
        principal = new TestPrincipal(new Random().nextLong(),
                GUEST_ELEVATED_USERNAME,
                rolesForElevatedPrivileges,
                TestUtils.getPolicy(TestUtils.GUEST_POLICY));
        principalCache.put(principal.getName(), principal);
    }
}
