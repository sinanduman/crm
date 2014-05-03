package crm.irfan.entity;

public enum IrsaliyeTip {
    OPEN(0), COMPLETED(1), ONAYLANDI(1);
    private Integer value;
    
    IrsaliyeTip(Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
}