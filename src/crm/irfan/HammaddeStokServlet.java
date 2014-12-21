package crm.irfan;

import crm.irfan.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HammaddeStokServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HammaddeStokServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        //hammadde = DAOFunctions.bilesenListeGetirTum( BilesenTip.HAMMADDE, 0 );
        hammadde = DAOFunctions.bilesenListeGetirTum( null, 0 );
                
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        List<Stok> stok = new ArrayList<Stok>();
        String stoklisteid  = request.getParameter("stoklisteid");
        String bilesenid    = request.getParameter("bilesenid");
        
        int totalrecord = 0;
        int page        = 1;
        int noofpages   = 0;
        int sumagg      = 0;
        
        if(stoklisteid!=null && Integer.valueOf(stoklisteid)==1) {
            
            // PAGING
            totalrecord = DAOFunctions.recordCount("stokbilesen"," where bilesenid= " + Integer.valueOf(bilesenid) );
            sumagg      = DAOFunctions.recordAgg("stokmiktar", "sum", "miktar", " where bilesenid= " + Integer.valueOf(bilesenid));
            if(request.getParameter("page") != null)
                page    = Integer.parseInt(request.getParameter("page"));        
            noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            // PAGING
            
            stok = DAOFunctions.stokListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL,0,page);
            
        }
        
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);
        request.setAttribute("bilesenid", bilesenid);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("sumagg", sumagg);
        request.getRequestDispatcher("hammaddestok.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String bilesenid    = request.getParameter("bilesenid");
        String bilesenekleid= request.getParameter("bilesenekleid");        
        String message      = null;
        List<Stok> stok     = new ArrayList<Stok>();
        
        int totalrecord = 0;
        int page        = 1;
        int noofpages   = 0;
        int sumagg      = 0;
        
        if(bilesenekleid.equals("1")) {
            String result   = ""; 
            String iade     = request.getParameter("iade");
            if(iade==null) {
                result = DAOFunctions.stokEkle(
                            request.getParameter("bilesenid"),
                            request.getParameter("miktar"),
                            request.getParameter("irsaliyeno"),
                            request.getParameter("lot"),
                            null, /* gkrno*/
                            request.getParameter("tarih"),
                            request.getParameter("not")
                );
            }
            else {
                result = DAOFunctions.stokDus(
                                request.getParameter("bilesenid"),
                                request.getParameter("miktar"),
                                request.getParameter("irsaliyeno"),
                                request.getParameter("lot"),
                                null, /* gkrno*/
                                request.getParameter("not")
                    );
            }
            message = result.equals("0")?"Hata oluştu!..":"";
        }
        
        // PAGING
        totalrecord = DAOFunctions.recordCount("stokbilesen"," where bilesenid= " +  Integer.valueOf(bilesenid));
        sumagg      = DAOFunctions.recordAgg("stokmiktar", "sum", "miktar", " where bilesenid= " +  Integer.valueOf(bilesenid));
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));        
        noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        // PAGING
        stok = DAOFunctions.stokListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL,Integer.valueOf(bilesenid),page);
        
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        hammadde = DAOFunctions.bilesenListeGetirTum( null, 0 );
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);
        request.setAttribute("bilesenid", bilesenid);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("sumagg", sumagg);
        request.setAttribute("message", message);
        request.getRequestDispatcher("hammaddestok.jsp").forward(request, response);
        
    }

}
