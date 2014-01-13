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
import crm.irfan.entity.Birim;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Irsaliye;
import crm.irfan.entity.IrsaliyeBilesen;

public class IrsaliyeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public IrsaliyeServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Birim> birim = new ArrayList<Birim>();
		birim = DAOFunctions.birimListeGetirTum();
		
		List<Firma> firma = new ArrayList<Firma>();
		firma = DAOFunctions.firmaListeGetirTum();
		
		List<Bilesen> mamul = new ArrayList<Bilesen>();
		mamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.MAMUL);
		
		List<Irsaliye> irsaliye = new ArrayList<Irsaliye>();
		irsaliye = DAOFunctions.irsaliyeListeGetirTum();
		
		List<IrsaliyeBilesen> irsaliyebilesen = new ArrayList<IrsaliyeBilesen>();
		irsaliyebilesen = DAOFunctions.irsaliyeBilesenListeGetirTum();
		
		request.setAttribute("birim", birim);
		request.setAttribute("firma", firma);
		request.setAttribute("mamul", mamul);
		request.setAttribute("irsaliye", irsaliye);
		request.setAttribute("irsaliyebilesen", irsaliyebilesen);
		
		request.getRequestDispatcher("irsaliye.jsp").forward(request, response);
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
				case 3:
					result = DAOFunctions.IrsaliyeSil(request.getParameter("irsaliyeid"));
					break;
			}
			PrintWriter out = response.getWriter();
			out.print(result);
		}
		else {
			int newIrsaliyeId = DAOFunctions.irsaliyeEkle();

			/* if Error occured */
			if(newIrsaliyeId == -1) {
				System.out.println("Hata Olustu!..");
			}
			else {
				System.out.println(newIrsaliyeId);
			}

			int mamulSayisi = Integer.valueOf(request.getParameter("irsaliye_length"));
			for(int i=0; i< mamulSayisi; i++) {
				String mamulid= request.getParameter("mamul_" + i);
				String miktar   = request.getParameter("miktar_" + i);
				
				DAOFunctions.irsaliyeBilesenEkle(
									newIrsaliyeId,
									Integer.valueOf(mamulid),
									Integer.valueOf(miktar)
				);
			}
		
			List<Birim> birim = new ArrayList<Birim>();
			birim = DAOFunctions.birimListeGetirTum();
			
			List<Firma> firma = new ArrayList<Firma>();
			firma = DAOFunctions.firmaListeGetirTum();
			
			List<Bilesen> mamul = new ArrayList<Bilesen>();
			mamul = DAOFunctions.bilesenListeGetirTum(BilesenTip.MAMUL);
			
			List<Irsaliye> irsaliye = new ArrayList<Irsaliye>();
			irsaliye = DAOFunctions.irsaliyeListeGetirTum();
			
			List<IrsaliyeBilesen> irsaliyebilesen = new ArrayList<IrsaliyeBilesen>();
			irsaliyebilesen = DAOFunctions.irsaliyeBilesenListeGetirTum();
			
			request.setAttribute("birim", birim);
			request.setAttribute("firma", firma);
			request.setAttribute("mamul", mamul);
			request.setAttribute("irsaliye", irsaliye);
			request.setAttribute("irsaliyebilesen", irsaliyebilesen);
			
			request.getRequestDispatcher("irsaliye.jsp").forward(request, response);
		
		}
		
	}
}