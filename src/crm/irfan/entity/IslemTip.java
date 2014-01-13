package crm.irfan.entity;

public enum IslemTip {
    UPDATE(1), COMPLETE(2), DELETE(3);
    private Integer value;
    
    IslemTip(Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
    
}
