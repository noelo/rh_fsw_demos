package com.redhat.ejb.beta;

import javax.ejb.Local;

@Local
public interface TestEJBBetaBeanLocal {
	public String firstMethod(String in);
	public int secondMethod(int in);
	public void thirdMethod(String in);
}
