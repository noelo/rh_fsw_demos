package com.redhat.ejb.beta;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * Session Bean implementation class TestEJBBetaBean
 */
@Stateless
@EJB(name = "java:global/TestEJBBetaBean", beanInterface = TestEJBBetaBeanLocal.class)
@RolesAllowed({"friend"})
@SecurityDomain("other")
public class TestEJBBetaBean implements TestEJBBetaBeanLocal {

	@Resource
	private SessionContext ctx;

	/**
	 * Default constructor.
	 */
	public TestEJBBetaBean() {
		System.out.println("TestEJBBetaBean constructor");
	}

	@PostConstruct
	private void postConstruct() {
		System.out.println("TestEJBBetaBean postConstruct");
	}

	@Override
	public String firstMethod(String in) {
		Principal principal = ctx.getCallerPrincipal();
		System.out.println("FirstMethod invoked with " + in +" as "+principal.toString());
		return in.toUpperCase();
	}

	@Override
	public int secondMethod(int in) {
		System.out.println("SecondMethod invoked with " + in);
		return in * 10;
	}

	@Override
	public void thirdMethod(String in) {
		System.out.println("ThirdMethod invoked with " + in);
	}
}
