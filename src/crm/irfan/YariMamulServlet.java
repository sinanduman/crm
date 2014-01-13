package crm.irfan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Firma;

public class YariMamulServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public YariMamulServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Bilesen> yarimamul = new ArrayList<Bilesen>();
        yarimamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.YARIMAMUL);
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();

        request.setAttribute("yarimamul", yarimamul);
        request.setAttribute("firma", firma);
        request.getRequestDispatcher("yarimamul.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String islemid = request.getParameter("islemid");
        if(islemid != null && islemid != "" ) {
            Integer result = 0;
            switch (Integer.valueOf(islemid)) {
                case 1:
                    result = DAOFunctions.bilesenGuncelle(
                                    new String(request.getParameter("bilesenid").getBytes("UTF-8")),
                                    new String(request.getParameter("birimid").getBytes("UTF-8")),
                                    new String(request.getParameter("firmaid").getBytes("UTF-8")),
                                    new String(request.getParameter("bilesenkod").getBytes("UTF-8")),
                                    new String(request.getParameter("bilesenad").getBytes("UTF-8")),
                                    null /* -- null cevrimsuresi icin */
                                    );
                    break;
                case 3:
                    result = DAOFunctions.bilesenSil(new String(request.getParameter("bilesenid").getBytes("UTF-8")));
                    break;
            }
            PrintWriter out = response.getWriter();
            out.print(result);
        }
        else {
            List<Bilesen> yarimamul = new ArrayList<Bilesen>();
            yarimamul = DAOFunctions.yarimamulEkle(
                    new String(request.getParameter("yarimamulkod").getBytes("UTF-8")), 
                    new String(request.getParameter("yarimamulad").getBytes("UTF-8")),
                    new String(request.getParameter("yarimamulbirim").getBytes("UTF-8")),
                    new String(request.getParameter("yarimamulfirma").getBytes("UTF-8"))
                    );
            
            List<Firma> firma = new ArrayList<Firma>();
            firma = DAOFunctions.firmaListeGetirTum();
    
            request.setAttribute("yarimamul", yarimamul);
            request.setAttribute("firma", firma);
            request.getRequestDispatcher("yarimamul.jsp").forward(request, response);
        }

    }

}
