package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
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

public class UretimPlanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public UretimPlanServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost( request,  response);
        
        /*
         
        List<HataSebep> hatasebep = new ArrayList<HataSebep>();
        hatasebep = DAOFunctions.hataSebepListeGetir();
        
        List<DurusSebep> durussebep = new ArrayList<DurusSebep>();
        durussebep = DAOFunctions.durusSebepListeGetir();
                
        List<Makina> makina = new ArrayList<Makina>();
        makina = DAOFunctions.makinaListeGetirTum(0);
        
        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = DAOFunctions.calisanListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        List<Mamul> mamul = new ArrayList<Mamul>();
        mamul = DAOFunctions.mamulListeGetir();
        
        int totalrecord = DAOFunctions.recordCount("uretimplan"," where tamamlandi=1 and tarih ='" + UtilFunctions.getTarih(new Date()) + "'" );
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
        int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        
        List<UretimPlan> uretimplan = new ArrayList<UretimPlan>();
        uretimplan  = DAOFunctions.uretimPlanListeGetir(UretimDurum.TAMAMLANMIS,page,"");
        
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
        request.getRequestDispatcher("uretimplan.jsp").forward(request, response);
        * */
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                    IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String mamullisteid = request.getParameter("mamullisteid")==null?"":request.getParameter("mamullisteid");
        String excelsql     = "";
        String message      = "";
        String tarih        = UtilFunctions.getTarih(new Date());
        String makinaid     = "";
        String filter1      = " and tarih='" + tarih + "'";
        String filter2      = "";
        String filter0      = filter1 + filter2;
        
        if(mamullisteid!=null && mamullisteid.matches("[0-9]+")) {
            if(mamullisteid.equals("1")) {            
                tarih   = (request.getParameter("tarih")==null || request.getParameter("tarih").equals(""))?"":request.getParameter("tarih");
                makinaid= (request.getParameter("makinaid")==null || request.getParameter("makinaid").equals(""))?"":request.getParameter("makinaid");
                
                filter1 = " and tarih='" + UtilFunctions.date_tr_to_eng(tarih)+ "'";
                if(!makinaid.equals("")) {
                    filter2  = " and makinaid=" + makinaid;
                }                
                filter0  =  filter1 + filter2;
            }
            else {
                String hedefuretim  = request.getParameter("hedefuretim").toString();
                String fark         = request.getParameter("fark")==""?null:request.getParameter("fark");
                String hataid       = request.getParameter("hatasebepid")==""?null:request.getParameter("hatasebepid");
                String uretimplanid = request.getParameter("uretimplanid")==""?null:request.getParameter("uretimplanid");
                String result = DAOFunctions.uretimPlanGuncelle(
                                Integer.valueOf(hedefuretim),
                                (fark==null)?null:Integer.valueOf(fark),
                                (hataid==null)?null:Integer.valueOf(hataid),
                                Integer.valueOf(uretimplanid)
                                );
                message = result;
            }
            excelsql = "SELECT * FROM uretimplantum WHERE 1=1 " + filter0;
        }
       
        List<HataSebep> hatasebep = new ArrayList<HataSebep>();
        hatasebep = DAOFunctions.hataSebepListeGetir();
        
        List<DurusSebep> durussebep = new ArrayList<DurusSebep>();
        durussebep = DAOFunctions.durusSebepListeGetir();
        
        List<Makina> makina = new ArrayList<Makina>();
        makina = DAOFunctions.makinaListeGetirTum(0);
        
        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = DAOFunctions.calisanListeGetirTum(0);
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        List<Mamul> mamul = new ArrayList<Mamul>();
        mamul = DAOFunctions.mamulListeGetir(0);
        
        int totalrecord = DAOFunctions.recordCount("uretimplantum"," where tamamlandi=1 " + filter0);
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
        int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        
        List<UretimPlan> uretimplan = new ArrayList<UretimPlan>();
        uretimplan  = DAOFunctions.uretimPlanListeGetir(UretimDurum.TAMAMLANMIS, page, filter0);
        
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
        request.setAttribute("tarih", tarih);
        request.setAttribute("makinaid", makinaid);
        request.setAttribute("message", message);
        request.setAttribute("excelsql", excelsql);        
        request.getRequestDispatcher("uretimplan.jsp").forward(request, response);        
    }
    
}
