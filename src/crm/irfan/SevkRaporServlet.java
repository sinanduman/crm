package crm.irfan;

import crm.irfan.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SevkRaporServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public SevkRaporServlet() {
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
        String irsaliyeid   = request.getParameter("irsaliyeid");
        String irsaliyeno   = request.getParameter("irsaliyeno");
        String firmaid      = request.getParameter("firmaid");
        String tarih        = request.getParameter("tarih");
        String page         = request.getParameter("page");
        String excelsql     = "";
        
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        int totalrecord = 0;
        int noofpages   = 0;
        int page0       = 1;
        
        List<UretimPlan> stokrapor = new ArrayList<UretimPlan>();
        List<Irsaliye> irsaliyejson = new ArrayList<Irsaliye>();
        irsaliyejson = DAOFunctions.irsaliyeListeGetirTum(IrsaliyeTip.ONAYLANDI, 0, null, null, null);
        
        List<Irsaliye> irsaliye = new ArrayList<Irsaliye>();
        List<IrsaliyeBilesen> irsaliyebilesenonaylandi = new ArrayList<IrsaliyeBilesen>();
        
        if(raporgetirid!=null || page != null ) {
            String tablename        = "uretimplanexcel";
            String filter0          = "";
            String filter1          = "";
            String filter2          = "";
            String filter3          = "";
            String andYes           = " AND ";
            if(irsaliyeid!=null && !irsaliyeid.equals("") ) {
                filter1     = andYes + " irsaliyeid = " + Integer.valueOf(irsaliyeid);
            }
            else {
                irsaliyeid  = null;
            }
            if(firmaid!=null && !firmaid.equals("") ) {
                filter2     = andYes + " firmaid = " + Integer.valueOf(firmaid);
            }
            else {
                firmaid=null;
            }
            if(tarih!=null && Util.isDate(tarih) ) {
                filter3     = andYes + " ( date(gonderimtarihi) = '" + Util.date_tr_to_eng(tarih) + "' )";
            }
            else {
                tarih=null;
            }
            filter0 = filter1 + filter2 + filter3;
            
            // PAGING
            //totalrecord = DAOFunctions.recordAgg(tablename, "COUNT", "*", " WHERE 1=1" + filter0 );
            totalrecord = DAOFunctions.irsaliyeListeGetirCount(IrsaliyeTip.ONAYLANDI, irsaliyeid, firmaid, tarih);
            if(request.getParameter("page") != null)
                page0   = Integer.parseInt(request.getParameter("page"));            
            noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            // PAGING
            
            irsaliye = DAOFunctions.irsaliyeListeGetirTum(IrsaliyeTip.ONAYLANDI, page0, irsaliyeid, firmaid, tarih);
            irsaliyebilesenonaylandi = DAOFunctions.irsaliyeBilesenListeGetirTum(IrsaliyeTip.ONAYLANDI, 1, irsaliyeid, firmaid, tarih);

            //stokrapor   = DAOFunctions.uretimBilesenRapor(irsaliyeid, page0, filter0);
            
            if (Genel.LOGMOD==LogMod.DEBUG) {
                System.out.println("noofpages: "+ noofpages + ", totalrecord: "+ totalrecord + ", " + tablename + " WHERE 1=1" + filter0 );
            }

            excelsql = "SELECT * FROM "  + tablename + " WHERE 1=1 " + filter0;
        }
        
        request.setAttribute("firma", firma);
        //request.setAttribute("stokrapor", stokrapor);
        request.setAttribute("irsaliyeid", irsaliyeid);
        request.setAttribute("irsaliyeno", irsaliyeno);
        request.setAttribute("irsaliye", irsaliye);
        request.setAttribute("irsaliyejson", irsaliyejson);
        request.setAttribute("irsaliyebilesenonaylandi", irsaliyebilesenonaylandi);
        request.setAttribute("firmaid", firmaid);
        request.setAttribute("tarih", tarih);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page0);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("excelsql", excelsql);        
        request.getRequestDispatcher("sevkrapor.jsp").forward(request, response);   
    }
    
}
