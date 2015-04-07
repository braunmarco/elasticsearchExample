package de.braun.javaee;

import javax.ejb.Local;

@Local
public interface Greeter {
	String greet ();
}
