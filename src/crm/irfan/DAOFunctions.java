package crm.irfan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Birim;
import crm.irfan.entity.Calisan;
import crm.irfan.entity.Depo;
import crm.irfan.entity.DurusSebep;
import crm.irfan.entity.Firma;
import crm.irfan.entity.HataSebep;
import crm.irfan.entity.Irsaliye;
import crm.irfan.entity.IrsaliyeBilesen;
import crm.irfan.entity.Makina;
import crm.irfan.entity.MamulBilesen;
import crm.irfan.entity.Siparis;
import crm.irfan.entity.SiparisPlan;
import crm.irfan.entity.SiparisTip;
import crm.irfan.entity.Stok;

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
                temp.add(new Firma(rs.getInt("id"), rs.getString("ad"), rs.getString("telefon"), rs.getString("adres")));
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
    
    protected static List<Bilesen> hammaddeEkle(String kod, String ad, String birimid, String firmaid) {
        List<Bilesen> temp = new ArrayList<Bilesen>();
        Connection conn = ConnectionManager.getConnection();
        
        BilesenTip bilesen = BilesenTip.HAMMADDE;
        String insertQuery = "insert into bilesen (kod, ad, birimid, bilesentipid, firmaid) values (?,?,?,?,?) ";
        String searchQuery = "select b.*, bt.ad bilesentipad, f.ad firmaad, bm.ad birimad from bilesen b "
                        + "join bilesentip bt on bt.id = b.bilesentipid " + "left join birim bm on bm.id = b.birimid "
                        + "join firma f on f.id = b.firmaid " + "where bilesentipid = ? order by b.ad";
        
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
            pstmt.setInt(1, bilesen.value());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Bilesen(rs.getInt("id"), rs.getString("ad"), rs.getString("kod"), rs
                                .getInt("bilesentipid"), rs.getString("bilesentipad"), rs.getInt("birimid"), rs
                                .getString("birimad"), rs.getInt("firmaid"), rs.getString("firmaad"), rs
                                .getInt("cevrimsuresi"), rs.getDate("eklemetarihi")));
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
    
    protected static List<Bilesen> yarimamulEkle(String kod, String ad, String birimid, String firmaid) {
        List<Bilesen> temp = new ArrayList<Bilesen>();
        Connection conn = ConnectionManager.getConnection();
        
        BilesenTip bilesen = BilesenTip.YARIMAMUL;
        String insertQuery = "insert into bilesen (kod, ad, birimid, bilesentipid, firmaid) values (?,?,?,?,?) ";
        String searchQuery = "select b.*, bt.ad bilesentipad, f.ad firmaad, bm.ad birimad from bilesen b "
                        + "join bilesentip bt on bt.id = b.bilesentipid " + "left join birim bm on bm.id = b.birimid "
                        + "join firma f on f.id = b.firmaid " + "where bilesentipid = ? order by b.ad";
        
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
            pstmt.setInt(1, bilesen.value());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Bilesen(rs.getInt("id"), rs.getString("ad"), rs.getString("kod"), rs
                                .getInt("bilesentipid"), rs.getString("bilesentipad"), rs.getInt("birimid"), rs
                                .getString("birimad"), rs.getInt("firmaid"), rs.getString("firmaad"), rs
                                .getInt("cevrimsuresi"), rs.getDate("eklemetarihi")));
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
                temp.add(new Firma(rs.getInt("id"), rs.getString("ad"), rs.getString("telefon"), rs.getString("adres")));
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
    
    protected static List<Makina> makinaListeGetirTum(Integer offset) {
        List<Makina> temp = new ArrayList<Makina>();
        Connection conn = ConnectionManager.getConnection();
       
        String pageFilter = (offset==0)?"":" offset " + (offset-1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
        
        String searchQuery = "select m.*, mt.ad makinatipad "
                        + "from makina m join makinatip mt on mt.id=m.makinatipid " 
                        //+ "order by makinatipid, m.ad "
                        + "order by m.id "
                        + pageFilter;
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Makina(
                                rs.getInt("id"), 
                                rs.getString("ad"),
                                rs.getInt("makinatipid"),
                                rs.getString("makinatipad")
                                )
                );
                
                
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
    
    protected static List<Makina> makinaEkle(String ad, String makinatip) {
        List<Makina> temp = new ArrayList<Makina>();
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into makina (ad, makinatipid) values (?, ?) ";
        String searchQuery = "select m.*, mt.ad makinatipad from makina m join makinatip mt on mt.id=m.makinatipid " 
        + "order by makinatipid, m.ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, ad);
            pstmt.setInt(2, Integer.valueOf(makinatip));
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Makina(
                                rs.getInt("id"), 
                                rs.getString("ad"),
                                rs.getInt("makinatipid"),
                                rs.getString("makinatipad")
                                )
                );
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
        int newMamulId = -1;
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
                newMamulId = rs.getInt(1);
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
        return newMamulId;
    }
    
    protected static void mamulUretimTipEkle(Integer mamulId, String mamulUretimSekli) {
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into mamuluretimtip (bilesenid, mamultipid) values (?,?) ";
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, mamulId);
            pstmt.setInt(2, Integer.valueOf(mamulUretimSekli));
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected static void mamulBilesenEkle(int mamulid, int bilesen, int birim, int miktar) {
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into mamulbilesen (mamulid, bilesenid, birimid, miktar) values (?,?,?,?)";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, mamulid);
            pstmt.setInt(2, bilesen);
            pstmt.setInt(3, birim);
            pstmt.setInt(4, miktar);
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
        return;
    }
    
    protected static List<MamulBilesen> mamulBilesenListeGetirTum() {
        List<MamulBilesen> temp = new ArrayList<MamulBilesen>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select mb.mamulid, mb.id, b2.id bilesenid, b2.ad bilesenad, b2.kod bilesenkod, bt.id bilesentipid, "
                        + "bt.ad bilesentipad, f.id firmaid, f.ad firmaad, bm.id birimid, bm.ad birimad, mb.miktar "
                        + "from bilesen b join mamulbilesen mb on mb.mamulid = b.id "
                        + "join bilesen b2 on b2.id = mb.bilesenid "
                        + "join bilesentip bt on bt.id = b2.bilesentipid "
                        + "join birim bm on bm.id = mb.birimid "
                        + "join firma f on f.id = b.firmaid "
                        + "order by mb.mamulid, b2.bilesentipid, b2.ad";
        
        //System.out.println("bilesenTip == " + bilesenTip.name());
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new MamulBilesen(
                                rs.getInt("id"),
                                rs.getInt("mamulid"),
                                rs.getInt("bilesenid"),
                                rs.getString("bilesenad"),
                                rs.getString("bilesenkod"),
                                rs.getInt("bilesentipid"), 
                                rs.getString("bilesentipad"), 
                                rs.getInt("birimid"), 
                                rs.getString("birimad"),
                                rs.getInt("firmaid"), 
                                rs.getString("firmaad"), 
                                rs.getInt("miktar"))
                );
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
    
    protected static List<Stok> stokListeGetirTum(BilesenTip bilesentip) {
        List<Stok> temp = new ArrayList<Stok>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select s.*, "
                        + "b.ad bilesenad, b.kod bilesenkod, "
                        + "bt.id bilesentipid, bt.ad bilesentipad, "
                        + "br.id birimid, br.ad birimad, "
                        + "f.id firmaid, f.ad firmaad "
                        + "from stok s "
                        + "join bilesen b on b.id=s.bilesenid "
                        + "join bilesentip bt on bt.id = b.bilesentipid "
                        + "join birim br on br.id = b.birimid "
                        + "join firma f on f.id = b.firmaid "
                        + "where bt.id = ? ";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1, bilesentip.value());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Stok(
                                rs.getInt("id"), 
                                rs.getInt("bilesenid"), 
                                rs.getString("bilesenad"),
                                rs.getString("bilesenkod"),
                                rs.getInt("bilesentipid"),
                                rs.getString("bilesentipad"),
                                rs.getInt("birimid"),
                                rs.getString("birimad"),
                                rs.getInt("firmaid"),
                                rs.getString("firmaad"),
                                rs.getInt("miktar"), 
                                rs.getString("gkrno"), 
                                rs.getString("irsaliyeno"), 
                                rs.getString("lot"), 
                                rs.getTimestamp("giristarihi"), 
                                rs.getTimestamp("cikistarihi"), 
                                rs.getString("not")
                                ));
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
    
    protected static List<DurusSebep> durusSebepListeGetir() {
        List<DurusSebep> temp = new ArrayList<DurusSebep>();
        Connection conn = ConnectionManager.getConnection();
        

        String searchQuery = "select * from durussebep order by ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new DurusSebep(
                                rs.getInt("id"), 
                                rs.getString("ad"), 
                                rs.getString("kod")
                                ));
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
    
    protected static List<HataSebep> hataSebepListeGetir() {
        List<HataSebep> temp = new ArrayList<HataSebep>();
        Connection conn = ConnectionManager.getConnection();
        

        String searchQuery = "select * from hatasebep order by ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new HataSebep(
                                rs.getInt("id"), 
                                rs.getString("ad"), 
                                rs.getString("kod")
                                ));
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
        
        String searchQuery = "select b.*, bt.ad bilesentipad, f.ad firmaad, bm.ad birimad "
                        + "from bilesen b "
                        + "join bilesentip bt on bt.id = b.bilesentipid " 
                        + "left join birim bm on bm.id = b.birimid "
                        + "join firma f on f.id = b.firmaid " 
                        + "where bilesentipid = ? order by b.ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1, bilesenTip.value());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Bilesen(
                                rs.getInt("id"), 
                                rs.getString("ad"), 
                                rs.getString("kod"),                                
                                rs.getInt("bilesentipid"), 
                                rs.getString("bilesentipad"), 
                                rs.getInt("birimid"), 
                                rs.getString("birimad"),
                                rs.getInt("firmaid"), 
                                rs.getString("firmaad"), 
                                rs.getInt("cevrimsuresi"), 
                                rs.getDate("eklemetarihi")));
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
    
    protected static List<Siparis> siparisEkle(String bilesenid, String miktar, String not) {
        List<Siparis> temp = new ArrayList<Siparis>();
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into siparis ( bilesenid, miktar, \"not\") values (?,?,?)";
        //String searchQuery = "select s.*,b.ad bilesenad from siparis s join bilesen b on s.bilesenid=b.id where s.id = ?";
        String searchQuery = "select s.*, b.ad bilesenad, "
                        + "coalesce((select sum(miktar) from siparisplan sp where sp.siparisid=s.id),0) as planmiktar, "
                        + "coalesce((select sum(miktar) from siparisplan sp where sp.siparisid=s.id and sp.tamamlandi=1),0) as tamammiktar "   
                        + "from siparis s join bilesen b on s.bilesenid=b.id " 
                        + "where s.id = ? "
                        + "order by s.id";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer newSiparisID = 0;
        try {
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, Integer.valueOf(bilesenid));
            pstmt.setInt(2, Integer.valueOf(miktar));
            pstmt.setString(3, not);
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newSiparisID = rs.getInt(1);
            }
            
            pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1, newSiparisID);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Siparis(
                                rs.getInt("id"), 
                                rs.getInt("bilesenid"), 
                                rs.getString("bilesenad"), 
                                rs.getInt("miktar"),
                                rs.getInt("planmiktar"),
                                rs.getInt("tamammiktar"),
                                rs.getTimestamp("tarih"), 
                                rs.getTimestamp("bitistarih"), 
                                rs.getString("not")
                                )
                );
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
    
    protected static List<Siparis> siparisListeGetirTum(SiparisTip siparis) {
        List<Siparis> temp = new ArrayList<Siparis>();
        Connection conn = ConnectionManager.getConnection();
        
        String filter = null;
        if (siparis == SiparisTip.BEKLEYEN) {
            filter = " where s.bitistarih is null ";
        }
        else if (siparis == SiparisTip.TAMAMLANMIS) {
            filter = " where s.bitistarih is not null ";
        }
        else {
            filter = " ";
        }
        
        String searchQuery = "select s.*, b.ad bilesenad, "
                        + "coalesce((select sum(miktar) from siparisplan sp where sp.siparisid=s.id),0) as planmiktar, "
                        + "coalesce((select sum(miktar) from siparisplan sp where sp.siparisid=s.id and sp.tamamlandi=1),0) as tamammiktar "   
                        + "from siparis s join bilesen b on s.bilesenid=b.id " 
                        + filter
                        + "order by s.id";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Siparis(
                                rs.getInt("id"), 
                                rs.getInt("bilesenid"), 
                                rs.getString("bilesenad"), 
                                rs.getInt("miktar"),
                                rs.getInt("planmiktar"), 
                                rs.getInt("tamammiktar"), 
                                rs.getTimestamp("tarih"), 
                                rs.getTimestamp("bitistarih"), 
                                rs.getString("not")
                                )
                );
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
    
    protected static List<SiparisPlan> siparisPlanEkle(String siparisid, String miktar, String makinaid, String calisanid,
                                                   String tarih, String bassaat, String bitsaat, String not) {
        List<SiparisPlan> temp = new ArrayList<SiparisPlan>();
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into siparisplan (siparisid, miktar, makinaid, calisanid, tarih, bassaat, bitsaat, \"not\") " 
        + " values (?,?,?,?,?,?,?,?)";
        String searchQuery = "select sp.*,b.ad bilesenad, m.ad makinaad, c.adsoy calisanad from siparisplan sp "
                        + "join siparis s on s.id=sp.siparisid "
                        + "join bilesen b on b.id=s.bilesenid "
                        + "join makina m on m.id=sp.makinaid "
                        + "join calisan c on c.id=sp.calisanid "
                        + "where sp.tamamlandi=0";
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, Integer.valueOf(siparisid));
            pstmt.setInt(2, Integer.valueOf(miktar));
            pstmt.setInt(3, Integer.valueOf(makinaid));
            pstmt.setInt(4, Integer.valueOf(calisanid));
            pstmt.setDate(5, Date.valueOf(tarih));
            pstmt.setTime(6, Time.valueOf(bassaat) );
            pstmt.setTime(7, Time.valueOf(bitsaat) );
            pstmt.setString(8, not);

            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new SiparisPlan(
                                rs.getInt("id"), 
                                rs.getInt("siparisid"), 
                                rs.getString("bilesenad"), 
                                rs.getInt("miktar"),
                                rs.getInt("makinaid"), 
                                rs.getString("makinaad"),
                                rs.getInt("calisanid"), 
                                rs.getString("calisanad"), 
                                rs.getDate("tarih"), 
                                rs.getTime("bassaat"),
                                rs.getTime("bitsaat"), 
                                rs.getInt("hataid"),
                                rs.getInt("hatamiktar"),
                                rs.getInt("durusid"),
                                rs.getString("not")
                                )
                );
                
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
    
    
    protected static List<SiparisPlan> siparisPlanListeGetirTum(SiparisTip siparistip, int offset) {
        List<SiparisPlan> temp = new ArrayList<SiparisPlan>();
        Connection conn = ConnectionManager.getConnection();
        
        String pageFilter = (offset==0)?"":" offset " + (offset-1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
        
        String searchQuery = "select sp.*,b.ad bilesenad, m.ad makinaad, c.adsoy calisanad from siparisplan sp "
                        + "join siparis s on s.id=sp.siparisid " 
                        + "join bilesen b on b.id=s.bilesenid "
                        + "join makina m on m.id=sp.makinaid " 
                        + "join calisan c on c.id=sp.calisanid "
                        + "where sp.tamamlandi=? "
                        + "order by s.id, sp.id "
                        + pageFilter;
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            
            pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1, siparistip.value());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new SiparisPlan(
                                rs.getInt("id"), 
                                rs.getInt("siparisid"), 
                                rs.getString("bilesenad"), 
                                rs.getInt("miktar"), 
                                rs.getInt("makinaid"), 
                                rs.getString("makinaad"), 
                                rs.getInt("calisanid"), 
                                rs.getString("calisanad"), 
                                rs.getDate("tarih"), 
                                rs.getTime("bassaat"), 
                                rs.getTime("bitsaat"), 
                                rs.getInt("hataid"),
                                rs.getInt("hatamiktar"),
                                rs.getInt("durusid"),
                                rs.getString("not")
                                )
                );
                
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
    
    
    protected static Integer SiparisPlanGuncelle(String siparisplanid, String miktar, String makinaid, String calisanid,
                                                 String tarih, String bassaat, String basdakika, String bitsaat, String bitdakika,
                                                 String hataid, String hatamiktar, String durusid, String not
                    ) {
        
        Connection conn = ConnectionManager.getConnection();
        int result = 0;        
        String updateQuery = "update siparisplan "
                        + "set "
                        + "miktar = ? ,"
                        + "makinaid  = ? ,"
                        + "calisanid  = ? ,"
                        + "tarih  = ? ,"
                        + "bassaat  = ? ,"
                        + "bitsaat  = ? ,"
                        + "hataid  = ? ,"
                        + "hatamiktar  = ? ,"
                        + "durusid  = ? ,"
                        + "\"not\"  = ? "
                        + "where id = ?";
        // connect to DB
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, Integer.valueOf(miktar));
            pstmt.setInt(2, Integer.valueOf(makinaid));
            pstmt.setInt(3, Integer.valueOf(calisanid));
            pstmt.setDate(4, Date.valueOf(tarih));
            pstmt.setTime(5, Time.valueOf(bassaat +":"+ basdakika +":00"));
            pstmt.setTime(6, Time.valueOf(bitsaat +":"+ bitdakika +":00"));
            if (hataid!=null && hataid.trim()!="") 
                pstmt.setInt(7, Integer.valueOf(hataid));
            else 
                pstmt.setNull(7, Types.INTEGER);
            if (hatamiktar!=null && (hatamiktar.trim()!="" && Integer.valueOf(hatamiktar.trim())!=0) ) 
                pstmt.setInt(8, Integer.valueOf(hatamiktar));
            else 
                pstmt.setNull(8, Types.INTEGER);
            if (durusid!=null && durusid.trim()!="") 
                pstmt.setInt(9, Integer.valueOf(durusid));
            else 
                pstmt.setNull(9, Types.INTEGER);
            if (not!=null && not.trim()!="") 
                pstmt.setString(10, not);
            else 
                pstmt.setNull(10, Types.VARCHAR);
            pstmt.setInt(11, Integer.valueOf(siparisplanid));
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    protected static Integer SiparisPlanTamamla(String siparisplanid) {
        
        Connection conn = ConnectionManager.getConnection();
        int result = 0;        
        String updateQuery = "update siparisplan set tamamlandi  = ? where id = ?";
        // connect to DB
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, SiparisTip.TAMAMLANMIS.value());
            pstmt.setInt(2, Integer.valueOf(siparisplanid));
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    protected static Integer SiparisPlanSil(String siparisplanid) {
        
        Connection conn = ConnectionManager.getConnection();
        int result = 0;        
        String updateQuery = "delete from siparisplan where id = ?";
        // connect to DB
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, Integer.valueOf(siparisplanid));
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    protected static List<Stok> stokEkle(BilesenTip bilesentip, String bilesenid, String miktar, String irsaliyeno,
                                         String lot, String not) {
        List<Stok> temp = new ArrayList<Stok>();
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into stok (bilesenid, miktar, irsaliyeno, lot, \"not\") values (?,?,?,?,?) ";
        String searchQuery = "select s.*, " 
                        + "b.ad bilesenad, b.kod bilesenkod, "
                        + "bt.id bilesentipid, bt.ad bilesentipad, " 
                        + "br.id birimid, br.ad birimad, "
                        + "f.id firmaid, f.ad firmaad " + "from stok s " 
                        + "join bilesen b on b.id=s.bilesenid "
                        + "join bilesentip bt on bt.id = b.bilesentipid " 
                        + "join birim br on br.id = b.birimid "
                        + "join firma f on f.id = b.firmaid " 
                        + "where bt.id = ?";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, Integer.valueOf(bilesenid));
            pstmt.setInt(2, Integer.valueOf(miktar));
            pstmt.setString(3, irsaliyeno);
            pstmt.setString(4, lot);
            pstmt.setString(5, not);
            
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1, bilesentip.value());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Stok(
                                rs.getInt("id"), 
                                rs.getInt("bilesenid"), 
                                rs.getString("bilesenad"), 
                                rs.getString("bilesenkod"), 
                                rs.getInt("bilesentipid"), 
                                rs.getString("bilesentipad"), 
                                rs.getInt("birimid"), 
                                rs.getString("birimad"), 
                                rs.getInt("firmaid"), 
                                rs.getString("firmaad"), 
                                rs.getInt("miktar"), 
                                rs.getString("gkrno"), 
                                rs.getString("irsaliyeno"), 
                                rs.getString("lot"), 
                                rs.getTimestamp("giristarihi"), 
                                rs.getTimestamp("cikistarihi"), 
                                rs.getString("not")));
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
    
    protected static int irsaliyeEkle() {
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into irsaliye (olusturmatarihi) values (now()) ";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int newIrsaliyeId = -1;
        try {
            pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                newIrsaliyeId = rs.getInt(1);
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
        return newIrsaliyeId;
    }
    
    
    protected static void irsaliyeBilesenEkle(int irsaliyeid, int mamulid, int miktar) {
        Connection conn = ConnectionManager.getConnection();
        
        String insertQuery = "insert into irsaliyebilesen (irsaliyeid, mamulid, miktar) values (?,?,?)";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, irsaliyeid);
            pstmt.setInt(2, mamulid);
            pstmt.setInt(3, miktar);
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
        return;
    }
    
    protected static List<Irsaliye> irsaliyeListeGetirTum() {
        List<Irsaliye> temp = new ArrayList<Irsaliye>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select * from irsaliye "
                        + "where gonderimtarihi is null "
                        + "order by id";
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new Irsaliye(
                                rs.getInt("id"), 
                                rs.getInt("irsaliyeno"), 
                                rs.getTimestamp("olusturmatarihi"), 
                                rs.getTimestamp("gonderimtarihi")
                                )
                );
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
    
    protected static List<IrsaliyeBilesen> irsaliyeBilesenListeGetirTum() {
        List<IrsaliyeBilesen> temp = new ArrayList<IrsaliyeBilesen>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select i.*, ib.id irsaliyebilesenid, ib.irsaliyeid, ib.mamulid, ib.miktar, b.ad mamulad, b.kod mamulkod "
                        + " from irsaliye i "
                        + " join irsaliyebilesen ib on ib.irsaliyeid=i.id "
                        + " join bilesen b on b.id = ib.mamulid "
                        + " where i.gonderimtarihi is null "
                        + " order by i.id, ib.id ";
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                temp.add(new IrsaliyeBilesen(
                                rs.getInt("id"),
                                rs.getInt("irsaliyeid"),
                                rs.getInt("irsaliyebilesenid"),
                                rs.getInt("mamulid"),
                                rs.getString("mamulad"),
                                rs.getString("mamulkod"),
                                rs.getInt("miktar"),
                                rs.getTimestamp("olusturmatarihi"), 
                                rs.getTimestamp("gonderimtarihi")
                                )
                );
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
    
    protected static Integer IrsaliyeSil(String irsaliyeid) {
        
        Connection conn = ConnectionManager.getConnection();
        int result = 0;        
        String updateQuery = "delete from irsaliye where id = ?";
        // connect to DB
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, Integer.valueOf(irsaliyeid));
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    protected static Integer bilesenSil(String bilesenid) {
        
        Connection conn = ConnectionManager.getConnection();
        int result = 0;        
        String updateQuery = "delete from bilesen where id = ?";
        // connect to DB
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, Integer.valueOf(bilesenid));
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }   
    
    protected static Integer bilesenGuncelle(String bilesenid, String birimid, String firmaid, String bilesenkod, String bilesenad, String cevrimsuresi) {
        
        Connection conn = ConnectionManager.getConnection();
        int result = 0;        
        String updateQuery = "update bilesen "
                        + "set "
                        + "birimid = ? ,"
                        + "firmaid  = ? ,"
                        + "kod  = ? ,"
                        + "ad  = ?, "
                        + "cevrimsuresi  = ? "
                        + "where id = ?";
        
        // connect to DB
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(updateQuery);
            if (birimid!=null) 
                pstmt.setInt(1, Integer.valueOf(birimid));
            else 
                pstmt.setNull(1, Types.INTEGER);
            pstmt.setInt(2, Integer.valueOf(firmaid));
            pstmt.setString(3, bilesenkod);
            pstmt.setString(4, bilesenad);
            if (cevrimsuresi!=null) 
                pstmt.setInt(5, Integer.valueOf(cevrimsuresi));
            else 
                pstmt.setNull(5, Types.INTEGER);
            pstmt.setInt(6, Integer.valueOf(bilesenid));
            
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static int recordCount(String tablename) {
        int result = 0;
        Connection conn = ConnectionManager.getConnection();
        String selectQuery = "select count(*) from " + tablename;
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(selectQuery);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

 
}
