package crm.irfan.entity;

public class DurusSebep {
    private Integer id;
    private String  ad;
    private String  kod;
    
    public DurusSebep(Integer id, String ad, String kod) {
        super();
        this.id = id;
        this.ad = ad;
        this.kod = kod;
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
    
}
