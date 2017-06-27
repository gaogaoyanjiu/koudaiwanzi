package com.example.administrator.koudaiwanzi.person.point.pointmall;

/**
 * Created by Administrator on 2016/8/31.
 */
public class TigerBean {
    /**
     * DetailUrl : null
     * msg : 1
     * BATMAN : 2
     * DORAEMON : 0
     * MARIO : 0
     * POINT : 10
     */

    private String DetailUrl;
    private int msg;
    private int BATMAN;
    private int DORAEMON;
    private int MARIO;
    private int POINT;

    public  String getDetailUrl() {
        return DetailUrl;
    }

    public void setDetailUrl( String DetailUrl) {
        this.DetailUrl = DetailUrl;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public int getBATMAN() {
        return BATMAN;
    }

    public void setBATMAN(int BATMAN) {
        this.BATMAN = BATMAN;
    }

    public int getDORAEMON() {
        return DORAEMON;
    }

    public void setDORAEMON(int DORAEMON) {
        this.DORAEMON = DORAEMON;
    }

    public int getMARIO() {
        return MARIO;
    }

    public void setMARIO(int MARIO) {
        this.MARIO = MARIO;
    }

    public int getPOINT() {
        return POINT;
    }

    public void setPOINT(int POINT) {
        this.POINT = POINT;
    }
}
