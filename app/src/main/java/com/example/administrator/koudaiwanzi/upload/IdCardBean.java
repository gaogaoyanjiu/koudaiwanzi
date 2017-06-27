package com.example.administrator.koudaiwanzi.upload;

/**
 * Created by Administrator on 2016/9/6.
 */
public class IdCardBean {

    /**
     * DetailUrl : Users.svc/nameandnum/59d20da6-ae62-4342-855d-d715ab7fa559/
     * msg : 0
     * PERSON : {"DetailUrl":null,"msg":0,"PICBACK":null,"PICFACE":null,"PICNUMBER":null,"REALNAME":null}
     * PICBACKURL : FileStream.svc/picback/59d20da6-ae62-4342-855d-d715ab7fa559
     * PICFACEURL : FileStream.svc/picface/59d20da6-ae62-4342-855d-d715ab7fa559
     */

    private String DetailUrl;
    private int msg;
    /**
     * DetailUrl : null
     * msg : 0
     * PICBACK : null
     * PICFACE : null
     * PICNUMBER : null
     * REALNAME : null
     */

    private PERSONBean PERSON;
    private String PICBACKURL;
    private String PICFACEURL;

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

    public PERSONBean getPERSON() {
        return PERSON;
    }

    public void setPERSON(PERSONBean PERSON) {
        this.PERSON = PERSON;
    }

    public String getPICBACKURL() {
        return PICBACKURL;
    }

    public void setPICBACKURL(String PICBACKURL) {
        this.PICBACKURL = PICBACKURL;
    }

    public String getPICFACEURL() {
        return PICFACEURL;
    }

    public void setPICFACEURL(String PICFACEURL) {
        this.PICFACEURL = PICFACEURL;
    }

    public static class PERSONBean {
        private String DetailUrl;
        private int msg;
        private String PICBACK;
        private String PICFACE;
        private String PICNUMBER;
        private String REALNAME;

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

        public String getPICBACK() {
            return PICBACK;
        }

        public void setPICBACK(String PICBACK) {
            this.PICBACK = PICBACK;
        }

        public String getPICFACE() {
            return PICFACE;
        }

        public void setPICFACE(String PICFACE) {
            this.PICFACE = PICFACE;
        }

        public String getPICNUMBER() {
            return PICNUMBER;
        }

        public void setPICNUMBER(String PICNUMBER) {
            this.PICNUMBER = PICNUMBER;
        }

        public String getREALNAME() {
            return REALNAME;
        }

        public void setREALNAME(String REALNAME) {
            this.REALNAME = REALNAME;
        }
    }
}
