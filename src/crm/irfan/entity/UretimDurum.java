package crm.irfan.entity;

public enum UretimDurum {
    BEKLEYEN(0), TAMAMLANMIS(1), TUM(2);
    private Integer value;
    
    UretimDurum(Integer value) {
        this.value = value;
    }
    
    public Integer value() {
        return value;
    }
}