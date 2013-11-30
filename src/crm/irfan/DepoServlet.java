package crm.irfan;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.irfan.entity.Depo;

public class DepoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DepoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Depo> depo = new ArrayList<Depo>();
        depo = depoListeGetirTum();
        for (Depo y : depo) {
            System.out.println(y.getId().toString() + " : " + y.getAd());
        }

        request.setAttribute("depo", depo);
        request.getRequestDispatcher("depo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Depo> depo = new ArrayList<Depo>();
        depo = depoEkle(new String(request.getParameter("depo_ad").getBytes("UTF-8")));

        request.setAttribute("depo", depo);
        request.getRequestDispatcher("depo.jsp").forward(request, response);

    }

    protected static List<Depo> depoListeGetirTum() {
        List<Depo> temp = new ArrayList<Depo>();
        Connection conn = ConnectionManager.getConnection();

        String searchQuery = "select * from depo order by ad";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Depo(rs.getInt("id"), rs.getString("ad")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    protected List<Depo> depoEkle(String ad) {
        List<Depo> temp = new ArrayList<Depo>();
        Connection conn = ConnectionManager.getConnection();

        System.out.println("ad " + ad);

        String insertQuery = "insert into depo (ad) values (?) ";
        String searchQuery = "select * from depo order by ad";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, ad);

            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                temp.add(new Depo(rs.getInt("id"), rs.getString("ad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

}
