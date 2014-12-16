package crm.irfan;

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HammaddeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static long token = 1L;

    public HammaddeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        
        String message = "";        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        
        int totalrecord = DAOFunctions.recordCount("bilesen"," ");
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
        int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        hammadde = DAOFunctions.bilesenListeGetirTum(null,page);
        
        token  = Util.getToken();
        request.setAttribute("token", token);
        request.setAttribute("firma", firma);
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("message", message);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.getRequestDispatcher("hammadde.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String islemid = request.getParameter("islemid");
        String message = "0";
        if(islemid != null && islemid != "" ) {
            
            Integer result = 0;
            switch (Integer.valueOf(islemid)) {
                case 1:
                    message = DAOFunctions.bilesenGuncelle(
                                    new String(request.getParameter("bilesenid").getBytes("UTF-8")),
                                    new String(request.getParameter("birimid").getBytes("UTF-8")),
                                    new String(request.getParameter("firmaid").getBytes("UTF-8")),
                                    new String(request.getParameter("bilesenkod").getBytes("UTF-8")),
                                    new String(request.getParameter("bilesenad").getBytes("UTF-8"))
                                    );
                    break;
                case 3:
                    message = DAOFunctions.bilesenSil(new String(request.getParameter("bilesenid").getBytes("UTF-8")));
                    break;
            }
            PrintWriter out = response.getWriter();
            out.print(result);
        }
        else {
            /* Prevent Resubmit */
            int totalrecord = DAOFunctions.recordCount("bilesen"," ");
            int page = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            
            int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            
            /* Prevent ReSubmit */
            if(Long.valueOf(request.getParameter("token"))==token) {
                message = DAOFunctions.hammaddeEkle(
                        new String(request.getParameter("hamkod").getBytes("UTF-8")),
                        new String(request.getParameter("hamad").getBytes("UTF-8")),
                        new String(request.getParameter("hambirim").getBytes("UTF-8")),
                        new String(request.getParameter("hamfirma").getBytes("UTF-8")),
                        new String(request.getParameter("hamtip").getBytes("UTF-8"))
                );
            }
            else {
                message="-1";
            }
            
            List<Bilesen> hammadde = new ArrayList<Bilesen>();
            hammadde = DAOFunctions.bilesenListeGetirTum(null,page);
    
            List<Firma> firma = new ArrayList<Firma>();
            firma = DAOFunctions.firmaListeGetirTum(0);
            
            token  = Util.getToken();
            request.setAttribute("token", token);
            request.setAttribute("firma", firma);
            request.setAttribute("hammadde", hammadde);
            request.setAttribute("totalrecord", totalrecord);
            request.setAttribute("currentpage", page);
            request.setAttribute("noofpages", noofpages);
            request.setAttribute("message", message);
            request.getRequestDispatcher("hammadde.jsp").forward(request, response);
        }
    }

}
