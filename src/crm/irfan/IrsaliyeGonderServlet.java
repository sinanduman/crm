package crm.irfan;

import crm.irfan.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IrsaliyeGonderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public IrsaliyeGonderServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        // PAGING
        int totalrecord = DAOFunctions.recordCount("irsaliye"," where onaylandi=1 ");
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));        
        int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        // PAGING
        
        List<Irsaliye> irsaliye = new ArrayList<Irsaliye>();
        irsaliye = DAOFunctions.irsaliyeListeGetirTum(IrsaliyeTip.ONAYLANDI, page, null, null, null);
        
        List<IrsaliyeBilesen> irsaliyebilesenonaylandi = new ArrayList<IrsaliyeBilesen>();
        irsaliyebilesenonaylandi = DAOFunctions.irsaliyeBilesenListeGetirTum(IrsaliyeTip.ONAYLANDI, 1, null, null, null, page);
        
        request.setAttribute("birim", birim);
        request.setAttribute("firma", firma);
        request.setAttribute("irsaliye", irsaliye);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("irsaliyebilesenonaylandi", irsaliyebilesenonaylandi);
        
        request.getRequestDispatcher("irsaliyegonder.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                    IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        // PAGING
        int totalrecord = DAOFunctions.recordCount("irsaliye"," where onaylandi=1 ");
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));        
        int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        // PAGING
        
        List<Irsaliye> irsaliye = new ArrayList<Irsaliye>();
        irsaliye = DAOFunctions.irsaliyeListeGetirTum(IrsaliyeTip.ONAYLANDI, page, null, null, null);
        
        List<IrsaliyeBilesen> irsaliyebilesenonaylandi = new ArrayList<IrsaliyeBilesen>();
        irsaliyebilesenonaylandi = DAOFunctions.irsaliyeBilesenListeGetirTum(IrsaliyeTip.ONAYLANDI, 1, null, null, null, page);
        
        request.setAttribute("birim", birim);
        request.setAttribute("firma", firma);
        request.setAttribute("irsaliye", irsaliye);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("irsaliyebilesenonaylandi", irsaliyebilesenonaylandi);
        
        request.getRequestDispatcher("irsaliye.jsp").forward(request, response);

    }
}