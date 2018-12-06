package com.component.preject.youlong.config;

/**
 * @Author: xiezhenggen
 * @Time: 2018 2018/12/6 11:43
 * @description: （运行环境配置信息）
 */
public class AppConfigInfo {
    /**
     * url 环境: 1 正式环境， 2 预发布环境， 3 测试环境
     */
    public static final int URL_TYPE = 1;
    public static final String BASE_URL = "https://www.baidu.com/";

    static {
        switch (URL_TYPE) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            default:

                break;
        }
    }
}
