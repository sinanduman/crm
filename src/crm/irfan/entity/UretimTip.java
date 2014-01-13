package crm.irfan.entity;

public enum UretimTip {
    Makina(1), Montaj(2);
    private Integer value;
    
    UretimTip(Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
}