package crm.irfan.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StokRapor {
    private Integer   id;
    private Integer   stokid;
    private String    bilesenad;
    private String    bilesenkod;
    private Integer   bilesentipid;
    private String    bilesentipad;
    private Integer   firmaid;
    private String    firmaad;
    private Integer   miktar;
    private Timestamp tarih;
    
    public StokRapor(Integer id, Integer stokid, String bilesenad, String bilesenkod, Integer bilesentipid,
                    String bilesentipad, Integer firmaid, String firmaad, Integer miktar, Timestamp tarih) {
        super();
        this.id = id;
        this.stokid = stokid;
        this.bilesenad = bilesenad;
        this.bilesenkod = bilesenkod;
        this.bilesentipid = bilesentipid;
        this.bilesentipad = bilesentipad;
        this.firmaid = firmaid;
        this.firmaad = firmaad;
        this.miktar = miktar;
        this.tarih = tarih;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getStokid() {
        return stokid;
    }
    
    public void setStokid(Integer stokid) {
        this.stokid = stokid;
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
    
    public String getTarih() {
        String date = (this.tarih != null) ? (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(tarih)) : "";
        return date;
    }
    
    public void setTarih(Timestamp tarih) {
        this.tarih = tarih;
    }
    
}