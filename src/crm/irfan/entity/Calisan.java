package crm.irfan.entity;

public class Calisan {
    private Integer id;
    private String adsoy;
    private String gorev;

    public Calisan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdsoy() {
        return adsoy;
    }

    public void setAdsoy(String adsoy) {
        this.adsoy = adsoy;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public Calisan(Integer id, String adsoy, String gorev) {
        this.id = id;
        this.adsoy = adsoy;
        this.gorev = gorev;
    }
}
