import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String reenteredPassword = request.getParameter("repass");

        String[] checkboxes = {
                "Sports", "Wedding", "Workshop", "Concert", "Christmas", "BookFair",
                "Easter", "Diwali", "Conference", "Trading", "Network", "Charity"
        };

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
        out.println("<title>Selected Events</title>");
        out.println("<style>");
        out.println("body {");
        out.println("  background: linear-gradient(to right, #cc3aa5, #2c0058);");
        out.println("  background-size: cover;");
        out.println("  height: 100vh;");
        out.println("  width: 100vw;");
        out.println("  background-position: center;");
        out.println("  background-repeat: no-repeat;");
        out.println("}");
        out.println(".title {");
        out.println("  font-family: Georgia, 'Times New Roman', Times, serif;");
        out.println("  font-weight: bold;");
        out.println("  text-align: center;");
        out.println("  margin-top: 50px;");
        out.println("  color: white;");
        out.println("  font-size: 40px;");
        out.println("}");
        out.println(".container {");
        out.println("  display: flex;");
        out.println("  justify-content: center;");
        out.println("  align-items: center;");
        out.println("  flex-direction: row;");
        out.println("  flex-wrap: wrap;");
        out.println("  gap: 110px;");
        out.println("  margin: 60px 10px;");
        out.println("}");
        out.println(".card {");
        out.println("  display: flex;");
        out.println("  justify-content: center;");
        out.println("  align-items: center;");
        out.println("  height: 310px;");
        out.println("  width: 270px;");
        out.println("  background-size: cover;");
        out.println("  border-radius: 7px;");
        out.println("  box-shadow: 10px 25px 35px -8px rgb(197, 189, 245);");
        out.println("  transition: transform 0.4s, box-shadow 0.4s;");
        out.println("}");
        out.println(".card h1 {");
        out.println("  color: white;");
        out.println("}");
        out.println(".card:hover {");
        out.println("  transform: scale(1.1, 1.1);");
        out.println("  box-shadow: 10px 25px 35px 8px rgb(197, 189, 245);");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        if (!password.equals(reenteredPassword)) {
            out.println("<p>Passwords do not match. Try Again!</p>");
        } else {
            out.println("<p class=\"title\">Welcome " + username + "<br/>These are your favorite events</p>");
            out.println("<div class=\"container\">");

            for (String event : checkboxes) {
                String eventValue = request.getParameter(event);
                if (eventValue != null) {
                    // Add hidden fields to the form
                    out.println(
                            "<form action=\"HiddenServlet\" method=\"post\"><input type=\"hidden\" name=\"event\" value=\""
                                    + event + "\">");
                    out.println(
                            "<button type=\"submit\" class=\"card\" style=\"background-image: url(assets/" + event
                                    + ".png)\">");
                    out.println("  <h1>" + event + "</h1>");
                    out.println("</button></form>");
                }
            }

            out.println("</div>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
