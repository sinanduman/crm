package crm.irfan.entity;

public class Depo {
    private Integer id;
    private String  ad;
    
    public Depo(Integer id, String ad) {
        super();
        this.id = id;
        this.ad = ad;
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
}
