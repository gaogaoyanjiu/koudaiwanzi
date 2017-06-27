package com.example.administrator.koudaiwanzi.classification.type;

import java.util.List;

/**
 * Created by liu on 2016/5/20.
 */
public class TypeBean {


    /**
     * DetailUrl : null
     * msg : 0
     * ICOURL : /Image/type/f2.jpg
     * SECLEVEL : [{"DetailUrl":"Items.svc//catelist//ab7b14ba-a2cb-4543-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"提亮肤色"},{"DetailUrl":"Items.svc//catelist//ae7b14ba-a2cb-4543-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"眼线"},{"DetailUrl":"Items.svc//catelist//ae7b14ba-a2cb-4553-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"眉笔"},{"DetailUrl":"Items.svc//catelist//ae7b14ba-a2cb-45d3-baa0-974a2d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"防晒"},{"DetailUrl":"Items.svc//catelist//ae7b14ba-a2cb-45d3-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"隔离"},{"DetailUrl":"Items.svc//catelist//ae7b14ba-a2cb-4643-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"睫毛"},{"DetailUrl":"Items.svc//catelist//ae7b14ba-aecb-4543-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"美容工具"},{"DetailUrl":"Items.svc//catelist//ae7b14ba-c2cb-4543-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"眼影"},{"DetailUrl":"Items.svc//catelist//ae7b14ea-a2cb-4543-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"腮红"},{"DetailUrl":"Items.svc//catelist//ae7c14ba-a2cb-4543-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"口红"},{"DetailUrl":"Items.svc//catelist//aeeb14ba-a2cb-4543-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98","msg":0,"CODENAME":"修容"}]
     */

    private Object DetailUrl;
    private int msg;
    private String ICOURL;
    /**
     * DetailUrl : Items.svc//catelist//ab7b14ba-a2cb-4543-baa0-974a3d4a8Cb7//1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98
     * msg : 0
     * CODENAME : 提亮肤色
     */

    private List<SECLEVELBean> SECLEVEL;

    public Object getDetailUrl() {
        return DetailUrl;
    }

    public void setDetailUrl(Object DetailUrl) {
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

    public static class SECLEVELBean {
        private String DetailUrl;
        private int msg;
        private String CODENAME;

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

        public String getCODENAME() {
            return CODENAME;
        }

        public void setCODENAME(String CODENAME) {
            this.CODENAME = CODENAME;
        }
    }
}
