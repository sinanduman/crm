package crm.irfan;

import crm.irfan.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TamamlananSiparisServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public TamamlananSiparisServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Bilesen> bilesen = new ArrayList<Bilesen>();
        bilesen = DAOFunctions.bilesenListeGetirTum(BilesenTip.MAMUL, 0, null);
        
        List<Siparis> siparis = new ArrayList<Siparis>();
        siparis = DAOFunctions.siparisListeGetir(SiparisTip.TAMAMLANMIS);
        
        request.setAttribute("bilesen", bilesen);
        request.setAttribute("siparis", siparis);
        request.setAttribute("result", ResultTip.NORESULT);
        request.getRequestDispatcher("tamamlanansiparis.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                    IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<Bilesen> bilesen = new ArrayList<Bilesen>();
        bilesen = DAOFunctions.bilesenListeGetirTum(BilesenTip.MAMUL, 0, null);
        
        List<Siparis> siparis = new ArrayList<Siparis>();
        siparis = DAOFunctions.siparisEkle(
                        new String(request.getParameter("bilesen").getBytes("UTF-8")), 
                        new String(request.getParameter("miktar").getBytes("UTF-8")), 
                        new String(request.getParameter("not").getBytes("UTF-8")));
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        request.setAttribute("firma", firma);
        request.setAttribute("birim", birim);
        request.setAttribute("bilesen", bilesen);
        request.setAttribute("siparis", siparis);
        request.setAttribute("result", (siparis.size() > 0 ? ResultTip.OK : ResultTip.ERROR));
        request.getRequestDispatcher("tamamlanansiparis.jsp").forward(request, response);
    }
    
}
