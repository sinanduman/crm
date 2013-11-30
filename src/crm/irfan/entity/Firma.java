package crm.irfan.entity;

public class Firma {
    private Integer id;
    private String  ad;
    private String  telefon;
    private String  adres;
    
    public Firma(Integer id, String ad, String telefon, String adres) {
        super();
        this.id = id;
        this.ad = ad;
        this.telefon = telefon;
        this.adres = adres;
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
    
    public String getTelefon() {
        return telefon;
    }
    
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    public String getAdres() {
        return adres;
    }
    
    public void setAdres(String adres) {
        this.adres = adres;
    }
    
}
