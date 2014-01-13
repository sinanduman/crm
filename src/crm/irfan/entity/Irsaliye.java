package crm.irfan.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Irsaliye {
    private Integer   id;
    private Integer   irsaliyeno;
    private Timestamp olusturmatarihi;
    private Timestamp gonderimtarihi;
    
    public Irsaliye(Integer id, Integer irsaliyeno, Timestamp olusturmatarihi, Timestamp gonderimtarihi) {
        super();
        this.id = id;
        this.irsaliyeno = irsaliyeno;
        this.olusturmatarihi = olusturmatarihi;
        this.gonderimtarihi = gonderimtarihi;
    }
    
    public Integer getId() {
        return id;
    }
    
    public Integer getIrsaliyeno() {
        return irsaliyeno;
    }
    
    public void setIrsaliyeno(Integer irsaliyeno) {
        this.irsaliyeno = irsaliyeno;
    }
    
    public void setOlusturmatarihi(Timestamp olusturmatarihi) {
        this.olusturmatarihi = olusturmatarihi;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOlusturmatarihi() {
        String date = (this.olusturmatarihi != null) ? (new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .format(olusturmatarihi)) : "";
        return date;
    }
    
    public String getGonderimtarihi() {
        String date = (this.gonderimtarihi != null) ? (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(gonderimtarihi))
                        : "";
        return date;
    }
    
    public void setGonderimtarihi(Timestamp gonderimtarihi) {
        this.gonderimtarihi = gonderimtarihi;
    }
    
}
