import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HiddenServlet")
public class HiddenServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve data from the hidden field
        String username = (String) request.getAttribute("username");
        String email = (String) request.getAttribute("email");

        // Retrieve selected events
        String[] selectedEvents = request.getParameterValues("selectedEvents");

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
        out.println("<p class=\"details\">Details for the selected event using Hidden Fields :</p>");
        out.println("<p>Username: " + username + "</p>");
        out.println("<p>Email: " + email + "</p>");

        if (selectedEvents != null) {
            out.println("<p>Selected Events:</p>");
            out.println("<ul>");
            for (String event : selectedEvents) {
                out.println("<li>" + event + "</li>");
            }
            out.println("</ul>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
