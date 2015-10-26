package crm.irfan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.StokRapor;

@WebServlet("/excelstok")
public class ExcelStokServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExcelStokServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String exportToExcel = request.getParameter("exportToExcel");
	    doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
        
        String excelegonder = request.getParameter("excelegonder");
        String exceltarih   = request.getParameter("exceltarih");
        String exceldetay   = request.getParameter("exceldetay");
        String exceldetayid = request.getParameter("exceldetayid");
        String excelstoktip = request.getParameter("excelstoktip");
        String excelsql     = request.getParameter("excelsql");
        String tablename    = request.getParameter("tablename");
        
        
        System.out.println("excelsql: " + excelsql);
        
        if(excelegonder!=null && excelegonder.equals("1")) {
            String prefix       = "";
            String prefixDetay  = "";
            
            List<StokRapor> stokrapor = new ArrayList<StokRapor>();            
            stokrapor   = DAOFunctions.stokBilesenRapor(tablename, excelsql, 0);
            
            if(Util.isNotEmptyOrNull(exceldetay) && Util.isNotEmptyOrNull(exceldetayid) && Util.isNumeric(exceldetayid)) {
                stokrapor   = DAOFunctions.stokBilesenDetayRapor(tablename, excelsql, 0, Integer.valueOf(exceldetayid));
                prefixDetay = "_detay";
            }
            
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            
            int cnt     = 0;
            String birim= (excelstoktip.equals("1"))?" (Kg)":" (Adet)";
            if(excelstoktip.equals("1")) {
                birim   = " (Kg)";
            }
            Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
            
            if( Util.isNotEmptyOrNull(exceldetayid) && Util.isNumeric(exceldetayid)) {
                                
                data.put(++cnt, new Object[] {
                                "Bileşen Kodu",
                                "Bileşen Adı",
                                "Firma",
                                "Tarih",
                                "İşlem",
                                "Değişim",
                                "Kalan Miktar" + birim
                                }
                );
            }
            else {
                data.put(++cnt, new Object[] {
                                "Bileşen Kodu",
                                "Bileşen Adı",
                                "Firma",
                                "Tarih",
                                "Kalan Miktar" + birim
                                }
                );
            }
            for(StokRapor sr: stokrapor) {
                if( Util.isNotEmptyOrNull(exceldetayid) && Util.isNumeric(exceldetayid)) {
                    data.put(++cnt, new Object[] {
                                    sr.getBilesenkod(),
                                    sr.getBilesenad(),
                                    sr.getFirmaad(),
                                    sr.getTarih(),
                                    (sr.getIslemyonu()==0)?" + ":" - ",
                                    //((sr.getBilesentipid()==BilesenTip.HAMMADDE.value()) ? Util.Round(sr.getMiktar(),1000) : sr.getMiktar() ),
                                    ((sr.getBilesentipid()==BilesenTip.HAMMADDE.value()) ? Util.Round(sr.getMiktar()/1000.0,2.0) : sr.getMiktar() ),
                                    ((sr.getBilesentipid()==BilesenTip.HAMMADDE.value()) ? Util.Round(sr.getKalan()/1000.0,2.0) : sr.getKalan() )
                                    });
                }
                else {
                    data.put(++cnt, new Object[] {
                                    sr.getBilesenkod(),
                                    sr.getBilesenad(),
                                    sr.getFirmaad(),
                                    sr.getTarih(),
                                    ((sr.getBilesentipid()==BilesenTip.HAMMADDE.value()) ? Util.Round(sr.getMiktar(),1000) : sr.getMiktar() ),
                                    });
                }
                
                if(cnt==2) {
                    System.out.println("tipid" + sr.getBilesentipid());
                    int i = sr.getBilesentipid();
                    switch (i) {
                        case 1:
                            prefix = "hammadde";
                            break;
                        case 2:
                            prefix = "yarimamul";
                            break;
                        case 3:
                            prefix = "mamul";
                            break;
                    }
                }
            }

            Set<Integer> keyset = data.keySet();
            int rownum = 0;
            for (Integer key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);                    
                    if(obj instanceof Date) {
                        cell.setCellValue((Date)obj);
                    }
                    else if(obj instanceof Boolean) {
                        cell.setCellValue((Boolean)obj);
                    }
                    else if(obj instanceof String) {
                        cell.setCellValue((String)obj);
                    }                        
                    else if(obj instanceof Double) {
                        cell.setCellValue((Double)obj);
                    }                        
                    else if(obj instanceof Integer) {
                        cell.setCellValue((Integer)obj);
                    }
                    if(key == 1) {
                        CellStyle style = workbook.createCellStyle();
                        XSSFFont font   = (XSSFFont) workbook.createFont();
                        font.setColor(HSSFColor.WHITE.index);
                        style.setFont(font);
                        style.setFillBackgroundColor( HSSFColor.RED.index );
                        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                        cell.setCellStyle(style);
                    }
                }
            }
            
            System.out.println("Excel written successfully..");
            
            // write it as an excel attachment
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            workbook.write(outByteStream);
            byte [] outArray = outByteStream.toByteArray();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setContentLength(outArray.length);
            response.setHeader("Content-Disposition", "attachment; filename="+ "excel_" + prefix + prefixDetay + "_"+ exceltarih + ".xls");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            outStream.close();
        }
        else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
	}

}
