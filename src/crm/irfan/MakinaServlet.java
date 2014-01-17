package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Makina;

public class MakinaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MakinaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Makina> makina = new ArrayList<Makina>();
        
        
        int totalrecord = DAOFunctions.recordCount("makina");
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        makina = DAOFunctions.makinaListeGetirTum(page);
        
        int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);

        request.setAttribute("makina", makina);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.getRequestDispatcher("makina.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Makina> makina = new ArrayList<Makina>();
        makina = DAOFunctions.makinaEkle(
                new String(request.getParameter("makina_ad").getBytes("UTF-8")),
                new String(request.getParameter("makina_tip").getBytes("UTF-8"))
                );
        request.setAttribute("makina", makina);
        request.getRequestDispatcher("makina.jsp").forward(request, response);
    }

}
