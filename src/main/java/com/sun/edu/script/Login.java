package com.sun.edu.script;

import org.testng.annotations.Test;

public class Login extends CommonTestCase {
	@Test(dataProvider = "SetLogin")
	public void login(String email, String pass) {
		super.login(email, pass);
	}
	
}
