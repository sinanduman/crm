package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Firma;

public class FirmaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public FirmaServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        request.setAttribute("firma", firma);
        request.getRequestDispatcher("firma.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaEkle(
                new String(request.getParameter("firma_ad").getBytes("UTF-8")),
                new String(request.getParameter("firma_tel").getBytes("UTF-8")),
                new String(request.getParameter("firma_adres").getBytes("UTF-8"))
                );        
        request.setAttribute("firma", firma);
        request.getRequestDispatcher("firma.jsp").forward(request, response);
    }
    
}
