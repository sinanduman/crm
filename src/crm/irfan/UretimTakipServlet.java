package crm.irfan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Calisan;
import crm.irfan.entity.DurusSebep;
import crm.irfan.entity.Firma;
import crm.irfan.entity.HataSebep;
import crm.irfan.entity.Makina;
import crm.irfan.entity.Siparis;
import crm.irfan.entity.SiparisPlan;
import crm.irfan.entity.SiparisTip;

public class UretimTakipServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public UretimTakipServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<HataSebep> hatasebep = new ArrayList<HataSebep>();
        hatasebep = DAOFunctions.hataSebepListeGetir();
        
        List<DurusSebep> durussebep = new ArrayList<DurusSebep>();
        durussebep = DAOFunctions.durusSebepListeGetir();
                
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
        
        request.setAttribute("hatasebep", hatasebep);
        request.setAttribute("durussebep", durussebep);
        request.setAttribute("makina", makina);
        request.setAttribute("calisan", calisan);
        request.setAttribute("firma", firma);
        request.setAttribute("siparis", siparis);
        request.setAttribute("siparisplan", siparisplan);
        request.getRequestDispatcher("uretimtakip.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                    IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        //String baszaman = request.getParameter("bassaat").toString() +":"+ request.getParameter("basdakika").toString()+":"+"00";
        //String bitzaman = request.getParameter("bitsaat").toString() +":"+ request.getParameter("bitdakika").toString()+":"+"00";
        Integer islemid  = Integer.valueOf(request.getParameter("islemid"));
        /*1:Update, 2:Completed, 3: Delete*/
        Integer result = 0;
        switch (islemid) {
            case 1:
                result = DAOFunctions.SiparisPlanGuncelle(
                                request.getParameter("siparisplanid"),
                                request.getParameter("miktar"),
                                request.getParameter("makinaid"),
                                request.getParameter("calisanid"),
                                request.getParameter("tarih"),
                                request.getParameter("bassaat"),
                                request.getParameter("basdakika"),
                                request.getParameter("bitsaat"),
                                request.getParameter("bitdakika"),
                                request.getParameter("hataid"),
                                request.getParameter("hatamiktar"),
                                request.getParameter("durusid"),
                                request.getParameter("not")
                                );
                break;
            case 2:
                result = DAOFunctions.SiparisPlanTamamla(request.getParameter("siparisplanid"));
                break;
            case 3:
                result = DAOFunctions.SiparisPlanSil(request.getParameter("siparisplanid"));
                break;            
        }
        PrintWriter out = response.getWriter();
        out.print(result);
        
        /*
        PrintWriter out = response.getWriter();
        out.println(request.getParameter("siparisplanid"));
        out.println(request.getParameter("miktar"));
        out.println(request.getParameter("makinaid"));
        out.println(request.getParameter("calisanid"));
        out.println(request.getParameter("tarih"));
        out.println(request.getParameter("bassaat"));
        out.println(request.getParameter("basdakika"));
        out.println(request.getParameter("bitsaat"));
        out.println(request.getParameter("bitdakika"));
        out.println(request.getParameter("islemid"));
                
        
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
        request.getRequestDispatcher("uretimtakip.jsp").forward(request, response);
        */
    }
    
}
