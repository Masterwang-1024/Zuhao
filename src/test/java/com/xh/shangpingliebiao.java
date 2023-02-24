//package com.xh;
//
//public class shangpingliebiao {
//    // ApifoxModel.java
//
//package com.apifox;
//
//    public class ApifoxModel {
//        /**
//         * 游戏数据
//         */
//        private Datum[] data;
//        /**
//         * 上下页链接
//         */
//        private Links links;
//        /**
//         * 分页信息
//         */
//        private Meta meta;
//
//        public Datum[] getData() { return data; }
//        public void setData(Datum[] value) { this.data = value; }
//
//        public Links getLinks() { return links; }
//        public void setLinks(Links value) { this.links = value; }
//
//        public Meta getMeta() { return meta; }
//        public void setMeta(Meta value) { this.meta = value; }
//    }
//
//// Datum.java
//
//package com.apifox;
//
//    public class Datum {
//        /**
//         * 客户端
//         */
//        private Client[] clients;
//        private String createdAt;
//        /**
//         * 手续费是否启用 1 启用 -1 禁用
//         */
//        private long feeStatus;
//        /**
//         * 游戏亮点模板
//         */
//        private Object gameTemplate;
//        /**
//         * 商品类型
//         */
//        private GameType[] gameTypes;
//        private long hot;
//        private long id;
//        /**
//         * 手机系统「1 ios 2 android 3 其他」
//         */
//        private long[] mobileSystem;
//        /**
//         * 游戏平台类型「1 手机游戏 2 电脑游戏」
//         */
//        private long platformType;
//        private Settings settings;
//        private long sort;
//        private long status;
//        /**
//         * 图片地址
//         */
//        private String thumb;
//        /**
//         * 游戏名称
//         */
//        private String title;
//        private String updatedAt;
//
//        public Client[] getClients() { return clients; }
//        public void setClients(Client[] value) { this.clients = value; }
//
//        public String getCreatedAt() { return createdAt; }
//        public void setCreatedAt(String value) { this.createdAt = value; }
//
//        public long getFeeStatus() { return feeStatus; }
//        public void setFeeStatus(long value) { this.feeStatus = value; }
//
//        public Object getGameTemplate() { return gameTemplate; }
//        public void setGameTemplate(Object value) { this.gameTemplate = value; }
//
//        public GameType[] getGameTypes() { return gameTypes; }
//        public void setGameTypes(GameType[] value) { this.gameTypes = value; }
//
//        public long getHot() { return hot; }
//        public void setHot(long value) { this.hot = value; }
//
//        public long getid() { return id; }
//        public void setid(long value) { this.id = value; }
//
//        public long[] getMobileSystem() { return mobileSystem; }
//        public void setMobileSystem(long[] value) { this.mobileSystem = value; }
//
//        public long getPlatformType() { return platformType; }
//        public void setPlatformType(long value) { this.platformType = value; }
//
//        public Settings getSettings() { return settings; }
//        public void setSettings(Settings value) { this.settings = value; }
//
//        public long getSort() { return sort; }
//        public void setSort(long value) { this.sort = value; }
//
//        public long getStatus() { return status; }
//        public void setStatus(long value) { this.status = value; }
//
//        public String getThumb() { return thumb; }
//        public void setThumb(String value) { this.thumb = value; }
//
//        public String getTitle() { return title; }
//        public void setTitle(String value) { this.title = value; }
//
//        public String getUpdatedAt() { return updatedAt; }
//        public void setUpdatedAt(String value) { this.updatedAt = value; }
//    }
//
//// Client.java
//
//package com.apifox;
//
//    public class Client {
//        private String createdAt;
//        private long id;
//        private Pivot pivot;
//        private String title;
//        private long type;
//        private String updatedAt;
//
//        public String getCreatedAt() { return createdAt; }
//        public void setCreatedAt(String value) { this.createdAt = value; }
//
//        public long getid() { return id; }
//        public void setid(long value) { this.id = value; }
//
//        public Pivot getPivot() { return pivot; }
//        public void setPivot(Pivot value) { this.pivot = value; }
//
//        public String getTitle() { return title; }
//        public void setTitle(String value) { this.title = value; }
//
//        public long getType() { return type; }
//        public void setType(long value) { this.type = value; }
//
//        public String getUpdatedAt() { return updatedAt; }
//        public void setUpdatedAt(String value) { this.updatedAt = value; }
//    }
//
//// Pivot.java
//
//package com.apifox;
//
//    public class Pivot {
//        private long clientid;
//        private long gameid;
//        private long id;
//
//        public long getClientid() { return clientid; }
//        public void setClientid(long value) { this.clientid = value; }
//
//        public long getGameid() { return gameid; }
//        public void setGameid(long value) { this.gameid = value; }
//
//        public long getid() { return id; }
//        public void setid(long value) { this.id = value; }
//    }
//
//// GameType.java
//
//package com.apifox;
//
//    public class GameType {
//        private long autoSend;
//        private String createdAt;
//        private long feeMax;
//        private long feeRate;
//        private long id;
//        private long supportGuarantee;
//        private String title;
//        private long typeCategoryid;
//        private String updatedAt;
//
//        public long getAutoSend() { return autoSend; }
//        public void setAutoSend(long value) { this.autoSend = value; }
//
//        public String getCreatedAt() { return createdAt; }
//        public void setCreatedAt(String value) { this.createdAt = value; }
//
//        public long getFeeMax() { return feeMax; }
//        public void setFeeMax(long value) { this.feeMax = value; }
//
//        public long getFeeRate() { return feeRate; }
//        public void setFeeRate(long value) { this.feeRate = value; }
//
//        public long getid() { return id; }
//        public void setid(long value) { this.id = value; }
//
//        public long getSupportGuarantee() { return supportGuarantee; }
//        public void setSupportGuarantee(long value) { this.supportGuarantee = value; }
//
//        public String getTitle() { return title; }
//        public void setTitle(String value) { this.title = value; }
//
//        public long getTypeCategoryid() { return typeCategoryid; }
//        public void setTypeCategoryid(long value) { this.typeCategoryid = value; }
//
//        public String getUpdatedAt() { return updatedAt; }
//        public void setUpdatedAt(String value) { this.updatedAt = value; }
//    }
//
//// Settings.java
//
//package com.apifox;
//
//    public class Settings {
//        private long baopeiStatus;
//
//        public long getBaopeiStatus() { return baopeiStatus; }
//        public void setBaopeiStatus(long value) { this.baopeiStatus = value; }
//    }
//
//// Links.java
//
//package com.apifox;
//
//    /**
//     * 上下页链接
//     */
//    public class Links {
//        private String first;
//        private String last;
//        private String next;
//        private Object prev;
//
//        public String getFirst() { return first; }
//        public void setFirst(String value) { this.first = value; }
//
//        public String getLast() { return last; }
//        public void setLast(String value) { this.last = value; }
//
//        public String getNext() { return next; }
//        public void setNext(String value) { this.next = value; }
//
//        public Object getPrev() { return prev; }
//        public void setPrev(Object value) { this.prev = value; }
//    }
//
//// Meta.java
//
//package com.apifox;
//
//    /**
//     * 分页信息
//     */
//    public class Meta {
//        private long currentPage;
//        private long from;
//        private long lastPage;
//        private String path;
//        private String perPage;
//        private long to;
//        private long total;
//
//        public long getCurrentPage() { return currentPage; }
//        public void setCurrentPage(long value) { this.currentPage = value; }
//
//        public long getFrom() { return from; }
//        public void setFrom(long value) { this.from = value; }
//
//        public long getLastPage() { return lastPage; }
//        public void setLastPage(long value) { this.lastPage = value; }
//
//        public String getPath() { return path; }
//        public void setPath(String value) { this.path = value; }
//
//        public String getPerPage() { return perPage; }
//        public void setPerPage(String value) { this.perPage = value; }
//
//        public long getTo() { return to; }
//        public void setTo(long value) { this.to = value; }
//
//        public long getTotal() { return total; }
//        public void setTotal(long value) { this.total = value; }
//    }
//}
