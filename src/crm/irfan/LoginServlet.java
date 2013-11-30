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
        User user = new User();
        if ((User) request.getSession().getAttribute("user") != null) {
            user = (User) request.getSession().getAttribute("user");
        }

        Boolean loggedin = (request.getSession().getAttribute("loggedin") != null) ? true : false;
        String username = (request.getParameter("username") != null) ? request.getParameter("username") : "";
        String password = (request.getParameter("password") != null) ? request.getParameter("password") : "";
        if (loggedin != null && loggedin) {
            user.setValid(true);
        }
        else {
            user.setUsername(username);
            user.setPassword(password);
            user = DAO.login(user);
        }
        request.getSession().setAttribute("user", user);
        loginResult(user, user.isValid(), request, response);
    }

    protected void loginResult(User user, Boolean loggedin, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String message = null;
        String fwdPage = null;
        String errorPage = "errorPage.jsp";
        String successPage = "index.jsp";
        if (loggedin) {
            message = "Login successful";
            fwdPage = successPage;
        }
        else {
            message = "Kullanıcı adı veya şifre yanlış!";
            fwdPage = errorPage;
        }
        request.getSession().setAttribute("message", message);
        request.getSession().setAttribute("loggedin", loggedin);
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher(fwdPage).forward(request, response);
    }

}
