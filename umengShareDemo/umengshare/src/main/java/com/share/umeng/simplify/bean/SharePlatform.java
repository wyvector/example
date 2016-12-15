package com.share.umeng.simplify.bean;

import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyong on 2016/11/16.
 */


public enum SharePlatform {
    WEIXIN_CIRCLE(0, "微信朋友圈"),
    WEIXIN(1, "微信"),
    TENCENT(2, "腾迅微博"),
    QQ(3, "QQ"),
    QZONE(4, "腾迅空间"),
    SINA(5, "微信朋友圈"),
    ALIPAY(6, "支付宝"),
    OTHER(7, "其它"),
    WEIXIN_FAVORITE(8, "微信收藏");

    private int code;
    private String desc;

    SharePlatform(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    /**
     * 根据code获取SharePlatform
     *
     * @param codeStr
     * @return
     */
    public static SharePlatform getPlatform(String codeStr) {
        int code = 0;
        try {
            code = Integer.parseInt(codeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharePlatform sharePlatform = WEIXIN;

        if (code == WEIXIN.code) {
            sharePlatform = WEIXIN;
        } else if (code == WEIXIN_CIRCLE.code) {
            sharePlatform = WEIXIN_CIRCLE;
        } else if (code == WEIXIN_FAVORITE.code) {
            sharePlatform = WEIXIN_FAVORITE;
        } else if (code == ALIPAY.code) {
            sharePlatform = ALIPAY;
        } else if (code == SINA.code) {
            sharePlatform = SINA;
        } else if (code == QZONE.code) {
            sharePlatform = QZONE;
        } else if (code == QQ.code) {
            sharePlatform = QQ;
        } else if (code == TENCENT.code) {
            sharePlatform = TENCENT;
        } else if (code == OTHER.code) {
            sharePlatform = OTHER;
        }

        return sharePlatform;

    }

    /**
     * 根据SHARE_MEDIA获取SharePlatform
     *
     * @param shareMedia
     * @return
     */
    public static SharePlatform getPlatform(SHARE_MEDIA shareMedia) {

        SharePlatform sharePlatform = WEIXIN;
        switch (shareMedia) {
            case WEIXIN:
                sharePlatform = WEIXIN;
                break;
            case WEIXIN_CIRCLE:
                sharePlatform = WEIXIN_CIRCLE;
                break;
            case WEIXIN_FAVORITE:
                sharePlatform = WEIXIN_FAVORITE;
                break;
            case ALIPAY:
                sharePlatform = ALIPAY;
                break;
            case SINA:
                sharePlatform = SINA;
                break;
            case QZONE:
                sharePlatform = QZONE;
                break;
            case QQ:
                sharePlatform = QQ;
                break;
            case TENCENT:
                sharePlatform = TENCENT;
                break;
        }
        return sharePlatform;

    }

    /**
     * 根据SharePlatform获取SHARE_MEDIA
     *
     * @param sharePlatform
     * @return
     */
    public static SHARE_MEDIA getShareMedia(SharePlatform sharePlatform) {

        SHARE_MEDIA shareMedia = SHARE_MEDIA.WEIXIN;
        switch (sharePlatform) {
            case WEIXIN:
                shareMedia = SHARE_MEDIA.WEIXIN;
                break;
            case WEIXIN_CIRCLE:
                shareMedia = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case WEIXIN_FAVORITE:
                shareMedia = SHARE_MEDIA.WEIXIN_FAVORITE;
                break;
            case ALIPAY:
                shareMedia = SHARE_MEDIA.ALIPAY;
                break;
            case SINA:
                shareMedia = SHARE_MEDIA.SINA;
                break;
            case QZONE:
                shareMedia = SHARE_MEDIA.QZONE;
                break;
            case QQ:
                shareMedia = SHARE_MEDIA.QQ;
                break;
            case TENCENT:
                shareMedia = SHARE_MEDIA.TENCENT;
                break;
        }
        return shareMedia;
    }

    /**
     * 根据SharePlatform获取SHARE_MEDIA
     *
     * @param sharePlatforms
     * @return
     */
    public static List<SHARE_MEDIA> getShareMedias(List<SharePlatform> sharePlatforms) {
        try {

        } catch (Exception e) {

        }
        List<SHARE_MEDIA> shareMedias = new ArrayList<>();
        for (SharePlatform sharePlatform : sharePlatforms) {
            SHARE_MEDIA shareMedia = SHARE_MEDIA.WEIXIN;
            switch (sharePlatform) {
                case WEIXIN:
                    shareMedia = SHARE_MEDIA.WEIXIN;
                    break;
                case WEIXIN_CIRCLE:
                    shareMedia = SHARE_MEDIA.WEIXIN_CIRCLE;
                    break;
                case WEIXIN_FAVORITE:
                    shareMedia = SHARE_MEDIA.WEIXIN_FAVORITE;
                    break;
                case ALIPAY:
                    shareMedia = SHARE_MEDIA.ALIPAY;
                    break;
                case SINA:
                    shareMedia = SHARE_MEDIA.SINA;
                    break;
                case QZONE:
                    shareMedia = SHARE_MEDIA.QZONE;
                    break;
                case QQ:
                    shareMedia = SHARE_MEDIA.QQ;
                    break;
                case TENCENT:
                    shareMedia = SHARE_MEDIA.TENCENT;
            }
            shareMedias.add(shareMedia);
        }

        return shareMedias;
    }
}
