package com.cary.moviesapp.entity;

import java.util.List;

public class OrderListEntity {


    /**
     * code : 0
     * data : [{"id":4,"mname":"逃离比勒陀利亚","showtime":"2020-3-26 19:30","place":"国际影城","price":"110","uid":1}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * mname : 逃离比勒陀利亚
         * showtime : 2020-3-26 19:30
         * place : 国际影城
         * price : 110
         * uid : 1
         */

        private int id;
        private String mname;
        private String showtime;
        private String place;
        private String price;
        private int uid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getShowtime() {
            return showtime;
        }

        public void setShowtime(String showtime) {
            this.showtime = showtime;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
