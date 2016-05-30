package com.dawnlightning.ucqa.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dawnlightning.ucqa.R;
import com.dawnlightning.ucqa.base.BaseActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/5/21.
 */
public class DisplayActivity extends BaseActivity {
    String path;
    @Bind(R.id.iv_display_back)
    ImageView ivDisplayBack;
    @Bind(R.id.iv_photo)
    PhotoView mImageView;
    @Bind(R.id.rl_display_title)
    RelativeLayout rlDisplayTitle;
    @Bind(R.id.iv_display_cover)
    RelativeLayout ivDisplayCover;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ButterKnife.bind(this);
        Intent data = getIntent();
        path = data.getExtras().get("image").toString();
        // Any implementation of ImageView can be used!
        // Set the Drawable displayed
        Bitmap bitmap = getLoacalBitmap(path);
        mImageView.setImageBitmap(bitmap);
        ivDisplayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTitleVisibility();
                Log.d("test","click");
            }
        });
        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        // (not needed unless you are going to change the drawable later)
    }

    private void changeTitleVisibility() {
        if (rlDisplayTitle.getVisibility() == View.VISIBLE) {
            rlDisplayTitle.setVisibility(View.INVISIBLE);
        } else {
            rlDisplayTitle.setVisibility(View.VISIBLE);
        }
    }

    public Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
