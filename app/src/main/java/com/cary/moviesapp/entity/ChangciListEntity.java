package com.cary.moviesapp.entity;

import java.io.Serializable;
import java.util.List;

public class ChangciListEntity {

    /**
     * code : 0
     * data : [{"id":1,"showtime":"2020-3-26 18:30","mid":1,"place":"国际影城"},{"id":2,"showtime":"2020-3-26 19:30","mid":1,"place":"国际影城"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * showtime : 2020-3-26 18:30
         * mid : 1
         * place : 国际影城
         */

        private int id;
        private String showtime;
        private int mid;
        private String place;
        private String price;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShowtime() {
            return showtime;
        }

        public void setShowtime(String showtime) {
            this.showtime = showtime;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }
    }
}
