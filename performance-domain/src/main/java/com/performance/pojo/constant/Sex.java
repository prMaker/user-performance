package com.performance.pojo.constant;

public enum Sex{
        MAN(1, "男"),
        WOMAN(2, "女");

        private int code;
        private String desc;

        private Sex(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }