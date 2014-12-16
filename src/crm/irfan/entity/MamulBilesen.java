package crm.irfan.entity;

public class MamulBilesen {
    
    private Integer id;
    private Integer mamulid;
    private Integer bilesenid;
    private String  bilesenad;
    private String  bilesenkod;
    private Integer bilesentipid;
    private String  bilesentipad;
    private Integer birimid;
    private String  birimad;
    private Integer firmaid;
    private String  firmaad;
    private Float miktar;
    
    public MamulBilesen(Integer id, Integer mamulid, Integer bilesenid, String bilesenad, String bilesenkod,
                    Integer bilesentipid, String bilesentipad, Integer birimid, String birimad, Integer firmaid,
                    String firmaad, Float miktar) {
        super();
        this.id = id;
        this.mamulid = mamulid;
        this.bilesenid = bilesenid;
        this.bilesenad = bilesenad;
        this.bilesenkod = bilesenkod;
        this.bilesentipid = bilesentipid;
        this.bilesentipad = bilesentipad;
        this.birimid = birimid;
        this.birimad = birimad;
        this.firmaid = firmaid;
        this.firmaad = firmaad;
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
    
    public String getBilesenad() {
        return bilesenad;
    }
    
    public void setBilesenad(String bilesenad) {
        this.bilesenad = bilesenad;
    }
    
    public String getBilesenkod() {
        return bilesenkod;
    }
    
    public void setBilesenkod(String bilesenkod) {
        this.bilesenkod = bilesenkod;
    }
    
    public Integer getBilesentipid() {
        return bilesentipid;
    }
    
    public void setBilesentipid(Integer bilesentipid) {
        this.bilesentipid = bilesentipid;
    }
    
    public String getBilesentipad() {
        return bilesentipad;
    }
    
    public void setBilesentipad(String bilesentipad) {
        this.bilesentipad = bilesentipad;
    }
    
    public Integer getBirimid() {
        return birimid;
    }
    
    public void setBirimid(Integer birimid) {
        this.birimid = birimid;
    }
    
    public String getBirimad() {
        return birimad;
    }
    
    public void setBirimad(String birimad) {
        this.birimad = birimad;
    }
    
    public Integer getFirmaid() {
        return firmaid;
    }
    
    public void setFirmaid(Integer firmaid) {
        this.firmaid = firmaid;
    }
    
    public String getFirmaad() {
        return firmaad;
    }
    
    public void setFirmaad(String firmaad) {
        this.firmaad = firmaad;
    }
    
    public Float getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Float miktar) {
        this.miktar = miktar;
    }
    
}
