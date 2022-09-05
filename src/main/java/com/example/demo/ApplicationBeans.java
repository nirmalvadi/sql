package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationBeans {

   @Autowired
   public ApplicationBeans(Environment environment) {
      
      String property = "java.security.auth.login.config";
      System.getProperties().setProperty(property, "/etc/jaas.conf");
      
      String property1 = "sun.security.krb5.debug";
      System.getProperties().setProperty(property1, "false");
      
      String property2 = "javax.security.auth.useSubjectCredsOnly";
      System.getProperties().setProperty(property2, "true");
      
   }

}
