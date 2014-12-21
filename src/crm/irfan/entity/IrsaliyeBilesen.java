package crm.irfan.entity;

import java.sql.Timestamp;

public class IrsaliyeBilesen {
    private Integer   id;
    private Integer   irsaliyeid;
    private String    irsaliyeno;
    private Integer   firmaid;
    private String    firmaad;
    private Integer   mamulid;
    private String    mamulad;
    private String    mamulkod;
    private Integer   gkrno;
    private Integer   stokid;
    private Integer   miktar;
    private Timestamp olusturmatarihi;
    private Timestamp gonderimtarihi;
    private String    not;
    private Integer   irsaliyefirmaid;
    
    public IrsaliyeBilesen(Integer id, Integer irsaliyeid, String irsaliyeno, Integer firmaid, String firmaad,
                    Integer mamulid, String mamulad, String mamulkod, Integer gkrno, Integer stokid, Integer miktar,
                    Timestamp olusturmatarihi, Timestamp gonderimtarihi, String not, Integer irsaliyefirmaid) {
        super();
        this.id = id;
        this.irsaliyeid = irsaliyeid;
        this.irsaliyeno = irsaliyeno;
        this.firmaid = firmaid;
        this.firmaad = firmaad;
        this.mamulid = mamulid;
        this.mamulad = mamulad;
        this.mamulkod = mamulkod;
        this.gkrno = gkrno;
        this.stokid = stokid;
        this.miktar = miktar;
        this.olusturmatarihi = olusturmatarihi;
        this.gonderimtarihi = gonderimtarihi;
        this.not = not;
        this.irsaliyefirmaid = irsaliyefirmaid;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIrsaliyeid() {
        return irsaliyeid;
    }
    
    public void setIrsaliyeid(Integer irsaliyeid) {
        this.irsaliyeid = irsaliyeid;
    }
    
    public String getIrsaliyeno() {
        return irsaliyeno;
    }
    
    public void setIrsaliyeno(String irsaliyeno) {
        this.irsaliyeno = irsaliyeno;
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
    
    public Integer getGkrno() {
        return gkrno;
    }
    
    public void setGkrno(Integer gkrno) {
        this.gkrno = gkrno;
    }
    
    public Integer getStokid() {
        return stokid;
    }
    
    public void setStokid(Integer stokid) {
        this.stokid = stokid;
    }
    
    public Integer getMiktar() {
        return miktar;
    }
    
    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }
    
    public Timestamp getOlusturmatarihi() {
        return olusturmatarihi;
    }
    
    public void setOlusturmatarihi(Timestamp olusturmatarihi) {
        this.olusturmatarihi = olusturmatarihi;
    }
    
    public Timestamp getGonderimtarihi() {
        return gonderimtarihi;
    }
    
    public void setGonderimtarihi(Timestamp gonderimtarihi) {
        this.gonderimtarihi = gonderimtarihi;
    }
    
    public String getNot() {
        return not;
    }
    
    public void setNot(String not) {
        this.not = not;
    }

    public Integer getIrsaliyefirmaid() {
        return irsaliyefirmaid;
    }

    public void setIrsaliyefirmaid(Integer irsaliyefirmaid) {
        this.irsaliyefirmaid = irsaliyefirmaid;
    }
    
}
