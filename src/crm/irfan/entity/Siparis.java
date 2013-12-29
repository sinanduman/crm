package crm.irfan.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Siparis {
    private Integer   id;
    private Integer   bilesenid;
    private String    bilesenad;
    private Integer   miktar;
    private Timestamp tarih;
    private Timestamp bitistarih;
    private String    not;
    
    public Siparis(Integer id, Integer bilesenid, String bilesenad, Integer miktar,
            Timestamp tarih, Timestamp bitistarih, String not) {
        super();
        this.id = id;
        this.bilesenid = bilesenid;
        this.bilesenad = bilesenad;
        this.miktar = miktar;
        this.tarih = tarih;
        this.bitistarih = bitistarih;
        this.not = not;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getBilesenid() {
        return bilesenid;
    }
    
    public void setBilesenid(Integer bilesenid) {
        this.bilesenid = bilesenid;
    }
    
    public String getBilesenad() {
        return bilesenad;
    }
    
    public void setBilesenad(String bilesenad) {
        this.bilesenad = bilesenad;
    }
    
    public Integer getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
    public String getTarih() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(tarih);
        return date;
    }
    
    public void setTarih(Timestamp tarih) {
        this.tarih = tarih;
    }
    
    public String getBitistarih() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(bitistarih);
        return date;
    }
    
    public void setBitistarih(Timestamp bitistarih) {
        this.bitistarih = bitistarih;
    }
    
    public String getNot() {
        return not;
    }
    
    public void setNot(String not) {
        this.not = not;
    }
    
}
