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
import crm.irfan.entity.Birim;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Hammadde;
import crm.irfan.entity.YariMamul;

public class MamulServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public MamulServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        hammadde = DAOFunctions.bilesenListeGetirTum(BilesenTip.HAMMADDE);
        
        List<Bilesen> yarimamul = new ArrayList<Bilesen>();
        yarimamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.YARIMAMUL);
        
        List<Bilesen> mamul = new ArrayList<Bilesen>();
        mamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.MAMUL);
        
        request.setAttribute("birim", birim);
        request.setAttribute("firma", firma);
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("yarimamul", yarimamul);
        request.setAttribute("mamul", mamul);
        
        request.getRequestDispatcher("mamul.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        int newMamulID = DAOFunctions.mamulEkle(
                new String(request.getParameter("mamulad").getBytes("UTF-8")),
                new String(request.getParameter("mamulkod").getBytes("UTF-8")),
                new String(request.getParameter("mamulcevrim").getBytes("UTF-8")),
                new String(request.getParameter("mamulfirma").getBytes("UTF-8"))
                );

        /* if Error occured */
        if(newMamulID == -1) {
            System.out.println("Hata Olustu!..");
        }
        else {
            System.out.println(newMamulID);
        }
        int bilesenSayisi = Integer.valueOf(request.getParameter("bilesen_length"));
        int bilesenResult = -1;
        for(int i=0; i< bilesenSayisi; i++) {
            String bilesen  = request.getParameter("bilesen_" + i);
            String uretimtip= request.getParameter("uretimtip_" + i);            
            String birim    = request.getParameter("birim_" + i);
            String miktar   = request.getParameter("miktar_" + i);
            
            bilesenResult = DAOFunctions.mamulBilesenEkle(
                    newMamulID,
                    Integer.valueOf(bilesen),
                    Integer.valueOf(uretimtip),                    
                    Integer.valueOf(birim),
                    Integer.valueOf(miktar)
            );
        }
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        hammadde = DAOFunctions.bilesenListeGetirTum(BilesenTip.HAMMADDE);
        
        List<Bilesen> yarimamul = new ArrayList<Bilesen>();
        yarimamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.YARIMAMUL);
        
        List<Bilesen> mamul = new ArrayList<Bilesen>();
        mamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.MAMUL);
        
        request.setAttribute("birim", birim);
        request.setAttribute("firma", firma);
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("yarimamul", yarimamul);
        request.setAttribute("mamul", mamul);
        
        request.getRequestDispatcher("mamul.jsp").forward(request, response);
        
    }
    
}
