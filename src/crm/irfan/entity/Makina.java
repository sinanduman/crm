package crm.irfan.entity;

public class Makina {
    private Integer id;
    private String  ad;
    private Integer makinatipid;
    private String  makinatipad;
    
    public Makina(Integer id, String ad, Integer makinatipid, String makinatipad) {
        super();
        this.id = id;
        this.ad = ad;
        this.makinatipid = makinatipid;
        this.makinatipad = makinatipad;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMakinaad() {
        return ad;
    }
    
    public void setMakinaad(String makinaad) {
        this.ad = makinaad;
    }
    
    public Integer getMakinatipid() {
        return makinatipid;
    }
    
    public void setMakinatipid(Integer makinatipid) {
        this.makinatipid = makinatipid;
    }
    
    public String getMakinatipad() {
        return makinatipad;
    }
    
    public void setMakinatipad(String makinatipad) {
        this.makinatipad = makinatipad;
    }
    
}
