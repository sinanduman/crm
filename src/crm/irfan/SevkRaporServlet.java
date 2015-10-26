package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;
import crm.irfan.entity.Irsaliye;
import crm.irfan.entity.IrsaliyeBilesen;
import crm.irfan.entity.IrsaliyeTip;
import crm.irfan.entity.LogMod;
import crm.irfan.entity.Mamul;

@WebServlet("/sevkrapor")
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
        String bas_tarih    = request.getParameter("bas_tarih");
        String bit_tarih    = request.getParameter("bit_tarih");
        String mamulid      = request.getParameter("mamulid");
        String page         = request.getParameter("page");
        String excelsql     = "";
        
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        int totalrecord = 0;
        int noofpages   = 0;
        int page0       = 1;
        
        List<Mamul> mamul = new ArrayList<Mamul>();
        mamul = DAOFunctions.mamulListeGetir(null, 0);
        
        List<Irsaliye> irsaliyejson = new ArrayList<Irsaliye>();
        irsaliyejson = DAOFunctions.irsaliyeListeGetirTum(IrsaliyeTip.ONAYLANDI, 0, null, null, null);
        
        List<IrsaliyeBilesen> irsaliyebilesenonaylandi = new ArrayList<IrsaliyeBilesen>();
        
        if(raporgetirid!=null || page != null ) {
            String tablename        = "irsaliye";
            String filter0          = "";
            String filter1          = "";
            String filter2          = "";
            String filter3          = "";
            String filter4          = "";
            String andYes           = " AND ";
            if(irsaliyeid!=null && Util.isNumeric(irsaliyeid)) {
                filter1     = andYes + " id = " + Integer.valueOf(irsaliyeid);
            }
            else {
                irsaliyeid  = null;
            }
            if(firmaid!=null && Util.isNumeric(firmaid)) {
                filter2     = andYes + " firmaid = " + Integer.valueOf(firmaid);
            }
            else {
                firmaid=null;
            }            
            if(bas_tarih!=null && bit_tarih!=null && !bas_tarih.equals("") && !bit_tarih.equals("") ) {
                filter3     = andYes + " ( date(gonderimtarihi) BETWEEN '" + Util.date_tr_to_eng(bas_tarih) + "' AND '" + Util.date_tr_to_eng(bit_tarih) +"')";
            }            
            if(mamulid!=null && Util.isNumeric(mamulid) ) {
                filter4     = andYes + " exists (select 1 from irsaliyebilesen ib where irsaliye.id = ib.irsaliyeid and ib.mamulid = " + Integer.valueOf(mamulid) + ")";
            }
            else {
                mamulid=null;
            }
            filter0 = filter1 + filter2 + filter3 + filter4;
            
            // PAGING
            totalrecord = DAOFunctions.recordAgg(tablename, "COUNT", "*", " WHERE 1=1" + filter0 );
            if(request.getParameter("page") != null)
                page0   = Integer.parseInt(request.getParameter("page"));
            noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            // PAGING
            
            if (Genel.LOGMOD == LogMod.DEBUG) {
                System.out.println("noofpages: " + noofpages + ", totalrecord: " + totalrecord + ", " + tablename + " WHERE 1=1" + filter0);
            }
            
            irsaliyebilesenonaylandi = DAOFunctions.irsaliyeBilesenListeTum(IrsaliyeTip.ONAYLANDI, 1, irsaliyeid, firmaid, bas_tarih, bit_tarih, page0, mamulid);

            excelsql = filter0;
        }
        
        request.setAttribute("mamul", mamul);
        request.setAttribute("firma", firma);
        request.setAttribute("irsaliyeid", irsaliyeid);
        request.setAttribute("irsaliyeno", irsaliyeno);
        request.setAttribute("irsaliyejson", irsaliyejson);
        request.setAttribute("irsaliyebilesenonaylandi", irsaliyebilesenonaylandi);
        request.setAttribute("firmaid", firmaid);
        request.setAttribute("bas_tarih", bas_tarih);
        request.setAttribute("bit_tarih", bit_tarih);
        request.setAttribute("mamulid", mamulid);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page0);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("excelsql", excelsql);        
        request.getRequestDispatcher("sevkrapor.jsp").forward(request, response);
    }
    
}
