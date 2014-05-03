package crm.irfan.entity;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import crm.irfan.UtilFunctions;

public class UretimPlan {
    private Integer id;
    private Integer mamulid;
    private String  mamulad;
    private String  mamulkod;
    private String  hammaddead;
    private String  yarimamulad;
    private Integer planlananmiktar;
    private Integer uretilenmiktar;
    private Integer hatalimiktar;
    private Integer makinaid;
    private String  makinaad;
    private Integer firmaid;
    private String  firmaad;
    private Integer calisanid;
    private String  calisanad;
    private String  calisansoyad;
    private Integer hataid;
    private String  hataad;
    private String  hatakodu;
    private Integer durusid;
    private String  durusad;
    private String  duruskodu;
    private Integer duruszaman;
    private Date    tarih;
    private Time    baszaman;
    private Time    bitzaman;
    private Integer mamulizlno;
    private String  hammaddeizlno;
    private String  yarimamulizlno;
    private Integer tamamlandi;
    private String  agirlik;
    private Integer cevrimsuresi;
    
    public UretimPlan(Integer id, Integer mamulid, String mamulad, String mamulkod, String hammaddead,
                    String yarimamulad, Integer planlananmiktar, Integer uretilenmiktar, Integer hatalimiktar,
                    Integer makinaid, String makinaad, Integer firmaid, String firmaad, Integer calisanid,
                    String calisanad, String calisansoyad, Integer hataid, String hataad, String hatakodu,
                    Integer durusid, String durusad, String duruskodu, Integer duruszaman, Date tarih, Time baszaman,
                    Time bitzaman, Integer mamulizlno, String hammaddeizlno, String yarimamulizlno, Integer tamamlandi,
                    String agirlik, Integer cevrimsuresi) {
        super();
        this.id = id;
        this.mamulid = mamulid;
        this.mamulad = mamulad;
        this.mamulkod = mamulkod;
        this.hammaddead = hammaddead;
        this.yarimamulad = yarimamulad;
        this.planlananmiktar = planlananmiktar;
        this.uretilenmiktar = uretilenmiktar;
        this.hatalimiktar = hatalimiktar;
        this.makinaid = makinaid;
        this.makinaad = makinaad;
        this.firmaid = firmaid;
        this.firmaad = firmaad;
        this.calisanid = calisanid;
        this.calisanad = calisanad;
        this.calisansoyad = calisansoyad;
        this.hataid = hataid;
        this.hataad = hataad;
        this.hatakodu = hatakodu;
        this.durusid = durusid;
        this.durusad = durusad;
        this.duruskodu = duruskodu;
        this.duruszaman = duruszaman;
        this.tarih = tarih;
        this.baszaman = baszaman;
        this.bitzaman = bitzaman;
        this.mamulizlno = mamulizlno;
        this.hammaddeizlno = hammaddeizlno;
        this.yarimamulizlno = yarimamulizlno;
        this.tamamlandi = tamamlandi;
        this.agirlik = agirlik;
        this.cevrimsuresi = cevrimsuresi;
    }
    
    public String getHataad() {
        return (hataad == null) ? "" : hataad;
    }
    
    public void setHataad(String hataad) {
        this.hataad = hataad;
    }
    
    public String getDurusad() {
        return (durusad == null) ? "" : durusad;
    }
    
