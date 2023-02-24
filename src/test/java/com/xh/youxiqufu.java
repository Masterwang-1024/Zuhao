package com.xh;

public class youxiqufu {

    public class ApifoxModel {
        private long clientGameid;
        /**
         * 所属客户端ID
         */
        private long clientid;
        private String createdAt;
        /**
         * 所属游戏ID
         */
        private long gameid;
        private long id;
        private Object sort;
        /**
         * 区域名称
         */
        private String title;
        private String updatedAt;

        public long getClientGameid() { return clientGameid; }
        public void setClientGameid(long value) { this.clientGameid = value; }

        public long getClientid() { return clientid; }
        public void setClientid(long value) { this.clientid = value; }

        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String value) { this.createdAt = value; }

        public long getGameid() { return gameid; }
        public void setGameid(long value) { this.gameid = value; }

        public long getid() { return id; }
        public void setid(long value) { this.id = value; }

        public Object getSort() { return sort; }
        public void setSort(Object value) { this.sort = value; }

        public String getTitle() { return title; }
        public void setTitle(String value) { this.title = value; }

        public String getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(String value) { this.updatedAt = value; }
    }
}
