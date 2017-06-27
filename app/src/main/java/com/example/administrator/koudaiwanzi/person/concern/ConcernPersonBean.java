package com.example.administrator.koudaiwanzi.person.concern;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class ConcernPersonBean {
    /**
     * DetailUrl : null
     * msg : 0
     * BLOYNUM : 0
     * CONUM : 0
     * HEADURL : null
     * USERNAME : null
     * story : [{"DetailUrl":"Items.svc/blogdetailed/2ff37f4f-d6a9-4c73-85f8-5b0c9f81c6ba/3dbab2c5-6e8e-4f38-baa4-c399c3601a06","msg":1,"CREATETIME":0,"HEADURL":null,"ITEMS":[],"LIKES":3,"LIKESURL":"Items.svc/ilikeit/2ff37f4f-d6a9-4c73-85f8-5b0c9f81c6ba/3dbab2c5-6e8e-4f38-baa4-c399c3601a06","NID":"2ff37f4f-d6a9-4c73-85f8-5b0c9f81c6ba","NOTES":"哈哈哈哈哈哈哈哈","PREVIEW":"/Image/blog/1c912fcc-362a-4292-a346-6c072aa08d62.jpg","THEMENAME":"Fany同学","THEMETYPE":1,"US":"3dbab2c5-6e8e-4f38-baa4-c399c3601a06","USERNAME":"管理员","VIDEOURL":"/Image/video/2c6791fe-46fc-4fa2-b0e5-d762b183f1af.MP4"},{"DetailUrl":"Items.svc/blogdetailed/cf982162-d7fe-46d1-b762-29081552bc40/3dbab2c5-6e8e-4f38-baa4-c399c3601a06","msg":1,"CREATETIME":0,"HEADURL":null,"ITEMS":[],"LIKES":4,"LIKESURL":"Items.svc/ilikeit/cf982162-d7fe-46d1-b762-29081552bc40/3dbab2c5-6e8e-4f38-baa4-c399c3601a06","NID":"cf982162-d7fe-46d1-b762-29081552bc40","NOTES":"据新华社报道，中国游泳协...","PREVIEW":"/Image/blog/d3cf3410-8e18-4a48-8c5b-0d3ac080a28b.jpg","THEMENAME":"陈欣怡兴奋剂检测未过关 11图告诉你她是谁？","THEMETYPE":0,"US":"3dbab2c5-6e8e-4f38-baa4-c399c3601a06","USERNAME":"管理员","VIDEOURL":null},{"DetailUrl":"Items.svc/blogdetailed/c9cb938b-5ea2-42d2-a381-aa4213cc8acf/3dbab2c5-6e8e-4f38-baa4-c399c3601a06","msg":1,"CREATETIME":0,"HEADURL":null,"ITEMS":[],"LIKES":5,"LIKESURL":"Items.svc/ilikeit/c9cb938b-5ea2-42d2-a381-aa4213cc8acf/3dbab2c5-6e8e-4f38-baa4-c399c3601a06","NID":"c9cb938b-5ea2-42d2-a381-aa4213cc8acf","NOTES":"中国游泳协会负责人表示，...","PREVIEW":"/Image/blog/429083c4-95fb-4d16-b63c-8270b50c9814.jpg","THEMENAME":"陈欣怡兴奋剂检测未过关 11图告诉你她是谁？","THEMETYPE":0,"US":"3dbab2c5-6e8e-4f38-baa4-c399c3601a06","USERNAME":"管理员","VIDEOURL":null},{"DetailUrl":"Items.svc/blogdetailed/03de22f3-3f0b-4fd6-bb24-18fda950dc50/3dbab2c5-6e8e-4f38-baa4-c399c3601a06","msg":0,"CREATETIME":1471313413,"HEADURL":null,"ITEMS":[],"LIKES":0,"LIKESURL":"Items.svc/ilikeit/03de22f3-3f0b-4fd6-bb24-18fda950dc50/3dbab2c5-6e8e-4f38-baa4-c399c3601a06","NID":"03de22f3-3f0b-4fd6-bb24-18fda950dc50","NOTES":"陈欣怡，1998年出生，...","PREVIEW":"/Image/blog/489a5cb5-e780-4992-a90a-fc0548b7d3df.jpg","THEMENAME":"陈欣怡兴奋剂检测未过关 11图告诉你她是谁？","THEMETYPE":0,"US":null,"USERNAME":"管理员","VIDEOURL":null}]
     */

    private Object DetailUrl;
    private int msg;
    private int BLOYNUM;
    private int CONUM;
    private Object HEADURL;
    private Object USERNAME;
    /**
     * DetailUrl : Items.svc/blogdetailed/2ff37f4f-d6a9-4c73-85f8-5b0c9f81c6ba/3dbab2c5-6e8e-4f38-baa4-c399c3601a06
     * msg : 1
     * CREATETIME : 0
     * HEADURL : null
     * ITEMS : []
     * LIKES : 3
     * LIKESURL : Items.svc/ilikeit/2ff37f4f-d6a9-4c73-85f8-5b0c9f81c6ba/3dbab2c5-6e8e-4f38-baa4-c399c3601a06
     * NID : 2ff37f4f-d6a9-4c73-85f8-5b0c9f81c6ba
     * NOTES : 哈哈哈哈哈哈哈哈
     * PREVIEW : /Image/blog/1c912fcc-362a-4292-a346-6c072aa08d62.jpg
     * THEMENAME : Fany同学
     * THEMETYPE : 1
     * US : 3dbab2c5-6e8e-4f38-baa4-c399c3601a06
     * USERNAME : 管理员
     * VIDEOURL : /Image/video/2c6791fe-46fc-4fa2-b0e5-d762b183f1af.MP4
     */

    private List<StoryBean> story;

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

    public int getBLOYNUM() {
        return BLOYNUM;
    }

    public void setBLOYNUM(int BLOYNUM) {
        this.BLOYNUM = BLOYNUM;
    }

    public int getCONUM() {
        return CONUM;
    }

    public void setCONUM(int CONUM) {
        this.CONUM = CONUM;
    }

    public Object getHEADURL() {
        return HEADURL;
    }

    public void setHEADURL(Object HEADURL) {
        this.HEADURL = HEADURL;
    }

    public Object getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(Object USERNAME) {
        this.USERNAME = USERNAME;
    }

    public List<StoryBean> getStory() {
        return story;
    }

    public void setStory(List<StoryBean> story) {
        this.story = story;
    }

    public static class StoryBean {
        private String DetailUrl;
        private int msg;
        private int CREATETIME;
        private Object HEADURL;
        private int LIKES;
        private String LIKESURL;
        private String NID;
        private String NOTES;
        private String PREVIEW;
        private String THEMENAME;
        private int THEMETYPE;
        private String US;
        private String USERNAME;
        private String VIDEOURL;
        private List<?> ITEMS;

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

        public int getCREATETIME() {
            return CREATETIME;
        }

        public void setCREATETIME(int CREATETIME) {
            this.CREATETIME = CREATETIME;
        }

        public Object getHEADURL() {
            return HEADURL;
        }

        public void setHEADURL(Object HEADURL) {
            this.HEADURL = HEADURL;
        }

        public int getLIKES() {
            return LIKES;
        }

        public void setLIKES(int LIKES) {
            this.LIKES = LIKES;
        }

        public String getLIKESURL() {
            return LIKESURL;
        }

        public void setLIKESURL(String LIKESURL) {
            this.LIKESURL = LIKESURL;
        }

        public String getNID() {
            return NID;
        }

        public void setNID(String NID) {
            this.NID = NID;
        }

        public String getNOTES() {
            return NOTES;
        }

        public void setNOTES(String NOTES) {
            this.NOTES = NOTES;
        }

        public String getPREVIEW() {
            return PREVIEW;
        }

        public void setPREVIEW(String PREVIEW) {
            this.PREVIEW = PREVIEW;
        }

        public String getTHEMENAME() {
            return THEMENAME;
        }

        public void setTHEMENAME(String THEMENAME) {
            this.THEMENAME = THEMENAME;
        }

        public int getTHEMETYPE() {
            return THEMETYPE;
        }

        public void setTHEMETYPE(int THEMETYPE) {
            this.THEMETYPE = THEMETYPE;
        }

        public String getUS() {
            return US;
        }

        public void setUS(String US) {
            this.US = US;
        }

        public String getUSERNAME() {
            return USERNAME;
        }

        public void setUSERNAME(String USERNAME) {
            this.USERNAME = USERNAME;
        }

        public String getVIDEOURL() {
            return VIDEOURL;
        }

        public void setVIDEOURL(String VIDEOURL) {
            this.VIDEOURL = VIDEOURL;
        }

        public List<?> getITEMS() {
            return ITEMS;
        }

        public void setITEMS(List<?> ITEMS) {
            this.ITEMS = ITEMS;
        }
    }
}
