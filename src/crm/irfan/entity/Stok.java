package crm.irfan.entity;

import java.util.Date;

public class Stok {
    private Integer id;
    private Integer bilesenid;
    private String  bilesenad;
    private Integer stoktipid;
    private String  stoktipad;
    private Integer miktar;
    private Date    giristarihi;
    private Date    cikistarihi;
    private String  not;
    
    public Stok(Integer id, Integer bilesenid, String bilesenad, Integer stoktipid,
            String stoktipad, Integer miktar, Date giristarihi, Date cikistarihi, String not) {
        super();
        this.id = id;
        this.bilesenid = bilesenid;
        this.bilesenad = bilesenad;
        this.stoktipid = stoktipid;
        this.stoktipad = stoktipad;
        this.miktar = miktar;
        this.giristarihi = giristarihi;
        this.cikistarihi = cikistarihi;
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
    
    public Integer getStoktipid() {
        return stoktipid;
    }
    
    public void setStoktipid(Integer stoktipid) {
        this.stoktipid = stoktipid;
    }
    
    public String getStoktipad() {
        return stoktipad;
    }
    
    public void setStoktipad(String stoktipad) {
        this.stoktipad = stoktipad;
    }
    
    public Integer getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
    public Date getGiristarihi() {
        return giristarihi;
    }
    
    public void setGiristarihi(Date giristarihi) {
        this.giristarihi = giristarihi;
    }
    
    public Date getCikistarihi() {
        return cikistarihi;
    }
    
    public void setCikistarihi(Date cikistarihi) {
        this.cikistarihi = cikistarihi;
    }
    
    public String getNot() {
        return not;
    }
    
    public void setNot(String not) {
        this.not = not;
    }
    
}
