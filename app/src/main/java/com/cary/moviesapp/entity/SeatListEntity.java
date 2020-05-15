package com.cary.moviesapp.entity;

import java.util.List;

public class SeatListEntity {

    /**
     * code : 0
     * data : [{"id":1,"seatnum":1,"status":0,"mid":1,"cid":1},{"id":2,"seatnum":2,"status":0,"mid":1,"cid":1},{"id":3,"seatnum":3,"status":0,"mid":1,"cid":1},{"id":4,"seatnum":4,"status":0,"mid":1,"cid":1},{"id":5,"seatnum":5,"status":0,"mid":1,"cid":1},{"id":6,"seatnum":6,"status":0,"mid":1,"cid":1}]
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


            private int id;
            private int seatnum;
            private int status;
            private int mid;
            private int cid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSeatnum() {
            return seatnum;
        }

        public void setSeatnum(int seatnum) {
            this.seatnum = seatnum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }
        /**
         * id : 1
         * seatnum : 1
         * status : 0
         * mid : 1
         * cid : 1
         */
    }
}
