This login module is based on the org.jboss.security.auth.spi.UsernamePasswordLoginModule.
It has been changed to all the future incorporation of a web service call to validate the username/password and return a list of partially completed roles.
The web service invocation and processing is done in the login method.

This login-module does not support propagated credentials using UseFirstPass.

Installation
Copy the POCPolicyMapperLoginModule-1.0-SNAPSHOT.jar ${jboss}/modules/com/redhat/gps/security/main/


Add the login-module to the JBoss standalone.xml file e.g.
        ```<subsystem xmlns="urn:jboss:domain:security:1.2">
            <security-domains>
                <security-domain name="POCMODULE" cache-type="default">
                    <authentication>
                        <login-module code="com.redhat.gps.security.POCPolicyMapperLoginModule" flag="required"/>
                    </authentication>
                </security-domain>
            </security-domains>
        </subsystem>```


Reference the module in the relevant application via a jboss-deployment-structure.xml e.g.
    ```<jboss-deployment-structure >
    	<deployment>
    		<dependencies>
     			<module name="com.redhat.gps.security" />
    		</dependencies>
    		<resources/>
    	</deployment>
    </jboss-deployment-structure>```

Configure the security domain in the application e.g. in a web applicaiton WEB-INF/jboss-web.xml

    ```<?xml version="1.0" encoding="UTF-8"?>
       <jboss-web>
       	<security-domain>POCMODULE</security-domain>
       </jboss-web>






