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

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;
import crm.irfan.entity.Stok;

@WebServlet("/hammaddestok")
public class HammaddeStokServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HammaddeStokServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        //hammadde = DAOFunctions.bilesenListeGetirTum( BilesenTip.HAMMADDE, 0 );
        hammadde = DAOFunctions.bilesenListeGetirTum(null, 0, null);
                
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
            
            stok        = DAOFunctions.stokListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL, Integer.valueOf(bilesenid), page, null);
            
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
        Boolean ajaxInAction= false;
        
        if(request.getParameter("islemid")!=null) {
            Integer islemid     = Integer.valueOf(request.getParameter("islemid"));
            String tarih        = request.getParameter("tarih");
            String gkrno        = request.getParameter("gkrno");
            String miktar       = request.getParameter("miktar");
            String iade         = request.getParameter("iade");
            String bilesentipid = request.getParameter("bilesentipid");
            /* with ajax */
            if (islemid == 6) {
                ajaxInAction    = true;
                String result   = DAOFunctions.StokKontrol(tarih, gkrno, miktar, iade, bilesentipid);
                PrintWriter out = response.getWriter();
                out.print(result);
            }            
            System.out.println("tarih: " + tarih + ", gkrno: " + gkrno + ", miktar: " + miktar + ", iade: " + iade );
        }
        
        if (!ajaxInAction) {
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
                                    request.getParameter("lot"), /* gkrno*/
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
                                    request.getParameter("lot"), /* gkrno*/
                                    request.getParameter("tarih"),
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
            stok = DAOFunctions.stokListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL, Integer.valueOf(bilesenid), page, null);
            
            List<Bilesen> hammadde = new ArrayList<Bilesen>();
            hammadde = DAOFunctions.bilesenListeGetirTum(null, 0, null);
            
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

}
