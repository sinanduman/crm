package crm.irfan.entity;

public class Mamul {
    private Integer id;
    private String  ad;
    private Integer cevrimSuresi;
    
    public Mamul(Integer id, String ad, Integer cevrimSuresi) {
        super();
        this.id = id;
        this.ad = ad;
        this.cevrimSuresi = cevrimSuresi;
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
    
    public void setCevrimSuresi(Integer cevrimSuresi) {
        this.cevrimSuresi = cevrimSuresi;
    }
    
    public Integer getCevrimSuresi() {
        return cevrimSuresi;
    }
    
    public void setAd(String ad) {
        this.ad = ad;
    }
    
}
