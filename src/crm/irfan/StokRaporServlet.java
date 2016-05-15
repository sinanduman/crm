package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Genel;
import crm.irfan.entity.LogMod;
import crm.irfan.entity.Mamul;
import crm.irfan.entity.StokRapor;

@WebServlet("/stokrapor")
public class StokRaporServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public StokRaporServlet() {
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
        
        String raporgetirid = request.getParameter("raporgetirid");
        String bilesenid    = request.getParameter("bilesenid");
        String bilesentip   = request.getParameter("bilesentipid");
        String tarih    	= request.getParameter("tarih");
        String page         = request.getParameter("page");
        String excelsql     = "";
        String tablename    = "mamul";
        
        int totalrecord = 0;
        int noofpages   = 0;
        int page0       = 1;
        
        List<Mamul> mamul = new ArrayList<Mamul>();
        mamul = DAOFunctions.mamulListeGetir(null, 0);
        
        List<Bilesen> bilesen = new ArrayList<Bilesen>();
        bilesen = DAOFunctions.bilesenListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL, 0, null);
        
        List<StokRapor> stokrapor = new ArrayList<StokRapor>();
        List<StokRapor> stokdetay = new ArrayList<StokRapor>();
        BilesenTip bilesentipenum = BilesenTip.MAMUL;
        if(raporgetirid!=null || page != null ) {
            String filter   = "";
            String filter0  = " AND bilesentipid IN (3) ";
            String filter1  = "";
            String filter2  = "";
            String filterIn = "";
            DateTime datetime = new DateTime(Util.date_tr_to_eng(tarih)).plusDays(1);
            if(bilesentip!=null) {
                if (Integer.valueOf(bilesentip)==1) {
                    tablename   = "bilesen m";
                    filter0     = " AND bilesentipid IN (1) ";
                    bilesentipenum = BilesenTip.HAMMADDE;
                }
                else if(Integer.valueOf(bilesentip)==2) {
                    tablename   = "bilesen m";
                    filter0     = " AND bilesentipid IN (2) ";
                    bilesentipenum = BilesenTip.YARIMAMUL;
                }
                else {
                    tablename   = "mamul m";
                    filter0     = " AND bilesentipid IN (3) ";
                    bilesentipenum = BilesenTip.MAMUL;
                }
                filter2 += " AND (case when islemyonu=0 then giristarihi else cikistarihi end)<'" + datetime.toString() +"' ";
            }
            if(Util.isNotEmptyOrNull(bilesenid)) {
                filter1 = " AND m.id = " + Integer.valueOf(bilesenid);
            }
            filter      = filter0 + filter1 + filter2;
            filterIn	= " AND id in (select bilesenid from stok where (case when islemyonu=0 then giristarihi else cikistarihi end) <'" + datetime.toString() +"') ";
            // PAGING
            totalrecord = DAOFunctions.recordAgg(tablename, "COUNT", "*", " WHERE 1=1 " + filter0 + filter1 + filterIn );
            if(request.getParameter("page") != null)
                page0   = Integer.parseInt(request.getParameter("page"));            
            noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            // PAGING
            stokrapor   = DAOFunctions.stokBilesenRapor(tablename, filter, page0);
            
            /* Eger bir urunde detay isteniyorsa*/
            if(Util.isNotEmptyOrNull(bilesenid)) {
                stokdetay   = DAOFunctions.stokBilesenDetayRapor(tablename, filter, page0, Integer.valueOf(bilesenid));
                totalrecord = DAOFunctions.recordAgg("stok", "COUNT", "*", " WHERE 1=1 " + "AND bilesenid = " + bilesenid);
                noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            }
            
            excelsql = filter;
            
            if(Genel.LOGMOD == LogMod.DEBUG) {
                System.out.println("noofpages: " + noofpages + ", totalrecord: "+ totalrecord + ", " + tablename + " WHERE 1=1 " + filter );
                System.out.println("bilesentip: " + bilesentipenum.id());
                System.out.println("excelsql: " + excelsql);
            }
            
        }         
        request.setAttribute("mamul", mamul);
        request.setAttribute("bilesen", bilesen);
        request.setAttribute("stokrapor", stokrapor);
        request.setAttribute("stokdetay", stokdetay);
        request.setAttribute("bilesentip", bilesentipenum.id());
        request.setAttribute("bilesenid", bilesenid);
        request.setAttribute("tarih", tarih);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page0);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("excelsql", excelsql);
        request.setAttribute("tablename", tablename);
        request.getRequestDispatcher("stokrapor.jsp").forward(request, response);   
    }
    
}
