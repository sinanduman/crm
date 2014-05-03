package crm.irfan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Genel;
import crm.irfan.entity.Makina;

public class MakinaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MakinaServlet() {
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
                    result = DAOFunctions.makinaGuncelle(
                                    new String(request.getParameter("id").getBytes("UTF-8")),
                                    new String(request.getParameter("ad").getBytes("UTF-8")),
                                    new String(request.getParameter("tip").getBytes("UTF-8"))
                                    );
                    break;
                case 3:
                    ajaxIncluded = true;
                    result = DAOFunctions.makinaSil(new String(request.getParameter("id").getBytes("UTF-8")));
                    break;
            }
            PrintWriter out = response.getWriter();
            out.print(result);
        }
        else {
            if(request.getParameter("makina_ad")!=null && request.getParameter("makina_tip")!=null) {
                message = DAOFunctions.makinaEkle(
                            new String(request.getParameter("makina_ad").getBytes("UTF-8")),
                            new String(request.getParameter("makina_tip").getBytes("UTF-8"))
                            );
            }
        }
        if(!ajaxIncluded) {
            List<Makina> makina = new ArrayList<Makina>();
            
            int totalrecord = DAOFunctions.recordCount("makina","");
            int page = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            makina = DAOFunctions.makinaListeGetirTum(page);
            
            int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);

            request.setAttribute("makina", makina);
            request.setAttribute("message", message);
            request.setAttribute("totalrecord", totalrecord);
            request.setAttribute("currentpage", page);
            request.setAttribute("noofpages", noofpages);
            request.getRequestDispatcher("makina.jsp").forward(request, response);
        }
        
    }

}
