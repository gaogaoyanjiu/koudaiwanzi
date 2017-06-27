package com.example.administrator.koudaiwanzi.ems;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class EMSBean {

    /**
     * DELINUM : 123456
     * com : ems
     * condition : 00
     * data : [{"context":"【遵义市】投递并签收，签收人：本人收","ftime":"2016-07-02 07:40:00","time":"2016-07-02 07:40:00"},{"context":"【无锡市】离开无锡市 发往连云港市（经转）","ftime":"2016-07-02 06:28:00","time":"2016-07-02 06:28:00"},{"context":"【无锡市】到达无锡区域处理中心处理中心（经转）","ftime":"2016-07-02 04:25:00","time":"2016-07-02 04:25:00"},{"context":"【宁波市】离开宁波市 发往无锡市（经转）","ftime":"2016-07-02 00:36:00","time":"2016-07-02 00:36:00"},{"context":"【镇江市】到达镇江转运中心处理中心（经转）","ftime":"2016-06-24 06:58:00","time":"2016-06-24 06:58:00"}]
     * ischeck : 0
     * message : ok
     * nu : 123456
     * state : 3
     * status : 200
     */

    private String DELINUM;
    private String com;
    private String condition;
    private String ischeck;
    private String message;
    private String nu;
    private String state;
    private String status;

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    private int TYPE;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    private ResponseBean response;
    /**
     * context : 【遵义市】投递并签收，签收人：本人收
     * ftime : 2016-07-02 07:40:00
     * time : 2016-07-02 07:40:00
     */

    private List<DataBean> data;

    public String getDELINUM() {
        return DELINUM;
    }

    public void setDELINUM(String DELINUM) {
        this.DELINUM = DELINUM;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String context;
        private String ftime;
        private String time;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class ResponseBean {
        /**
         * expressName : 圆通速递
         * expressNo : 809426534276
         * expressRouteItemList : {"expressRouteItems":[{"notes":"已收到订单信息  收到订单信息","optime":"2016-12-07 17:25:39","state":"20100"},{"notes":"海外仓待入库  海外仓待入库","optime":"2016-12-07 17:27:26","state":"20200"},{"notes":"海外仓库分拣包装完成，等待出库  海外仓待出库","optime":"2016-12-07 17:47:59","state":"20250"}]}
         * orderId : 335196377161056
         * wtdOrderId : 161207172039000e9
         */

        private ExpressRouteBean expressRoute;

        public ExpressRouteBean getExpressRoute() {
            return expressRoute;
        }

        public void setExpressRoute(ExpressRouteBean expressRoute) {
            this.expressRoute = expressRoute;
        }

        public static class ExpressRouteBean {
            private String expressName;
            private String expressNo;
            private ExpressRouteItemListBean expressRouteItemList;
            private String orderId;
            private String wtdOrderId;

            public String getExpressName() {
                return expressName;
            }

            public void setExpressName(String expressName) {
                this.expressName = expressName;
            }

            public String getExpressNo() {
                return expressNo;
            }

            public void setExpressNo(String expressNo) {
                this.expressNo = expressNo;
            }

            public ExpressRouteItemListBean getExpressRouteItemList() {
                return expressRouteItemList;
            }

            public void setExpressRouteItemList(ExpressRouteItemListBean expressRouteItemList) {
                this.expressRouteItemList = expressRouteItemList;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getWtdOrderId() {
                return wtdOrderId;
            }

            public void setWtdOrderId(String wtdOrderId) {
                this.wtdOrderId = wtdOrderId;
            }

            public static class ExpressRouteItemListBean {
                /**
                 * notes : 已收到订单信息  收到订单信息
                 * optime : 2016-12-07 17:25:39
                 * state : 20100
                 */

                private List<ExpressRouteItemsBean> expressRouteItems;

                public List<ExpressRouteItemsBean> getExpressRouteItems() {
                    return expressRouteItems;
                }

                public void setExpressRouteItems(List<ExpressRouteItemsBean> expressRouteItems) {
                    this.expressRouteItems = expressRouteItems;
                }

                public static class ExpressRouteItemsBean {
                    private String notes;
                    private String optime;
                    private String state;

                    public String getNotes() {
                        return notes;
                    }

                    public void setNotes(String notes) {
                        this.notes = notes;
                    }

                    public String getOptime() {
                        return optime;
                    }

                    public void setOptime(String optime) {
                        this.optime = optime;
                    }

                    public String getState() {
                        return state;
                    }

                    public void setState(String state) {
                        this.state = state;
                    }
                }
            }
        }
    }
}
