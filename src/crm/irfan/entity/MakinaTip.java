package crm.irfan.entity;

public enum MakinaTip {
    MAKINA(1,"Makina"), BANT(2, "Bant");
    private Integer value;
    private String ad;
    
    MakinaTip(Integer value, String ad) {
        this.value = value;
        this.ad = ad;
    }
    
    public Integer value() {
        return value;
    }
    public String ad() {
        return ad;
    }
}