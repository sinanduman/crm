package crm.irfan;

import crm.irfan.entity.Calisan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalisanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CalisanServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = calisanListeGetirTum();
        for (Calisan c : calisan) {
            System.out.println(c.getId().toString() + " : " + c.getAdsoy()
                    + " : " + c.getGorev());
        }

        request.setAttribute("calisan", calisan);
        request.getRequestDispatcher("calisan.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Calisan> calisan = new ArrayList<Calisan>();
        calisan = calisanEkle(new String(request.getParameter("adsoy")
                .getBytes("UTF-8")), new String(request.getParameter("gorev")
                .getBytes("UTF-8")));

        request.setAttribute("calisan", calisan);
        request.getRequestDispatcher("calisan.jsp").forward(request, response);

    }

    protected List<Calisan> calisanListeGetirTum() {
        List<Calisan> temp = new ArrayList<Calisan>();
        Connection conn = ConnectionManager.getConnection();

        String searchQuery = "select * from calisan order by id ";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Calisan(rs.getInt("id"), rs.getString("adsoy"), rs
                        .getString("gorev")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                //if (conn != null && !conn.isClosed()) {
                //    conn.close();
                //}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    protected List<Calisan> calisanEkle(String adsoy, String gorev) {
        List<Calisan> temp = new ArrayList<Calisan>();
        Connection conn = ConnectionManager.getConnection();

        System.out.println("adsoy " + adsoy);
        System.out.println("gorev " + gorev);

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
                temp.add(new Calisan(rs.getInt("id"), rs.getString("adsoy"), rs
                        .getString("gorev")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                //if (conn != null && !conn.isClosed()) {
                //    conn.close();
                //}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

}
