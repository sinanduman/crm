package crm.irfan.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class IrsaliyeBilesen {
    private Integer   id;
    private Integer   irsaliyeid;
    private Integer   irsaliyebilesenid;
    private Integer   mamulid;
    private String    mamulad;
    private String    mamulkod;
    private Integer   miktar;
    private Timestamp olusturmatarihi;
    private Timestamp gonderimtarihi;
    
    public IrsaliyeBilesen(Integer id, Integer irsaliyeid, Integer irsaliyebilesenid, Integer mamulid, String mamulad,
                    String mamulkod, Integer miktar, Timestamp olusturmatarihi, Timestamp gonderimtarihi) {
        super();
        this.id = id;
        this.irsaliyeid = irsaliyeid;
        this.irsaliyebilesenid = irsaliyebilesenid;
        this.mamulid = mamulid;
        this.mamulad = mamulad;
        this.mamulkod = mamulkod;
        this.miktar = miktar;
        this.olusturmatarihi = olusturmatarihi;
        this.gonderimtarihi = gonderimtarihi;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIrsaliyeid() {
        return irsaliyeid;
    }
    
    public void setIrsaliyeid(Integer irsaliyeid) {
        this.irsaliyeid = irsaliyeid;
    }
    
    public Integer getIrsaliyebilesenid() {
        return irsaliyebilesenid;
    }
    
    public void setIrsaliyebilesenid(Integer irsaliyebilesenid) {
        this.irsaliyebilesenid = irsaliyebilesenid;
    }
    
    public Integer getMamulid() {
        return mamulid;
    }
    
    public void setMamulid(Integer mamulid) {
        this.mamulid = mamulid;
    }
    
    public String getMamulad() {
        return mamulad;
    }
    
    public void setMamulad(String mamulad) {
        this.mamulad = mamulad;
    }
    
    public String getMamulkod() {
        return mamulkod;
    }
    
    public void setMamulkod(String mamulkod) {
        this.mamulkod = mamulkod;
    }
    
    public Integer getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
    public String getOlusturmatarihi() {
        String date = (this.olusturmatarihi != null) ? (new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .format(olusturmatarihi)) : "";
        return date;
    }
    
    public void setOlusturmatarihi(Timestamp olusturmatarihi) {
        this.olusturmatarihi = olusturmatarihi;
    }
    
    public void setGonderimtarihi(Timestamp gonderimtarihi) {
        this.gonderimtarihi = gonderimtarihi;
    }
    
    public String getGonderimtarihi() {
        String date = (this.gonderimtarihi != null) ? (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(gonderimtarihi))
                        : "";
        return date;
    }
    
}
