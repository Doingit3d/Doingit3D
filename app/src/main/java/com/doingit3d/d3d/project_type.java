package com.doingit3d.d3d;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by cice on 3/7/17.
 */

public class project_type extends AppCompatActivity {

    ImageView imp, des, scan, video, realidad, merch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_type);


        imp = (ImageView)findViewById(R.id.imag_imp);
        des = (ImageView)findViewById(R.id.imag_dis);
        scan = (ImageView)findViewById(R.id.imag_scann);
        video = (ImageView)findViewById(R.id.imag_video);
        realidad = (ImageView)findViewById(R.id.imag_real_aum);
        merch = (ImageView)findViewById(R.id.imag_merch);




        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imag_imp:
            }
        }
}
