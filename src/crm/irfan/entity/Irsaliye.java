package crm.irfan.entity;

import java.sql.Timestamp;

public class Irsaliye {
    private Integer   id;
    private String    irsaliyeno;
    private Timestamp olusturmatarihi;
    private Timestamp gonderimtarihi;
    
    public Irsaliye(Integer id, String irsaliyeno, Timestamp olusturmatarihi, Timestamp gonderimtarihi) {
        super();
        this.id = id;
        this.irsaliyeno = irsaliyeno;
        this.olusturmatarihi = olusturmatarihi;
        this.gonderimtarihi = gonderimtarihi;
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
    
    public Timestamp getGonderimtarihi() {
        return gonderimtarihi;
    }
    
    public void setGonderimtarihi(Timestamp gonderimtarihi) {
        this.gonderimtarihi = gonderimtarihi;
    }
    
}
