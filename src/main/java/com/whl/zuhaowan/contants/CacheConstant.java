package com.whl.zuhaowan.contants;

import java.util.concurrent.TimeUnit;

/**
 * @ModifyTime 2021/08/25
 * 缓存常量
 **/
public interface CacheConstant {

    /**
     * 缓存超时不超过3分钟
     */
    public static final long THREE_MINUTE_TIME_OUT = 180L;

    /**
    * 功能描述： 用户登陆token的有效时间 单位S 2小时
    */
    public static final long LOGIN_TOKEN_EXPIRE_TIME = 120 * 60L;

    public static String X_ACCESS_TOKEN = "x-access-token";

    enum UnitEnum {
        //长久
        FOREVER(-1L, null),
        //1个月
        ONE_MONTH(30L, TimeUnit.DAYS),
        //WEEK
        ONE_WEEK(7L, TimeUnit.DAYS),
        //1天
        ONE_DAY(1L, TimeUnit.DAYS),
        //2个小时
        TWO_HOUR(2L, TimeUnit.HOURS),
        //1个小时
        ONE_HOUR(1L, TimeUnit.HOURS),
        //30分钟
        THIRTY_MIN(30L, TimeUnit.MINUTES),
        //15分钟
        FIFTEEN_MIN(15L, TimeUnit.MINUTES),
        //5分钟
        FIVE_MIN(5L, TimeUnit.MINUTES),
        //1分钟
        ONE_MIN(1L, TimeUnit.MINUTES),
        //10秒
        TEN_SEC(10L, TimeUnit.SECONDS),
        //5秒
        FIVE_SEC(5L, TimeUnit.SECONDS),
        //1秒
        ONE_SEC(1L, TimeUnit.SECONDS),

        FIFTY_MS(1L, TimeUnit.MILLISECONDS);

        /**
         * 存存储时间
         */
        private Long stortageTime;
        //
        /**
        * 存储时间单位
        */
        private TimeUnit timeUnit;

        private UnitEnum(Long stortageTime, TimeUnit timeUnit) {
            this.stortageTime = stortageTime;
            this.timeUnit = timeUnit;
        }

        public Long getStortageTime() {
            return stortageTime;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}

