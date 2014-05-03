package crm.irfan.entity;

public enum BilesenTip {
    HAMMADDE(1,"Hammadde","1"), YARIMAMUL(2,"Yarımamül","2"), MAMUL(3,"Mamül","3"), HAMMADDEYARIMAMUL(4,"Hammadde-Yarımamül","1,2");
    private Integer value;
    private String ad;
    private String id;
    
    BilesenTip(Integer value, String ad, String id) {
        this.value = value;
        this.ad = ad;
        this.id = id;
    }
    
    public Integer value() {
        return value;
    }
    
    public String ad() {
        return ad;
    }
    public String id() {
        return id;
    }
}