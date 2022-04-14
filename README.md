##Authorization Engine

**Fine Grained Access Control on your REST APIs**
- Enable granular access control in your applications using developer-friendly APIs
- AuthorizationEngine is Pluggable, Extensible and Easy to integrate
- Works on JSON schema for Authorization Policies, Realtime policy update is possible

**The Problem Statement**
- In traditional RBAC systems, To establish granular policies, administrators need to keep adding more roles. This can very easily lead to “role explosion,” which requires administrators to manage thousands of organizational roles.
- Translating user requirements to roles can be a complicated task.
- What about obsolete roles which were created for a particular cases, and currently are not assigned to any principal?
- What about accessing the resources which are added at runtime?

**Authorization Engine**
- Define a granular access control policy, fine-grained access control to your REST APIs is now possible 
- No need to maintain the entire ACL (access control list) with the Principal. Authorization resolution and access check happens at runtime
- No need to modify existing rules or policies to accommodate new users. Just define a simple JSON access policy for new users, and you're done!
- Revoking or adding permissions is easy

**Integration with your Application**
- At this stage, this is just a POC. You can clone and run it your self. Simple SpringBoot app is given with sample policy and REST
- I will be integrating it with YAAS (https://github.com/bhushan1987/AuthenticationService)

**Future Enhancement**
- Extend support for other type of resources than just the REST APIs 
- Handle access control for pattern based REST APIs

**Note:**
- It is currently under development, stay tuned for the updates.
- Passionate developers are welcome to contribute. Reach out: bhushan.karmarkar12@gmail.com
- Cheers!