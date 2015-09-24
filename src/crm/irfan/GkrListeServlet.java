package crm.irfan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;
import crm.irfan.entity.Stok;

public class GkrListeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GkrListeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        int totalrecord     = 0;
        int page0           = 1;
        int noofpages       = 0;
        int bilesenid       = 0;
        
        String bas_tarih    = request.getParameter("bas_tarih");
        String bit_tarih    = request.getParameter("bit_tarih");
        String page         = request.getParameter("page");
        String excelsql     = "";
        String filter0      = "";
        String filter1      = "";
        String filter2      = "";
        String andYes       = " AND ";
        
        if(Util.isNotEmptyOrNull(request.getParameter("bilesenid"))) {
            bilesenid   = Integer.parseInt(request.getParameter("bilesenid"));
        }

        List<Bilesen> hammadde = new ArrayList<Bilesen>();
        hammadde = DAOFunctions.bilesenListeGetirTum( BilesenTip.HAMMADDEYARIMAMUL, 0, null );
                
        List<Firma> firma = new ArrayList<Firma>();
        firma = DAOFunctions.firmaListeGetirTum(0);
        
        Map<String,String> param = new HashMap<String, String>();
        param.put("islemyonu", String.valueOf(Genel.IslemYonuGiris));
        param.put("rowperpage", String.valueOf(Genel.ROWPERLONGPAGE));
        
        filter1     = (bilesenid==0) ? "" : " and bilesenid=" + bilesenid;
        
        if(bas_tarih!=null && bit_tarih!=null && !bas_tarih.equals("") && !bit_tarih.equals("") ) {
            filter2 = andYes + 
                            " ( giristarihi BETWEEN '" + Util.date_tr_to_eng(bas_tarih) + 
                            "' AND '" + Util.date_tr_to_eng(bit_tarih) + 
                            "')";
            param.put("bas_tarih", bas_tarih);
            param.put("bit_tarih", bit_tarih);
        }
        
        filter0 = filter1 + filter2;
        excelsql= filter0;
        
        // PAGING        
        totalrecord = DAOFunctions.recordAgg("stokbilesen","count","*"," where bilesentipid IN (1,2) and islemyonu=" + Genel.IslemYonuGiris + filter0);
        if(request.getParameter("page") != null)
            page0   = Integer.parseInt(page);        
        noofpages   = (int) Math.ceil(totalrecord * 1.0 / Genel.ROWPERLONGPAGE);
        // PAGING
        
        List<Stok> stok = new ArrayList<Stok>();
        stok = DAOFunctions.stokListeGetirTum(BilesenTip.HAMMADDEYARIMAMUL, bilesenid, page0, param);
        
        request.setAttribute("hammadde", hammadde);
        request.setAttribute("firma", firma);
        request.setAttribute("stok", stok);
        request.setAttribute("bas_tarih", bas_tarih);
        request.setAttribute("bit_tarih", bit_tarih);
        request.setAttribute("totalrecord", totalrecord);
        request.setAttribute("currentpage", page0);
        request.setAttribute("noofpages", noofpages);
        request.setAttribute("bilesenid", bilesenid);
        request.setAttribute("excelsql", excelsql);
        request.getRequestDispatcher("gkrliste.jsp").forward(request, response);
        
    }

}
