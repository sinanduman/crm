package crm.irfan;

import java.io.IOException;
import java.io.PrintWriter;
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
import crm.irfan.entity.Mamul;
import crm.irfan.entity.MamulBilesen;

public class MamulServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static long token = 1L;
    
    public MamulServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String message  = "";
        Integer mamulid = null;
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        hammadde = DAOFunctions.bilesenListeGetirTum(BilesenTip.HAMMADDE, 0, null);
        
        List<Bilesen> yarimamul = new ArrayList<Bilesen>();
        yarimamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.YARIMAMUL, 0, null);
        
        // PAGING
        int totalrecord = DAOFunctions.recordAgg("mamultum", "COUNT", "*", ((mamulid==null)?"":" where id=" + mamulid));        
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));        
        int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
        // PAGING
        
        if(request.getParameter("mamulidara") != null)
            mamulid = Integer.parseInt(request.getParameter("mamulidara")); 
        
        List<Mamul> mamul   = new ArrayList<Mamul>();
        List<Mamul> mamultum= new ArrayList<Mamul>();
        mamul   = DAOFunctions.mamulListeGetir(mamulid, page);
        mamultum= DAOFunctions.mamulListeGetir(null, 0);
        
        List<MamulBilesen> mamulbilesen = new ArrayList<MamulBilesen>();
        mamulbilesen = DAOFunctions.mamulBilesenListeGetirTum(mamulid);
        
        token  = Util.getToken();
        request.setAttribute("token", token);
        request.setAttribute("firma", firma);
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("yarimamul", yarimamul);
        request.setAttribute("mamul", mamul);
        request.setAttribute("mamultum", mamultum);
        request.setAttribute("message", message);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("mamulbilesen", mamulbilesen);
        
        request.getRequestDispatcher("mamul.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String islemid  = request.getParameter("islemid");
        String message  = "";
        Integer mamulid = null;
        
        if(Util.isNotEmptyOrNull(islemid)) {
            switch (Integer.valueOf(islemid)) {
                case 1:
                    message = DAOFunctions.mamulGuncelle(
                                    new String(request.getParameter("bilesenid").getBytes("UTF-8")),
                                    new String(request.getParameter("firmaid").getBytes("UTF-8")),
                                    new String(request.getParameter("bilesenkod").getBytes("UTF-8")),
                                    new String(request.getParameter("bilesenad").getBytes("UTF-8")),
                                    new String(request.getParameter("figur").getBytes("UTF-8")),
                                    new String(request.getParameter("cevrimsuresi").getBytes("UTF-8"))
                                    );
                    break;
                case 3:
                    message = DAOFunctions.mamulSil(new String(request.getParameter("bilesenid").getBytes("UTF-8")));                    
                    break;
            }
            PrintWriter out = response.getWriter();
            out.print(message);
        }
        else {
            if(Util.isNotEmptyOrNull(request.getParameter("stoklisteid")) && 
                            Integer.valueOf(request.getParameter("stoklisteid"))==1 ) {
                mamulid = Integer.valueOf(request.getParameter("mamulidara"));
            }
            /* Prevent ReSubmit */
            else if(Long.valueOf(request.getParameter("token"))==token) {
                String newMamulId = DAOFunctions.mamulEkle(
                        new String(request.getParameter("mamulad").getBytes("UTF-8")),
                        new String(request.getParameter("mamulkod").getBytes("UTF-8")),
                        new String(request.getParameter("mamulcevrim").getBytes("UTF-8")),
                        new String(request.getParameter("mamulfirma").getBytes("UTF-8")),
                        new String(request.getParameter("mamulfigur").getBytes("UTF-8"))
                        );
        
                /* if Error occured */
                if(!Util.isNumeric(newMamulId)) {
                    message = newMamulId;
                }
                else {
                    message = "0"; //Basarili
                    int bilesenSayisi = Integer.valueOf(request.getParameter("bilesen_length"));
                    for(int i=0; i< bilesenSayisi; i++) {
                        String bilesenid= request.getParameter("bilesenid_" + i);
                        String birimid  = request.getParameter("birimid_" + i);
                        String miktar   = request.getParameter("miktar_" + i);
                        //System.out.println("newMamulId: "+ newMamulId + ", bilesenid: "+ bilesenid);
                        DAOFunctions.mamulBilesenEkle(
                                Integer.valueOf(newMamulId),
                                Integer.valueOf(bilesenid),
                                Integer.valueOf(birimid),
                                Float.valueOf(miktar)
                        );
                    }
                }
            }
            else {
                message="-1";
            }
                    
            List<Firma> firma = new ArrayList<Firma>();
            firma = DAOFunctions.firmaListeGetirTum(0);
            
            List<Bilesen> hammadde = new ArrayList<Bilesen>();
            hammadde = DAOFunctions.bilesenListeGetirTum(BilesenTip.HAMMADDE, 0, null);
            
            List<Bilesen> yarimamul = new ArrayList<Bilesen>();
            yarimamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.YARIMAMUL, 0, null);
            
            // PAGING
            int totalrecord = DAOFunctions.recordAgg("mamultum", "COUNT", "*", ((mamulid==null)?"":" where id=" + mamulid));
            int page = 1;
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));        
            int noofpages = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERPAGE);
            // PAGING
            
            List<Mamul> mamul   = new ArrayList<Mamul>();
            List<Mamul> mamultum= new ArrayList<Mamul>();
            mamul   = DAOFunctions.mamulListeGetir(mamulid, page);
            mamultum= DAOFunctions.mamulListeGetir(null, 0);
            
            List<MamulBilesen> mamulbilesen = new ArrayList<MamulBilesen>();
            mamulbilesen = DAOFunctions.mamulBilesenListeGetirTum(mamulid);
            
            token  = Util.getToken();
            request.setAttribute("token", token);
            request.setAttribute("firma", firma);
            request.setAttribute("hammadde", hammadde);
            request.setAttribute("yarimamul", yarimamul);
            request.setAttribute("mamul", mamul);
            request.setAttribute("mamultum", mamultum);
            request.setAttribute("message", message);
            request.setAttribute("totalrecord", totalrecord);
            request.setAttribute("currentpage", page);
            request.setAttribute("noofpages", noofpages);
            request.setAttribute("mamulbilesen", mamulbilesen);
            
            request.getRequestDispatcher("mamul.jsp").forward(request, response);
        }
    }
    
}
