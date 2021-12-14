
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class fazerLogin extends HttpServlet {
    
    String usuarioGiovane;
    String senhaGiovane;
    
    @Override
    public void init(){
        
        usuarioGiovane=getInitParameter("username");
        senhaGiovane=getInitParameter("password");
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        try {
            
            String usuarioDoFormulario = request.getParameter("username");
            String senhaDoFormulario = request.getParameter("password");

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE HTML>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            if (usuarioGiovane.equals(usuarioDoFormulario) && senhaGiovane.equals(senhaDoFormulario))
            {
                out.println("<h1> Seja bem vindo, " + usuarioGiovane + "! </h1>");
            } else 
            {
                throw new ServletException("Senha e/ou usuário incorreto!");
            }
            out.println("</div>");
            out.println("</body>");
            
        } catch (ServletException e) {
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
            out.println("<a href=\"redirect\">Continua</a>");
        }
        
    }
    
}
/**
 *
 * @author giovaneaguiar
 */

