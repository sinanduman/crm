package crm.irfan;

import java.math.BigDecimal;
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
import java.util.Map;

import crm.irfan.entity.Bilesen;
import crm.irfan.entity.BilesenTip;
import crm.irfan.entity.Birim;
import crm.irfan.entity.Calisan;
import crm.irfan.entity.Depo;
import crm.irfan.entity.DurusSebep;
import crm.irfan.entity.Firma;
import crm.irfan.entity.Genel;
import crm.irfan.entity.HataSebep;
import crm.irfan.entity.Irsaliye;
import crm.irfan.entity.IrsaliyeBilesen;
import crm.irfan.entity.IrsaliyeTip;
import crm.irfan.entity.IzlemeNo;
import crm.irfan.entity.LogMod;
import crm.irfan.entity.Makina;
import crm.irfan.entity.Mamul;
import crm.irfan.entity.MamulBilesen;
import crm.irfan.entity.Siparis;
import crm.irfan.entity.SiparisPlan;
import crm.irfan.entity.SiparisTip;
import crm.irfan.entity.Stok;
import crm.irfan.entity.StokRapor;
import crm.irfan.entity.UretimDurum;
import crm.irfan.entity.UretimPlan;

public class DAOFunctions {
	
	public static Connection conn = null;
	
	protected static List<Birim> birimListeGetirTum() {
		List<Birim> temp = new ArrayList<Birim>();
		conn = ConnectionManager.getConnection();
		String searchQuery = "select * from irfan.birim order by ad ";
		
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
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Firma> firmaListeGetirTum(int offset) {
		List<Firma> temp = new ArrayList<Firma>();
		conn = ConnectionManager.getConnection();
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
				
		String searchQuery = "select * from irfan.firma order by ad ASC" + pageFilter;
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(
						new Firma(rs.getInt("id"), rs.getString("ad"), rs.getString("telefon"), rs.getString("adres")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static String hammaddeEkle(String kod, String ad, String birimid, String firmaid, String bilesentip) {
		String result = "0";
		conn = ConnectionManager.getConnection();
		String insertQuery = "insert into irfan.bilesen (kod, ad, birimid, bilesentipid, firmaid) values (?,?,?,?,?) ";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setString(1, kod);
			pstmt.setString(2, ad);
			pstmt.setInt(3, Integer.valueOf(birimid));
			pstmt.setInt(4, Integer.valueOf(bilesentip));
			pstmt.setInt(5, Integer.valueOf(firmaid));
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				result = e.getMessage();
			}
		}
		return result;
	}
	
	protected static int gkrnoGetir() {
		int result = -1;
		conn = ConnectionManager.getConnection();
		String selectQuery = "select irfan.gkrno_next()";
		// System.out.println(selectQuery);
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static List<Calisan> calisanListeGetirTum(int offset, Integer durum) {
		List<Calisan> temp = new ArrayList<Calisan>();
		conn = ConnectionManager.getConnection();
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
		String durumFilter = (durum != null && durum == 1) ? " where durum = 1 " : "";
		String searchQuery = "select * from irfan.calisan " + durumFilter + " order by ad ASC " + pageFilter;
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new Calisan(rs.getInt("id"), rs.getString("ad"), rs.getString("soyad"), rs.getString("gorev"),
						rs.getInt("durum")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static String calisanEkle(String ad, String soyad, String gorev) {
		String retVal = "0";
		conn = ConnectionManager.getConnection();
		String insertQuery = "insert into irfan.calisan (ad, soyad, gorev) values (?,?,?) ";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setString(1, ad);
			pstmt.setString(2, soyad);
			pstmt.setString(3, gorev);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			retVal = e.getMessage();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static List<Depo> depoListeGetirTum() {
		List<Depo> temp = new ArrayList<Depo>();
		conn = ConnectionManager.getConnection();
		String searchQuery = "select * from irfan.depo order by ad";
		
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
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static String firmaEkle(String ad, String telefon, String adres) {
		String retVal = "0";
		conn = ConnectionManager.getConnection();
		String insertQuery = "insert into irfan.firma (ad, telefon, adres) values (?,?, ?) ";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setString(1, ad);
			pstmt.setString(2, telefon);
			pstmt.setString(3, adres);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			retVal = e.getMessage();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static List<Makina> makinaListeGetirTum(Integer offset) {
		List<Makina> temp = new ArrayList<Makina>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
				
		String searchQuery = "select m.*, mt.ad makinatipad "
				+ "from irfan.makina m join irfan.makinatip mt on mt.id=m.makinatipid " + "order by m.ad ASC "
				+ pageFilter;
				
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new Makina(rs.getInt("id"), rs.getString("ad"), rs.getInt("makinatipid"),
						rs.getString("makinatipad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static String makinaEkle(String ad, String makinatip) {
		String retVal = "0";
		conn = ConnectionManager.getConnection();
		String insertQuery = "insert into irfan.makina (ad, makinatipid) values (?, ?) ";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setString(1, ad);
			pstmt.setInt(2, Integer.valueOf(makinatip));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			retVal = e.getMessage();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static String mamulEkle(String ad, String kod, String cevrimSuresi, String firmaid, String figur) {
		conn = ConnectionManager.getConnection();
		BilesenTip bilesen = BilesenTip.MAMUL;
		String insertQuery = "insert into irfan.mamul (ad, kod, cevrimsuresi, bilesentipid, firmaid, figur) values (?,?,?,?,?,?) ";
		String newMamulId = "-1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, ad);
			pstmt.setString(2, kod);
			pstmt.setInt(3, Integer.valueOf(cevrimSuresi));
			pstmt.setInt(4, bilesen.value());
			pstmt.setInt(5, Integer.valueOf(firmaid));
			pstmt.setInt(6, Integer.valueOf(figur));
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				newMamulId = String.valueOf(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			newMamulId = e.getMessage();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				newMamulId = e.getMessage();
			}
		}
		return newMamulId;
	}
	
	protected static void mamulBilesenEkle(int mamulid, int bilesen, int birim, float miktar) {
		conn = ConnectionManager.getConnection();
		String insertQuery = "insert into irfan.mamulbilesen (mamulid, bilesenid, birimid, miktar) values (?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, mamulid);
			pstmt.setInt(2, bilesen);
			pstmt.setInt(3, birim);
			pstmt.setFloat(4, miktar);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	protected static List<MamulBilesen> mamulBilesenListeGetirTum(Integer mamulid) {
		conn = ConnectionManager.getConnection();
		List<MamulBilesen> temp = new ArrayList<MamulBilesen>();
		
		String queryFilter = (mamulid == null) ? "" : " where mamulid = " + mamulid;
		String searchQuery = "select * from irfan.mamulbilesentum" + queryFilter;
		
		// System.out.println("bilesenTip == " + bilesenTip.name());
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new MamulBilesen(rs.getInt("id"), rs.getInt("mamulid"), rs.getInt("bilesenid"),
						rs.getString("bilesenad"), rs.getString("bilesenkod"), rs.getInt("bilesentipid"),
						rs.getString("bilesentipad"), rs.getInt("birimid"), rs.getString("birimad"),
						rs.getInt("firmaid"), rs.getString("firmaad"), rs.getFloat("miktar")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Stok> stokMamulListeGetirTum(Integer mamulid) {
		conn = ConnectionManager.getConnection();
		List<Stok> temp = new ArrayList<Stok>();
		
		String filter = (mamulid == null) ? "" : "where bilesenid = " + mamulid;
		String searchQuery = "select * from irfan.stokmamultum " + filter;
		
		System.out.println(searchQuery);
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new Stok(rs.getInt("id"), rs.getInt("bilesenid"), rs.getString("bilesenad"),
						rs.getString("bilesenkod"), rs.getInt("bilesentipid"), rs.getString("bilesentipad"),
						rs.getInt("birimid"), rs.getString("birimad"), rs.getInt("firmaid"), rs.getString("firmaad"),
						rs.getInt("miktar"), null, // Stok: Kalan
						rs.getString("gkrno"), rs.getString("irsaliyeno"), rs.getString("lot"),
						rs.getTimestamp("giristarihi"), rs.getTimestamp("cikistarihi"), rs.getString("not"),
						(rs.getString("mamuldetay"))
								.split("\\|")[0], /* 0: Miktar */
						(rs.getString("mamuldetay"))
								.split("\\|")[1], /* 1: GkrNo */
						(rs.getString("mamuldetay"))
								.split("\\|")[2], /* 1: StokId */
						rs.getInt("islemyonu"), rs.getString("bilesenkod")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Stok> stokListeRapor(String tablename, int offset, String filterPage) {
		
		List<Stok> temp = new ArrayList<Stok>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
		String filter0 = filterPage;
		
		String searchQuery = "SELECT e.*, f.ad firmaad FROM irfan." + tablename
				+ " e JOIN irfan.firma f on f.id = e.firmaid" + " WHERE 1=1 " + filter0 + " and islemyonu = 0"
				+ " ORDER BY e.giristarihi, e.id " + pageFilter;
				
		System.out.println(searchQuery);
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new Stok(rs.getInt("id"), rs.getInt("bilesenid"), rs.getString("bilesenad"),
						rs.getString("bilesenkod"), rs.getInt("bilesentipid"), rs.getString("bilesentipad"),
						rs.getInt("birimid"), rs.getString("birimad"), rs.getInt("firmaid"), rs.getString("firmaad"),
						rs.getInt("miktar"), null, // rs.getInt("kalan"),
						rs.getString("gkrno"), rs.getString("irsaliyeno"), rs.getString("lot"),
						rs.getTimestamp("giristarihi"), rs.getTimestamp("cikistarihi"), rs.getString("not"), null, // rs.getString("miktarstr"),
						null, // rs.getString("gkrnostr"),
						null, // rs.getString("stokidstr"),
						rs.getInt("islemyonu"), null // rs.getString("mamulkod")
				));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Stok> stokListeGetirTum(BilesenTip bilesentip, Integer bilesenid, int offset,
			Map<String, String> param) {
		List<Stok> temp = new ArrayList<Stok>();
		conn = ConnectionManager.getConnection();
		Integer rowperpage = Genel.ROWPERPAGE;
		String where = " where 1=1 ";
		
		if (param != null) {
			rowperpage = Integer.valueOf(param.get("rowperpage"));
		}
		
		String pageFilter = (offset == 0) ? "" : " offset " + (offset - 1) * rowperpage + " limit " + rowperpage;
		
		String join0 = (bilesentip != null && bilesentip.value() == BilesenTip.MAMUL.value())
				? "join irfan.mamul b on b.id=s.bilesenid " : "join irfan.bilesen b on b.id=s.bilesenid ";
		String filter0 = (bilesentip == null) ? "" : "and b.bilesentipid IN (" + bilesentip.id() + ") ";
		
		if (bilesenid != 0) {
			filter0 = filter0 + " and s.bilesenid = " + bilesenid + " ";
		}
		
		String filter1 = (param == null) ? " " : " and islemyonu=" + param.get("islemyonu");
		
		String filter2 = (param == null) ? " "
				: (param.get("bas_tarih") != null)
						? " and ( giristarihi BETWEEN '" + Util.date_tr_to_eng(param.get("bas_tarih")) + "' AND '"
								+ Util.date_tr_to_eng(param.get("bit_tarih")) + "')"
						: "";
						
		String searchQuery = "select m2.kod mamulkod, s.*, " + "b.ad bilesenad, b.kod bilesenkod, "
				+ "bt.id bilesentipid, bt.ad bilesentipad, " + "br.id birimid, br.ad birimad, "
				+ "f.id firmaid, f.ad firmaad, (sum(CASE WHEN s.islemyonu = 0 THEN s.miktar ELSE -1*miktar END) "
				+ "OVER (ORDER BY CASE WHEN s.islemyonu=0 THEN date(s.giristarihi) ELSE date(s.cikistarihi) END, s.id)) kalan "
				+ "from irfan.stok s " + "left join irfan.uretimplan p on p.id=s.planid "
				+ "left join irfan.mamul m2 on m2.id=p.mamulid " + join0
				+ "join irfan.bilesentip bt on bt.id = b.bilesentipid "
				+ "left join irfan.birim br on br.id = b.birimid " + "join irfan.firma f on f.id = b.firmaid " + where
				+ filter0 + filter1 + filter2
				+ "order by case when s.islemyonu=0 then date(s.giristarihi) else date(s.cikistarihi) end desc, id desc "
				+ pageFilter;
				
		System.out.println(searchQuery);
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new Stok(rs.getInt("id"), rs.getInt("bilesenid"), rs.getString("bilesenad"),
						rs.getString("bilesenkod"), rs.getInt("bilesentipid"), rs.getString("bilesentipad"),
						rs.getInt("birimid"), rs.getString("birimad"), rs.getInt("firmaid"), rs.getString("firmaad"),
						rs.getInt("miktar"), rs.getInt("kalan"), rs.getString("gkrno"), rs.getString("irsaliyeno"),
						rs.getString("lot"), rs.getTimestamp("giristarihi"), rs.getTimestamp("cikistarihi"),
						rs.getString("not"), null, null, null, rs.getInt("islemyonu"), rs.getString("mamulkod")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<StokRapor> stokBilesenRapor(String tablename, String filter, int offset) {
		List<StokRapor> temp = new ArrayList<StokRapor>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
				
		String searchQuery = "SELECT b.*, null::INTEGER as stokid, -1::INTEGER as islemyonu, s.miktar, s.tarih, f.ad firmaad, bt.ad bilesentipad FROM "
				+ tablename + " LEFT JOIN (SELECT bilesenid, "
				+ " MAX(CASE WHEN islemyonu = 0 THEN giristarihi ELSE cikistarihi END) tarih, "
				+ " SUM(CASE WHEN islemyonu = 0 THEN miktar ELSE -1*miktar END) miktar FROM stok "
				+ " GROUP BY bilesenid) s on s.bilesenid=b.id " + " JOIN firma f ON f.id=b.firmaid "
				+ " JOIN bilesentip bt ON bt.id=b.bilesentipid " + " WHERE 1=1 " + filter
				+ " ORDER BY b.bilesentipid, b.ad " + pageFilter;
				
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new StokRapor(rs.getInt("id"), rs.getInt("stokid"), rs.getInt("islemyonu"), rs.getString("ad"),
						rs.getString("kod"), rs.getInt("bilesentipid"), rs.getString("bilesentipad"),
						rs.getInt("firmaid"), rs.getString("firmaad"), rs.getInt("miktar"), null, // kalan
						rs.getTimestamp("tarih")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<IzlemeNo> izlemeNoTum(String tablename, String filter, int offset) {
		List<IzlemeNo> temp = new ArrayList<IzlemeNo>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " OFFSET " + (offset - 1) * Genel.ROWPERLONGPAGE + " LIMIT " + Genel.ROWPERLONGPAGE;
				
		String searchQuery = "SELECT * FROM (SELECT * FROM " + tablename + ") e" + " WHERE 1=1 " + filter
				+ " ORDER BY e.kullanildi_tarih DESC, e.gkrno DESC" + pageFilter;
				
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new IzlemeNo(rs.getInt("id"), rs.getInt("gkrno"), rs.getDate("kullanildi_tarih"),
						rs.getString("kod"), rs.getString("ad"), rs.getInt("miktar"), rs.getInt("kullanildi")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<StokRapor> stokBilesenDetayRapor(String tablename, String filter, int offset, int bilesenid) {
		List<StokRapor> temp = new ArrayList<StokRapor>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
				
		String searchQuery = "SELECT b.*, s.id stokid, s.islemyonu, s.miktar,f.ad firmaad,bt.ad bilesentipad, "
				+ "s.islemyonu, case when s.islemyonu=0 THEN s.giristarihi ELSE s.cikistarihi END as tarih, "
				+ "sum(CASE s.islemyonu WHEN 1 THEN (-1) * s.miktar ELSE 1 * s.miktar END) OVER "
				+ "(PARTITION BY s.bilesenid ORDER BY s.id) kalan " + "FROM " + tablename
				+ " LEFT JOIN stok s on s.bilesenid=b.id " + " JOIN firma f ON f.id=b.firmaid "
				+ " JOIN bilesentip bt ON bt.id=b.bilesentipid " + " WHERE 1=1 " + filter + " ORDER BY stokid DESC "
				+ pageFilter;
				
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new StokRapor(rs.getInt("id"), rs.getInt("stokid"), rs.getInt("islemyonu"), rs.getString("ad"),
						rs.getString("kod"), rs.getInt("bilesentipid"), rs.getString("bilesentipad"),
						rs.getInt("firmaid"), rs.getString("firmaad"), rs.getInt("miktar"), rs.getInt("kalan"),
						rs.getTimestamp("tarih")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<UretimPlan> uretimBilesenRapor(String tablename, int offset, String filterPage) {
		List<UretimPlan> temp = new ArrayList<UretimPlan>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
		String filter0 = filterPage;
		
		String searchQuery = "SELECT * FROM irfan." + tablename + " e " + " WHERE 1=1 " + filter0
				+ " ORDER BY e.tarih, e.id " + pageFilter;
				
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new UretimPlan(rs.getInt("id"), rs.getInt("mamulid"), rs.getString("mamulad"),
						rs.getString("mamulkod"),
						Util.mamulInfoDecompose(rs.getString("hammadde"),
								1), /* -- 1: hammadde adi icin */
						rs.getString("yarimamul"),
						
						rs.getInt("planlananmiktar"), rs.getInt("uretilenmiktar"), rs.getInt("hatalimiktar"),
						
						rs.getInt("makinaid"), rs.getString("makinaad"),
						
						rs.getInt("firmaid"), rs.getString("firmaad"),
						
						rs.getInt("calisanid"), rs.getString("calisanad"), rs.getString("calisansoyad"),
						
						rs.getInt("hataid"), rs.getString("hataad"),
						(rs.getString("hatakodu") == null) ? "" : (rs.getString("hatakodu")), rs.getInt("durusid"),
						rs.getString("durusad"), (rs.getString("duruskodu") == null) ? "" : (rs.getString("duruskodu")),
						rs.getInt("duruszaman"),
						
						rs.getDate("tarih"), rs.getTime("baszaman"), rs.getTime("bitzaman"),
						
						rs.getInt("mamulizleno"),
						(rs.getString("hammadde") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("hammadde"),
										4), /*
											 * -- 1: hammadde izle no adi icin
											 */
						(rs.getString("yarimamul") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("yarimamul"),
										4), /*
											 * -- 1: yarimamul izle no adi icin
											 */
						rs.getInt("tamamlandi"),
						(rs.getString("yarimamul") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("hammadde"),
										3), /*
											 * -- 3: hammadde toplam agirlik
											 * icin
											 */
						rs.getInt("cevrimsuresi")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<DurusSebep> durusSebepListeGetir() {
		List<DurusSebep> temp = new ArrayList<DurusSebep>();
		conn = ConnectionManager.getConnection();
		
		String searchQuery = "select * from irfan.durussebep order by ad";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new DurusSebep(rs.getInt("id"), rs.getString("ad"), rs.getString("kod")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<HataSebep> hataSebepListeGetir() {
		List<HataSebep> temp = new ArrayList<HataSebep>();
		conn = ConnectionManager.getConnection();
		
		String searchQuery = "select * from irfan.hatasebep order by ad";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				temp.add(new HataSebep(rs.getInt("id"), rs.getString("ad"), rs.getString("kod")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Bilesen> bilesenListeGetirTum(BilesenTip bilesentip, int offset, Integer bilesenid) {
		List<Bilesen> temp = new ArrayList<Bilesen>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
				
		String filter1 = (bilesentip == null) ? "" : " and bilesentipid IN (" + bilesentip.id() + ") ";
		String filter2 = (bilesenid == null) ? "" : " and b.id = " + bilesenid + " ";
		String filter0 = " where 1=1 " + filter1 + filter2;
		
		String searchQuery = "select b.*, bt.ad bilesentipad, f.ad firmaad, bm.ad birimad " + "from irfan.bilesen b "
				+ "join irfan.bilesentip bt on bt.id = b.bilesentipid "
				+ "left join irfan.birim bm on bm.id = b.birimid " + "join irfan.firma f on f.id = b.firmaid " + filter0
				+ "order by b.kod ASC " + pageFilter;
				
		// connect to DB
		PreparedStatement pstmt = null;
		// System.out.println(searchQuery);
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(searchQuery);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(
						new Bilesen(rs.getInt("id"), rs.getString("ad"), rs.getString("kod"), rs.getInt("bilesentipid"),
								rs.getString("bilesentipad"), rs.getInt("birimid"), rs.getString("birimad"),
								rs.getInt("firmaid"), rs.getString("firmaad"), rs.getDate("eklemetarihi")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Mamul> mamulListeGetir(Integer mamulid, int offset) {
		List<Mamul> temp = new ArrayList<Mamul>();
		conn = ConnectionManager.getConnection();
		
		String queryFilter = (mamulid == null) ? "" : " where id = " + mamulid;
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
		String searchQuery = "select * from irfan.mamultum" + queryFilter + " ORDER BY kod ASC "
				+ pageFilter; /* view */
								
		// connect to DB
		// System.out.println(searchQuery);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new Mamul(rs.getInt("id"), rs.getString("ad"), rs.getString("kod"), rs.getInt("firmaid"),
						rs.getString("firmaad"), rs.getInt("cevrimsuresi"), rs.getFloat("agirlik"), rs.getInt("figur"),
						rs.getDate("eklemetarihi"),
						Util.mamulInfoDecompose(rs.getString("hammadde"),
								1), /* -- 1: hammadde adi icin */
						Util.mamulInfoDecompose(rs.getString("hammadde"),
								4), /* -- 1: hammadde gkrno icin */
						Util.mamulInfoDecompose(rs.getString("hammadde"),
								3), /* -- 1: hammadde agirlik icin */
						Util.mamulInfoDecompose(rs.getString("hammadde"),
								5), /* -- 1: hammadde mamul basi icin */
						Util.mamulInfoDecompose(rs.getString("yarimamul"),
								1), /* -- 1: yarimamul adi icin */
						Util.mamulInfoDecompose(rs.getString("yarimamul"),
								4), /* -- 1: yarimamul gkrno icin */
						Util.mamulInfoDecompose(rs.getString("yarimamul"),
								3), /* -- 1: yarimamul agirlik icin */
						Util.mamulInfoDecompose(rs.getString("yarimamul"),
								5) /* -- 1: yarimamul mamul basi icin */
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Siparis> siparisEkle(String bilesenid, String miktar, String not) {
		List<Siparis> temp = new ArrayList<Siparis>();
		conn = ConnectionManager.getConnection();
		
		String insertQuery = "insert into irfan.siparis ( bilesenid, miktar, \"not\") values (?,?,?)";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		@SuppressWarnings("unused")
		Integer newSiparisId = 0;
		try {
			pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, Integer.valueOf(bilesenid));
			pstmt.setInt(2, Integer.valueOf(miktar));
			pstmt.setString(3, not);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				newSiparisId = rs.getInt(1);
			}
			temp = siparisListeGetir(SiparisTip.BEKLEYEN);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Siparis> siparisListeGetir(SiparisTip siparis) {
		List<Siparis> temp = new ArrayList<Siparis>();
		conn = ConnectionManager.getConnection();
		
		String filter = null;
		if (siparis == SiparisTip.BEKLEYEN) {
			filter = " where s.bitistarih is null ";
		} else if (siparis == SiparisTip.TAMAMLANMIS) {
			filter = " where s.bitistarih is not null ";
		} else {
			filter = " ";
		}
		
		String searchQuery = "select s.*, b.ad bilesenad, "
				+ "coalesce((select sum(miktar) from irfan.siparisplan sp where sp.siparisid=s.id),0) as planmiktar, "
				+ "coalesce((select sum(miktar) from irfan.siparisplan sp where sp.siparisid=s.id and sp.tamamlandi=1),0) as tamammiktar "
				+ "from irfan.siparis s join irfan.bilesen b on s.bilesenid=b.id " + filter + "order by s.id";
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new Siparis(rs.getInt("id"), rs.getInt("bilesenid"), rs.getString("bilesenad"),
						rs.getInt("miktar"), rs.getInt("planmiktar"), rs.getInt("tamammiktar"),
						rs.getTimestamp("tarih"), rs.getTimestamp("bitistarih"), rs.getString("not")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<UretimPlan> uretimPlanListeGetir(UretimDurum uretimdurum, int offset, String filter,
			String view) {
		List<UretimPlan> temp = new ArrayList<UretimPlan>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
				
		String filter0 = null;
		if (uretimdurum == UretimDurum.BEKLEYEN) {
			filter0 = " where tamamlandi=0 " + filter;
		} else if (uretimdurum == UretimDurum.TAMAMLANMIS) {
			filter0 = " where tamamlandi=1 " + filter;
		} else {
			filter0 = " ";
		}
		
		String searchQuery = "select * from irfan.uretimplantum_" + view + " " /* view */
				+ filter0 + " order by id " + pageFilter;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			
			if (Genel.LOGMOD == LogMod.DEBUG) {
				System.out.println(pstmt);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new UretimPlan(rs.getInt("id"), rs.getInt("mamulid"), rs.getString("mamulad"),
						rs.getString("mamulkod"),
						Util.mamulInfoDecompose(rs.getString("hammadde"),
								1), /* -- 1: hammadde adi icin */
						rs.getString("yarimamul"),
						
						rs.getInt("planlananmiktar"), rs.getInt("uretilenmiktar"), rs.getInt("hatalimiktar"),
						
						rs.getInt("makinaid"), rs.getString("makinaad"),
						
						rs.getInt("firmaid"), rs.getString("firmaad"),
						
						rs.getInt("calisanid"), rs.getString("calisanad"), rs.getString("calisansoyad"),
						
						rs.getInt("hataid"), rs.getString("hataad"),
						(rs.getString("hatakodu") == null) ? "" : (rs.getString("hatakodu")), rs.getInt("durusid"),
						rs.getString("durusad"), (rs.getString("duruskodu") == null) ? "" : (rs.getString("duruskodu")),
						rs.getInt("duruszaman"),
						
						rs.getDate("tarih"), rs.getTime("baszaman"), rs.getTime("bitzaman"),
						
						rs.getInt("mamulizleno"),
						(rs.getString("hammadde") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("hammadde"),
										4), /*
											 * -- 1: hammadde izle no adi icin
											 */
						(rs.getString("yarimamul") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("yarimamul"),
										4), /*
											 * -- 1: yarimamul izle no adi icin
											 */
						rs.getInt("tamamlandi"),
						(rs.getString("yarimamul") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("hammadde"),
										3), /*
											 * -- 3: hammadde toplam agirlik
											 * icin
											 */
						rs.getInt("cevrimsuresi")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<UretimPlan> uretimPlanListeGetirExcel(UretimDurum uretimdurum, String tarih,
			String excelsql) {
		List<UretimPlan> temp = new ArrayList<UretimPlan>();
		conn = ConnectionManager.getConnection();
		
		System.out.println("excel: " + excelsql);
		
		// String searchQuery ="SELECT * FROM irfan.uretimplantum WHERE
		// tamamlandi=? AND tarih=? "; /* view */
		String searchQuery = excelsql; /* view */
		// System.out.println(searchQuery);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			// pstmt.setInt(1, uretimdurum.value());
			// pstmt.setDate(2,
			// Date.valueOf(UtilFunctions.date_tr_to_eng(tarih)));
			rs = pstmt.executeQuery();
			System.out.println(pstmt);
			
			while (rs.next()) {
				temp.add(new UretimPlan(rs.getInt("id"), rs.getInt("mamulid"), rs.getString("mamulad"),
						rs.getString("mamulkod"),
						Util.mamulInfoDecompose(rs.getString("hammadde"),
								1), /* -- 1: hammadde adi icin */
						rs.getString("yarimamul"),
						
						rs.getInt("planlananmiktar"), rs.getInt("uretilenmiktar"), rs.getInt("hatalimiktar"),
						
						rs.getInt("makinaid"), rs.getString("makinaad"),
						
						rs.getInt("firmaid"), rs.getString("firmaad"),
						
						rs.getInt("calisanid"), rs.getString("calisanad"), rs.getString("calisansoyad"),
						
						rs.getInt("hataid"), rs.getString("hataad"),
						(rs.getString("hatakodu") == null) ? "" : (rs.getString("hatakodu")), rs.getInt("durusid"),
						rs.getString("durusad"), (rs.getString("duruskodu") == null) ? "" : (rs.getString("duruskodu")),
						rs.getInt("duruszaman"),
						
						rs.getDate("tarih"), rs.getTime("baszaman"), rs.getTime("bitzaman"),
						
						rs.getInt("mamulizleno"),
						(rs.getString("hammadde") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("hammadde"),
										4), /*
											 * -- 1: hammadde izle no adi icin
											 */
						(rs.getString("yarimamul") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("yarimamul"),
										4), /*
											 * -- 1: yarimamul izle no adi icin
											 */
						rs.getInt("tamamlandi"),
						(rs.getString("yarimamul") == null) ? ""
								: Util.mamulInfoDecompose(rs.getString("hammadde"),
										3), /*
											 * -- 3: hammadde toplam agirlik
											 * icin
											 */
						rs.getInt("cevrimsuresi")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<SiparisPlan> siparisPlanEkle(String siparisid, String miktar, String makinaid,
			String calisanid, String tarih, String bassaat, String bitsaat, String not) {
		List<SiparisPlan> temp = new ArrayList<SiparisPlan>();
		conn = ConnectionManager.getConnection();
		
		String insertQuery = "insert into irfan.siparisplan (siparisid, miktar, makinaid, calisanid, tarih, bassaat, bitsaat, \"not\") "
				+ " values (?,?,?,?,?,?,?,?)";
		String searchQuery = "select sp.*,b.ad bilesenad, m.ad makinaad, c.ad calisanad, c.soyad calisansoyad from irfan.siparisplan sp "
				+ "join irfan.siparis s on s.id=sp.siparisid " + "join irfan.bilesen b on b.id=s.bilesenid "
				+ "join irfan.makina m on m.id=sp.makinaid " + "join irfan.calisan c on c.id=sp.calisanid "
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
			pstmt.setTime(6, Time.valueOf(bassaat));
			pstmt.setTime(7, Time.valueOf(bitsaat));
			pstmt.setString(8, not);
			
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(searchQuery);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new SiparisPlan(rs.getInt("id"), rs.getInt("siparisid"), rs.getString("bilesenad"),
						rs.getInt("miktar"), rs.getInt("makinaid"), rs.getString("makinaad"), rs.getInt("calisanid"),
						rs.getString("calisanad"), rs.getString("calisansoyad"), rs.getDate("tarih"),
						rs.getTime("bassaat"), rs.getTime("bitsaat"), rs.getInt("hataid"), rs.getInt("hatamiktar"),
						rs.getInt("durusid"), rs.getString("not")));
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static int uretimTakipEkleGuncelle(String mamulid, String uretilenmiktar, String hatalimiktar,
			String makinaid, String calisanid, String tarih, String baszaman, String bitzaman, String hataid,
			String durusid, String duruszaman, Integer uretimplanid, Integer mamulizleno) {
		int result = -1;
		conn = ConnectionManager.getConnection();
		
		String insertQuery = null;
		
		if (uretimplanid == 0) {
			insertQuery = "insert into irfan.uretimplan (" + "mamulid, " + "uretilenmiktar, " + "hatalimiktar, "
					+ "makinaid, " + "calisanid, " + "tarih, " + "baszaman, " + "bitzaman, " + "hataid, " + "durusid, "
					+ "duruszaman, " + "mamulizleno " + ")" + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
		} else {
			insertQuery = "update irfan.uretimplan set " + "mamulid = ?, " + "uretilenmiktar = ?, "
					+ "hatalimiktar = ?, " + "makinaid = ?, " + "calisanid = ?, " + "tarih = ?, " + "baszaman = ?, "
					+ "bitzaman = ?, " + "hataid = ?, " + "durusid = ?, " + "duruszaman = ?, " + "mamulizleno = ? "
					+ "where id  = ?";
		}
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, Integer.valueOf(mamulid));
			pstmt.setInt(2, Integer.valueOf(uretilenmiktar));
			
			if (hatalimiktar == null || hatalimiktar.trim().equals("")) pstmt.setNull(3, Types.INTEGER);
			else pstmt.setInt(3, Integer.valueOf(hatalimiktar));
			
			pstmt.setInt(4, Integer.valueOf(makinaid));
			pstmt.setInt(5, Integer.valueOf(calisanid));
			
			pstmt.setDate(6, Date.valueOf(Util.date_tr_to_eng(tarih)));
			pstmt.setTime(7, Time.valueOf(baszaman));
			pstmt.setTime(8, Time.valueOf(bitzaman));
			
			if (hataid == null || hataid.trim().equals("")) pstmt.setNull(9, Types.INTEGER);
			else pstmt.setInt(9, Integer.valueOf(hataid));
			
			if (durusid == null || durusid.trim().equals("")) pstmt.setNull(10, Types.INTEGER);
			else pstmt.setInt(10, Integer.valueOf(durusid));
			
			if (duruszaman == null || duruszaman.trim().equals("")) pstmt.setNull(11, Types.INTEGER);
			else pstmt.setInt(11, Integer.valueOf(duruszaman));
			
			if (uretimplanid != 0) { /* Guncelleme */
				pstmt.setInt(12, mamulizleno);
				pstmt.setInt(13, uretimplanid);
			} else { /* Ekleme */
				pstmt.setInt(12, mamulizleno);
			}
			
			System.out.println(pstmt);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String uretimPlanGuncelle(Integer gerceklesenmiktar, Integer planlananmiktar, Integer hatalimiktar,
			Integer hataid, Integer uretimplanid) {
		String result = "0";
		conn = ConnectionManager.getConnection();
		
		String updateQuery = "update irfan.uretimplan set " + "uretilenmiktar = ?, " + "planlananmiktar = ?, "
				+ "hatalimiktar = ?, " + "hataid = ? " + "where id = ?";
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, gerceklesenmiktar);
			pstmt.setInt(2, planlananmiktar);
			
			if (hatalimiktar == null) pstmt.setNull(3, Types.INTEGER);
			else pstmt.setInt(3, hatalimiktar);
			
			if (hataid == null) pstmt.setNull(4, Types.INTEGER);
			else pstmt.setInt(4, hataid);
			
			pstmt.setInt(5, uretimplanid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static List<SiparisPlan> siparisPlanListeGetir(SiparisTip siparistip, int offset) {
		List<SiparisPlan> temp = new ArrayList<SiparisPlan>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
				
		String searchQuery = "select sp.*, mam.ad bilesenad, m.ad makinaad, c.ad calisanad, c.soyad calisansoyad from irfan.siparisplan sp "
				+ "join irfan.siparis s on s.id=sp.siparisid " + "join irfan.mamul mam on mam.id=s.bilesenid "
				+ "join irfan.makina m on m.id=sp.makinaid " + "join irfan.calisan c on c.id=sp.calisanid "
				+ "where sp.tamamlandi=? " + "order by s.id, sp.id " + pageFilter;
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setInt(1, siparistip.value());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new SiparisPlan(rs.getInt("id"), rs.getInt("siparisid"), rs.getString("bilesenad"),
						rs.getInt("miktar"), rs.getInt("makinaid"), rs.getString("makinaad"), rs.getInt("calisanid"),
						rs.getString("calisanad"), rs.getString("calisansoyad"), rs.getDate("tarih"),
						rs.getTime("bassaat"), rs.getTime("bitsaat"), rs.getInt("hataid"), rs.getInt("hatamiktar"),
						rs.getInt("durusid"), rs.getString("not")));
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static Integer SiparisPlanGuncelle(String siparisplanid, String miktar, String makinaid, String calisanid,
			String tarih, String bassaat, String basdakika, String bitsaat, String bitdakika, String hataid,
			String hatamiktar, String durusid, String not) {
			
		conn = ConnectionManager.getConnection();
		int result = 0;
		String updateQuery = "update irfan.siparisplan " + "set " + "miktar = ? ," + "makinaid  = ? ,"
				+ "calisanid  = ? ," + "tarih  = ? ," + "bassaat  = ? ," + "bitsaat  = ? ," + "hataid  = ? ,"
				+ "hatamiktar  = ? ," + "durusid  = ? ," + "\"not\"  = ? " + "where id = ?";
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(miktar));
			pstmt.setInt(2, Integer.valueOf(makinaid));
			pstmt.setInt(3, Integer.valueOf(calisanid));
			pstmt.setDate(4, Date.valueOf(tarih));
			pstmt.setTime(5, Time.valueOf(bassaat + ":" + basdakika + ":00"));
			pstmt.setTime(6, Time.valueOf(bitsaat + ":" + bitdakika + ":00"));
			if (hataid != null && hataid.trim() != "") pstmt.setInt(7, Integer.valueOf(hataid));
			else pstmt.setNull(7, Types.INTEGER);
			if (hatamiktar != null && (hatamiktar.trim() != "" && Integer.valueOf(hatamiktar.trim()) != 0))
				pstmt.setInt(8, Integer.valueOf(hatamiktar));
			else pstmt.setNull(8, Types.INTEGER);
			if (durusid != null && durusid.trim() != "") pstmt.setInt(9, Integer.valueOf(durusid));
			else pstmt.setNull(9, Types.INTEGER);
			if (not != null && not.trim() != "") pstmt.setString(10, not);
			else pstmt.setNull(10, Types.VARCHAR);
			pstmt.setInt(11, Integer.valueOf(siparisplanid));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static Integer SiparisPlanTamamla(String siparisplanid) {
		
		conn = ConnectionManager.getConnection();
		int result = 0;
		String updateQuery = "update irfan.siparisplan set tamamlandi  = ? where id = ?";
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, SiparisTip.TAMAMLANMIS.value());
			pstmt.setInt(2, Integer.valueOf(siparisplanid));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static Integer SiparisPlanSil(String siparisplanid) {
		
		conn = ConnectionManager.getConnection();
		int result = 0;
		String updateQuery = "delete from irfan.siparisplan where id = ?";
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(siparisplanid));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String uretimPlanSil(Integer uretimplanid) {
		
		conn = ConnectionManager.getConnection();
		String retVal = "0";
		String deleteQuery = "select irfan.plan_sil(?)";
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(deleteQuery);
			pstmt.setInt(1, uretimplanid);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static String hammaddeStokSil(Integer stokid) {
		
		conn = ConnectionManager.getConnection();
		String retVal = "0";
		String deleteQuery = "select irfan.hammaddestok_sil(?)";
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(deleteQuery);
			pstmt.setInt(1, stokid);
			
			// System.out.println(pstmt);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
				// System.out.println("Sonuc: " + retVal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static String stokYeterli(Integer uretimplanid, Integer mamulid, Integer miktar) {
		String retVal = "0";
		conn = ConnectionManager.getConnection();
		
		String insertQuery = "SELECT irfan.stok_yeterli(?,?,?) ";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, uretimplanid);
			pstmt.setInt(2, mamulid);
			pstmt.setInt(3, miktar);
			
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static String uretimPlanOk(Integer uretimplanid) {
		
		conn = ConnectionManager.getConnection();
		String retVal = "0";
		String planQuery = "SELECT irfan.plan_ok (?) ";
		/*
		 * String mamulPlanQuery =
		 * "select b.*, coalesce( p.uretilenmiktar,0) uretilenmiktar, coalesce(p.hatalimiktar,0) hatalimiktar "
		 * + "from irfan.mamulbilesen b " +
		 * "join irfan.uretimplan p on b.mamulid=p.mamulid where p.id = ?";
		 */
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(planQuery);
			pstmt.setInt(1, uretimplanid);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static String stokEkle(String bilesenid, String miktar, String irsaliyeno, String lot, String gkrno,
			String tarih, String not) {
		conn = ConnectionManager.getConnection();
		String retVal = "0";
		
		String insertQuery = "SELECT irfan.stok_ekle(?,?,?,?,?,?,?,?) ";
		
		PreparedStatement pstmt = null;
		try {
			System.out.println(tarih);
			
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setNull(1, Types.INTEGER); // planid
			pstmt.setInt(2, Integer.valueOf(bilesenid));
			pstmt.setBigDecimal(3, BigDecimal.valueOf(Double.valueOf(miktar)));
			if (gkrno != null) pstmt.setInt(4, Integer.valueOf(gkrno));
			else pstmt.setNull(4, Types.INTEGER);
			if (irsaliyeno != null) pstmt.setString(5, irsaliyeno);
			else pstmt.setNull(5, Types.VARCHAR);
			if (lot != null) pstmt.setString(6, lot);
			else pstmt.setNull(6, Types.VARCHAR);
			if (not != null) pstmt.setString(7, not);
			else pstmt.setNull(7, Types.VARCHAR);
			if (tarih != null) pstmt.setTimestamp(8, Util.date_tr_to_timestamp(tarih));
			else pstmt.setNull(8, Types.TIMESTAMP);
			
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
			}
		} catch (SQLException e) {
			retVal = e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				retVal = e.getMessage();
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	public static String stokDus(String bilesenid, String miktar, String irsaliyeno, String lot, String gkrno,
			String tarih, String not) {
		String retVal = "-1";
		conn = ConnectionManager.getConnection();
		String updateQuery = "SELECT irfan.stok_dus(?,?,?,?,?,?,?,?) ";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setNull(1, Types.INTEGER); // Planid
			pstmt.setInt(2, Integer.valueOf(bilesenid));
			pstmt.setBigDecimal(3, BigDecimal.valueOf(Double.valueOf(miktar)));
			if (gkrno != null) pstmt.setInt(4, Integer.valueOf(gkrno));
			else pstmt.setNull(4, Types.INTEGER);
			if (irsaliyeno != null) pstmt.setString(5, irsaliyeno);
			else pstmt.setNull(5, Types.VARCHAR);
			if (lot != null) pstmt.setString(6, lot);
			else pstmt.setNull(6, Types.VARCHAR);
			if (not != null) pstmt.setString(7, not);
			else pstmt.setNull(7, Types.VARCHAR);
			if (tarih != null) pstmt.setTimestamp(8, Util.date_tr_to_timestamp(tarih));
			else pstmt.setNull(8, Types.TIMESTAMP);
			
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	protected static int irsaliyeEkle(String irsaliyeno, String tarih, String firmaid) {
		conn = ConnectionManager.getConnection();
		String insertQuery = "insert into irfan.irsaliye (irsaliyeno, olusturmatarihi, firmaid) values (?,?,?) ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int newIrsaliyeId = -1;
		try {
			pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, irsaliyeno);
			pstmt.setTimestamp(2, Util.date_tr_to_timestamp(tarih));
			pstmt.setInt(3, Integer.valueOf(firmaid));
			
			System.out.println(pstmt);
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				newIrsaliyeId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newIrsaliyeId;
	}
	
	protected static String irsaliyeBilesenEkle(int irsaliyeid, int mamulid, int miktar, String not) {
		conn = ConnectionManager.getConnection();
		String retVal = "-1";
		
		String insertQuery = "SELECT irfan.irsaliyebilesen_ekle (?,?,?,?) ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, irsaliyeid);
			pstmt.setInt(2, mamulid);
			pstmt.setInt(3, miktar);
			if (not != null) pstmt.setString(4, not);
			else pstmt.setNull(4, Types.VARCHAR);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static int irsaliyeListeGetirCount(IrsaliyeTip irsaliyetip, String irsaliyeid, String firmaid,
			String tarih) {
		int temp = 0;
		conn = ConnectionManager.getConnection();
		
		String irsaliyeFilter = (irsaliyeid == null) ? "" : " and id = " + Integer.valueOf(irsaliyeid) + " ";
		String tarihFilter = (tarih == null) ? "" : " and date(gonderimtarihi) = '" + Util.date_tr_to_eng(tarih) + "' ";
		String firmaFilter = (firmaid == null) ? ""
				: "" + " and id IN (" + "   select irsaliyeid from irfan.irsaliyebilesen ib "
						+ "   join irfan.mamul m on m.id = ib.mamulid " + "   join irfan.firma f on f.id = m.firmaid "
						+ "   WHERE f.id= " + Integer.valueOf(firmaid) + ")";
						
		String searchQuery = "select count(*) from irfan.irsaliye " + "where kapatildi=1 and onaylandi = ? "
				+ firmaFilter + tarihFilter + irsaliyeFilter;
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setInt(1, irsaliyetip.value());
			
			if (Genel.LOGMOD == LogMod.DEBUG) {
				System.out.println(pstmt);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Irsaliye> irsaliyeListeGetirTum(IrsaliyeTip irsaliyetip, int offset, String irsaliyeid,
			String firmaid, String tarih) {
		List<Irsaliye> temp = new ArrayList<Irsaliye>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
		String irsaliyeFilter = (irsaliyeid == null) ? "" : " and i.id = " + Integer.valueOf(irsaliyeid) + " ";
		String tarihFilter = (tarih == null) ? "" : " and date(gonderimtarihi) = '" + Util.date_tr_to_eng(tarih) + "' ";
		String firmaFilter = (firmaid == null) ? "" : " and f.id= " + Integer.valueOf(firmaid) + " ";
		
		String searchQuery = "select i.*, f.ad firmaad from irfan.irsaliye i "
				// + "join irfan.irsaliyebilesen ib on ib.irsaliyeid = i.id "
				+ "left join firma f on f.id=i.firmaid " + "where i.kapatildi=1 and i.onaylandi = ? " + firmaFilter
				+ irsaliyeFilter + tarihFilter + "order by i.id DESC " + pageFilter;
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setInt(1, irsaliyetip.value());
			
			if (Genel.LOGMOD == LogMod.DEBUG) {
				System.out.println(pstmt);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new Irsaliye(rs.getInt("id"), rs.getString("irsaliyeno"), rs.getTimestamp("olusturmatarihi"),
						rs.getTimestamp("gonderimtarihi"), rs.getInt("firmaid"), rs.getString("firmaad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<Irsaliye> irsaliyeListeGetirKapaliTum(IrsaliyeTip irsaliyetip, int offset) {
		List<Irsaliye> temp = new ArrayList<Irsaliye>();
		conn = ConnectionManager.getConnection();
		
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
				
		String searchQuery = "select i.*, f.ad firmaad from irfan.irsaliye i "
				// + "join irfan.irsaliyebilesen ib on ib.irsaliyeid = i.id "
				+ "left join firma f on f.id=i.firmaid " + "where i.kapatildi= ? " + "order by i.id DESC " + pageFilter;
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setInt(1, irsaliyetip.value());
			
			if (Genel.LOGMOD == LogMod.DEBUG) {
				System.out.println(pstmt);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new Irsaliye(rs.getInt("id"), rs.getString("irsaliyeno"), rs.getTimestamp("olusturmatarihi"),
						rs.getTimestamp("gonderimtarihi"), rs.getInt("firmaid"), rs.getString("firmaad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<IrsaliyeBilesen> irsaliyeBilesenListeGetirTum(IrsaliyeTip irsaliyetip, int onaylandi,
			String irsaliyeid, String firmaid, String tarih, int offset) {
		List<IrsaliyeBilesen> temp = new ArrayList<IrsaliyeBilesen>();
		conn = ConnectionManager.getConnection();
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
		String irsaliyeFilter = (irsaliyeid == null) ? "" : " and x.id = " + Integer.valueOf(irsaliyeid) + " ";
		String tarihFilter = (tarih == null) ? "" : " and date(gonderimtarihi) = '" + Util.date_tr_to_eng(tarih) + "' ";
		String firmaFilter = (firmaid == null) ? "" : " and f.id = " + Integer.valueOf(firmaid) + " ";
		
		/* acik olan irsaliye bilsenlerini getirir */
		String searchQuery = "select ib.*, i.olusturmatarihi, i.gonderimtarihi, i.irsaliyeno, i.firmaid irsaliyefirmaid, "
				+ "m.ad mamulad, m.kod mamulkod, fb.id firmaid, fb.ad firmaad " + "from irfan.irsaliyebilesen ib "
				+ "join (select x.* from irfan.irsaliye x left join irfan.firma f on f.id = x.firmaid where x.kapatildi=? and x.onaylandi=? "
				+ firmaFilter + irsaliyeFilter + tarihFilter + " order by x.id DESC " + pageFilter
				+ " ) i on i.id=ib.irsaliyeid " + "join irfan.mamul m on m.id = ib.mamulid "
				+ "join irfan.firma fb on fb.id = m.firmaid " + "order by i.id desc, ib.id ";
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setInt(1, irsaliyetip.value());
			pstmt.setInt(2, onaylandi);
			
			if (Genel.LOGMOD == LogMod.DEBUG) {
				System.out.println(pstmt);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new IrsaliyeBilesen(rs.getInt("id"), rs.getInt("irsaliyeid"), rs.getString("irsaliyeno"),
						rs.getInt("firmaid"), rs.getString("firmaad"), rs.getInt("mamulid"), rs.getString("mamulad"),
						rs.getString("mamulkod"), rs.getInt("gkrno"), rs.getInt("stokid"), rs.getInt("miktar"),
						rs.getTimestamp("olusturmatarihi"), rs.getTimestamp("gonderimtarihi"), rs.getString("not"),
						rs.getInt("irsaliyefirmaid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<IrsaliyeBilesen> irsaliyeBilesenListeTum(IrsaliyeTip irsaliyetip, int onaylandi,
			String irsaliyeid, String firmaid, String bas_tarih, String bit_tarih, int offset, String mamulid) {
		List<IrsaliyeBilesen> temp = new ArrayList<IrsaliyeBilesen>();
		conn = ConnectionManager.getConnection();
		String pageFilter = (offset == 0) ? ""
				: " offset " + (offset - 1) * Genel.ROWPERPAGE + " limit " + Genel.ROWPERPAGE;
		String irsaliyeFilter = (irsaliyeid == null || irsaliyeid.equals("")) ? ""
				: " and e.id = " + Integer.valueOf(irsaliyeid) + " ";
		String tarihFilter = (bas_tarih != null && bit_tarih != null) ? " and (date(gonderimtarihi) BETWEEN '"
				+ Util.date_tr_to_eng(bas_tarih) + "' AND '" + Util.date_tr_to_eng(bit_tarih) + "')" : "";
		String firmaFilter = (firmaid == null || firmaid.equals("")) ? ""
				: " and e.firmaid = " + Integer.valueOf(firmaid) + " ";
		String mamulFilter = (mamulid == null || mamulid.equals("")) ? ""
				: " where ib.mamulid = " + Integer.valueOf(mamulid) + " ";
		String mamulFilterIrs = (mamulid == null || mamulid.equals("")) ? ""
				: " and exists (select 1 from irsaliyebilesen ib where e.id = ib.irsaliyeid and ib.mamulid = "
						+ Integer.valueOf(mamulid) + ") ";
						
		/* acik olan irsaliye bilsenlerini getirir */
		String searchQuery = "select ib.*, i.olusturmatarihi, i.gonderimtarihi, i.irsaliyeno, i.firmaid irsaliyefirmaid, "
				+ "m.ad mamulad, m.kod mamulkod, f.id firmaid, f.ad firmaad " + "from irfan.irsaliyebilesen ib "
				+ "join (select * " + "from irfan.irsaliye e " + "where e.kapatildi=? and e.onaylandi=? " + firmaFilter
				+ irsaliyeFilter + tarihFilter + mamulFilterIrs + " " + "order by e.id DESC " + pageFilter
				+ " ) i on i.id=ib.irsaliyeid " + "join irfan.mamul m on m.id = ib.mamulid "
				+ "join irfan.firma f on f.id = m.firmaid " + mamulFilter + " " + "order by i.id desc, ib.id ";
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(searchQuery);
			pstmt.setInt(1, irsaliyetip.value());
			pstmt.setInt(2, onaylandi);
			
			if (Genel.LOGMOD == LogMod.DEBUG) {
				System.out.println(pstmt);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				temp.add(new IrsaliyeBilesen(rs.getInt("id"), rs.getInt("irsaliyeid"), rs.getString("irsaliyeno"),
						rs.getInt("firmaid"), rs.getString("firmaad"), rs.getInt("mamulid"), rs.getString("mamulad"),
						rs.getString("mamulkod"), rs.getInt("gkrno"), rs.getInt("stokid"), rs.getInt("miktar"),
						rs.getTimestamp("olusturmatarihi"), rs.getTimestamp("gonderimtarihi"), rs.getString("not"),
						rs.getInt("irsaliyefirmaid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static String IrsaliyeSil(String irsaliyeid) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "select irfan.irsaliye_sil(?)";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(irsaliyeid));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static Integer IrsaliyeBilesenSil(String irsaliyebilesenid) {
		
		conn = ConnectionManager.getConnection();
		Integer result = -1;
		String updateQuery = "delete from irfan.irsaliyebilesen where id = ?";
		
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(irsaliyebilesenid));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String IrsaliyeNoKontrol(String irsaliyeno) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "select count(*) from irfan.irsaliye where trim(irsaliyeno) = ?";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, irsaliyeno);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String IrsaliyeOnayla(String irsaliyeid) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "SELECT irfan.irsaliye_onayla(?)";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(irsaliyeid));
			
			System.out.println(irsaliyeid);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static void IrsaliyeKapat(String irsaliyeid) {
		
		conn = ConnectionManager.getConnection();
		String updateQuery = "update irfan.irsaliye set kapatildi=1 where id = ?";
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(irsaliyeid));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
	protected static String bilesenSil(String bilesenid) {
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "select irfan.bilesen_sil(?)";
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(bilesenid));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String mamulSil(String mamulid) {
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "select irfan.mamul_sil(?)";
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(mamulid));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static List<IzlemeNo> IzlemeNoUret(String mamulid, String kullanildi) {
		
		List<IzlemeNo> temp = new ArrayList<IzlemeNo>();
		conn = ConnectionManager.getConnection();
		String updateQuery = "insert into irfan.izlemeno (mamulid) values(?)";
		
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(mamulid));
			pstmt.executeUpdate();
			
			temp = IzlemeNoKontrol(mamulid, kullanildi);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<IzlemeNo> IzlemeNoKontrol(String mamulid, String kullanildi) {
		
		List<IzlemeNo> temp = new ArrayList<IzlemeNo>();
		conn = ConnectionManager.getConnection();
		String updateQuery = "select i.*, m.ad mamulad, m.kod mamulkod " + "from irfan.izlemeno i "
				+ "join irfan.mamul m on m.id=i.mamulid "
				+ "where mamulid = ? and kullanildi <= ? order by gkrno desc limit 10";
				
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(mamulid));
			pstmt.setInt(2, Integer.valueOf(kullanildi));
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			
			System.out.println(pstmt);
			
			while (rs.next()) {
				temp.add(new IzlemeNo(rs.getInt("mamulid"), rs.getInt("gkrno"), rs.getDate("kullanildi_tarih"),
						rs.getString("mamulkod"), rs.getString("mamulad"),
						Integer.valueOf(0), /* Ilk Uretim */
						rs.getInt("kullanildi")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static List<IzlemeNo> IzlemeNoSil(String mamulid, String gkrno, String kullanildi) {
		
		List<IzlemeNo> temp = new ArrayList<IzlemeNo>();
		conn = ConnectionManager.getConnection();
		String updateQuery = "delete from irfan.izlemeno where mamulid = ? and gkrno = ? and kullanildi = 0";
		
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(mamulid));
			pstmt.setInt(2, Integer.valueOf(gkrno));
			pstmt.executeUpdate();
			if (Genel.LOGMOD == LogMod.DEBUG) {
				System.out.println(pstmt);
			}
			temp = IzlemeNoKontrol(mamulid, kullanildi);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}
	
	protected static String StokKontrol(String tarih, String gkrno, String miktar, String iade, String bilesentipid) {
		
		conn = ConnectionManager.getConnection();
		Integer sum = null;
		String result = "0";
		String updateQuery = "select sum(case when islemyonu = 0 then miktar else -1*miktar end) " + "from irfan.stok "
				+ "where gkrno = ? and exists "
				+ "(select 1 from irfan.stok where gkrno = ? and islemyonu = 0 and (date(giristarihi) = ? or date(cikistarihi) = ?)) "
				+ "group by gkrno ";
				
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(gkrno));
			pstmt.setInt(2, Integer.valueOf(gkrno));
			pstmt.setDate(3, Date.valueOf(Util.date_tr_to_eng(tarih)));
			pstmt.setDate(4, Date.valueOf(Util.date_tr_to_eng(tarih)));
			
			rs = pstmt.executeQuery();
			
			System.out.println(pstmt);
			
			if (rs.next()) {
				sum = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (Boolean.valueOf(iade)) {
			if (sum == null) sum = 0;
			String resStr = "";
			Double hammaddeMiktar = 0.0;
			if (Integer.valueOf(miktar) > sum) {
				resStr = sum.toString();
				/* Donus degerinin KG olarak verilmesi icin */
				if (bilesentipid.equals("1")) {
					hammaddeMiktar = Util.Round((Double.valueOf(sum) / 1000.0), 3.0);
					resStr = hammaddeMiktar.toString() + " Kg";
				}
				result = Util.getTarihTRShort(Date.valueOf(Util.date_tr_to_eng(tarih))) + " tarihli stok miktarı "
						+ resStr + ". İade işlemini gözden geçirin!";
			}
		} else {
			if (sum == null) {
				result = Util.getTarihTRShort(Date.valueOf(Util.date_tr_to_eng(tarih))) + " tarihinde bir üretim yok!";
			}
		}
		return result;
	}
	
	protected static String calisanSil(String id) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "select irfan.calisan_sil(?)";
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(id));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String firmaSil(String id) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "select irfan.firma_sil(?)";
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(id));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String makinaSil(String id) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "select irfan.makina_sil(?)";
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(id));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String bilesenGuncelle(String bilesenid, String birimid, String firmaid, String bilesenkod,
			String bilesenad) {
			
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "update irfan.bilesen " + "set " + "birimid = ? ," + "firmaid  = ? ," + "kod  = ? ,"
				+ "ad  = ? " + "where id = ?";
				
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			if (birimid != null) pstmt.setInt(1, Integer.valueOf(birimid));
			else pstmt.setNull(1, Types.INTEGER);
			pstmt.setInt(2, Integer.valueOf(firmaid));
			pstmt.setString(3, bilesenkod);
			pstmt.setString(4, bilesenad);
			pstmt.setInt(5, Integer.valueOf(bilesenid));
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String calisanGuncelle(String id, String ad, String soyad, String gorev, String durum) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "update irfan.calisan " + "set " + "ad = ? ," + "soyad  = ? ," + "gorev  = ? ,"
				+ "durum  = ? " + "where id = ?";
				
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, ad);
			pstmt.setString(2, soyad);
			pstmt.setString(3, gorev);
			pstmt.setInt(4, Integer.valueOf(durum));
			pstmt.setInt(5, Integer.valueOf(id));
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String firmaGuncelle(String id, String ad, String tel, String adres) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "update irfan.firma " + "set " + "ad = ? ," + "telefon  = ? ," + "adres  = ? "
				+ "where id = ?";
				
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, ad);
			pstmt.setString(2, tel);
			pstmt.setString(3, adres);
			pstmt.setInt(4, Integer.valueOf(id));
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String makinaGuncelle(String id, String ad, String tip) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "update irfan.makina " + "set " + "ad = ? ," + "makinatipid  = ? " + "where id = ?";
		
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, ad);
			pstmt.setInt(2, Integer.valueOf(tip));
			pstmt.setInt(3, Integer.valueOf(id));
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String irsaliyeBilesenGuncelle(int irsaliyeid, int irsaliyebilesenid, int mamulid, int gkrno,
			int miktar, String not) {
			
		conn = ConnectionManager.getConnection();
		String retVal = "-1";
		String updateQuery = "SELECT irfan.irsaliyebilesen_guncelle(?,?,?,?,?,?,?) ";
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, irsaliyeid);
			pstmt.setInt(2, irsaliyebilesenid);
			pstmt.setInt(3, mamulid);
			pstmt.setInt(4, gkrno);
			pstmt.setInt(5, miktar);
			pstmt.setString(6, not);
			
			System.out.println(pstmt);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getString(1);
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	protected static String mamulGuncelle(String mamulid, String firmaid, String kod, String ad, String figur,
			String cevrimsuresi) {
			
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "update irfan.mamul " + "set " + "firmaid  = ? ," + "kod  = ? ," + "ad  = ? ,"
				+ "figur  = ? ," + "cevrimsuresi  = ? " + "where id = ?";
				
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, Integer.valueOf(firmaid));
			pstmt.setString(2, kod);
			pstmt.setString(3, ad);
			pstmt.setInt(4, Integer.valueOf(figur));
			pstmt.setInt(5, Integer.valueOf(cevrimsuresi));
			pstmt.setInt(6, Integer.valueOf(mamulid));
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	protected static String mamulBilesenGuncelle(String mamulid, String bilesenid, String miktar) {
		
		conn = ConnectionManager.getConnection();
		String result = "0";
		String updateQuery = "update irfan.mamulbilesen set miktar = ? where mamulid = ? and bilesenid = ? ";
		
		// connect to DB
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setFloat(1, Float.valueOf(miktar));
			pstmt.setInt(2, Integer.valueOf(mamulid));
			pstmt.setInt(3, Integer.valueOf(bilesenid));
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static int recordCount(String tablename, String filter) {
		int result = 0;
		conn = ConnectionManager.getConnection();
		String selectQuery = "select count(*) from irfan." + tablename + filter;
		// System.out.println(selectQuery);
		
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static int recordAgg(String tablename, String aggfunc, String aggfield, String filter) {
		int result = 0;
		conn = ConnectionManager.getConnection();
		String selectQuery = "select " + aggfunc + "(" + aggfield + ")" + " from " + tablename + filter;
		// System.out.println(selectQuery);
		System.out.println(selectQuery);
		// connect to DB
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}