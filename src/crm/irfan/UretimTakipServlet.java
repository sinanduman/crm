package crm.irfan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Calisan;
import crm.irfan.entity.DurusSebep;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;
import crm.irfan.entity.HataSebep;
import crm.irfan.entity.Makina;
import crm.irfan.entity.Mamul;
import crm.irfan.entity.UretimDurum;
import crm.irfan.entity.UretimPlan;

@WebServlet("/uretimtakip")
public class UretimTakipServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public UretimTakipServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                    IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String message      = "";
        Boolean ajaxInAction= false;
        
        System.out.println(request.getParameter("islemid"));
        if(request.getParameter("islemid")!=null) {
            Integer islemid     = (request.getParameter("islemid")==null)?-1:Integer.valueOf(request.getParameter("islemid"));
            String uretimplanid = request.getParameter("uretimplanid");        
            String mamulid      = request.getParameter("mamulid");        
            String uret_miktar  = request.getParameter("uretilenmiktar");
            String hata_miktar  = (request.getParameter("hatalimiktar")==null || request.getParameter("hatalimiktar")=="") ? "0" : request.getParameter("hatalimiktar");
            
            if(islemid==3) {  /* Stok Yeterli mi ? */
                //String result = DAOFunctions.stokYeterli(Integer.valueOf(uretimplanid), String mamulid, String miktar);
                ajaxInAction    = true;
                String result   = DAOFunctions.stokYeterli(
                                Integer.valueOf(uretimplanid), 
                                Integer.valueOf(mamulid),  
                                Integer.valueOf(uret_miktar) + Integer.valueOf(hata_miktar)
                                );
                PrintWriter out = response.getWriter();
                out.print(result);
            }
            else if(islemid==2) {  /* Silme */
                ajaxInAction    = true;
                String result   = DAOFunctions.uretimPlanSil(Integer.valueOf(uretimplanid));
                PrintWriter out = response.getWriter();
                out.print(result);
            }
            else if(islemid==1) {  /* Onaylama */
                ajaxInAction    = true;
                String result   = DAOFunctions.uretimPlanOk(Integer.valueOf(uretimplanid));
                PrintWriter out = response.getWriter();
                out.print(result);
            }else if(islemid==0){ /* Takip ekleme */
                System.out.println("islemid: " +request.getParameter("islemid"));
                String baszaman = request.getParameter("bassaat").toString() +":"+ request.getParameter("basdakika").toString()+":"+"00";
                String bitzaman = request.getParameter("bitsaat").toString() +":"+ request.getParameter("bitdakika").toString()+":"+"00";
                String hataliad = (request.getParameter("hataliadet").trim() == "")?"0":request.getParameter("hataliadet");
                int result = DAOFunctions.uretimTakipEkleGuncelle(
                                new String(request.getParameter("mamulid").getBytes("UTF-8")),
                                new String(request.getParameter("uretimadet").getBytes("UTF-8")),
                                hataliad,
                                new String(request.getParameter("makinaid").getBytes("UTF-8")),
                                new String(request.getParameter("calisanid").getBytes("UTF-8")),
                                new String(request.getParameter("tarih").getBytes("UTF-8")),                        
                                baszaman,
                                bitzaman,
                                new String(request.getParameter("hatasebepid").getBytes("UTF-8")),
                                new String(request.getParameter("durussebepid").getBytes("UTF-8")),
                                new String(request.getParameter("duruszaman").getBytes("UTF-8")),
                                Integer.valueOf(uretimplanid),
                                Integer.valueOf(request.getParameter("mamulizleno"))
                                );
                message = (result==-1) ? "Hata oluştu" : "";
            }
        }
        if(!ajaxInAction) {
            List<HataSebep> hatasebep = new ArrayList<HataSebep>();
            hatasebep = DAOFunctions.hataSebepListeGetir();
            
            List<DurusSebep> durussebep = new ArrayList<DurusSebep>();
            durussebep = DAOFunctions.durusSebepListeGetir();
            
            List<Makina> makina = new ArrayList<Makina>();
            makina = DAOFunctions.makinaListeGetirTum(0);
            
            List<Calisan> calisan = new ArrayList<Calisan>();
            calisan = DAOFunctions.calisanListeGetirTum(0, 1);
            
            List<Firma> firma = new ArrayList<Firma>();
            firma = DAOFunctions.firmaListeGetirTum(0);
            
            List<Mamul> mamul = new ArrayList<Mamul>();
            mamul = DAOFunctions.mamulListeGetir(null, 0);
            
            int totalrecord = DAOFunctions.recordAgg("uretimplantum_takip","count","*"," where tamamlandi=0 ");
            int page = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            
            int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            
            List<UretimPlan> uretimplan = new ArrayList<UretimPlan>();
            uretimplan  = DAOFunctions.uretimPlanListeGetir(UretimDurum.BEKLEYEN, page, "","takip");
            
            request.setAttribute("hatasebep", hatasebep);
            request.setAttribute("durussebep", durussebep);
            request.setAttribute("makina", makina);
            request.setAttribute("calisan", calisan);
            request.setAttribute("firma", firma);
            request.setAttribute("mamul", mamul);
            request.setAttribute("uretimplan", uretimplan);
            request.setAttribute("totalrecord", totalrecord);
            request.setAttribute("currentpage", page);
            request.setAttribute("noofpages", noofpages);
            request.setAttribute("message", message);
            request.getRequestDispatcher("uretimtakip.jsp").forward(request, response);
            
        }        
    }
    
}
