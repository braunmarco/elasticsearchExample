package de.braun.javaee;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.braun.javaee.models.User;
import de.braun.javaee.service.UserManagerBean;

public class GreeterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	UserManagerBean umb;

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
		// GreetService greetService = new GermanGreeter();
		out.println("<p> " + common.greet() + "</p>");
		out.println("<p> " + germanGreet.greet() + "</p>");
		out.println("<p> " + englishGreet.greet() + "</p>");
		// addBooks();
		// addUsers();
		//printBooks(out);
		printUsers(out);
		out.println("</body></html>");
		out.flush();
	}

	public void printBooks(ServletOutputStream out) throws IOException, ServletException {
		List<Book> bookList = lpb.getBooks();
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>ID</th>");
		out.println("<th>NAME</th>");
		out.println("</tr>");
		for (Book book : bookList) {
			out.println("<tr>");
			out.println("<td>" + book.getId() + "</td>");
			out.println("<td>" + book.getName() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}

	public void printUsers(ServletOutputStream out) throws IOException, ServletException{
		List<User> userList = umb.getUsers();
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>ID</th>");
		out.println("<th>NAME</th>");
		out.println("</tr>");
		for (User user : userList) {
			out.println("<tr>");
			out.println("<td>" + user.getId() + "</td>");
			out.println("<td>" + user.getName() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}

	public void addBooks() {

		for (int i = 1; i <= 10; i++) {
			Book book = new Book();
			book.setName("Book_" + i);
			lpb.addBook(book);
		}
	}

	public void addUsers (){
		for (int i=1;i<=10;i++) {
			User user = new User ();
			user.setName("Name_" + i);
			user.setSurname("Surname_"+i);
			user.setUsername("Username_"+i);
			user.setPassword(generate(8));
			umb.addUser(user);
			umb.addUser(user);
		}
	}

	public String generate(final int len) {
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!§$%&/()=?*_:;,.-#+";
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
