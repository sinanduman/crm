package crm.irfan;

import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/firma")
public class FirmaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public FirmaServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost( request,  response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Boolean ajaxIncluded= false;
        String message      = "";
        
        String islemid = request.getParameter("islemid");
        if(islemid != null && islemid != "" ) {
            String result = "";
            switch (Integer.valueOf(islemid)) {
                case 1:
                    ajaxIncluded = true;
                    result = DAOFunctions.firmaGuncelle(
                                    new String(request.getParameter("id").getBytes("UTF-8")),
                                    new String(request.getParameter("ad").getBytes("UTF-8")),
                                    new String(request.getParameter("tel").getBytes("UTF-8")),
                                    new String(request.getParameter("adres").getBytes("UTF-8"))
                                    );
                    break;
                case 3:
                    ajaxIncluded = true;
                    result = DAOFunctions.firmaSil(new String(request.getParameter("id").getBytes("UTF-8")));
                    break;
            }
            PrintWriter out = response.getWriter();
            out.print(result);
        }
        else {
            if(request.getParameter("firma_ad")!=null && request.getParameter("firma_tel")!=null && request.getParameter("firma_adres")!=null) {
                message = DAOFunctions.firmaEkle(
                            new String(request.getParameter("firma_ad").getBytes("UTF-8")),
                            new String(request.getParameter("firma_tel").getBytes("UTF-8")),
                            new String(request.getParameter("firma_adres").getBytes("UTF-8"))
                            );
            }
            
        }
        if(!ajaxIncluded) {
            int totalrecord = DAOFunctions.recordCount("firma","");
            int page = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            
            int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            
            List<Firma> firma = new ArrayList<Firma>();
            firma = DAOFunctions.firmaListeGetirTum(page);
            
            request.setAttribute("firma", firma);
            request.setAttribute("message", message);
            request.setAttribute("totalrecord", totalrecord);
            request.setAttribute("noofpages", noofpages);
            request.setAttribute("currentpage", page);
            request.getRequestDispatcher("firma.jsp").forward(request, response);            
        }
    }
    
}
