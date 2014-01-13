package crm.irfan.entity;

import java.sql.Timestamp;

public class Stok {
    private Integer   id;
    private Integer   bilesenid;
    private String    bilesenad;
    private String    bilesenkod;
    private Integer   bilesentipid;
    private String    bilesentipad;
    private Integer   birimid;
    private String    birimad;
    private Integer   firmaid;
    private String    firmaad;
    private Integer   miktar;
    private String    gkrno;
    private String    irsaliyeno;
    private String    lot;
    private Timestamp giristarihi;
    private Timestamp cikistarihi;
    private String    not;
    
    public Stok(Integer id, Integer bilesenid, String bilesenad, String bilesenkod, Integer bilesentipid,
                    String bilesentipad, Integer birimid, String birimad, Integer firmaid, String firmaad,
                    Integer miktar, String gkrno, String irsaliyeno, String lot, Timestamp giristarihi,
                    Timestamp cikistarihi, String not) {
        super();
        this.id = id;
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
        this.gkrno = gkrno;
        this.irsaliyeno = irsaliyeno;
        this.lot = lot;
        this.giristarihi = giristarihi;
        this.cikistarihi = cikistarihi;
        this.not = not;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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
    
    public Integer getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
    public String getGkrno() {
        return gkrno;
    }
    
    public void setGkrno(String gkrno) {
        this.gkrno = gkrno;
    }
    
    public String getIrsaliyeno() {
        return irsaliyeno;
    }
    
    public void setIrsaliyeno(String irsaliyeno) {
        this.irsaliyeno = irsaliyeno;
    }
    
    public String getLot() {
        return lot;
    }
    
    public void setLot(String lot) {
        this.lot = lot;
    }
    
    public Timestamp getGiristarihi() {
        return giristarihi;
    }
    
    public void setGiristarihi(Timestamp giristarihi) {
        this.giristarihi = giristarihi;
    }
    
    public Timestamp getCikistarihi() {
        return cikistarihi;
    }
    
    public void setCikistarihi(Timestamp cikistarihi) {
        this.cikistarihi = cikistarihi;
    }
    
    public String getNot() {
        return not;
    }
    
    public void setNot(String not) {
        this.not = not;
    }
    
}
