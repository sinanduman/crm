package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Calisan;

public class CalisanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CalisanServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = DAOFunctions.calisanListeGetirTum();

        request.setAttribute("calisan", calisan);
        request.getRequestDispatcher("calisan.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = DAOFunctions.calisanEkle(
                new String(request.getParameter("adsoy").getBytes("UTF-8")), 
                new String(request.getParameter("gorev").getBytes("UTF-8"))
                );

        request.setAttribute("calisan", calisan);
        request.getRequestDispatcher("calisan.jsp").forward(request, response);

    }

    

}
