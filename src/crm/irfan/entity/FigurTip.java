package crm.irfan.entity;

public enum FigurTip {
    Goz4(1,"4 Göz"), Goz8(2,"8 Göz"), Goz12(3,"12 Göz"), Goz16(4,"16 Göz");
    private Integer value;
    private String ad;
    
    FigurTip(Integer value, String ad) {
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