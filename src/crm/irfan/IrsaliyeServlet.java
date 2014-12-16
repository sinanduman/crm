package crm.irfan;

import crm.irfan.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class IrsaliyeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public IrsaliyeServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
					IOException {		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String message        = "";
		Boolean ajaxInAction  = false;
		
		if(request.getParameter("islemid")!=null) {
			Integer islemid = Integer.valueOf(request.getParameter("islemid"));
			/* with ajax */
			if (islemid == 3) {
			    ajaxInAction  = true;
				String result = DAOFunctions.IrsaliyeSil(request.getParameter("irsaliyeid"));
				PrintWriter out = response.getWriter();
				out.print(result);
			}
			/* with ajax */
			else if (islemid == 4) {
			    ajaxInAction  = true;
				String result = DAOFunctions.IrsaliyeOnayla(request.getParameter("irsaliyeid"));
				PrintWriter out = response.getWriter();
				out.print(result);
			}
			else {
			    if(Genel.LOGMOD == LogMod.DEBUG) {
			        System.out.println("islemid :" + islemid);
			    }
				if (islemid == 1) {
					int irsaliyeid         = Integer.valueOf(request.getParameter("irsaliyeid"));
					int irsaliyebilesenid  = Integer.valueOf(request.getParameter("irsaliyebilesenid"));
					int mamulid            = Integer.valueOf(request.getParameter("mamulid"));
					int gkrno              = Integer.valueOf(request.getParameter("gkrno"));
					int miktar             = Integer.valueOf(request.getParameter("miktar"));
					String not             = request.getParameter("not");
					
					DAOFunctions.irsaliyeBilesenGuncelle(irsaliyeid, irsaliyebilesenid, mamulid, gkrno, miktar, not );
				}
				else if (islemid == 2) {
					DAOFunctions.IrsaliyeKapat(request.getParameter("irsaliyeid"));
				}			
				else {				
					int irsaliyeid = Integer.valueOf(request.getParameter("irsaliyeid"));
					System.out.println("irsaliyeid :" + irsaliyeid);
					/* 0:Insert */
					if (0 == irsaliyeid) {
						String irsaliyeno = request.getParameter("irsaliyeno");
						int newIrsaliyeId = DAOFunctions.irsaliyeEkle(irsaliyeno);
						
						/* if Error occured */
						if (newIrsaliyeId == -1) {
							System.out.println("Hata Olustu!..");
						}
						else {
							irsaliyeid = newIrsaliyeId;
						}
					}
					String mamulid     = request.getParameter("mamulid");
					String gkrno       = request.getParameter("gkrno");
					String miktar      = request.getParameter("miktar");
					String not         = request.getParameter("not");
					String irsaliyeno  = request.getParameter("irsaliyeno");
					
					message = DAOFunctions.irsaliyeBilesenEkle(
									irsaliyeid,
									irsaliyeno,
									Integer.valueOf(mamulid),
									Integer.valueOf(gkrno),
									Integer.valueOf(miktar),								
									irsaliyeno  + " İrsaliye No. " +not);
				}
			}
		}
		if(!ajaxInAction) {
		    List<Birim> birim = new ArrayList<Birim>();
	        birim = DAOFunctions.birimListeGetirTum();
	        
	        List<Firma> firma = new ArrayList<Firma>();
	        firma = DAOFunctions.firmaListeGetirTum(0);
	        	        
	        List<Stok> stok = new ArrayList<Stok>();
	        stok = DAOFunctions.stokMamulListeGetirTum(null);
	        
	        List<Irsaliye> irsaliye = new ArrayList<Irsaliye>();
	        irsaliye = DAOFunctions.irsaliyeListeGetirTum(IrsaliyeTip.OPEN, 0, null, null, null);
	        
	        List<IrsaliyeBilesen> irsaliyebilesenopen = new ArrayList<IrsaliyeBilesen>();
	        irsaliyebilesenopen = DAOFunctions.irsaliyeBilesenListeGetirTum(IrsaliyeTip.OPEN, 0, null, null, null);
	        
	        List<IrsaliyeBilesen> irsaliyebilesencompleted = new ArrayList<IrsaliyeBilesen>();
	        irsaliyebilesencompleted = DAOFunctions.irsaliyeBilesenListeGetirTum(IrsaliyeTip.COMPLETED, 0, null, null, null);
	        
	        request.setAttribute("birim", birim);
	        request.setAttribute("firma", firma);
	        request.setAttribute("stok", stok);
	        request.setAttribute("message", message);
	        request.setAttribute("irsaliye", irsaliye);
	        request.setAttribute("irsaliyebilesenopen", irsaliyebilesenopen);
	        request.setAttribute("irsaliyebilesencompleted", irsaliyebilesencompleted);
	        
	        request.getRequestDispatcher("irsaliye.jsp").forward(request, response);		    
		}
		
	}
}