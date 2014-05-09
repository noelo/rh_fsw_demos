/*
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.switchyard.quickstarts.demo.policy.security.basic.propagate;

import static org.switchyard.policy.SecurityPolicy.AUTHORIZATION;
import static org.switchyard.policy.SecurityPolicy.CLIENT_AUTHENTICATION;
import static org.switchyard.policy.SecurityPolicy.CONFIDENTIALITY;

import java.util.Hashtable;
import java.util.Properties;

//import org.apache.log4j.Logger;
import org.switchyard.annotations.Requires;
import org.switchyard.component.bean.Service;

import com.redhat.ejb.beta.*;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.jboss.security.auth.callback.UsernamePasswordHandler;

@Requires(security = { CONFIDENTIALITY, CLIENT_AUTHENTICATION, AUTHORIZATION })
@Service(BackEndService.class)
public class BackEndServiceBean implements BackEndService {

	 @EJB(lookup="java:global/TestEJBBeta-ejb/TestEJBBetaBean")
	 TestEJBBetaBeanLocal tb2;

//	private TestEJBBetaBeanLocal ejbLookup() {
//		TestEJBBetaBeanLocal ejbApp2 = null;
//		try {
//			Properties props = new Properties();
//			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
//					"org.jboss.security.jndi.LoginInitialContextFactory");
//			props.put(Context.SECURITY_PRINCIPAL, "kermit");
//			props.put(Context.SECURITY_CREDENTIALS, "the-frog-1");
//			props.setProperty(Context.SECURITY_PROTOCOL, "other");
//			Context ctx = new InitialContext(props);
//			ejbApp2 = (TestEJBBetaBeanLocal) ctx
//					.lookup("java:global/TestEJBBeta-ejb/TestEJBBetaBean");
//
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return ejbApp2;
//	}

	@Override
	public String process(String in) {
		System.out.println(":: BackEndService :: process => " + in);
//		TestEJBBetaBeanLocal tb2 = ejbLookup();
		System.out.println("Looked up ejb now invoking");

//		try {
//			UsernamePasswordHandler handler = new UsernamePasswordHandler(
//					"kermit", "the-frog-1");
//			LoginContext lc;
//			lc = new LoginContext("client-login", handler);
//			lc.login();
		
			tb2.firstMethod(in);
			
//			lc.logout();
//		} catch (LoginException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return "Processed by BackEndService: " + in;
	}

}
