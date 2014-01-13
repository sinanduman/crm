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
import crm.irfan.entity.Stok;

public class YariMamulStokServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public YariMamulStokServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        
        List<Bilesen> yarimamul = new ArrayList<Bilesen>();
        yarimamul = DAOFunctions.bilesenListeGetirTum( BilesenTip.YARIMAMUL );
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        List<Stok> stok = new ArrayList<Stok>();
        stok = DAOFunctions.stokListeGetirTum();
        
        request.setAttribute("yarimamul", yarimamul);
        request.setAttribute("birim", birim);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);
        request.getRequestDispatcher("yarimamulstok.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Bilesen> yarimamul = new ArrayList<Bilesen>();
        yarimamul = DAOFunctions.bilesenListeGetirTum( BilesenTip.YARIMAMUL );
        
        List<Stok> stok = new ArrayList<Stok>();
        stok = DAOFunctions.stokEkle(
                        BilesenTip.YARIMAMUL,
                        request.getParameter("bilesenid"),
                        request.getParameter("miktar"),
                        request.getParameter("irsaliyeno"),
                        request.getParameter("lot"),
                        request.getParameter("not")
        );

        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        request.setAttribute("yarimamul", yarimamul);
        request.setAttribute("birim", birim);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);
        request.getRequestDispatcher("yarimamulstok.jsp").forward(request, response);
    }

}
