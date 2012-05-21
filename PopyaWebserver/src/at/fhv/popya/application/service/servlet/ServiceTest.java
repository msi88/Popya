package at.fhv.popya.application.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.fhv.popya.application.service.impl.WebserverImpl;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.UserTO;

/**
 * Servlet to show the webservice results.
 * 
 * @author Michael
 * 
 */
public class ServiceTest extends HttpServlet {
	private static final long serialVersionUID = -3670819352188649507L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// check if the map should be cleared
		if (req.getParameter("clear") != null
				&& !req.getParameter("clear").isEmpty()) {
			WebserverImpl.getMessages().clear();
		}

		// get the writer
		PrintWriter out = resp.getWriter();

		// start html
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Service Test Servlet </title>");
		out.println("</head>");
		out.println("<body>");

		// get the messages
		Map<UserTO, List<MessageTO<Object>>> messages = WebserverImpl
				.getMessages();

		out.println("<b>Connected users:</b><br/>");
		for (UserTO user : messages.keySet()) {
			out.println(user.getChatName() + "<br/>");
		}

		out.println("<b>Stored messages:</b><br/>");
		for (UserTO user : messages.keySet()) {
			out.println(user.getChatName() + "<br/>");

			// print the messages for the user
			List<MessageTO<Object>> tmp = messages.get(user);

			for (MessageTO<Object> messageTO : tmp) {
				out.println("   " + messageTO.getMessage());
			}
		}

		// add a clear button
		out.println("<form method='POST' action='" + req.getContextPath()
				+ "/test/service'>");
		out.println("<input type='submit' name='clear' value='Clear messages'/>");
		out.println("</form>");

		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
