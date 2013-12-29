package crm.irfan.entity;

public enum ResultTip {
    NORESULT(0), OK(1), ERROR(2);
    private Integer value;
    
    ResultTip(Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
}