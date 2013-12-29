package crm.irfan.entity;

public enum BirimTip {
    GRAM(1), ADET(2), KILOGRAM(3);
    private Integer value;
    
    BirimTip(Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
    
}
