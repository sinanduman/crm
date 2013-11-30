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

import crm.irfan.entity.Birim;
import crm.irfan.entity.Hammadde;

public class HammaddeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HammaddeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        List<Hammadde> hammadde = new ArrayList<Hammadde>();
        hammadde = hammaddeListeGetirTum();
        for (Hammadde h : hammadde) {
            System.out.println(h.getId().toString() +
                    " : " + h.getKod() +
                    " : " + h.getAd() +
                    " : " + h.getBirimid() +
                    " : " + h.getBirimad()
            );
        }

        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();

        request.setAttribute("birim", birim);
        request.setAttribute("hammadde", hammadde);
        request.getRequestDispatcher("hammadde.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Hammadde> hammadde = new ArrayList<Hammadde>();
        hammadde = hammaddeEkle(
                new String(request.getParameter("hamkod").getBytes("UTF-8")),
                new String(request.getParameter("hamad").getBytes("UTF-8")),
                new String(request.getParameter("hambirim").getBytes("UTF-8")),
                new String(request.getParameter("hamfirma").getBytes("UTF-8"))
        );

        List<Birim> birim = new ArrayList<Birim>();
        birim = DAOFunctions.birimListeGetirTum();

        request.setAttribute("birim", birim);
        request.setAttribute("hammadde", hammadde);
        request.getRequestDispatcher("hammadde.jsp").forward(request, response);

    }

    protected static List<Hammadde> hammaddeListeGetirTum() {
        List<Hammadde> temp = new ArrayList<Hammadde>();
        Connection conn = ConnectionManager.getConnection();

        String searchQuery = "select h.*, b.ad birimad, f.ad firmaad "
                + "from hammadde h "
                + "join birim b on h.birimid=b.id "
                + "join firma f on h.firmaid=f.id "
                + "order by h.ad";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Hammadde(
                        rs.getInt("id"),
                        rs.getString("kod"),
                        rs.getString("ad"),
                        rs.getInt("birimid"),
                        rs.getString("birimad"),
                        rs.getInt("firmaid"),
                        rs.getString("firmaad")
                )
                );
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

    protected List<Hammadde> hammaddeEkle(String kod, String ad, String birimid, String firmaid) {
        List<Hammadde> temp = new ArrayList<Hammadde>();
        Connection conn = ConnectionManager.getConnection();

        System.out.println("kod " + kod);
        System.out.println("ad " + ad);
        System.out.println("ad " + ad);
        System.out.println("birim " + birimid);
        System.out.println("firma " + firmaid);

        String insertQuery = "insert into hammadde (kod, ad, adozel, birimid) values (?,?,?) ";
        String searchQuery = "select h.*, b.ad birimad, f.ad firmaad "
                + "from hammadde h "
                + "join birim b on h.birimid=b.id "
                + "join firma f on h.firmaid=f.id "
                + "order by h.ad";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, kod);
            pstmt.setString(2, ad);
            pstmt.setInt(3, Integer.valueOf(birimid));

            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                temp.add(new Hammadde(
                        rs.getInt("id"),
                        rs.getString("kod"),
                        rs.getString("ad"),
                        rs.getInt("birimid"),
                        rs.getString("birimad"),
                        rs.getInt("firmaid"),
                        rs.getString("firmaad")
                )
                );
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