    public void setDurusad(String durusad) {
        this.durusad = durusad;
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
    
    public String getMamulad() {
        return mamulad;
    }
    
    public void setMamulad(String mamulad) {
        this.mamulad = mamulad;
    }
    
    public String getMamulkod() {
        return mamulkod;
    }
    
    public void setMamulkod(String mamulkod) {
        this.mamulkod = mamulkod;
    }
    
    public String getHammaddead() {
        return hammaddead;
    }
    
    public void setHammaddead(String hammaddead) {
        this.hammaddead = hammaddead;
    }
    
    public String getYarimamulad() {
        return yarimamulad;
    }
    
    public void setYarimamulad(String yarimamulad) {
        this.yarimamulad = yarimamulad;
    }
    
    public Integer getPlanlananmiktar() {
        return planlananmiktar;
    }
    
    public void setPlanlananmiktar(Integer planlananmiktar) {
        this.planlananmiktar = planlananmiktar;
    }
    
    public Integer getUretilenmiktar() {
        return uretilenmiktar;
    }
    
    public void setUretilenmiktar(Integer uretilenmiktar) {
        this.uretilenmiktar = uretilenmiktar;
    }
    
    public Integer getHatalimiktar() {
        return hatalimiktar;
    }
    
    public void setHatalimiktar(Integer hatalimiktar) {
        this.hatalimiktar = hatalimiktar;
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
    
    public String getCalisanShortName() {
        return calisanad.substring(0, 1) + "." + calisansoyad;
    }
    
    public Time getBaszaman() {
        return baszaman;
    }
    
    public void setBaszaman(Time baszaman) {
        this.baszaman = baszaman;
    }
    
    public Time getBitzaman() {
        return bitzaman;
    }
    
    public void setBitzaman(Time bitzaman) {
        this.bitzaman = bitzaman;
    }
    
    public Integer getHataid() {
        return hataid;
    }
    
    public void setHataid(Integer hataid) {
        this.hataid = hataid;
    }
    
    public String getHatakodu() {
        return hatakodu;
    }
    
    public void setHatakodu(String hatakodu) {
        this.hatakodu = hatakodu;
    }
    
    public Integer getDurusid() {
        return durusid;
    }
    
    public void setDurusid(Integer durusid) {
        this.durusid = durusid;
    }
    
    public String getDuruskodu() {
        return duruskodu;
    }
    
    public void setDuruskodu(String duruskodu) {
        this.duruskodu = duruskodu;
    }
    
    public Integer getDuruszaman() {
        return duruszaman;
    }
    
    public void setDuruszaman(Integer duruszaman) {
        this.duruszaman = duruszaman;
    }
    
    public Integer getMamulizlno() {
        return mamulizlno;
    }
    
    public void setMamulizlno(Integer mamulizlno) {
        this.mamulizlno = mamulizlno;
    }
    
    public String getHammaddeizlno() {
        return hammaddeizlno;
    }
    
    public void setHammaddeizlno(String hammaddeizlno) {
        this.hammaddeizlno = hammaddeizlno;
    }
    
    public String getYarimamulizlno() {
        return yarimamulizlno;
    }
    
    public void setYarimamulizlno(String yarimamulizlno) {
        this.yarimamulizlno = yarimamulizlno;
    }
    
    public Integer getTamamlandi() {
        return tamamlandi;
    }
    
    public void setTamamlandi(Integer tamamlandi) {
        this.tamamlandi = tamamlandi;
    }
    
    public void setTarih(Date tarih) {
        this.tarih = tarih;
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
    
    public String getAgirlik() {
        return agirlik;
    }
    
    public void setAgirlik(String agirlik) {
        this.agirlik = agirlik;
    }
    
    public Integer getCevrimsuresi() {
        return cevrimsuresi;
    }
    
    public void setCevrimsuresi(Integer cevrimsuresi) {
        this.cevrimsuresi = cevrimsuresi;
    }
    
    public Integer getFark() {
        if (planlananmiktar == null || planlananmiktar == 0) {
            if (hatalimiktar == null || hatalimiktar == 0) {
                return 0;
            }
            else {
                return hatalimiktar;
            }
            
        }
        return planlananmiktar - uretilenmiktar;
    }
    
    public double getSapma() {
        if (planlananmiktar == null || planlananmiktar == 0) {
            return 0;
        }
        return UtilFunctions.Round((planlananmiktar - uretilenmiktar)*100, planlananmiktar);
    }
    
}
