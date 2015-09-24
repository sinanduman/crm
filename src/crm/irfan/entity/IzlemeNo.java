package crm.irfan.entity;

import java.util.Date;

public class IzlemeNo {
    private Integer id;
    private Integer mamulid;
    private Integer gkrno;
    private Integer kullanildi;
    private Date    kullanildi_tarih;
    private String  mamulkod;
    private String  mamulad;
    private Integer miktar;
    
    public IzlemeNo(Integer id, Integer mamulid, Integer gkrno, Integer kullanildi, Date kullanildi_tarih) {
        super();
        this.id = id;
        this.mamulid = mamulid;
        this.gkrno = gkrno;
        this.kullanildi = kullanildi;
        this.kullanildi_tarih = kullanildi_tarih;
    }
    
    public IzlemeNo(Integer mamulid, Integer gkrno, Date kullanildi_tarih,
                    String mamulkod, String mamulad, Integer miktar, Integer kullanildi) {
        super();
        this.mamulid = mamulid;
        this.gkrno = gkrno;
        this.kullanildi_tarih = kullanildi_tarih;
        this.mamulkod = mamulkod;
        this.mamulad = mamulad;
        this.miktar = miktar;
        this.kullanildi = kullanildi;
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
    
    public Integer getGkrno() {
        return gkrno;
    }
    
    public void setGkrno(Integer gkrno) {
        this.gkrno = gkrno;
    }
    
    public Integer getKullanildi() {
        return kullanildi;
    }
    
    public void setKullanildi(Integer kullanildi) {
        this.kullanildi = kullanildi;
    }
    
    public Date getKullanildi_tarih() {
        return kullanildi_tarih;
    }
    
    public void setKullanildi_tarih(Date kullanildi_tarih) {
        this.kullanildi_tarih = kullanildi_tarih;
    }

    public String getMamulkod() {
        return mamulkod;
    }

    public void setMamulkod(String mamulkod) {
        this.mamulkod = mamulkod;
    }

    public String getMamulad() {
        return mamulad;
    }

    public void setMamulad(String mamulad) {
        this.mamulad = mamulad;
    }

    public Integer getMiktar() {
        return miktar;
    }

    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
}
