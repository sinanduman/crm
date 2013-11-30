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

import crm.irfan.entity.Makina;

public class MakinaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MakinaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Makina> makina = new ArrayList<Makina>();
        makina = makinaListeGetirTum();
        for (Makina m : makina) {
            System.out.println(m.getId().toString() + " : " + m.getAd() + " : " + m.getKod());
        }

        request.setAttribute("makina", makina);
        request.getRequestDispatcher("makina.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Makina> makina = new ArrayList<Makina>();
        makina = makinaEkle(
                new String(request.getParameter("makina_ad").getBytes("UTF-8")),
                new String(request.getParameter("makina_kod").getBytes("UTF-8"))
                );
        request.setAttribute("makina", makina);
        request.getRequestDispatcher("makina.jsp").forward(request, response);

    }

    protected static List<Makina> makinaListeGetirTum() {
        List<Makina> temp = new ArrayList<Makina>();
        Connection conn = ConnectionManager.getConnection();

        String searchQuery = "select * from makina order by ad";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Makina(rs.getInt("id"), rs.getString("ad"), rs.getString("kod")));
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

    protected List<Makina> makinaEkle(String ad, String kod) {
        List<Makina> temp = new ArrayList<Makina>();
        Connection conn = ConnectionManager.getConnection();

        System.out.println("ad " + ad);
        System.out.println("kod " + kod);

        String insertQuery = "insert into makina (ad, kod) values (?, ?) ";
        String searchQuery = "select * from makina order by ad";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, ad);
            pstmt.setString(2, kod);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                temp.add(new Makina(rs.getInt("id"), rs.getString("ad"),rs.getString("kod")));
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
