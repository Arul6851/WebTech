import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        // String event = request.getParameter("event");

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
        out.println("<title>Selected Event Details</title>");
        out.println("<style>");
        out.println("body {");
        out.println("  background: linear-gradient(to right, #cc3aa5, #2c0058);");
        out.println("  background-size: cover;");
        out.println("  height: 100vh;");
        out.println("  width: 100vw;");
        out.println("  background-position: center;");
        out.println("  background-repeat: no-repeat;");
        out.println("}");
        out.println(".details {");
        out.println("  font-family: Georgia, 'Times New Roman', Times, serif;");
        out.println("  font-weight: bold;");
        out.println("  text-align: center;");
        out.println("  margin-top: 50px;");
        out.println("  color: white;");
        out.println("  font-size: 40px;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p class=\"details\">Details for the selected event using Cookies: <br/></p>");
        if (cookies != null)
            for (Cookie cookie : cookies)
                out.println("<p class=\"details\">" + cookie.getValue() + "</p>");

        else
            out.println("<p class=\"details\">Event not selected</p>");

        out.println("</body>");
        out.println("</html>");
    }
}