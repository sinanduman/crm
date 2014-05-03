package crm.irfan.entity;

import java.util.Date;

public class Bilesen {
    private Integer id;
    private String  ad;
    private String  kod;
    private Integer bilesentipid;
    private String  bilesentipad;
    private Integer birimid;
    private String  birimad;
    private Integer firmaid;
    private String  firmaad;
    private Date    eklemetarihi;
    
    public Bilesen(Integer id, String ad, String kod, Integer bilesentipid, String bilesentipad, Integer birimid,
                    String birimad, Integer firmaid, String firmaad, Date eklemetarihi) {
        super();
        this.id = id;
        this.ad = ad;
        this.kod = kod;
        this.bilesentipid = bilesentipid;
        this.bilesentipad = bilesentipad;
        this.birimid = birimid;
        this.birimad = birimad;
        this.firmaid = firmaid;
        this.firmaad = firmaad;
        this.eklemetarihi = eklemetarihi;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getAd() {
        return ad;
    }
    
    public void setAd(String ad) {
        this.ad = ad;
    }
    
    public String getKod() {
        return kod;
    }
    
    public void setKod(String kod) {
        this.kod = kod;
    }
    
    public Integer getBilesentipid() {
        return bilesentipid;
    }
    
    public void setBilesentipid(Integer bilesentipid) {
        this.bilesentipid = bilesentipid;
    }
    
    public String getBilesentipad() {
        return bilesentipad;
    }
    
    public void setBilesentipad(String bilesentipad) {
        this.bilesentipad = bilesentipad;
    }
    
    public Integer getBirimid() {
        return birimid;
    }
    
    public void setBirimid(Integer birimid) {
        this.birimid = birimid;
    }
    
    public String getBirimad() {
        return birimad;
    }
    
    public void setBirimad(String birimad) {
        this.birimad = birimad;
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
    
    public Date getEklemetarihi() {
        return eklemetarihi;
    }
    
    public void setEklemetarihi(Date eklemetarihi) {
        this.eklemetarihi = eklemetarihi;
    }
    
}
