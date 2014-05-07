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

//import javax.ejb.EJB;

import org.switchyard.annotations.Requires;
import org.switchyard.component.bean.Service;

//import com.redhat.ejb.beta.TestEJBBetaBeanLocal;

@Requires(security = {CLIENT_AUTHENTICATION, AUTHORIZATION})
@Service(BackEndService.class)
public class BackEndServiceBean implements BackEndService {
	//@EJB(lookup="java:global/TestEJBBeta-ejb/TestEJBBetaBean") TestEJBBetaBeanLocal tb2;


    @Override
    public String process(String in) {
        System.out.println(":: BackEndService :: process => " + in);
        
        return "Processed by BackEndService: " + in;
    }

}
