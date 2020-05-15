package com.cary.moviesapp.entity;

import java.io.Serializable;
import java.util.List;

public class MoviesEntity {

    /**
     * code : 0
     * data : [{"id":1,"name":"逃离比勒陀利亚","descrition":"电影根据真实事件改编。丹尼尔将扮演蒂姆·詹金，是两名南非白人中的一员。他们1978年因为参加非洲人民大会秘密反种族隔离行动而被视为恐怖分子并入狱，并策划越狱活动。","imgsrc":"/img/m1.jpg","actor":" 丹尼尔·雷德克里夫","performtime":" 2020-03-06"}]
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


        private int id;
        private String name;
        private String descrition;
        private String imgsrc;
        private String actor;
        private String performtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescrition() {
            return descrition;
        }

        public void setDescrition(String descrition) {
            this.descrition = descrition;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getActor() {
            return actor;
        }

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getPerformtime() {
            return performtime;
        }

        public void setPerformtime(String performtime) {
            this.performtime = performtime;
        }
    }

    /**
     * id : 1
     * name : 逃离比勒陀利亚
     * descrition : 电影根据真实事件改编。丹尼尔将扮演蒂姆·詹金，是两名南非白人中的一员。他们1978年因为参加非洲人民大会秘密反种族隔离行动而被视为恐怖分子并入狱，并策划越狱活动。
     * imgsrc : /img/m1.jpg
     * actor :  丹尼尔·雷德克里夫
     * performtime :  2020-03-06
     */
}
