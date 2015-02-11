package de.braun.javaee;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class GermanGreeter
 */
@Stateless
@LocalBean
public class GermanGreeter implements Greeter{
	@Override
	public String greet() {
		// TODO Auto-generated method stub
		return "Guten Tag!";
	}
}
