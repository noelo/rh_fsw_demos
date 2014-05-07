package com.example.switchyard.switchyard_example_ejb;


import org.switchyard.component.bean.Service;

import com.redhat.ejb.beta.*;

import java.io.Serializable;

import javax.ejb.EJB;

@Service(EJBWrapper.class)
public class EJBWrapper2Bean implements Serializable, EJBWrapper {

	private static final long serialVersionUID = 3674723120217992934L;
	
	@EJB(lookup="java:global/TestEJBBeta-ejb/TestEJBBetaBean") TestEJBBetaBeanLocal tb2;

	@Override
	public String wrapperMethod1(String in) {
		System.out.println("Invoking wrapperMethod1 with " + in);
		if (tb2 == null)
			System.out.println("Null reference for EJB...");
		else
			tb2.firstMethod(in);
		return new String("This worked");
	}

	@Override
	public String wrapperMethod2(int in) {
		System.out.println("Invoking wrapperMethod2 with " + in);
		if (tb2 == null)
			System.out.println("Null reference for EJB...");
		else
			tb2.secondMethod(in);
		return new String("this worked");
	}

	@Override
	public String wrapperMethod3(String in) {
		System.out.println("Invoking wrapperMethod3 with " + in);
		if (tb2 == null)
			System.out.println("Null reference for EJB...");
		else
			tb2.thirdMethod(in);
		return new String("This worked");
	}

}
