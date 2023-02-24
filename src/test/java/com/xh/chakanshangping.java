//package com.xh;
//
//public class chakanshangping {
//    // ApifoxModel.java
//
//package com.apifox;
//
//    public class ApifoxModel {
//        private AccountInfo accountInfo;
//        private long audit;
//        private Object baopeiRate;
//        private long clientid;
//        private String content;
//        private String createdAt;
//        private Object faceCertify;
//        private long favorite;
//        private long gameAreaServiceid;
//        private Object gameAreaServiceTitle;
//        private long gameid;
//        /**
//         * 是否有账号亮点
//         */
//        private boolean hasContent;
//        /**
//         * 是否有图片
//         */
//        private boolean hasImage;
//        private long id;
//        /**
//         * 图片
//         */
//        private String[] images;
//        private String ip;
//        private long isAutoAudit;
//        private long isDeleted;
//        private boolean isNew;
//        private boolean isRecommend;
//        private boolean isTop;
//        private String lastAuditAt;
//        private long like;
//        private Object nickname;
//        private long orderQuantity;
//        private Object ordersCount;
//        /**
//         * 价格
//         */
//        private long price;
//        private long recommendNum;
//        private Object remark;
//        private ServiceSettings serviceSettings;
//        private String shelvesAt;
//        private long shopid;
//        private long shopStatus;
//        private Object source;
//        private long status;
//        /**
//         * 库存
//         */
//        private long stock;
//        /**
//         * 封面图
//         */
//        private String thumb;
//        /**
//         * 商品名称
//         */
//        private String title;
//        private String topAt;
//        private long topNum;
//        private long tradeType;
//        private long typeid;
//        private String updatedAt;
//        private long userid;
//        /**
//         * 浏览量
//         */
//        private long view;
//
//        public AccountInfo getAccountInfo() { return accountInfo; }
//        public void setAccountInfo(AccountInfo value) { this.accountInfo = value; }
//
//        public long getAudit() { return audit; }
//        public void setAudit(long value) { this.audit = value; }
//
//        public Object getBaopeiRate() { return baopeiRate; }
//        public void setBaopeiRate(Object value) { this.baopeiRate = value; }
//
//        public long getClientid() { return clientid; }
//        public void setClientid(long value) { this.clientid = value; }
//
//        public String getContent() { return content; }
//        public void setContent(String value) { this.content = value; }
//
//        public String getCreatedAt() { return createdAt; }
//        public void setCreatedAt(String value) { this.createdAt = value; }
//
//        public Object getFaceCertify() { return faceCertify; }
//        public void setFaceCertify(Object value) { this.faceCertify = value; }
//
//        public long getFavorite() { return favorite; }
//        public void setFavorite(long value) { this.favorite = value; }
//
//        public long getGameAreaServiceid() { return gameAreaServiceid; }
//        public void setGameAreaServiceid(long value) { this.gameAreaServiceid = value; }
//
//        public Object getGameAreaServiceTitle() { return gameAreaServiceTitle; }
//        public void setGameAreaServiceTitle(Object value) { this.gameAreaServiceTitle = value; }
//
//        public long getGameid() { return gameid; }
//        public void setGameid(long value) { this.gameid = value; }
//
//        public boolean getHasContent() { return hasContent; }
//        public void setHasContent(boolean value) { this.hasContent = value; }
//
//        public boolean getHasImage() { return hasImage; }
//        public void setHasImage(boolean value) { this.hasImage = value; }
//
//        public long getid() { return id; }
//        public void setid(long value) { this.id = value; }
//
//        public String[] getImages() { return images; }
//        public void setImages(String[] value) { this.images = value; }
//
//        public String getip() { return ip; }
//        public void setip(String value) { this.ip = value; }
//
//        public long getIsAutoAudit() { return isAutoAudit; }
//        public void setIsAutoAudit(long value) { this.isAutoAudit = value; }
//
//        public long getIsDeleted() { return isDeleted; }
//        public void setIsDeleted(long value) { this.isDeleted = value; }
//
//        public boolean getIsNew() { return isNew; }
//        public void setIsNew(boolean value) { this.isNew = value; }
//
//        public boolean getIsRecommend() { return isRecommend; }
//        public void setIsRecommend(boolean value) { this.isRecommend = value; }
//
//        public boolean getIsTop() { return isTop; }
//        public void setIsTop(boolean value) { this.isTop = value; }
//
//        public String getLastAuditAt() { return lastAuditAt; }
//        public void setLastAuditAt(String value) { this.lastAuditAt = value; }
//
//        public long getLike() { return like; }
//        public void setLike(long value) { this.like = value; }
//
//        public Object getNickname() { return nickname; }
//        public void setNickname(Object value) { this.nickname = value; }
//
//        public long getOrderQuantity() { return orderQuantity; }
//        public void setOrderQuantity(long value) { this.orderQuantity = value; }
//
//        public Object getOrdersCount() { return ordersCount; }
//        public void setOrdersCount(Object value) { this.ordersCount = value; }
//
//        public long getPrice() { return price; }
//        public void setPrice(long value) { this.price = value; }
//
//        public long getRecommendNum() { return recommendNum; }
//        public void setRecommendNum(long value) { this.recommendNum = value; }
//
//        public Object getRemark() { return remark; }
//        public void setRemark(Object value) { this.remark = value; }
//
//        public ServiceSettings getServiceSettings() { return serviceSettings; }
//        public void setServiceSettings(ServiceSettings value) { this.serviceSettings = value; }
//
//        public String getShelvesAt() { return shelvesAt; }
//        public void setShelvesAt(String value) { this.shelvesAt = value; }
//
//        public long getShopid() { return shopid; }
//        public void setShopid(long value) { this.shopid = value; }
//
//        public long getShopStatus() { return shopStatus; }
//        public void setShopStatus(long value) { this.shopStatus = value; }
//
//        public Object getSource() { return source; }
//        public void setSource(Object value) { this.source = value; }
//
//        public long getStatus() { return status; }
//        public void setStatus(long value) { this.status = value; }
//
//        public long getStock() { return stock; }
//        public void setStock(long value) { this.stock = value; }
//
//        public String getThumb() { return thumb; }
//        public void setThumb(String value) { this.thumb = value; }
//
//        public String getTitle() { return title; }
//        public void setTitle(String value) { this.title = value; }
//
//        public String getTopAt() { return topAt; }
//        public void setTopAt(String value) { this.topAt = value; }
//
//        public long getTopNum() { return topNum; }
//        public void setTopNum(long value) { this.topNum = value; }
//
//        public long getTradeType() { return tradeType; }
//        public void setTradeType(long value) { this.tradeType = value; }
//
//        public long getTypeid() { return typeid; }
//        public void setTypeid(long value) { this.typeid = value; }
//
//        public String getUpdatedAt() { return updatedAt; }
//        public void setUpdatedAt(String value) { this.updatedAt = value; }
//
//        public long getUserid() { return userid; }
//        public void setUserid(long value) { this.userid = value; }
//
//        public long getView() { return view; }
//        public void setView(long value) { this.view = value; }
//    }
//
//// AccountInfo.java
//
//package com.apifox;
//
//    public class AccountInfo {
//        private Account[] accounts;
//        private boolean bindEmail;
//        private boolean bindidCard;
//        private boolean bindMobile;
//        private String mobile;
//        private long qq;
//
//        public Account[] getAccounts() { return accounts; }
//        public void setAccounts(Account[] value) { this.accounts = value; }
//
//        public boolean getBindEmail() { return bindEmail; }
//        public void setBindEmail(boolean value) { this.bindEmail = value; }
//
//        public boolean getBindidCard() { return bindidCard; }
//        public void setBindidCard(boolean value) { this.bindidCard = value; }
//
//        public boolean getBindMobile() { return bindMobile; }
//        public void setBindMobile(boolean value) { this.bindMobile = value; }
//
//        public String getMobile() { return mobile; }
//        public void setMobile(String value) { this.mobile = value; }
//
//        public long getQq() { return qq; }
//        public void setQq(long value) { this.qq = value; }
//    }
//
//// Account.java
//
//package com.apifox;
//
//    public class Account {
//        private String account;
//        private String password;
//
//        public String getAccount() { return account; }
//        public void setAccount(String value) { this.account = value; }
//
//        public String getPassword() { return password; }
//        public void setPassword(String value) { this.password = value; }
//    }
//
//// ServiceSettings.java
//
//package com.apifox;
//
//    public class ServiceSettings {
//        private String isBaopei;
//
//        public String getIsBaopei() { return isBaopei; }
//        public void setIsBaopei(String value) { this.isBaopei = value; }
//    }
//}
