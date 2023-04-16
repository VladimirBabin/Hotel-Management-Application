import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){

    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        pw.println("<html>");
        pw.println("<h1> Hello </h1>");
        pw.println("</html>");
    }
}
