package com.umeng.soexample.share_auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.share.umeng.simplify.ShareUtil;
import com.share.umeng.simplify.bean.SharePlatform;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.soexample.R;
import com.umeng.soexample.model.Defaultcontent;

import java.util.ArrayList;

/**
 * Created by wangfei on 16/7/12.
 */
public class ShareMenuActivity extends Activity {
    private ListView listView;
    private ShareAdapter shareAdapter;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    
    private ShareUtil mShareUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_sharemenu);
        listView = (ListView) findViewById(R.id.list);
        initPlatforms();
        mShareUtil = new ShareUtil(ShareMenuActivity.this,null);
        shareAdapter = new ShareAdapter(this, platforms);
        listView.setAdapter(shareAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShareMenuActivity.this, ShareActivity.class);
                intent.putExtra("platform", platforms.get(position).mPlatform);
                ShareMenuActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.share_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new ShareAction(ShareMenuActivity.this).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.ALIPAY)
//                        .withTitle(Defaultcontent.title)
//                        .withText(Defaultcontent.text + "——来自友盟分享面板")
//                        .withMedia(new UMImage(ShareMenuActivity.this, Defaultcontent.imageurl))
//                        .withTargetUrl("https://wsq.umeng.com/")
//                        .setCallback(umShareListener)
//                        .open();
                mShareUtil.shareAllChannel("注册赢流量","手机上网，想看啥就看啥，再也不担心超流量了", "http://dev.umeng.com/images/tab2_1.png","http://192.168.0.96:8995/emsc-liwt-portal/mgr/registerDraw/default.html");
            }
        });
        findViewById(R.id.share_menu_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new ShareAction(ShareMenuActivity.this).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.ALIPAY)
//                        .addButton("umeng_sharebutton_custom","umeng_sharebutton_custom","info_icon_1","info_icon_1")
//                        .setShareboardclickCallback(new ShareBoardlistener() {
//                            @Override
//                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                                if (snsPlatform.mShowWord.equals("umeng_sharebutton_custom")){
//                                    Toast.makeText(ShareMenuActivity.this,"自定义按钮",Toast.LENGTH_LONG).show();
//                                }else {
//                                    new ShareAction(ShareMenuActivity.this).withText(Defaultcontent.text+"来自友盟自定义分享面板")
//                                            .setPlatform(share_media)
//                                            .setCallback(umShareListener)
//                                            .share();
//
//                                }
//                            }
//                        }).open();
                Log.d("-->", "share signal");
//                ShareAction shareAction = new ShareAction(ShareMenuActivity.this);
//                shareAction.setPlatform(SHARE_MEDIA.ALIPAY);
//                shareAction.withTitle(Defaultcontent.title);
//                shareAction.withText(Defaultcontent.text + "——来自友盟分享面板");
////                      shareAction  .withMedia(new UMImage(ShareMenuActivity.this, Defaultcontent.imageurl));
//                shareAction.withTargetUrl("https://wsq.umeng.com/");
//                shareAction.setCallback(umShareListener);
//                shareAction.open();
                mShareUtil.shareSingalPlatfrom(SharePlatform.WEIXIN,"title","text", Defaultcontent.imageurl,"https://www.baidu.com/");
            }
        });

    }

    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : SHARE_MEDIA.values()) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(ShareMenuActivity.this, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ShareMenuActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareMenuActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareMenuActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareUtil.onResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result", "onActivityResult");
    }
}
