package crm.irfan.entity;

public class Hammadde {
    private Integer id;
    private String  kod;
    private String  ad;
    private Integer birimid;
    private String  birimad;
    private Integer firmaid;
    private String firmaad;
    
    public Hammadde(Integer id, String kod, String ad, Integer birimid, String birimad,
            Integer firmaid, String firmaad) {
        super();
        this.id = id;
        this.kod = kod;
        this.ad = ad;
        this.birimid = birimid;
        this.birimad = birimad;
        this.firmaid = firmaid;
        this.firmaad = firmaad;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getKod() {
        return kod;
    }
    
    public void setKod(String kod) {
        this.kod = kod;
    }
    
    public String getAd() {
        return ad;
    }
    
    public void setAd(String ad) {
        this.ad = ad;
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
    
}
