package com.example.administrator.koudaiwanzi.product;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class FilterBean {
    /**
     * DetailUrl : Items.svc/displayfilter/1e4b15ba-a3ca-48d3-baa0-97a3cd4a8C98/
     * msg : 0
     * Items : [{"CODENAME":"蛋糕蛋卷","LID":"ae7b14ba-a2cb-4543-bba0-974a3d4a8Cb7"},{"CODENAME":"休闲下午茶","LID":"ae7b14ba-a2cb-4543-bba0-974a3d4a8Cbd"},{"CODENAME":"特色饮品","LID":"ae7b14ba-a2cb-4543-bba0-a74a3d4a8Cb7"},{"CODENAME":"调味料","LID":"ae7b14ba-a2cb-4543-bba1-974a3d4a8Cb7"},{"CODENAME":"休闲零食","LID":"ae7b14ba-a2cb-4d43-baa0-974a3d4a8Cb7"},{"CODENAME":"糖果","LID":"ae7b14ba-a2cb-4e43-baa0-974a3d4a8Cb7"},{"CODENAME":"饮料","LID":"ae7b14ba-aceb-4543-bba0-974a3d4a8Cb7"},{"CODENAME":"饼干","LID":"ae7b1cbe-a2cb-4543-baa0-974a3d4a8Cb7"}]
     */

    private String DetailUrl;
    private int msg;
    /**
     * CODENAME : 蛋糕蛋卷
     * LID : ae7b14ba-a2cb-4543-bba0-974a3d4a8Cb7
     */

    private List<ItemsBean> Items;

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

    public List<ItemsBean> getItems() {
        return Items;
    }

    public void setItems(List<ItemsBean> Items) {
        this.Items = Items;
    }

    public static class ItemsBean {
        private String CODENAME;
        private String LID;
        private boolean NameIsSelect;

        public boolean getNameIsSelect() {
            return NameIsSelect;
        }

        public void setNameIsSelect(boolean nameIsSelect) {
            NameIsSelect = nameIsSelect;
        }

        public String getCODENAME() {
            return CODENAME;
        }

        public void setCODENAME(String CODENAME) {
            this.CODENAME = CODENAME;
        }

        public String getLID() {
            return LID;
        }

        public void setLID(String LID) {
            this.LID = LID;
        }
    }
}
