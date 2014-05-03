package crm.irfan.entity;

public enum Aylar {
    OCAK(1, "Ocak", "January"), SUBAT(2, "Şubat", "February"), MART(3, "Mart", "March"), NISAN(4, "Nisan", "April"), MAYIS(
                    5, "Mayıs", "May"), HAZIRAN(6, "Haziran", "June"), TEMMUZ(7, "Temmuz", "July"), AGUSTOS(8,
                    "Ağustos", "August"), EYLUL(9, "Eylül", "September"), EKIM(10, "Ekim", "October"), KASIM(11,
                    "Kasım", "November"), ARALIK(12, "Aralık", "December");
    private Integer value;
    private String  tr;
    private String  eng;
    
    Aylar(Integer value, String tr, String eng) {
        this.value = value;
        this.tr = tr;
        this.eng = eng;
    }
    
    public Integer value() {
        return value;
    }
    
    public String tr() {
        return tr;
    }
    
    public String eng() {
        return eng;
    }
}