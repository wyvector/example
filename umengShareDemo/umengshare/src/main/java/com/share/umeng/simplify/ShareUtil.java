package com.share.umeng.simplify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.share.umeng.simplify.bean.ShareCallback;
import com.share.umeng.simplify.bean.SharePlatform;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.List;


/**
 * Created by wuyong on 2016/11/16.
 */
public class ShareUtil {
    private Activity mActivity;
    private ShareCallback mShareCallback;
    private static final String TAG = ShareUtil.class.getSimpleName();

    public ShareUtil(Activity activity, ShareCallback shareCallback) {
        this.mActivity = activity;
        this.mShareCallback = shareCallback;
    }

    public void shareAllChannel(String title, String text, String imageUrl, String targetUrl) {

        share(title, text, imageUrl, targetUrl);

    }

    public void shareAllChannel(String title, String text, Bitmap bitmap, String targetUrl) {

        share(title, text, bitmap, targetUrl);

    }

    public void shareSingalPlatfrom(SharePlatform sharePlatform, String title, String text, String imageUrl, String targetUrl) {
        SHARE_MEDIA shareMedia = SharePlatform.getShareMedia(sharePlatform);
        share(shareMedia, title, text, imageUrl, targetUrl);
    }

    public void shareSingalPlatfrom(SharePlatform sharePlatform, String title, String text, Bitmap bitmap, String targetUrl) {
        SHARE_MEDIA shareMedia = SharePlatform.getShareMedia(sharePlatform);
        share(shareMedia, title, text, bitmap, targetUrl);
    }

