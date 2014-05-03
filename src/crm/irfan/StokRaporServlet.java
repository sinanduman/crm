package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Genel;
import crm.irfan.entity.LogMod;
import crm.irfan.entity.Mamul;
import crm.irfan.entity.StokRapor;

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
        String page         = request.getParameter("page");
        String excelsql     = "";
        String tablename    = "mamul b";
        
        int totalrecord = 0;
        int noofpages   = 0;
        int page0       = 1;
        
        List<Mamul> mamul = new ArrayList<Mamul>();
        mamul = DAOFunctions.mamulListeGetir(0);
        
        List<Bilesen> bilesen = new ArrayList<Bilesen>();
        bilesen = DAOFunctions.bilesenListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL,0);
        
        
        List<StokRapor> stokrapor = new ArrayList<StokRapor>();
        BilesenTip bilesentipenum = BilesenTip.MAMUL;
        if(raporgetirid!=null || page != null ) {
            String filter   = "";
            String filter0  = " AND bilesentipid IN (3) ";
            String filter1  = "";
            if(bilesentip!=null) {
                if (Integer.valueOf(bilesentip)==1) {
                    tablename   = "bilesen b";
                    filter0     = " AND bilesentipid IN (1) ";
                    bilesentipenum = BilesenTip.HAMMADDE;
                }
                else if(Integer.valueOf(bilesentip)==2) {
                    tablename   = "bilesen b";
                    filter0     = " AND bilesentipid IN (2) ";
                    bilesentipenum = BilesenTip.YARIMAMUL;
                }
                else {
                    tablename   = "mamul b";
                    filter0     = " AND bilesentipid IN (3) ";
                    bilesentipenum = BilesenTip.MAMUL;
                }
            }
            if(bilesenid!=null && !bilesenid.equals("") ) {
                filter1     = " AND b.id = " + Integer.valueOf(bilesenid);
            }
            filter = filter0 + filter1 ;
            // PAGING
            totalrecord = DAOFunctions.recordAgg(tablename, "COUNT", "*", " WHERE 1=1 " + filter );
            if(request.getParameter("page") != null)
                page0   = Integer.parseInt(request.getParameter("page"));            
            noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            // PAGING
            stokrapor   = DAOFunctions.stokBilesenRapor(tablename, filter, page0);
            if(Genel.LOGMOD == LogMod.DEBUG) {
                System.out.println("noofpages: "+ noofpages + ", totalrecord: "+ totalrecord + ", " + tablename + " WHERE 1=1 " + filter );
            }
            excelsql    = filter;
        } 
        System.out.println("bilesentip: " +bilesentipenum.id());
        request.setAttribute("mamul", mamul);
        request.setAttribute("bilesen", bilesen);
        request.setAttribute("stokrapor", stokrapor);
        request.setAttribute("bilesentip", bilesentipenum.id());
        request.setAttribute("bilesenid", bilesenid);  
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page0);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("excelsql", excelsql);
        request.setAttribute("tablename", tablename);
        request.getRequestDispatcher("stokrapor.jsp").forward(request, response);   
    }
    
}
