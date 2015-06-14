package de.braun.javaee;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class EnglishGreeter
 */
@Stateless
@LocalBean
public class EnglishGreeter implements Greeter{

	@Override
	public String greet() {
		return "Hello, have a nice day!";
	}

}
