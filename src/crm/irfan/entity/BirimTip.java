package crm.irfan.entity;

public enum BirimTip {
    GRAM(1,"Gram"), ADET(2,"Adet"), KILOGRAM(3,"Kg");
    private Integer value;
    private String ad;
    
    BirimTip(Integer value, String ad) {
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
