package crm.irfan;

import crm.irfan.entity.YariMamul;

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

public class YariMamulServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public YariMamulServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<YariMamul> yarimamul = new ArrayList<YariMamul>();
        yarimamul = yarimamulListeGetirTum();
        for (YariMamul y : yarimamul) {
            System.out.println(y.getId().toString() + " : " + y.getKod() + " : " + y.getAd());
        }

        request.setAttribute("yarimamul", yarimamul);
        request.getRequestDispatcher("yarimamul.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<YariMamul> yarimamul = new ArrayList<YariMamul>();
        yarimamul = yarimamulEkle(
                new String(request.getParameter("yarimamulkod").getBytes("UTF-8")), new String(
                request.getParameter("yarimamulad").getBytes("UTF-8")));

        request.setAttribute("yarimamul", yarimamul);
        request.getRequestDispatcher("yarimamul.jsp").forward(request, response);

    }

    protected static List<YariMamul> yarimamulListeGetirTum() {
        List<YariMamul> temp = new ArrayList<YariMamul>();
        Connection conn = ConnectionManager.getConnection();

        String searchQuery = "select * from yarimamul order by ad";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new YariMamul(rs.getInt("id"), rs.getString("kod"), rs.getString("ad")));
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

    protected List<YariMamul> yarimamulEkle(String kod, String ad) {
        List<YariMamul> temp = new ArrayList<YariMamul>();
        Connection conn = ConnectionManager.getConnection();

        System.out.println("kod " + kod);
        System.out.println("ad " + ad);

        String insertQuery = "insert into yarimamul (kod, ad) values (?,?) ";
        String searchQuery = "select * from yarimamul order by ad";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, kod);
            pstmt.setString(2, ad);

            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                temp.add(new YariMamul(rs.getInt("id"), rs.getString("kod"), rs.getString("ad")));
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
