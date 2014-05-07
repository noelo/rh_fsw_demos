package com.redhat.ejb.beta;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class TestEJBBetaBean
 */
@Stateless
@TestEJBQualifier
@EJB(name = "java:global/TestEJBBetaBean", beanInterface = TestEJBBetaBeanLocal.class)
public class TestEJBBetaBean implements TestEJBBetaBeanLocal {

    /**
     * Default constructor. 
     */
    public TestEJBBetaBean() {
    	System.out.println("TestEJBBetaBean constructor");
    }
    
    @PostConstruct
    private void postConstruct(){
    	System.out.println("TestEJBBetaBean postConstruct");   	
    }

	@Override
	public String firstMethod(String in) {
		System.out.println("FirstMethod invoked with "+in);
		return in.toUpperCase();
	}

	@Override
	public int secondMethod(int in) {
		System.out.println("SecondMethod invoked with "+in);
		return in*10;
	}

	@Override
	public void thirdMethod(String in) {
		System.out.println("ThirdMethod invoked with "+in);	
	}
}
