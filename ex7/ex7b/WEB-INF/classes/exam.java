import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
public class exam extends HttpServlet {
    private int counter = 0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/webtech", "root", "");
            for (int i = 1; i <= 5; i++) {
                String user_ans = request.getParameter("answer" + i);
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM quiz WHERE question_no = ?");
                stmt.setInt(1, i);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String correct_ans = rs.getString("answer");
                    if (user_ans != null && user_ans.equals(correct_ans)) {
                        counter++;
                    }}
                rs.close();
                stmt.close();
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body style='font-family: Arial, sans-serif; background: linear-gradient(to right, #cc3aa5, #2c0058); text-align: center; padding: 50px;'>");
        out.println("<div style='max-width: 400px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);'>");
        out.println("<h2 style='color: #2c0058;'>Result</h2>");
        out.println("<p style='color: #2c0058; font-size: 18px;'>Total Score: " + counter + "</p>");
        out.println(counter >= 5 ? "<p>Congratulations! You have Passed!</p>" : "<p>Sorry, You Failed!</p>");
        out.println("</div>");
        out.println("</body></html>");
        counter = 0;
    }
}
