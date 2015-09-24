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

import crm.irfan.entity.IrsaliyeBilesen;
import crm.irfan.entity.IrsaliyeTip;

public class ExcelSevkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExcelSevkServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setCharacterEncoding("UTF-8");
        
        String excelegonder     = request.getParameter("excelegonder");
        String excelirsaliyeid  = request.getParameter("excelirsaliyeid");
        String excelfirmaid     = request.getParameter("excelfirmaid");
        String excelmamulid     = request.getParameter("excelmamulid");
        String exceltarih       = request.getParameter("exceltarih");
        String excelsql         = request.getParameter("excelsql");
        
        excelirsaliyeid = (Util.isNotEmptyOrNull(excelirsaliyeid))?excelirsaliyeid:null;
        excelfirmaid    = (Util.isNotEmptyOrNull(excelfirmaid))?excelfirmaid:null;
        exceltarih      = (Util.isNotEmptyOrNull(exceltarih))?exceltarih:null;
        excelmamulid    = (Util.isNotEmptyOrNull(excelmamulid))?excelmamulid:null;

        System.out.println("excelsql: " + excelsql);
        
        if(excelegonder!=null && excelegonder.equals("1")) {
            
            List<IrsaliyeBilesen> irsaliyebilesen = new ArrayList<IrsaliyeBilesen>();
            
            irsaliyebilesen = DAOFunctions.irsaliyeBilesenListeTum(IrsaliyeTip.ONAYLANDI, 1, excelirsaliyeid, excelfirmaid, exceltarih, 0, excelmamulid);
            
            Workbook workbook = new XSSFWorkbook();
            //Sheet sheeti    = workbook.createSheet("Irsaliye");
            Sheet sheetib   = workbook.createSheet("IrsaliyeBilesen");
            //int cnti        = 0;
            int cntib       = 0;
            Map<Integer, Object[]> dataib   = new TreeMap<Integer, Object[]>();
            
            dataib.put(++cntib, new Object[] {
                            "Gönderim Tarihi",
                            "İrsaliye No",
                            "Mamül Adı",
                            "Mamül Kodu",
                            "Firma",
                            "GKR.No",
                            "Miktarı"
                            }
            );
            
            
            for(IrsaliyeBilesen sr: irsaliyebilesen) {
                
                dataib.put(++cntib, new Object[] {
                                Util.getTarih(sr.getGonderimtarihi()),
                                sr.getIrsaliyeno(),
                                sr.getMamulad(),
                                sr.getMamulkod(),
                                sr.getFirmaad(),
                                sr.getGkrno(),
                                sr.getMiktar()
                                });                
            }

            Set<Integer> keysetib = dataib.keySet();
            int rownumib = 0;
            for (Integer key : keysetib) {
                Row row = sheetib.createRow(rownumib++);
                Object [] objArr = dataib.get(key);
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
            response.setHeader("Content-Disposition", "attachment; filename="+ "excel_sevk_" + (new Date()).toString()  + ".xls");
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
