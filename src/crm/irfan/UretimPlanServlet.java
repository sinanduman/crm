package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Birim;
import crm.irfan.entity.Calisan;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Makina;
import crm.irfan.entity.Siparis;
import crm.irfan.entity.SiparisPlan;
import crm.irfan.entity.SiparisTip;

public class UretimPlanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public UretimPlanServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Makina> makina = new ArrayList<Makina>();
        makina = DAOFunctions.makinaListeGetirTum();
        
        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = DAOFunctions.calisanListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        List<Siparis> siparis = new ArrayList<Siparis>();
        siparis = DAOFunctions.siparisListeGetirTum(SiparisTip.BEKLEYEN);
        
        List<SiparisPlan> siparisplan = new ArrayList<SiparisPlan>();
        siparisplan  = DAOFunctions.siparisPlanListeGetirTum(SiparisTip.BEKLEYEN);
        
        request.setAttribute("birim", birim);
        request.setAttribute("makina", makina);
        request.setAttribute("calisan", calisan);
        request.setAttribute("firma", firma);
        request.setAttribute("siparis", siparis);
        request.setAttribute("siparisplan", siparisplan);
        request.getRequestDispatcher("uretimplan.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                    IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String baszaman = request.getParameter("bassaat").toString() +":"+ request.getParameter("basdakika").toString()+":"+"00";
        String bitzaman = request.getParameter("bitsaat").toString() +":"+ request.getParameter("bitdakika").toString()+":"+"00";        
        
        List<SiparisPlan> siparisplan = new ArrayList<SiparisPlan>();
        siparisplan = DAOFunctions.siparisPlanEkle(
                        new String(request.getParameter("siparis").getBytes("UTF-8")),
                        new String(request.getParameter("miktar").getBytes("UTF-8")),
                        new String(request.getParameter("makina").getBytes("UTF-8")),
                        new String(request.getParameter("calisan").getBytes("UTF-8")),
                        new String(request.getParameter("tarih").getBytes("UTF-8")),
                        baszaman,
                        bitzaman,
                        new String(request.getParameter("not").getBytes("UTF-8")));
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Makina> makina = new ArrayList<Makina>();
        makina = DAOFunctions.makinaListeGetirTum();
        
        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = DAOFunctions.calisanListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        List<Siparis> siparis = new ArrayList<Siparis>();
        siparis = DAOFunctions.siparisListeGetirTum(SiparisTip.BEKLEYEN);
        
        request.setAttribute("birim", birim);
        request.setAttribute("makina", makina);
        request.setAttribute("calisan", calisan);
        request.setAttribute("firma", firma);
        request.setAttribute("siparis", siparis);
        request.setAttribute("siparisplan", siparisplan);
        request.getRequestDispatcher("uretimplan.jsp").forward(request, response);
    }
    
}
