package com.redhat.gps.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface TestEJBAlpha {
	public void method1(String in);
	public String method2(String in);
	public void method3(String in);

}
