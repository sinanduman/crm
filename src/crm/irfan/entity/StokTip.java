package crm.irfan.entity;

public enum StokTip {
    Giris(1, "Giriş"), Cikis(2, "Çıkış");
    private Integer stokTipId;
    private String  stokTipAd;
    
    StokTip(Integer stokTipId, String stokTipAd) {
        this.stokTipId = stokTipId;
        this.stokTipAd = stokTipAd;  
    }
    
    public Integer getStokTipId() {
        return stokTipId;
    }
    
    public String getStokTipAd() {
        return stokTipAd;
    }
}
