package crm.irfan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm.irfan.entity.Birim;
import crm.irfan.entity.Calisan;
import crm.irfan.entity.Depo;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Hammadde;
import crm.irfan.entity.Makina;
import crm.irfan.entity.Mamul;
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
    
    protected static List<Hammadde> hammaddeEkle(String kod, String ad, String birimid, String firmaid) {
        List<Hammadde> temp = new ArrayList<Hammadde>();
        Connection conn = ConnectionManager.getConnection();

        String insertQuery = "insert into hammadde (kod, ad, birimid, firmaid) values (?,?,?,?) ";
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
            pstmt.setInt(4, Integer.valueOf(firmaid));

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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    protected static List<YariMamul> yarimamulEkle(String kod, String ad) {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    protected static List<Calisan> calisanEkle(String adsoy, String gorev) {
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
    
    protected static List<Mamul> mamulListeGetirTum() {
        List<Mamul> temp = new ArrayList<Mamul>();
        Connection conn = ConnectionManager.getConnection();
        
        String searchQuery = "select * from mamul order by ad";
        
        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Mamul(rs.getInt("id"), rs.getString("ad")));
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

    protected static List<Depo> depoEkle(String ad) {
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
    
    protected static List<Firma> firmaEkle(String ad, String telefon, String adres) {
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
                temp.add(new Firma(
                        rs.getInt("id"), 
                        rs.getString("ad"), 
                        rs.getString("telefon"), 
                        rs.getString("adres")
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

    protected static List<Makina> makinaEkle(String ad, String kod) {
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
