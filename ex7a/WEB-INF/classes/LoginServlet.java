import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private List<Credentials> credentials = new ArrayList<>();

    public LoginServlet() {
        credentials.add(new Credentials("admin", "1234", "admin@mail.com"));
        credentials.add(new Credentials("arul", "5678", "arul@mail.com"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String i_name = request.getParameter("name");
        String i_password = request.getParameter("pass");
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        boolean isValid = false;
        String usernameToDisplay = "";

        for (Credentials credential : credentials) {
            if ((credential.getUsername().equals(i_name) || credential.getEmail().equals(i_name))
                    && credential.getPassword().equals(i_password)) {
                isValid = true;
                usernameToDisplay = credential.getUsername();
                break;
            }
        }

        if (i_name.isEmpty() || i_password.isEmpty()) {
            out.print("<html><body><p>Fields cannot be null</p></body></html>");
        } else if (!isValid) {
            out.print("<html><body><p>Enter the right credentials</p></body></html>");
        } else if (!i_name.matches(emailPattern)) {
            out.print("<html><body><p>Enter a valid email</p></body></html>");
        } else {
            out.print("<html><body><p>Welcome, " + usernameToDisplay
                    + ", you are already late with your record work!</p></body></html>");
        }
    }

    public class Credentials {
        private String username;
        private String password;
        private String email;

        public Credentials(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
