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
import crm.irfan.entity.Stok;

public class UretimPlanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UretimPlanServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        
        List<Bilesen> bilesen = new ArrayList<Bilesen>();
        bilesen = DAOFunctions.bilesenListeGetirTum( BilesenTip.MAMUL );
        
        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();
        
        List<Stok> stok = new ArrayList<Stok>();
        stok = DAOFunctions.stokListeGetirTum();
        
        request.setAttribute("bilesen", bilesen);
        request.setAttribute("birim", birim);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);
        request.getRequestDispatcher("uretimplan.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Hammadde> hammadde = new ArrayList<Hammadde>();
        hammadde = DAOFunctions.hammaddeEkle(
                new String(request.getParameter("hamkod").getBytes("UTF-8")),
                new String(request.getParameter("hamad").getBytes("UTF-8")),
                new String(request.getParameter("hambirim").getBytes("UTF-8")),
                new String(request.getParameter("hamfirma").getBytes("UTF-8"))
        );

        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum();

        request.setAttribute("hammadde", hammadde);
        request.setAttribute("birim", birim);
        request.setAttribute("firma", firma);
        request.getRequestDispatcher("uretimplan.jsp").forward(request, response);
    }

}
