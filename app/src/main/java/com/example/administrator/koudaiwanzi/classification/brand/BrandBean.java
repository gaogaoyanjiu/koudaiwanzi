package com.example.administrator.koudaiwanzi.classification.brand;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class BrandBean {

    /**
     * DetailUrl : Items.svc//display//5d2b08d9-c741-4dfb-b9c0-daa31e0cfb38
     * msg : 0
     * ICOURL : /Image/icocategory/200ae584-7ff4-4f64-8a17-e24468af5e6e.jpg
     * SECLEVEL : [{"DetailUrl":"Items.svc//displayforbrand//0b9ae90d-1085-495d-bc70-f859d5b961ef","msg":0,"CODENAME":"/Image/logo//00eab550-f662-4672-826f-a3c62a9d571b.jpg"},{"DetailUrl":"Items.svc//displayforbrand//2b50015f-aa8d-4d97-bfe5-ab94ed9136c3","msg":0,"CODENAME":"/Image/logo//a47c8111-2432-49fd-8627-9114bef853fb.png"},{"DetailUrl":"Items.svc//displayforbrand//fc3ece07-5f18-49a8-a19e-f9c767d8b6ff","msg":0,"CODENAME":"/Image/logo//3702c2fe-00e5-4d54-95bc-2bf9c1d6b99f.jpg"}]
     */

    private String DetailUrl;
    private int msg;
    private String ICOURL;
    private String TID;

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    /**
     * DetailUrl : Items.svc//displayforbrand//0b9ae90d-1085-495d-bc70-f859d5b961ef
     * msg : 0
     * CODENAME : /Image/logo//00eab550-f662-4672-826f-a3c62a9d571b.jpg
     */

    private List<SECLEVELBean> SECLEVEL;

    public String getDetailUrl() {
        return DetailUrl;
    }

    public void setDetailUrl(String DetailUrl) {
        this.DetailUrl = DetailUrl;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public String getICOURL() {
        return ICOURL;
    }

    public void setICOURL(String ICOURL) {
        this.ICOURL = ICOURL;
    }

    public List<SECLEVELBean> getSECLEVEL() {
        return SECLEVEL;
    }

    public void setSECLEVEL(List<SECLEVELBean> SECLEVEL) {
        this.SECLEVEL = SECLEVEL;
    }
}
