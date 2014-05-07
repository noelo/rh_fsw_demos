package com.redhat.gps.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class TestEJBAlpha
 */
@Stateless
public class TestEJBAlphaBean implements TestEJBAlpha {
	
	@PostConstruct
	private void postCons(){
		System.out.println("@PostConstruct called");
	}

	/**
	 * Default constructor.
	 */
	public TestEJBAlphaBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void method1(String in) {
		System.out.println("Method1 called with " + in);

	}

	@Override
	public String method2(String in) {
		System.out.println("Method2 called with " + in);
		return in.toUpperCase();
	}

	@Override
	public void method3(String in) {
		System.out.println("Method3 called with " + in);

	}

}
