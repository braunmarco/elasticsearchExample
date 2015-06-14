package de.braun.javaee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;

public class GreeterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	LibraryPersistenceBean lpb;
	
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
        addBooks();
        List <Book> bookList = lpb.getBooks();
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>NAME</th>");
        out.println("</tr>");
        for (Book book : bookList) {
        	out.println("<tr>");
        	out.println("<td>"+book.getId()+"</td>");
        	out.println("<td>"+book.getName()+"</td>");
        	out.println("</tr>");
        }
        out.println("</table>");
        out.println ("</body></html>");
        out.flush();
	}
	
	public void addBooks () {
		
		for (int i=1;i<=10;i++) {
			Book book = new Book ();
			book.setName("Book_"+i);
			lpb.addBook(book);
		}
	}
}
