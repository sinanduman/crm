package crm.irfan.entity;

public enum BilesenTip {
    HAMMADDE(1), YARIMAMUL(2), MAMUL(3);
    private Integer value;
    
    BilesenTip(Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
}