    private void share(final String title, final String text, final String imageUrl, final String targetUrl) {
        new ShareAction(mActivity).setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.ALIPAY, SHARE_MEDIA.SINA /*,SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,   SHARE_MEDIA.WEIXIN_FAVORITE*/)
                .withTitle(title)
                .withText(text)
                .withMedia(new UMImage(mActivity, imageUrl))
                .withTargetUrl(targetUrl)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        share(share_media, title, text, imageUrl, targetUrl);
                    }
                })
                .setCallback(umShareListener)
                .open();
    }

    private void share(final String title, final String text, final Bitmap bitmap, final String targetUrl) {
        new ShareAction(mActivity).setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.ALIPAY)
                .withTitle(title)
                .withText(text)
                .withMedia(new UMImage(mActivity, bitmap))
                .withTargetUrl(targetUrl)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        share(share_media, title, text, bitmap, targetUrl);
                    }
                })
                .setCallback(umShareListener)
                .open();
    }

    private void share(SHARE_MEDIA shareMedia, String title, String text, String imageUrl, String targetUrl) {
        if (SHARE_MEDIA.WEIXIN_CIRCLE == shareMedia || SHARE_MEDIA.TENCENT == shareMedia) {
            //由于微信朋友圈、腾讯微博没分享内容，因此将分享标题的文本设置成需要分享的内容文本
            title = title + "\n" + text;
        }
        new ShareAction(mActivity).setPlatform(shareMedia)
                .withTitle(title)
                .withText(text)
                .withMedia(new UMImage(mActivity, imageUrl))
                .withTargetUrl(targetUrl)
                .setCallback(umShareListener)
                .share();
    }

    private void share(SHARE_MEDIA shareMedia, String title, String text, Bitmap bitmap, String targetUrl) {
        if (SHARE_MEDIA.WEIXIN_CIRCLE == shareMedia || SHARE_MEDIA.TENCENT == shareMedia) {
            //由于微信朋友圈、腾讯微博没分享内容，因此将分享标题的文本设置成需要分享的内容文本
            title = title + "\n" + text;
        }
        new ShareAction(mActivity).setPlatform(shareMedia)
                .withTitle(title)
                .withText(text)
                .withMedia(new UMImage(mActivity, bitmap))
                .withTargetUrl(targetUrl)
                .setCallback(umShareListener)
                .share();
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            SharePlatform sharePlatform = SharePlatform.getPlatform(platform);
            String msg = "";
            Log.d(TAG, "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                msg = mActivity.getString(R.string.umeng_share_provite_success);
            } else {
                msg = mActivity.getString(R.string.umeng_share_success);
            }
            mShareCallback.shareSuccess(sharePlatform, msg);
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            SharePlatform sharePlatform = SharePlatform.getPlatform(platform);
            String msg = "";
            Log.d(TAG, "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                msg = mActivity.getString(R.string.umeng_share_provite_fail);
            } else {
                msg = mActivity.getString(R.string.umeng_share_fail);
            }
            mShareCallback.shareFail(sharePlatform, msg);
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            SharePlatform sharePlatform = SharePlatform.getPlatform(platform);
            String msg = "";
            Log.d(TAG, "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                msg = mActivity.getString(R.string.umeng_share_provite_cancel);
            } else {
                msg = mActivity.getString(R.string.umeng_share_cancel);
            }
            mShareCallback.shareCancel(sharePlatform, msg);
        }
    };

    public void onResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(mActivity).onActivityResult(requestCode, resultCode, data);
    }

    public void shareSingalPlatfrom(List<SharePlatform> sharePlatforms, final String title, final String text, final String iconUrl, final String targetUrl) {
        List<SHARE_MEDIA> list = SharePlatform.getShareMedias(sharePlatforms);
        if (0 == list.size()) {
            String msg = mActivity.getString(R.string.umeng_share_fail);
            mShareCallback.shareFail(SharePlatform.WEIXIN, msg);
        }
        switch (list.size()) {
            case 1:
                shareSingalPlatfrom(sharePlatforms.get(0), title, text, iconUrl, targetUrl);
                break;
            case 2:
                new ShareAction(mActivity).setDisplayList(list.get(0), list.get(1))
                        .withTitle(title)
                        .withText(text)
                        .withMedia(new UMImage(mActivity, iconUrl))
                        .withTargetUrl(targetUrl)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                share(share_media, title, text, iconUrl, targetUrl);
                            }
                        })
                        .setCallback(umShareListener)
                        .open();
                break;
            case 3:
                new ShareAction(mActivity).setDisplayList(list.get(0), list.get(1), list.get(2))
                        .withTitle(title)
                        .withText(text)
                        .withMedia(new UMImage(mActivity, iconUrl))
                        .withTargetUrl(targetUrl)
                        .setCallback(umShareListener)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                share(share_media, title, text, iconUrl, targetUrl);
                            }
                        })
                        .open();
                break;
            case 4:
                new ShareAction(mActivity).setDisplayList(list.get(0), list.get(1), list.get(2), list.get(3))
                        .withTitle(title)
                        .withText(text)
                        .withMedia(new UMImage(mActivity, iconUrl))
                        .withTargetUrl(targetUrl)
                        .setCallback(umShareListener)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                share(share_media, title, text, iconUrl, targetUrl);
                            }
                        })
                        .open();
                break;
            case 5:
                new ShareAction(mActivity).setDisplayList(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4))
                        .withTitle(title)
                        .withText(text)
                        .withMedia(new UMImage(mActivity, iconUrl))
                        .withTargetUrl(targetUrl)
                        .setCallback(umShareListener)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                share(share_media, title, text, iconUrl, targetUrl);
                            }
                        })
                        .open();
                break;

            case 6:
                new ShareAction(mActivity).setDisplayList(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5))
                        .withTitle(title)
                        .withText(text)
                        .withMedia(new UMImage(mActivity, iconUrl))
                        .withTargetUrl(targetUrl)
                        .setCallback(umShareListener)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                share(share_media, title, text, iconUrl, targetUrl);
                            }
                        })
                        .open();
                break;
            case 7:
                new ShareAction(mActivity).setDisplayList(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6))
                        .withTitle(title)
                        .withText(text)
                        .withMedia(new UMImage(mActivity, iconUrl))
                        .withTargetUrl(targetUrl)
                        .setCallback(umShareListener)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                share(share_media, title, text, iconUrl, targetUrl);
                            }
                        })
                        .open();
                break;

        }

    }
}
