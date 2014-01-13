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
import crm.irfan.entity.MamulBilesen;

public class MamulServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public MamulServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        hammadde = DAOFunctions.bilesenListeGetirTum(BilesenTip.HAMMADDE);
        
        List<Bilesen> yarimamul = new ArrayList<Bilesen>();
        yarimamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.YARIMAMUL);
        
        List<Bilesen> mamul = new ArrayList<Bilesen>();
        mamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.MAMUL);
        
        List<MamulBilesen> mamulbilesen = new ArrayList<MamulBilesen>();
        mamulbilesen = DAOFunctions.mamulBilesenListeGetirTum();
        
        request.setAttribute("firma", firma);
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("yarimamul", yarimamul);
        request.setAttribute("mamul", mamul);
        request.setAttribute("mamulbilesen", mamulbilesen);
        
        request.getRequestDispatcher("mamul.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String islemid = request.getParameter("islemid");
        if(islemid != null && islemid != "" ) {
            Integer result = 0;
            switch (Integer.valueOf(islemid)) {
                case 1:
                    result = DAOFunctions.bilesenGuncelle(
                                    new String(request.getParameter("bilesenid").getBytes("UTF-8")),
                                    null, /* -- null birimid icin */
                                    new String(request.getParameter("firmaid").getBytes("UTF-8")),
                                    new String(request.getParameter("bilesenkod").getBytes("UTF-8")),
                                    new String(request.getParameter("bilesenad").getBytes("UTF-8")),
                                    new String(request.getParameter("cevrimsuresi").getBytes("UTF-8"))                              
                                    );
                    break;
                case 3:
                    result = DAOFunctions.bilesenSil(new String(request.getParameter("bilesenid").getBytes("UTF-8")));
                    break;
            }
            PrintWriter out = response.getWriter();
            out.print(result);
        }
        else {
            int newMamulId = DAOFunctions.mamulEkle(
                    new String(request.getParameter("mamulad").getBytes("UTF-8")),
                    new String(request.getParameter("mamulkod").getBytes("UTF-8")),
                    new String(request.getParameter("mamulcevrim").getBytes("UTF-8")),
                    new String(request.getParameter("mamulfirma").getBytes("UTF-8"))
                    );
    
            /* if Error occured */
            if(newMamulId == -1) {
                System.out.println("Hata Olustu!..");
            }
            else {
                System.out.println(newMamulId);
            }
            DAOFunctions.mamulUretimTipEkle(
                    newMamulId,
                    request.getParameter("mamuluretimsekli")
            );
            int bilesenSayisi = Integer.valueOf(request.getParameter("bilesen_length"));
            for(int i=0; i< bilesenSayisi; i++) {
                String bilesenid= request.getParameter("bilesenid_" + i);
                String birimid  = request.getParameter("birimid_" + i);
                String miktar   = request.getParameter("miktar_" + i);
                
                DAOFunctions.mamulBilesenEkle(
                        newMamulId,
                        Integer.valueOf(bilesenid),
                        Integer.valueOf(birimid),
                        Integer.valueOf(miktar)
                );
            }
                    
            List<Firma> firma = new ArrayList<Firma>();
            firma = DAOFunctions.firmaListeGetirTum();
            
            List<Bilesen> hammadde = new ArrayList<Bilesen>();
            hammadde = DAOFunctions.bilesenListeGetirTum(BilesenTip.HAMMADDE);
            
            List<Bilesen> yarimamul = new ArrayList<Bilesen>();
            yarimamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.YARIMAMUL);
            
            List<Bilesen> mamul = new ArrayList<Bilesen>();
            mamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.MAMUL);
            
            List<MamulBilesen> mamulbilesen = new ArrayList<MamulBilesen>();
            mamulbilesen = DAOFunctions.mamulBilesenListeGetirTum();
            
            request.setAttribute("firma", firma);
            request.setAttribute("hammadde", hammadde);
            request.setAttribute("yarimamul", yarimamul);
            request.setAttribute("mamul", mamul);
            request.setAttribute("mamulbilesen", mamulbilesen);
            
            request.getRequestDispatcher("mamul.jsp").forward(request, response);
        }
    }
    
}
