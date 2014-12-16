package crm.irfan;

import crm.irfan.entity.Calisan;
import crm.irfan.entity.Genel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CalisanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CalisanServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

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
                    result = DAOFunctions.calisanGuncelle(
                                    new String(request.getParameter("id").getBytes("UTF-8")),
                                    new String(request.getParameter("ad").getBytes("UTF-8")),
                                    new String(request.getParameter("soyad").getBytes("UTF-8")),
                                    new String(request.getParameter("gorev").getBytes("UTF-8"))
                                    );
                    break;
                case 3:
                    ajaxIncluded = true;
                    result = DAOFunctions.calisanSil(new String(request.getParameter("id").getBytes("UTF-8")));
                    break;
            }
            PrintWriter out = response.getWriter();
            out.print(result);
        }
        else {
            if(request.getParameter("ad")!=null && request.getParameter("soyad")!=null && request.getParameter("gorev")!=null) {
                message = DAOFunctions.calisanEkle(
                                new String(request.getParameter("ad").getBytes("UTF-8")).trim(), 
                                new String(request.getParameter("soyad").getBytes("UTF-8")).trim(), 
                                new String(request.getParameter("gorev").getBytes("UTF-8")).trim()
                                );    
            }
            
        }
        if(!ajaxIncluded) {
            
            int totalrecord = DAOFunctions.recordCount("calisan","");
            int page = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            
            int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            
            List<Calisan> calisan = new ArrayList<Calisan>();
            calisan = DAOFunctions.calisanListeGetirTum(page);
            
            request.setAttribute("calisan", calisan);
            request.setAttribute("message", message);
            request.setAttribute("totalrecord", totalrecord);
            request.setAttribute("noofpages", noofpages);
            request.setAttribute("currentpage", page);
            request.getRequestDispatcher("calisan.jsp").forward(request, response);
            
        }
    }
}