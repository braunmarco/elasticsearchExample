package de.braun.javaee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import de.braun.javaee.Greeter;

public class GreeterServlet extends HttpServlet {

	@EJB
	private GermanGreeter germanGreet;
	
	@EJB
	private EnglishGreeter englishGreet;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("<html><body>");
        out.println("<p>Hello</p>");
        Common common = new Common();
        //GreetService greetService = new GermanGreeter();
        out.println("<p> " + common.greet() + "</p>");
        out.println("<p> " + germanGreet.greet() + "</p>");
        out.println("<p> " + englishGreet.greet() + "</p>");
        out.println ("</body></html>");
        out.flush();
	}
}
