package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Birim;
import crm.irfan.entity.Hammadde;
import crm.irfan.entity.Mamul;
import crm.irfan.entity.YariMamul;

public class MamulServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public MamulServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Hammadde> hammadde = new ArrayList<Hammadde>();
        hammadde = DAOFunctions.hammaddeListeGetirTum();
        
        List<YariMamul> yarimamul = new ArrayList<YariMamul>();
        yarimamul = DAOFunctions.yarimamulListeGetirTum();
        
        List<Mamul> mamul = new ArrayList<Mamul>();
        mamul = DAOFunctions.mamulListeGetirTum();
        
        request.setAttribute("birim", birim);
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("yarimamul", yarimamul);
        request.setAttribute("mamul", mamul);
        
        request.getRequestDispatcher("mamul.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<Hammadde> hammadde = new ArrayList<Hammadde>();
        hammadde = DAOFunctions.hammaddeEkle(
                new String(request.getParameter("hamkod").getBytes("UTF-8")), new String(request
                        .getParameter("hamad").getBytes("UTF-8")),
                new String(request.getParameter("hambirim").getBytes("UTF-8")), new String(request
                        .getParameter("hamfirma").getBytes("UTF-8")));
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<YariMamul> yarimamul = new ArrayList<YariMamul>();
        yarimamul = DAOFunctions.yarimamulListeGetirTum();
        
        request.setAttribute("birim", birim);
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("yarimamul", yarimamul);
        request.getRequestDispatcher("mamul.jsp").forward(request, response);
        
    }
    
}
