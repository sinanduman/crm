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

import crm.irfan.entity.Firma;

public class FirmaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public FirmaServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = firmaListeGetirTum();
        for (Firma f : firma) {
            System.out.println(f.getId().toString() + " : " + f.getAd() + " : " + f.getTelefon() + " : " + f.getAdres());
        }
        
        request.setAttribute("firma", firma);
        request.getRequestDispatcher("firma.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        List<Firma> firma = new ArrayList<Firma>();
        firma = firmaEkle(
                new String(request.getParameter("firma_ad").getBytes("UTF-8")),
                new String(request.getParameter("firma_tel").getBytes("UTF-8")),
                new String(request.getParameter("firma_adres").getBytes("UTF-8"))
                );        
        request.setAttribute("firma", firma);
        request.getRequestDispatcher("firma.jsp").forward(request, response);
    }
    
    protected List<Firma> firmaListeGetirTum() {
        List<Firma> temp = new ArrayList<Firma>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select * from firma order by ad ";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Firma(rs.getInt("id"), rs.getString("ad"), rs.getString("telefon"), rs
                        .getString("adres")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }
    
    protected List<Firma> firmaEkle(String ad, String telefon, String adres) {
        List<Firma> temp = new ArrayList<Firma>();
        Connection conn = ConnectionManager.getConnection();
        
        System.out.println("ad " + ad);
        System.out.println("telefon " + telefon);
        System.out.println("adres " + adres);
        
        String insertQuery = "insert into firma (ad, telefon, adres) values (?,?, ?) ";
        String searchQuery = "select * from firma order by ad ";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, ad);
            pstmt.setString(2, telefon);
            pstmt.setString(3, adres);
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Firma(rs.getInt("id"), rs.getString("ad"), rs.getString("telefon"), rs
                        .getString("adres")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }
    
}
