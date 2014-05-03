package crm.irfan;

import crm.irfan.entity.Calisan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class AjaxUtils
 */
public class AjaxUtils extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjaxUtils() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // Map<String, String> map = new HashMap<String, String>();
        /*
		Map m=request.getParameterMap();
        Set s = m.entrySet();
        Iterator<?> it = s.iterator();

            while(it.hasNext()){

                Map.Entry<String,String[]> entry = (Map.Entry<String,String[]>)it.next();

                String key             = entry.getKey();
                String[] value         = entry.getValue();

                out.println("Key is "+key+"<br>");

                    if(value.length>1){
                        for (int i = 0; i < value.length; i++) {
                        	out.println("<li>" + value[i].toString() + "</li><br>");
                        }
                    }else
                    	out.println("Value is "+value[0].toString()+"<br>");

                    out.println("-------------------<br>");
            }
		*/
        //System.out.println(request.getSession().getAttribute("username"));
        //System.out.println(request.getSession().getAttribute("password"));
        //System.out.println(new String(request.getParameter("username").getBytes("UTF-8")) );
        //System.out.println(new String(request.getParameter("password").getBytes("UTF-8")) );

        out.println(request.getParameter("username"));
        out.println(request.getParameter("password"));

        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = calisanEkle((String) request.getParameter("username"), (String) request.getParameter("password"));

        request.setAttribute("calisan", calisan);
        request.getRequestDispatcher("calisan.jsp").forward(request, response);

        //out.println(request.getSession().getAttribute("username") + " 1");
        //out.println(request.getSession().getAttribute("password"));
        //out.println(request.getSession().getAttribute("password") + " 2");

        // out.print(new
        // String(request.getParameter("username").getBytes("UTF-8")));
        // out.print(new
        // String(request.getParameter("password").getBytes("UTF-8")));
        // out.print(new String("HTML".getBytes("UTF-8")));
        // out.print(new String("JAVA".getBytes("UTF-8")));
        // out.println(new String("AJAX Çalışın Üleynğ".getBytes("UTF-8")));
    }

    protected List<Calisan> calisanEkle(String adsoy, String gorev) {
        List<Calisan> temp = new ArrayList<Calisan>();
        Connection conn = ConnectionManager.getConnection();

        String insertQuery = "insert into calisan (adsoy, gorev) values (?,?) ";
        String searchQuery = "select * from calisan order by id ";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, adsoy);
            pstmt.setString(2, gorev);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                temp.add(new Calisan(
                                rs.getInt("id"), 
                                rs.getString("ad"), 
                                rs.getString("soyad"), 
                                rs.getString("gorev")
                                )
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getStackTrace());
            }
        }
        return temp;
    }

}
