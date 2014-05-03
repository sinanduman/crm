package crm.irfan.entity;

public class Calisan {
    private Integer id;
    private String  ad;
    private String  soyad;
    private String  gorev;
    
    public Calisan() {
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
    
    public String getSoyad() {
        return soyad;
    }
    
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
    
    public String getGorev() {
        return gorev;
    }
    
    public void setGorev(String gorev) {
        this.gorev = gorev;
    }
    
    public String getFullName() {
        return ad + " " + soyad;
    }
    
    public String getShortName() {
        return ad.substring(0, 1) + "." + soyad;
    }
    
    public Calisan(Integer id, String ad, String soyad, String gorev) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.gorev = gorev;
    }
}
