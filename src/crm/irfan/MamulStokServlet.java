package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;
import crm.irfan.entity.Mamul;
import crm.irfan.entity.Stok;

public class MamulStokServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MamulStokServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Mamul> mamul = new ArrayList<Mamul>();
        mamul = DAOFunctions.mamulListeGetir(0);
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        List<Stok> stok = new ArrayList<Stok>();
        String stoklisteid  = request.getParameter("stoklisteid");
        String mamulid      = request.getParameter("mamulid");
        
        int totalrecord = 0;
        int page        = 1;
        int noofpages   = 0;
        int sumagg      = 0;
        
        if(stoklisteid!=null && Integer.valueOf(stoklisteid)==1) {
            
            // PAGING
            //totalrecord = DAOFunctions.recordCount("stok"," where id= " + stoklisteid);
            totalrecord = DAOFunctions.recordAgg("stok", "count", "*", " where bilesenid= " + Integer.valueOf(mamulid));
            sumagg      = DAOFunctions.recordAgg("stokmiktar", "sum", "miktar", " where bilesenid= " + Integer.valueOf(mamulid));
            if(request.getParameter("page") != null)
                page    = Integer.parseInt(request.getParameter("page"));            
            noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            // PAGING
            stok        = DAOFunctions.stokListeGetirTum(BilesenTip.MAMUL, Integer.valueOf(mamulid), page);
            
            System.out.println(totalrecord);
            System.out.println(noofpages);
        }        
        
        request.setAttribute("mamul", mamul);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);        
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("sumagg", sumagg);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("mamulid", mamulid);
        request.setAttribute("excelsql", mamulid);
        
        request.getRequestDispatcher("mamulstok.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        //Integer stoklisteid = Integer.valueOf(request.getParameter("stoklisteid"));
        String mamulid      = request.getParameter("mamulid");
        Integer mamulekleid = Integer.valueOf(request.getParameter("mamulekleid"));
        
        String message = null;
        List<Stok> stok = new ArrayList<Stok>();
        
        int totalrecord = 0;
        int page        = 1;
        int noofpages   = 0;
        int sumagg      = 0;
        
        if(mamulekleid==1) {
            String result = ""; 
            String iade =  request.getParameter("iade");
            if(iade==null) {
                result = DAOFunctions.stokEkle(
                            request.getParameter("mamulid"),
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
                                request.getParameter("mamulid"),
                                request.getParameter("miktar"),
                                null, /* gkrno*/
                                request.getParameter("irsaliyeno"),
                                request.getParameter("lot"),
                                request.getParameter("not")
                    );
            }
            message = (result=="0")?"Hata oluştu!..":"";
        }
        
        //if(stoklisteid!=null && stoklisteid==1) {
        
        totalrecord = DAOFunctions.recordAgg("stok", "count", "*", " where bilesenid= " +  Integer.valueOf(mamulid));
        sumagg      = DAOFunctions.recordAgg("stokmiktar", "sum", "miktar", " where bilesenid= " +  Integer.valueOf(mamulid));
        if(request.getParameter("page") != null)
            page    = Integer.parseInt(request.getParameter("page"));
        noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        stok        = DAOFunctions.stokListeGetirTum(BilesenTip.MAMUL, Integer.valueOf(mamulid), page);
        //}
        
        List<Mamul> mamul = new ArrayList<Mamul>();
        mamul = DAOFunctions.mamulListeGetir(0);

        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
                
        request.setAttribute("mamul", mamul);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);
        request.setAttribute("message", message);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("sumagg", sumagg);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("mamulid", mamulid);
        request.setAttribute("excelsql", mamulid);
        
        request.getRequestDispatcher("mamulstok.jsp").forward(request, response);
    }

}
