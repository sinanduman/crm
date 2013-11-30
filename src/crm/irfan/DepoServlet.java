package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Depo;

public class DepoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DepoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Depo> depo = new ArrayList<Depo>();
        depo = DAOFunctions.depoListeGetirTum();

        request.setAttribute("depo", depo);
        request.getRequestDispatcher("depo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Depo> depo = new ArrayList<Depo>();
        depo = DAOFunctions.depoEkle(new String(request.getParameter("depo_ad").getBytes("UTF-8")));

        request.setAttribute("depo", depo);
        request.getRequestDispatcher("depo.jsp").forward(request, response);

    }

}
