package crm.irfan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (request.getParameter("username") != null) ? request.getParameter("username") : "";
        String password = (request.getParameter("password") != null) ? request.getParameter("password") : "";        

        User user = DAO.login(username, password);
        logRedirect (user, request, response);
    }
    
    public static void logRedirect(User user, HttpServletRequest request, HttpServletResponse response) {
        
        String errorPage    = "login.jsp";
        String successPage  = "index.jsp";
        
        if (DAO.loginResult(user)) {
            if((User)request.getSession().getAttribute("user")==null) {
                request.getSession().setAttribute("loggedin", (user!=null));
                request.getSession().setAttribute("user", user);
                try {
                    request.getRequestDispatcher(successPage).forward(request, response);
                }
                catch (ServletException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                request.getRequestDispatcher(errorPage).forward(request, response);
            }
            catch (ServletException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }            
        }
        
    }

    

}
