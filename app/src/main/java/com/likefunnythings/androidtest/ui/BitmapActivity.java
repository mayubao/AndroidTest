package com.likefunnythings.androidtest.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.likefunnythings.androidtest.R;

/**
 * BitmapFactory.Options 参数的应用，主要应用于压缩图片
 *
 */
public class BitmapActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = BitmapActivity.class.getSimpleName();
    Button btn;
    TextView et;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

           initView();
    }

    private void initView() {
        btn = (Button) this.findViewById(R.id.btn);
        et = (EditText) this.findViewById(R.id.et);
        iv = (ImageView) this.findViewById(R.id.iv);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:



                String size = et.getText().toString();
                if(null == size || "".equals(size)){
                    Toast.makeText(this, "请输入放缩的尺寸", Toast.LENGTH_SHORT).show();
                    return;
                }

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, options);

                int width = options.outWidth;
                int height = options.outHeight;
                Log.i(TAG, "width======>>>" + width);
                Log.i(TAG, "height======>>>" + height);

                options.inJustDecodeBounds = false;
                options.inSampleSize = Integer.valueOf(size);
                Bitmap newBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher, options);
                iv.setImageBitmap(newBitmap);

            break;
            default:
        }
    }
}
