//package com.xh;
//
//public class xiugaishangping {
//    // ApifoxModel.java
//
//package com.apifox;
//
//    public class ApifoxModel {
//        /**
//         * 账号节点信息
//         */
//        private AccountInfo accountInfo;
//        /**
//         * 账号亮点
//         */
//        private String content;
//        /**
//         * 游戏区服ID，和创建游戏区服二选一，优选使用创建游戏区服
//         */
//        private long gameAreaServiceid;
//        /**
//         * 区服名称，创建游戏区服，只有当选择的商品类型的auto_send = 1 时才可以创建区服
//         */
//        private String gameAreaServiceName;
//        /**
//         * 选中的游戏信息
//         */
//        private GameChoose gameChoose;
//        /**
//         * 商品图片，可传多个
//         */
//        private String[] images;
//        /**
//         * 是否包赔，1-包赔 2-不包赔，包赔需在网站进行人脸身份认证
//         */
//        private long isBaopei;
//        /**
//         * 合作商key，联系客服索取
//         */
//        private String key;
//        /**
//         * 商品价格
//         */
//        private long price;
//        /**
//         * 商品名称
//         */
//        private String title;
//
//        public AccountInfo getAccountInfo() { return accountInfo; }
//        public void setAccountInfo(AccountInfo value) { this.accountInfo = value; }
//
//        public String getContent() { return content; }
//        public void setContent(String value) { this.content = value; }
//
//        public long getGameAreaServiceid() { return gameAreaServiceid; }
//        public void setGameAreaServiceid(long value) { this.gameAreaServiceid = value; }
//
//        public String getGameAreaServiceName() { return gameAreaServiceName; }
//        public void setGameAreaServiceName(String value) { this.gameAreaServiceName = value; }
//
//        public GameChoose getGameChoose() { return gameChoose; }
//        public void setGameChoose(GameChoose value) { this.gameChoose = value; }
//
//        public String[] getImages() { return images; }
//        public void setImages(String[] value) { this.images = value; }
//
//        public long getIsBaopei() { return isBaopei; }
//        public void setIsBaopei(long value) { this.isBaopei = value; }
//
//        public String getKey() { return key; }
//        public void setKey(String value) { this.key = value; }
//
//        public long getPrice() { return price; }
//        public void setPrice(long value) { this.price = value; }
//
//        public String getTitle() { return title; }
//        public void setTitle(String value) { this.title = value; }
//    }
//
//// AccountInfo.java
//
//package com.apifox;
//
//    /**
//     * 账号节点信息
//     */
//    public class AccountInfo {
//        /**
//         * 账号信息，交易类型为寄售交易时必填，且只能录入一组账号密码
//         */
//        private Account[] accounts;
//        /**
//         * 是否绑定邮箱，true-已绑定 false-未绑定
//         */
//        private boolean bindEmail;
//        /**
//         * 是否绑定身份证，true-已绑定 false-未绑定
//         */
//        private boolean bindidCard;
//        /**
//         * 是否绑定手机，true-已绑定 false-未绑定
//         */
//        private boolean bindMobile;
//        /**
//         * 交易通知手机
//         */
//        private String mobile;
//        /**
//         * 交易通知QQ
//         */
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
//        /**
//         * 账号
//         */
//        private String account;
//        /**
//         * 密码
//         */
//        private String password;
//
//        public String getAccount() { return account; }
//        public void setAccount(String value) { this.account = value; }
//
//        public String getPassword() { return password; }
//        public void setPassword(String value) { this.password = value; }
//    }
//
//// GameChoose.java
//
//package com.apifox;
//
//    /**
//     * 选中的游戏信息
//     */
//    public class GameChoose {
//        /**
//         * 客户端ID
//         */
//        private long clientid;
//        /**
//         * 游戏ID
//         */
//        private long gameid;
//        /**
//         * 交易类型，1-寄售交易 2-担保交易 3-包赔交易
//         */
//        private long tradeType;
//        /**
//         * 商品类别ID
//         */
//        private long typeid;
//
//        public long getClientid() { return clientid; }
//        public void setClientid(long value) { this.clientid = value; }
//
//        public long getGameid() { return gameid; }
//        public void setGameid(long value) { this.gameid = value; }
//
//        public long getTradeType() { return tradeType; }
//        public void setTradeType(long value) { this.tradeType = value; }
//
//        public long getTypeid() { return typeid; }
//        public void setTypeid(long value) { this.typeid = value; }
//    }
//}
