package crm.irfan.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Irsaliye {
    private Integer   id;
    private String    irsaliyeno;
    private Timestamp olusturmatarihi;
    private Timestamp gonderimtarihi;
    private Integer   firmaid;
    private String    firmaad;
    
    public Irsaliye(Integer id, String irsaliyeno, Timestamp olusturmatarihi, Timestamp gonderimtarihi,
                    Integer firmaid, String firmaad) {
        super();
        this.id = id;
        this.irsaliyeno = irsaliyeno;
        this.olusturmatarihi = olusturmatarihi;
        this.gonderimtarihi = gonderimtarihi;
        this.firmaid = firmaid;
        this.firmaad = firmaad;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getIrsaliyeno() {
        return irsaliyeno;
    }
    
    public void setIrsaliyeno(String irsaliyeno) {
        this.irsaliyeno = irsaliyeno;
    }
    
    public void setOlusturmatarihi(Timestamp olusturmatarihi) {
        this.olusturmatarihi = olusturmatarihi;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Timestamp getOlusturmatarihi() {
        return olusturmatarihi;
    }
    
    public String getOlusturmatarihiTR() {
        String date = (this.olusturmatarihi != null) ? (new SimpleDateFormat("dd-MM-yyyy").format(olusturmatarihi)) : "";
        return date;
    }
    
    public Timestamp getGonderimtarihi() {
        return gonderimtarihi;
    }
    
    public String getGonderimtarihiTR() {
        String date = (this.gonderimtarihi != null) ? (new SimpleDateFormat("dd-MM-yyyy").format(gonderimtarihi)) : "";
        return date;
    }
    
    public void setGonderimtarihi(Timestamp gonderimtarihi) {
        this.gonderimtarihi = gonderimtarihi;
    }
    
    public Integer getFirmaid() {
        return firmaid;
    }
    
    public void setFirmaid(Integer firmaid) {
        this.firmaid = firmaid;
    }
    
    public String getFirmaad() {
        return firmaad;
    }
    
    public void setFirmaad(String firmaad) {
        this.firmaad = firmaad;
    }
    
}
