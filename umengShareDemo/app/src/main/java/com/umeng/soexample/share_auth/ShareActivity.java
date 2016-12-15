package com.umeng.soexample.share_auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;



import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.UmengTool;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;

import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.umeng.socialize.utils.SocializeUtils;

import com.umeng.soexample.R;
import com.umeng.soexample.model.Defaultcontent;

import java.io.File;
import java.util.Map;

/**
 * Created by umeng on 15/9/14.
 */
public class ShareActivity extends Activity{
    private CheckBox textc,linkc,titlec;
    private RadioGroup rg;
    private boolean isText = true;
    private boolean isTitle,isLink;
    private int mediatype = 0;//0 none 1 image 2 music 3 video
    private SHARE_MEDIA share_media = SHARE_MEDIA.ALIPAY;
    UMImage image ;

    UMusic music ;

    UMVideo video;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_share);

         image = new UMImage(this.getApplicationContext(), R.drawable.dadadatu);
         music = new UMusic(Defaultcontent.musicurl);



         video = new UMVideo(Defaultcontent.videourl);
        share_media = (SHARE_MEDIA) getIntent().getSerializableExtra("platform");

        //init music
        music.setTitle("This is music title");
        music.setThumb(new UMImage(this,"http://www.umeng.com/images/pic/social/chart_1.png"));
        music.setDescription("my description");
        //init video
        video.setThumb(new UMImage(this,"http://www.umeng.com/images/pic/social/chart_1.png"));
        video.setTitle("umeng");
        // share URL
        /** need not init ,but must config App.java**/
       textc = (CheckBox)findViewById(R.id.checkBox_text_editor);
        linkc = (CheckBox)findViewById(R.id.checkBox_link_editor);
        titlec = (CheckBox)findViewById(R.id.checkBox_title_editor);
        textc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isText = true;
                }else {
                    isText = false;
                }
            }
        });

        linkc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isLink = true;
                }else {
                    isLink = false;
                }
            }
        });
        titlec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isTitle = true;
                }else {
                    isTitle = false;
                }
            }
        });
        rg = (RadioGroup)findViewById(R.id.buttongroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getCheckedRadioButtonId() == R.id.nonebutton){
                    mediatype = 0;
                }else if (group.getCheckedRadioButtonId() == R.id.imagebutton){
                    mediatype = 1;
                }else if (group.getCheckedRadioButtonId() == R.id.musicbutton){
                    mediatype = 2;
                }else if (group.getCheckedRadioButtonId() == R.id.videobutton){
                    mediatype = 3;
                }
            }
        });

        findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ShareAction shareAction = new ShareAction(ShareActivity.this);
                if (isText){
                    shareAction.withText(Defaultcontent.text);
                }
                if (mediatype == 1){
                    shareAction.withMedia(image);
                }else if (mediatype == 2){
                    shareAction.withMedia(music);
                }else if (mediatype == 3){
                    shareAction.withMedia(video);
                }

                if (isTitle){
                    shareAction.withTitle(Defaultcontent.title);
                }
                if (isLink){
                    shareAction.withTargetUrl(Defaultcontent.url);
                }
                shareAction.setPlatform(share_media).setCallback(umShareListener).share();
            }
        });

    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this,platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            Log.d("plat","platform"+platform);

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareActivity.this,platform + " 分享失败啦"+t.getMessage(), Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result","onActivityResult");
    }


}
