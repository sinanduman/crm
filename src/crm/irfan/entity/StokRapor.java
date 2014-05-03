package crm.irfan.entity;

public class StokRapor {
    private Integer id;
    private String  bilesenad;
    private String  bilesenkod;
    private Integer bilesentipid;
    private String  bilesentipad;
    private Integer firmaid;
    private String  firmaad;
    private Integer miktar;
    
    public StokRapor(Integer id, String bilesenad, String bilesenkod, Integer bilesentipid, String bilesentipad,
                    Integer firmaid, String firmaad, Integer miktar) {
        super();
        this.id = id;
        this.bilesenad = bilesenad;
        this.bilesenkod = bilesenkod;
        this.bilesentipid = bilesentipid;
        this.bilesentipad = bilesentipad;
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
    
    public Integer getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
}