package crm.irfan.entity;

public enum SiparisTip {
    BEKLEYEN(0), TAMAMLANMIS(1), TUM(2);
    private Integer value;
    
    SiparisTip(Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
}