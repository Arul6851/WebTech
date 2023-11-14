import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/webtech";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String i_name = request.getParameter("name");
        String i_password = request.getParameter("pass");
        boolean isValid = false;
        String usernameToDisplay = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, i_name);
            statement.setString(2, i_name);
            statement.setString(3, i_password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                isValid = true;
                usernameToDisplay = resultSet.getString("username");
            }

            if (isValid) {
                String eventsQuery = "SELECT event_name FROM user_events WHERE user_id = (SELECT user_id FROM users WHERE username = ?)";
                PreparedStatement eventsStatement = connection.prepareStatement(eventsQuery);
                eventsStatement.setString(1, usernameToDisplay);

                ResultSet eventsResultSet = eventsStatement.executeQuery();

                out.print("<!DOCTYPE html>");
                out.print("<html lang=\"en\">");
                out.print("<head>");
                out.print("<meta charset=\"UTF-8\" />");
                out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
                out.print("<title>Selected Events</title>");
                out.print("<style>");
                out.print("body {");
                out.print("  background: linear-gradient(to right, #cc3aa5, #2c0058);");
                out.print("  background-size: cover;");
                out.print("  height: 100%;");
                out.print("  width: 100%;");
                out.print("  background-position: center;");
                out.print("  background-repeat: no-repeat;");
                out.print("}");
                out.print(".title {");
                out.print("  font-family: Georgia, 'Times New Roman', Times, serif;");
                out.print("  font-weight: bold;");
                out.print("  text-align: center;");
                out.print("  margin-top: 50px;");
                out.print("  color: white;");
                out.print("  font-size: 40px;");
                out.print("}");
                out.print(".container {");
                out.print("  display: flex;");
                out.print("  justify-content: center;");
                out.print("  align-items: center;");
                out.print("  flex-direction: row;");
                out.print("  flex-wrap: wrap;");
                out.print("  gap: 110px;");
                out.print("  margin: 60px 10px;");
                out.print("}");
                out.print(".card {");
                out.print("  display: flex;");
                out.print("  justify-content: center;");
                out.print("  align-items: center;");
                out.print("  height: 310px;");
                out.print("  width: 270px;");
                out.print("  background-size: cover;");
                out.print("  border-radius: 7px;");
                out.print("  box-shadow: 10px 25px 35px -8px rgb(197, 189, 245);");
                out.print("  transition: transform 0.4s, box-shadow 0.4s;");
                out.print("}");
                out.print(".card h1 {");
                out.print("  color: white;");
                out.print("}");
                out.print(".card:hover {");
                out.print("  transform: scale(1.1, 1.1);");
                out.print("  box-shadow: 10px 25px 35px 8px rgb(197, 189, 245);");
                out.print("}");
                out.print("</style>");
                out.print("</head>");
                out.print("<body>");
                out.print(
                        "<p class=\"title\">Welcome " + usernameToDisplay + "<br/>These are your favorite events</p>");
                out.print("<div class=\"container\">");

                while (eventsResultSet.next()) {
                    String eventName = eventsResultSet.getString("event_name");
                    out.print("<div class=\"card\" style=\"background-image: url(assets/" + eventName + ".png)\">");
                    out.print("  <h1>" + eventName + "</h1>");
                    out.print("</div>");
                }

                out.print("</div>");
                out.print("</body>");
                out.print("</html>");
            } else {
                out.print(
                        "<html><body><p class=\"error\">Credentials are wrong. Please try again.</p></body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("<html><body><p>Error connecting to the database</p></body></html>");
        }
    }

}
