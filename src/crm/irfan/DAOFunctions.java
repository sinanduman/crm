package crm.irfan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Birim;
import crm.irfan.entity.Calisan;
import crm.irfan.entity.Depo;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Hammadde;
import crm.irfan.entity.Makina;
import crm.irfan.entity.Siparis;
import crm.irfan.entity.Stok;
import crm.irfan.entity.YariMamul;

public class DAOFunctions {
    
    protected static List<Birim> birimListeGetirTum() {
        List<Birim> temp = new ArrayList<Birim>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select * from birim order by ad ";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Birim(rs.getInt("id"), rs.getString("ad")));
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
    
    protected static List<Firma> firmaListeGetirTum() {
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
    
    protected static List<Hammadde> __hammaddeListeGetirTum() {
        List<Hammadde> temp = new ArrayList<Hammadde>();
        Connection conn = ConnectionManager.getConnection();
        
        /*
         * String searchQuery = "select h.*, b.ad birimad, f.ad firmaad " +
         * "from hammadde h " + "join birim b on h.birimid=b.id " +
         * "join firma f on h.firmaid=f.id " + "order by h.ad";
         */
        
        BilesenTip bilesen = BilesenTip.HAMMADDE;
        String searchQuery = "select h.*, b.ad birimad, f.ad firmaad " 
                + "from bilesen h "
                + "join birim b on h.birimid=b.id " 
                + "join firma f on h.firmaid=f.id "
                + "where h.bilesentipid= " + bilesen.value() 
                + "order by h.ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Hammadde(rs.getInt("id"), rs.getString("kod"), rs.getString("ad"), rs
                        .getInt("birimid"), rs.getString("birimad"), rs.getInt("firmaid"), rs
                        .getString("firmaad")));
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
    
    protected static List<Hammadde> hammaddeEkle(String kod, String ad, String birimid,
            String firmaid) {
        List<Hammadde> temp = new ArrayList<Hammadde>();
        Connection conn = ConnectionManager.getConnection();
        
        BilesenTip bilesen = BilesenTip.HAMMADDE;
        String insertQuery = "insert into bilesen (kod, ad, birimid, bilesentipid, firmaid) values (?,?,?,?,?) ";
        String searchQuery = "select h.*, b.ad birimad, f.ad firmaad " + "from bilesen h "
                + "join birim b on h.birimid=b.id " + "join firma f on h.firmaid=f.id "
                + "where h.bilesentipid= " + bilesen.value() + "order by h.ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, kod);
            pstmt.setString(2, ad);
            pstmt.setInt(3, Integer.valueOf(birimid));
            pstmt.setInt(4, bilesen.value());
            pstmt.setInt(5, Integer.valueOf(firmaid));
            
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Hammadde(rs.getInt("id"), rs.getString("kod"), rs.getString("ad"), rs
                        .getInt("birimid"), rs.getString("birimad"), rs.getInt("firmaid"), rs
                        .getString("firmaad")));
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
    
    protected static List<YariMamul> __yarimamulListeGetirTum() {
        List<YariMamul> temp = new ArrayList<YariMamul>();
        Connection conn = ConnectionManager.getConnection();
        
        BilesenTip bilesen = BilesenTip.YARIMAMUL;
        String searchQuery = "select h.*, b.ad birimad, f.ad firmaad " + "from bilesen h "
                + "join birim b on h.birimid=b.id " + "join firma f on h.firmaid=f.id "
                + "where h.bilesentipid= " + bilesen.value() + "order by h.ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new YariMamul(rs.getInt("id"), rs.getString("kod"), rs.getString("ad")));
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
    
    protected static List<YariMamul> yarimamulEkle(String kod, String ad, String birimid,
            String firmaid) {
        List<YariMamul> temp = new ArrayList<YariMamul>();
        Connection conn = ConnectionManager.getConnection();
        
        BilesenTip bilesen = BilesenTip.YARIMAMUL;
        String insertQuery = "insert into bilesen (kod, ad, birimid, bilesentipid, firmaid) values (?,?,?,?,?) ";
        String searchQuery = "select h.*, b.ad birimad, f.ad firmaad " + "from bilesen h "
                + "join birim b on h.birimid=b.id " + "join firma f on h.firmaid=f.id "
                + "where h.bilesentipid= " + bilesen.value() + "order by h.ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, kod);
            pstmt.setString(2, ad);
            pstmt.setInt(3, Integer.valueOf(birimid));
            pstmt.setInt(4, bilesen.value());
            pstmt.setInt(5, Integer.valueOf(firmaid));
            
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new YariMamul(rs.getInt("id"), rs.getString("kod"), rs.getString("ad")));
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
    
    protected static List<Calisan> calisanListeGetirTum() {
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
                temp.add(new Calisan(rs.getInt("id"), rs.getString("adsoy"), rs.getString("gorev")));
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
    
    protected static List<Calisan> calisanEkle(String adsoy, String gorev) {
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
                temp.add(new Calisan(rs.getInt("id"), rs.getString("adsoy"), rs.getString("gorev")));
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
                // if (conn != null && !conn.isClosed()) {
                // conn.close();
                // }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
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
    
    protected static List<Depo> depoEkle(String ad) {
        List<Depo> temp = new ArrayList<Depo>();
        Connection conn = ConnectionManager.getConnection();
        
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
    
    protected static List<Firma> firmaEkle(String ad, String telefon, String adres) {
        List<Firma> temp = new ArrayList<Firma>();
        Connection conn = ConnectionManager.getConnection();
        
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
    
    protected static List<Makina> makinaEkle(String ad, String kod) {
        List<Makina> temp = new ArrayList<Makina>();
        Connection conn = ConnectionManager.getConnection();
        
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
                temp.add(new Makina(rs.getInt("id"), rs.getString("ad"), rs.getString("kod")));
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
    
    protected static int mamulEkle(String ad, String kod, String cevrimSuresi, String firmaid) {
        Connection conn = ConnectionManager.getConnection();
        
        BilesenTip bilesen = BilesenTip.MAMUL;
        String insertQuery = "insert into bilesen (ad, kod, cevrimsuresi, bilesentipid, firmaid) values (?,?,?,?,?) ";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int newMamulID = -1;
        try {
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, ad);
            pstmt.setString(2, kod);
            pstmt.setInt(3, Integer.valueOf(cevrimSuresi));
            pstmt.setInt(4, bilesen.value());
            pstmt.setInt(5, Integer.valueOf(firmaid));
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newMamulID = rs.getInt(1);
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
        return newMamulID;
    }
    
    protected static int mamulBilesenEkle(int mamulid, int bilesen, int uretimtip, int birim,
            int miktar) {
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into mamulbilesen (mamulid, bilesenid, bilesentipid, birimid, miktar) values (?,?,?,?,?)";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int newMamulID = -1;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, mamulid);
            pstmt.setInt(2, bilesen);
            pstmt.setInt(3, uretimtip);
            pstmt.setInt(4, birim);
            pstmt.setInt(5, miktar);
            pstmt.executeUpdate();
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
        return newMamulID;
    }
    
    protected static List<Makina> mamulBilesenListeGetirTum() {
        List<Makina> temp = new ArrayList<Makina>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select mb.*, (case when mb.bilesentipid=1 then h.ad else y.ad end) bilesenad from mamulbilesen mb "
                + "join mamul m on mb.mamulid=m.id "
                + "join mamultip mt on mb.bilesentipid=mt.id "
                + "join birim b on mb.birimid=b.id "
                + "left join hammadde h on mb.bilesenid=h.id "
                + "left join yarimamul y on mb.bilesenid=y.id";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Makina(rs.getInt("id"), rs.getString("ad"), rs.getString("kod")));
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
    
    protected static List<Stok> stokListeGetirTum() {
        List<Stok> temp = new ArrayList<Stok>();
        Connection conn = ConnectionManager.getConnection();
        
        /*
         * String searchQuery = "select h.*, b.ad birimad, f.ad firmaad " +
         * "from hammadde h " + "join birim b on h.birimid=b.id " +
         * "join firma f on h.firmaid=f.id " + "order by h.ad";
         */
        
        // BilesenTip bilesen = BilesenTip.HAMMADDE;
        String searchQuery = "select s.*, b.ad bad, st.ad sad, bt.ad btad, bm.ad bmad, f.ad fad from stok s "
                + " join stoktip st on s.stoktipid = st.id "
                + " join bilesen b on s.bilesenid = b.id "
                + " join bilesentip bt on b.bilesentipid=bt.id "
                + " join birim bm on b.birimid = bm.id " + " join firma f on b.firmaid = f.id;";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Stok(rs.getInt("id"), rs.getInt("bilesenid"), rs.getString("bad"), rs
                        .getInt("stoktipid"), rs.getString("sad"), rs.getInt("miktar"), rs
                        .getDate("giristarihi"), rs.getDate("cikistarihi"), rs.getString("not")));
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
    
    protected static List<Bilesen> bilesenListeGetirTum(BilesenTip bilesenTip) {
        List<Bilesen> temp = new ArrayList<Bilesen>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select b.*, bt.ad bilesentipad, f.ad firmaad, bm.ad birimad from bilesen b "
                + "join bilesentip bt on bt.id = b.bilesentipid "
                + "left join birim bm on bm.id = b.birimid "
                + "join firma f on f.id = b.firmaid "
                + "where bilesentipid = ? order by b.ad";
        
        System.out.println(searchQuery + " == " + bilesenTip.value());
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1, bilesenTip.value());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                temp.add(new Bilesen(rs.getInt("id"), rs.getString("ad"), rs.getString("kod"), rs
                        .getInt("bilesentipid"), rs.getString("bilesentipad"),
                        rs.getInt("birimid"), rs.getString("birimad"), rs.getInt("firmaid"), rs
                                .getString("firmaad"), rs.getInt("cevrimsuresi"), rs
                                .getDate("eklemetarihi")));
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
    
    protected static List<Siparis> siparisEkle(int bilesenid, int miktar) {
        List<Siparis> temp = new ArrayList<Siparis>();
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into siparis ( bilesenid, miktar) values (?,?)";
        String searchQuery = "select s.*,b.ad bilesenad from siparis s join bilesen b on s.bilesenid=b.id where s.id = ?";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer newSiparisID = 0;
        try {
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, bilesenid);
            pstmt.setInt(2, miktar);
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newSiparisID = rs.getInt(1);
            }
            
            pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1, newSiparisID);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Siparis(rs.getInt("id"), rs.getInt("bilesenid"), rs
                        .getString("bilesenad"), rs.getInt("miktar"), rs.getTimestamp("tarih"), rs
                        .getTimestamp("bitistarih"), rs.getString("not")));
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
    
    protected static List<Siparis> siparisListeGetirTum() {
        List<Siparis> temp = new ArrayList<Siparis>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select s.*,b.ad bilesenad from siparis s join bilesen b on s.bilesenid=b.id "
                + "where s.bitistarih is null order by s.tarih desc";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Siparis(rs.getInt("id"), rs.getInt("bilesenid"), rs
                        .getString("bilesenad"), rs.getInt("miktar"), rs.getTimestamp("tarih"), rs
                        .getTimestamp("bitistarih"), rs.getString("not")));
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
    
    protected static List<Siparis> siparisBitenListeGetirTum() {
        List<Siparis> temp = new ArrayList<Siparis>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select s.*,b.ad bilesenad from siparis s join bilesen b on s.bilesenid=b.id "
                + "where s.bitistarih is not null order by s.tarih desc";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Siparis(rs.getInt("id"), rs.getInt("bilesenid"), rs
                        .getString("bilesenad"), rs.getInt("miktar"),
                // rs.getDate("tarih"),
                        rs.getTimestamp("tarih"), rs.getTimestamp("bitistarih"), rs
                                .getString("not")));
                System.out.println(rs.getDate("tarih"));
                System.out.println(rs.getTimestamp("tarih"));
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
