package crm.irfan.entity;

public class MamulBilesen {
    
    private Integer id;
    private Integer mamulid;
    private Integer bilesenid;
    private Integer bilesentipid;
    private Integer birimid;
    private Integer miktar;
    
    public MamulBilesen(Integer id, Integer mamulid, Integer bilesenid, Integer bilesentipid,
            Integer birimid, Integer miktar) {
        super();
        this.id = id;
        this.mamulid = mamulid;
        this.bilesenid = bilesenid;
        this.bilesentipid = bilesentipid;
        this.birimid = birimid;
        this.miktar = miktar;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getMamulid() {
        return mamulid;
    }
    
    public void setMamulid(Integer mamulid) {
        this.mamulid = mamulid;
    }
    
    public Integer getBilesenid() {
        return bilesenid;
    }
    
    public void setBilesenid(Integer bilesenid) {
        this.bilesenid = bilesenid;
    }
    
    public Integer getBilesentipid() {
        return bilesentipid;
    }
    
    public void setBilesentipid(Integer bilesentipid) {
        this.bilesentipid = bilesentipid;
    }
    
    public Integer getBirimid() {
        return birimid;
    }
    
    public void setBirimid(Integer birimid) {
        this.birimid = birimid;
    }
    
    public Integer getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
}
