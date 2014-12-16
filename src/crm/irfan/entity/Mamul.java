package crm.irfan.entity;

import java.util.Date;

public class Mamul {
    private Integer id;
    private String  ad;
    private String  kod;
    private Integer firmaid;
    private String  firmaad;
    private Integer cevrimsuresi;
    private Float agirlik;
    private Integer figur;
    private Date    eklemetarihi;
    private String  hammadde;
    private String  hammaddegkrno;
    private String  hammaddemiktar;
    private String  hammaddemamulbasi;
    private String  yarimamul;
    private String  yarimamulgkrno;
    private String  yarimamulmiktar;
    private String  yarimamulmamulbasi;
    
    public Mamul(Integer id, String ad, String kod, Integer firmaid, String firmaad, Integer cevrimsuresi,
                 Float agirlik, Integer figur, Date eklemetarihi, String hammadde, String hammaddegkrno, String hammaddemiktar,
                    String hammaddemamulbasi, String yarimamul, String yarimamulgkrno, String yarimamulmiktar,
                    String yarimamulmamulbasi) {
        super();
        this.id = id;
        this.ad = ad;
        this.kod = kod;
        this.firmaid = firmaid;
        this.firmaad = firmaad;
        this.cevrimsuresi = cevrimsuresi;
        this.agirlik = agirlik;
        this.figur = figur;
        this.eklemetarihi = eklemetarihi;
        this.hammadde = hammadde;
        this.hammaddegkrno = hammaddegkrno;
        this.hammaddemiktar = hammaddemiktar;
        this.hammaddemamulbasi = hammaddemamulbasi;
        this.yarimamul = yarimamul;
        this.yarimamulgkrno = yarimamulgkrno;
        this.yarimamulmiktar = yarimamulmiktar;
        this.yarimamulmamulbasi = yarimamulmamulbasi;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getAd() {
        return ad;
    }
    
    public void setAd(String ad) {
        this.ad = ad;
    }
    
    public String getKod() {
        return kod;
    }
    
    public void setKod(String kod) {
        this.kod = kod;
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
    
    public Integer getCevrimsuresi() {
        return cevrimsuresi;
    }
    
    public void setCevrimsuresi(Integer cevrimsuresi) {
        this.cevrimsuresi = cevrimsuresi;
    }
    
    public Date getEklemetarihi() {
        return eklemetarihi;
    }
    
    public void setEklemetarihi(Date eklemetarihi) {
        this.eklemetarihi = eklemetarihi;
    }
    
    public String getHammadde() {
        return hammadde;
    }
    
    public void setHammadde(String hammadde) {
        this.hammadde = hammadde;
    }
    
    public String getHammaddegkrno() {
        return hammaddegkrno;
    }
    
    public void setHammaddegkrno(String hammaddegkrno) {
        this.hammaddegkrno = hammaddegkrno;
    }
    
    public String getYarimamul() {
        return yarimamul;
    }
    
    public void setYarimamul(String yarimamul) {
        this.yarimamul = yarimamul;
    }
    
    public String getYarimamulgkrno() {
        return yarimamulgkrno;
    }
    
    public void setYarimamulgkrno(String yarimamulgkrno) {
        this.yarimamulgkrno = yarimamulgkrno;
    }
    
    public String getHammaddemiktar() {
        return hammaddemiktar;
    }
    
    public void setHammaddemiktar(String hammaddemiktar) {
        this.hammaddemiktar = hammaddemiktar;
    }
    
    public String getYarimamulmiktar() {
        return yarimamulmiktar;
    }
    
    public void setYarimamulmiktar(String yarimamulmiktar) {
        this.yarimamulmiktar = yarimamulmiktar;
    }
    
    public Float getAgirlik() {
        return agirlik;
    }
    
    public void setAgirlik(Float agirlik) {
        this.agirlik = agirlik;
    }
    
    public Integer getFigur() {
        return figur;
    }

    public void setFigur(Integer figur) {
        this.figur = figur;
    }

    public String getHammaddemamulbasi() {
        return hammaddemamulbasi;
    }
    
    public void setHammaddemamulbasi(String hammaddemamulbasi) {
        this.hammaddemamulbasi = hammaddemamulbasi;
    }
    
    public String getYarimamulmamulbasi() {
        return yarimamulmamulbasi;
    }
    
    public void setYarimamulmamulbasi(String yarimamulmamulbasi) {
        this.yarimamulmamulbasi = yarimamulmamulbasi;
    }
}
