package crm.irfan.entity;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SiparisPlan {
    private Integer id;
    private Integer siparisid;
    private String  bilesenad;
    private Integer miktar;
    private Integer makinaid;
    private String  makinaad;
    private Integer calisanid;
    private String  calisanad;
    private String  calisansoyad;
    private Date    tarih;
    private Time    baszaman;
    private Time    bitzaman;
    private Integer hataid;
    private Integer hatamiktar;
    private Integer durusid;
    private String  not;
    
    public SiparisPlan(Integer id, Integer siparisid, String bilesenad, Integer miktar, Integer makinaid,
                    String makinaad, Integer calisanid, String calisanad, String calisansoyad, Date tarih, Time baszaman, Time bitzaman,
                    Integer hataid, Integer hatamiktar, Integer durusid, String not) {
        super();
        this.id = id;
        this.siparisid = siparisid;
        this.bilesenad = bilesenad;
        this.miktar = miktar;
        this.makinaid = makinaid;
        this.makinaad = makinaad;
        this.calisanid = calisanid;
        this.calisanad = calisanad;
        this.calisansoyad = calisansoyad;
        this.tarih = tarih;
        this.baszaman = baszaman;
        this.bitzaman = bitzaman;
        this.hataid = hataid;
        this.hatamiktar = hatamiktar;
        this.durusid = durusid;
        this.not = not;
    }
    
    public Integer getHataid() {
        return hataid;
    }
    
    public void setHataid(Integer hataid) {
        this.hataid = hataid;
    }
    
    public Integer getHatamiktar() {
        return hatamiktar;
    }
    
    public void setHatamiktar(Integer hatamiktar) {
        this.hatamiktar = hatamiktar;
    }
    
    public Integer getDurusid() {
        return durusid;
    }
    
    public void setDurusid(Integer durusid) {
        this.durusid = durusid;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getSiparisid() {
        return siparisid;
    }
    
    public void setSiparisid(Integer siparisid) {
        this.siparisid = siparisid;
    }
    
    public String getBilesenad() {
        return bilesenad;
    }
    
    public void setBilesenad(String bilesenad) {
        this.bilesenad = bilesenad;
    }
    
    public Integer getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
    public Integer getMakinaid() {
        return makinaid;
    }
    
    public void setMakinaid(Integer makinaid) {
        this.makinaid = makinaid;
    }
    
    public String getMakinaad() {
        return makinaad;
    }
    
    public void setMakinaad(String makinaad) {
        this.makinaad = makinaad;
    }
    
    public Integer getCalisanid() {
        return calisanid;
    }
    
    public void setCalisanid(Integer calisanid) {
        this.calisanid = calisanid;
    }
    
    public String getCalisanad() {
        return calisanad;
    }
        
    public void setCalisanad(String calisanad) {
        this.calisanad = calisanad;
    }
    
    public String getCalisansoyad() {
        return calisansoyad;
    }
        
    public void setCalisansoyad(String calisansoyad) {
        this.calisansoyad = calisansoyad;
    }
    
    public String getCalisanFullName() {
        return calisanad + " " + calisansoyad;
    }
    
    public String getCalisanShortName() {
        return calisanad.substring(0, 1) + "." + calisansoyad;
    }
    
    public String getTarih() {
        String date = (this.tarih != null) ? (new SimpleDateFormat("yyyy-MM-dd").format(tarih)) : "";
        return date;
    }
    public String getTarihTR() {
        String date = (this.tarih != null) ? (new SimpleDateFormat("dd-MM-yyyy").format(tarih)) : "";
        return date;
    }
    public String getTarihTRShort() {
        String date = (this.tarih != null) ? (new SimpleDateFormat("dd.MM.yy").format(tarih)) : "";
        return date;
    }
    
    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    
    public String getBasZaman() {
        String date = (this.baszaman != null) ? (new SimpleDateFormat("HH:mm").format(baszaman)) : "";
        return date;
    }
    
    public String getBasSaat() {
        String date = (this.baszaman != null) ? (new SimpleDateFormat("HH").format(baszaman)) : "";
        return date;
    }
    
    public String getBasDakika() {
        String date = (this.baszaman != null) ? (new SimpleDateFormat("mm").format(baszaman)) : "";
        return date;
    }
    
    public void setBasZaman(Time baszaman) {
        this.baszaman = baszaman;
    }
    
    public String getBitZaman() {
        String date = (this.bitzaman != null) ? (new SimpleDateFormat("HH:mm").format(bitzaman)) : "";
        return date;
    }
    
    public String getBitSaat() {
        String date = (this.bitzaman != null) ? (new SimpleDateFormat("HH").format(bitzaman)) : "";
        return date;
    }
    
    public String getBitDakika() {
        String date = (this.bitzaman != null) ? (new SimpleDateFormat("mm").format(bitzaman)) : "";
        return date;
    }
    
    public void setBitZaman(Time bitzaman) {
        this.bitzaman = bitzaman;
    }
    
    public String getNot() {
        return (not!=null)?not:"";
    }
    
    public void setNot(String not) {
        this.not = not;
    }
    
}
