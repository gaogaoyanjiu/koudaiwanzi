package com.example.administrator.koudaiwanzi.newproduct;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class NewProductBean {



    private String DetailUrl;
    private int msg;
    private int PAYCOUNT;
    /**
     * DetailUrl :
     * msg : 0
     * ICOURL : /Image/icocategory/e3cc1b26-50b9-4a77-83ee-bc0bc08249db.jpg
     * NEWITEMS : [{"DetailUrl":"Items.svc//item//0163cb05-5182-4fb0-99a8-cc8d10ce2b3c","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"0163cb05-5182-4fb0-99a8-cc8d10ce2b3c","DISCOUNT":0,"ICOURL":"/Image/ico/67730d8a-1262-4c73-a561-6e676ae52c7c.jpg","PRICE":35,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"狮王 KIREI 水果混合香型泡沫洁净洗手液"},{"DetailUrl":"Items.svc//item//03b19b2d-d5d8-438c-bd8e-5279015e5149","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"03b19b2d-d5d8-438c-bd8e-5279015e5149","DISCOUNT":0,"ICOURL":"/Image/ico/838eec54-82c8-4be2-b4d8-8f41eca05afa.jpg","PRICE":200,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"skin peace 防干燥全食物成分润唇膏"},{"DetailUrl":"Items.svc//item//09aa010a-b146-4238-87a7-b845ce104815","msg":0,"BARGAIN":39.2,"BONAME":"明治meiji","CAPACITY":0,"CID":"09aa010a-b146-4238-87a7-b845ce104815","DISCOUNT":7,"ICOURL":"/Image/ico/5e4f79f3-81eb-4a0b-b652-94219e554709.jpg","PRICE":56,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"水润防晒凝露 清新香皂味 SPF40 PA+++"},{"DetailUrl":"Items.svc//item//09f29941-a301-491b-8762-f35b773cf46b","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"09f29941-a301-491b-8762-f35b773cf46b","DISCOUNT":0,"ICOURL":"/Image/ico/839e9ce2-3626-410c-b71b-5aade382b8b1.jpg","PRICE":140,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"minon 氨基酸保湿乳液"},{"DetailUrl":"Items.svc//item//23855e64-943e-4c65-9ee6-40fdaa1b0d70","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"23855e64-943e-4c65-9ee6-40fdaa1b0d70","DISCOUNT":0,"ICOURL":"/Image/ico/0d16275b-9905-4188-9a1c-b28eec24dd50.jpg","PRICE":1200,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"skin peace 保湿润肤护手霜"},{"DetailUrl":"Items.svc//item//294c1f6c-93de-4cae-88da-44bfff932c0f","msg":0,"BARGAIN":0,"BONAME":"Fuwarie","CAPACITY":0,"CID":"294c1f6c-93de-4cae-88da-44bfff932c0f","DISCOUNT":0,"ICOURL":"/Image/ico/28f0fd0e-4889-40ea-89f8-8d997793fada.jpg","PRICE":150,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Kracie Fuwarie 卷翘造型发雾"},{"DetailUrl":"Items.svc//item//2bbd38e1-77be-454b-81c9-9f8131625ae8","msg":0,"BARGAIN":0,"BONAME":"Himecosme","CAPACITY":0,"CID":"2bbd38e1-77be-454b-81c9-9f8131625ae8","DISCOUNT":0,"ICOURL":"/Image/ico/ef0e5ca8-f4b4-4900-b910-1f4921a7a050.jpg","PRICE":345,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Himecosme 纤长瞬翘防水睫毛膏"},{"DetailUrl":"Items.svc//item//2e22afec-02aa-4698-9488-03be13b9eacc","msg":0,"BARGAIN":0,"BONAME":"肌美精","CAPACITY":0,"CID":"2e22afec-02aa-4698-9488-03be13b9eacc","DISCOUNT":0,"ICOURL":"/Image/ico/49e706d0-94cf-4001-9306-93138910075f.jpg","PRICE":250,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Kracie 肌美精all in one导入化妆水"},{"DetailUrl":"Items.svc//item//454d839b-783a-4206-89e1-0c3eaa8792f3","msg":0,"BARGAIN":0,"BONAME":"Himecosme","CAPACITY":0,"CID":"454d839b-783a-4206-89e1-0c3eaa8792f3","DISCOUNT":0,"ICOURL":"/Image/ico/3f8754c9-dca7-42ef-8702-67701c2ad5ad.jpg","PRICE":300,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Himecosme 防晒霜"},{"DetailUrl":"Items.svc//item//78cee74f-e205-4dd0-88f6-eb8677dddb51","msg":0,"BARGAIN":190,"BONAME":"minon","CAPACITY":0,"CID":"78cee74f-e205-4dd0-88f6-eb8677dddb51","DISCOUNT":9.5,"ICOURL":"/Image/ico/5afe10db-84d1-47ee-822a-ef0e02686595.jpg","PRICE":200,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"skin peace 高保湿化妆水"},{"DetailUrl":"Items.svc//item//7c62ab1e-414e-4a20-8a75-ec0c238e1d4f","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"7c62ab1e-414e-4a20-8a75-ec0c238e1d4f","DISCOUNT":0,"ICOURL":"/Image/ico/944d3736-53c9-4295-b456-21c851366709.jpg","PRICE":78.8,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"狮王 脚底专用穴位刺激贴"},{"DetailUrl":"Items.svc//item//7f490f35-bd6d-45ee-b20d-6e3894fbb74b","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"7f490f35-bd6d-45ee-b20d-6e3894fbb74b","DISCOUNT":0,"ICOURL":"/Image/ico/b496718b-7e65-41df-aec7-1dd8382a0f48.jpg","PRICE":420,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"skin peace 无添加驱蚊防晒霜"},{"DetailUrl":"Items.svc//item//826646e1-17ca-4099-9e96-608feab7eca8","msg":0,"BARGAIN":0,"BONAME":"ICHIKAMI","CAPACITY":0,"CID":"826646e1-17ca-4099-9e96-608feab7eca8","DISCOUNT":0,"ICOURL":"/Image/ico/bdbed7dd-7366-4719-bd6a-32c4e6a42b20.jpg","PRICE":230,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Kracie ICHIKAMI 和草美人丝滑润泽顺发水 "},{"DetailUrl":"Items.svc//item//83e9448b-f317-42be-a4e0-01cfba002ff7","msg":0,"BARGAIN":0,"BONAME":"Fuwarie","CAPACITY":0,"CID":"83e9448b-f317-42be-a4e0-01cfba002ff7","DISCOUNT":0,"ICOURL":"/Image/ico/5650d193-1c3b-4715-bde0-b74a86117fce.jpg","PRICE":130,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Kracie Fuwarie 卷发造型喷雾 "},{"DetailUrl":"Items.svc//item//8e311000-3125-4312-ad3e-6827d84d9a5c","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"8e311000-3125-4312-ad3e-6827d84d9a5c","DISCOUNT":0,"ICOURL":"/Image/ico/f5f90369-fb67-4f1d-9f6c-41e9753171bd.jpg","PRICE":90,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"狮王 KIREI 淡清香泡沫洁净洗手液"},{"DetailUrl":"Items.svc//item//9701e52c-bf51-4ce6-a383-334a2f87d262","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"9701e52c-bf51-4ce6-a383-334a2f87d262","DISCOUNT":0,"ICOURL":"/Image/ico/2dfcae4c-fc3c-4cf4-993c-97ba8adb2a60.jpg","PRICE":130,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"minon 氨基酸保湿面膜"},{"DetailUrl":"Items.svc//item//9c01706f-089f-4ca0-a920-672b8c056083","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"9c01706f-089f-4ca0-a920-672b8c056083","DISCOUNT":0,"ICOURL":"/Image/ico/49acedf4-b629-4a01-99e9-13f6e80fa0e8.jpg","PRICE":150,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"minon 全身用滋润型洗发液浴液二合一"},{"DetailUrl":"Items.svc//item//9e4b12f1-4af7-4eef-98a3-7379b4500387","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"9e4b12f1-4af7-4eef-98a3-7379b4500387","DISCOUNT":0,"ICOURL":"/Image/ico/97a527f9-22e2-41cb-ab24-4b03d77cbeb0.jpg","PRICE":603,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"skin peace 美白保湿喷雾"},{"DetailUrl":"Items.svc//item//a0a7f23b-ed90-4d32-baf3-d1c93287533f","msg":0,"BARGAIN":0,"BONAME":"肌美精","CAPACITY":0,"CID":"a0a7f23b-ed90-4d32-baf3-d1c93287533f","DISCOUNT":0,"ICOURL":"/Image/ico/aaa66f81-1bb1-4869-896d-9d58cf48e6b8.jpg","PRICE":500,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Kracie 肌美精all in one导入化妆水+美白面霜套装"},{"DetailUrl":"Items.svc//item//ae618be8-52d5-420e-bf86-adf89553c484","msg":0,"BARGAIN":0,"BONAME":"ICHIKAMI","CAPACITY":0,"CID":"ae618be8-52d5-420e-bf86-adf89553c484","DISCOUNT":0,"ICOURL":"/Image/ico/ba344239-6bf0-4e0d-9454-eb2dc6496fc6.jpg","PRICE":230,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Kracie ICHIKAMI 和草美人顺滑润泽顺发水 "},{"DetailUrl":"Items.svc//item//bedbd0a4-4e42-4a50-9e9b-d5139533290a","msg":0,"BARGAIN":199.8,"BONAME":"AQUA SAVON","CAPACITY":0,"CID":"bedbd0a4-4e42-4a50-9e9b-d5139533290a","DISCOUNT":9,"ICOURL":"/Image/ico/f26c6f68-9630-4e8b-958d-3291157fa3f4.jpg","PRICE":222,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"无添加清爽防晒凝露 SPF32 PA+++"},{"DetailUrl":"Items.svc//item//cf1bdf84-8a29-4979-9fc0-f61648f135a6","msg":0,"BARGAIN":0,"BONAME":"AQUA SAVON","CAPACITY":0,"CID":"cf1bdf84-8a29-4979-9fc0-f61648f135a6","DISCOUNT":0,"ICOURL":"/Image/ico/8c126683-b9a6-43fe-92ba-a142d77a7bbb.jpg","PRICE":222,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"水润防晒凝露 清新香皂味 SPF40 PA+++"},{"DetailUrl":"Items.svc//item//de1e157b-0880-4451-9413-30dd64128e9c","msg":0,"BARGAIN":0,"BONAME":"肌美精","CAPACITY":0,"CID":"de1e157b-0880-4451-9413-30dd64128e9c","DISCOUNT":0,"ICOURL":"/Image/ico/fec3dd9d-49e7-443e-be2a-25c447fff210.jpg","PRICE":120,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Kracie 肌美精美白药用乳液"},{"DetailUrl":"Items.svc//item//e2eed500-bbc3-4d3e-b0df-1aed77439bab","msg":0,"BARGAIN":0,"BONAME":"Fuwarie","CAPACITY":0,"CID":"e2eed500-bbc3-4d3e-b0df-1aed77439bab","DISCOUNT":0,"ICOURL":"/Image/ico/b9c4d3a7-f479-440a-843a-d35ae88b01c1.jpg","PRICE":160,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Kracie Fuwarie 直发造型喷雾"},{"DetailUrl":"Items.svc//item//e8e3adf6-e9bd-4897-92ca-b6b053f91173","msg":0,"BARGAIN":0,"BONAME":"肌美精","CAPACITY":0,"CID":"e8e3adf6-e9bd-4897-92ca-b6b053f91173","DISCOUNT":0,"ICOURL":"/Image/ico/ca50ea27-302f-46ca-b41f-75f260b93ef7.jpg","PRICE":300,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Mandom happy deo降温喷雾 冰霜薄荷型"},{"DetailUrl":"Items.svc//item//e9b2b1bb-c8e9-47a1-9875-7b34d45c91c0","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"e9b2b1bb-c8e9-47a1-9875-7b34d45c91c0","DISCOUNT":0,"ICOURL":"/Image/ico/794ba47e-7785-4da6-a829-e59540e0d167.jpg","PRICE":80,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"狮王 Disney儿童可吞食防龋齿草莓味牙膏"},{"DetailUrl":"Items.svc//item//ebbb8bdd-1fe4-4f7b-8f43-95d3b11761ef","msg":0,"BARGAIN":0,"BONAME":"Himecosme","CAPACITY":0,"CID":"ebbb8bdd-1fe4-4f7b-8f43-95d3b11761ef","DISCOUNT":0,"ICOURL":"/Image/ico/328ce6a1-b9fc-4c6c-a0ea-42751219f798.jpg","PRICE":460,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Himecosme大眼立现微雕精华眼霜"},{"DetailUrl":"Items.svc//item//ee78bbe5-4a27-4abc-b081-1b2d67726be9","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"ee78bbe5-4a27-4abc-b081-1b2d67726be9","DISCOUNT":0,"ICOURL":"/Image/ico/88ae4faf-3454-4279-9f69-45f93267328a.jpg","PRICE":120,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"minon 氨基酸保湿化妆水"},{"DetailUrl":"Items.svc//item//eebbbece-c54f-437c-9bc6-f20c22c5fe99","msg":0,"BARGAIN":108,"BONAME":"minon","CAPACITY":0,"CID":"eebbbece-c54f-437c-9bc6-f20c22c5fe99","DISCOUNT":9,"ICOURL":"/Image/ico/305a5174-7332-4866-956b-ce0763f7bc45.jpg","PRICE":120,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"Himecosme 防晒霜"},{"DetailUrl":"Items.svc//item//f09113de-ecb0-42c3-ba9a-0c7e6e27d479","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"f09113de-ecb0-42c3-ba9a-0c7e6e27d479","DISCOUNT":0,"ICOURL":"/Image/ico/4e189c78-69b4-4063-ac3f-0fc2314b78a3.jpg","PRICE":150,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"minon 婴儿用全身用低刺激全身保湿洗发液浴液二合一"},{"DetailUrl":"Items.svc//item//fa12fb6d-6462-4a0b-a3fc-16f0e1cd314a","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"fa12fb6d-6462-4a0b-a3fc-16f0e1cd314a","DISCOUNT":0,"ICOURL":"/Image/ico/c7c5d20f-1489-4dda-93b9-00a746221978.jpg","PRICE":230,"SALESVOL":0,"SORTPRICE":0,"TRADENAME":"skin peace 精华液喷雾"}]
     */

    private List<CATEGORYBean> CATEGORY;
    /**
     * DetailUrl : null
     * msg : 0
     * IMGINDEX : 0
     * IMGURL : /Image/banner//76f0b9bf-eaf5-43eb-b61e-c5a7c188b560.jpg
     */

    private List<NEWBANNERBean> NEWBANNER;

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

    public int getPAYCOUNT() {
        return PAYCOUNT;
    }

    public void setPAYCOUNT(int PAYCOUNT) {
        this.PAYCOUNT = PAYCOUNT;
    }

    public List<CATEGORYBean> getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(List<CATEGORYBean> CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public List<NEWBANNERBean> getNEWBANNER() {
        return NEWBANNER;
    }

    public void setNEWBANNER(List<NEWBANNERBean> NEWBANNER) {
        this.NEWBANNER = NEWBANNER;
    }

    public static class CATEGORYBean {
        private String DetailUrl;
        private int msg;
        private String ICOURL;
        /**
         * DetailUrl : Items.svc//item//0163cb05-5182-4fb0-99a8-cc8d10ce2b3c
         * msg : 0
         * BARGAIN : 0
         * BONAME : minon
         * CAPACITY : 0
         * CID : 0163cb05-5182-4fb0-99a8-cc8d10ce2b3c
         * DISCOUNT : 0.0
         * ICOURL : /Image/ico/67730d8a-1262-4c73-a561-6e676ae52c7c.jpg
         * PRICE : 35.0
         * SALESVOL : 0
         * SORTPRICE : 0
         * TRADENAME : 狮王 KIREI 水果混合香型泡沫洁净洗手液
         */

        private List<NEWITEMBean> NEWITEMS;

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

        public List<NEWITEMBean> getNEWITEMS() {
            return NEWITEMS;
        }

        public void setNEWITEMS(List<NEWITEMBean> NEWITEMS) {
            this.NEWITEMS = NEWITEMS;
        }

    }

    public static class NEWBANNERBean {
        private Object DetailUrl;
        private int msg;
        private int IMGINDEX;
        private String IMGURL;

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

        public int getIMGINDEX() {
            return IMGINDEX;
        }

        public void setIMGINDEX(int IMGINDEX) {
            this.IMGINDEX = IMGINDEX;
        }

        public String getIMGURL() {
            return IMGURL;
        }

        public void setIMGURL(String IMGURL) {
            this.IMGURL = IMGURL;
        }
    }
}
