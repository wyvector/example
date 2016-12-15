package com.share.umeng.simplify.bean;

/**
 * Created by wuyong on 2016/11/16.
 */
public interface ShareCallback {
    /**
     * 分享成功
     *
     * @param sharePlatform
     * @param msg
     */
    void shareSuccess(SharePlatform sharePlatform, String msg);

    /**
     * 分享失败
     *
     * @param sharePlatform
     * @param msg
     */
    void shareFail(SharePlatform sharePlatform, String msg);

    /**
     * 分享取消
     *
     * @param sharePlatform
     * @param msg
     */
    void shareCancel(SharePlatform sharePlatform, String msg);
}
