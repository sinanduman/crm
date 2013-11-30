package crm.irfan.entity;

public class YariMamul {
    private Integer id;
    private String kod;
    private String ad;

    public YariMamul(Integer id, String kod, String ad) {
        super();
        this.id = id;
        this.kod = kod;
        this.ad = ad;
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

}
