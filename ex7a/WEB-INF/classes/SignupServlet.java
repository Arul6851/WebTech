import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/webtech";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
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
            out.println("<style>");
            out.println("body {");
            out.println("  background: linear-gradient(to right, #cc3aa5, #2c0058);");
            out.println("  background-size: cover;");
            out.println("  height: 100%;");
            out.println("  width: 100%;");
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
            out.println(".error {");
            out.println("  font-family: Georgia, 'Times New Roman', Times, serif;");
            out.println("  font-weight: bold;");
            out.println("  text-align: center;");
            out.println("  margin-top: 20px;");
            out.println("  color: red;");
            out.println("  font-size: 18px;");
            out.println("}");
            out.println("</style>");
            out.println("<title>Selected Events</title>");
            out.println("</head>");
            out.println("<body>");
            if (!password.equals(reenteredPassword)) {
                out.println("<p class=\"error\">Passwords do not match. Try Again!</p>");
            } else {
                HttpSession session = request.getSession(true);

                saveUser(username, email, password);

                String selectedEvents = "";
                out.println("<p class=\"title\">Welcome " + username + "<br/>These are your favorite events</p>");
                out.println("<div class=\"container\">");

                for (String event : checkboxes) {
                    String eventValue = request.getParameter(event);
                    if (eventValue != null) {
                        session.setAttribute(event, "selected");
                        selectedEvents += "<a href=\"SessionServlet?event=" + event
                                + "\"><div class=\"card\" style=\"background-image: url(assets/" + event + ".png)\">";
                        selectedEvents += "  <h1>" + event + "</h1>";
                        selectedEvents += "</div></a>";

                        saveUserEvent(username, event);
                    }
                }

                out.println(selectedEvents);
                out.println("</div>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String username, String email, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "INSERT INTO users VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUserEvent(String username, String eventName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {

            String query = "INSERT INTO user_events (user_id, event_name) VALUES ((SELECT user_id FROM users WHERE username = ?), ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, eventName);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
