package crm.irfan.entity;

public class Birim {
    private Integer id;
    private String ad;

    public Birim(Integer id, String ad) {
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
