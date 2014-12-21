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
import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;
import crm.irfan.entity.Stok;

public class GkrListeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GkrListeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String islemid  = request.getParameter("islemid");
        String message  = "";
        if(islemid != null && islemid != "" ) {
            switch (Integer.valueOf(islemid)) {
                case 3:
                    message = DAOFunctions.hammaddeStokSil(Integer.valueOf(request.getParameter("stokid")));
                    break;
            }
            PrintWriter out = response.getWriter();
            out.print(message);      
        }
        else {
            String result = DAOFunctions.stokEkle(
                            request.getParameter("bilesenid"),
                            request.getParameter("miktar"),
                            request.getParameter("irsaliyeno"),
                            request.getParameter("lot"),
                            null,
                            request.getParameter("tarih"),
                            null
            );
            message = (result!="0")? result:"";
            
            // PAGING
            int totalrecord = DAOFunctions.recordCount("stokbilesen"," where bilesentipid IN (1,2) ");
            int page = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));        
            int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            // PAGING
            
            List<Stok> stok = new ArrayList<Stok>();
            stok = DAOFunctions.stokListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL,0,page);
            
            List<Bilesen> hammadde = new ArrayList<Bilesen>();
            hammadde = DAOFunctions.bilesenListeGetirTum( null, 0 );
            
            List<Firma> firma = new ArrayList<Firma>();
            firma = DAOFunctions.firmaListeGetirTum(0);
            
            request.setAttribute("hammadde", hammadde);
            request.setAttribute("firma", firma);
            request.setAttribute("stok", stok);
            request.setAttribute("totalrecord", totalrecord);
            request.setAttribute("currentpage", page);
            request.setAttribute("noofpages", noofpages);
            request.setAttribute("message", message);
            request.getRequestDispatcher("hammaddestok.jsp").forward(request, response);
        }
        */
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        //hammadde = DAOFunctions.bilesenListeGetirTum( BilesenTip.HAMMADDE, 0 );
        hammadde = DAOFunctions.bilesenListeGetirTum( null, 0 );
                
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        // PAGING
        int totalrecord = DAOFunctions.recordCount("stokbilesen"," where bilesentipid IN (1,2) ");
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));        
        int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        // PAGING
        
        List<Stok> stok = new ArrayList<Stok>();
        stok = DAOFunctions.stokListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL,0,page);
        
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.getRequestDispatcher("gkrliste.jsp").forward(request, response);
        
    }

}
