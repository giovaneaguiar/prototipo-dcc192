/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giovaneaguiar
 */
public class conexaoComBanco extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pega senha do banco de dados
         PrintWriter out = response.getWriter();
        String user_bd = "giovane";
        String pw_bd = "aguiar";
        boolean result = false;
        String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
        String DB_URL = "jdbc:derby://localhost:1527/Giovane";
        //  Database credentials
        Connection conn = null;
        Statement stmt = null;
        // Set response content type
            
        try {
            out.println("<head>");
            
            out.println("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
            out.println("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
            out.println("<link href=\"https://fonts.googleapis.com/css2?family=Outfit:wght@300&display=swap\" rel=\"stylesheet\">");
            
            out.println("</head>");
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, user_bd, pw_bd);
            // Execute SQL query
            stmt = (Statement) conn.createStatement();
            String sql;
            
            String usuarioDoFormulario = request.getParameter("username");
            String senhaDoFormulario = request.getParameter("password");
            
            sql = "SELECT usuario, senha FROM usuarios where upper(usuario) = '"
                + usuarioDoFormulario.toUpperCase() + "' and senha='" + senhaDoFormulario + "'";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            if (rs.next()) {
                result = true;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
             //Handle errors for JDBC
             //throw new ServletException(e);
             String resp = e.getMessage();
            throw new ServletException(e);
        } catch (Exception e) {
            //Handle errors for Class.forName
            //throw new ServletException(e);
            String resp = e.getMessage();
            throw new ServletException(e);
        }
        finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }//end finally try
        } //end try    
        if (result){
            response.sendRedirect("/semana3-pratico/menu.jsp");
        }
        else {
            out.println("<div style=\"font-family: 'Outfit', sans-serif;\">");
            out.println("<h1 \"div style='text-align:center'\">Usuário e/ou senha inválidos!</h1>");
            out.println("<h2 \"div style='text-align:center'\">");
            out.println("<a href=\"matar\" style=\"text-decoration:none\">Sair do Sistema</a>");
            out.println("</h2>");
            out.println("</div>");
            
    }

    } 

}
