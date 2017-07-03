package com.doingit3d.d3d;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by cice on 3/7/17.
 */

public class project_type extends AppCompatActivity {

    ImageView imp, des, scan, video, realidad, merch;
    private static final int IMP=1, DIS=2, SCAN=3, VID=4, REAL=5, MERCH=6;


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
            Intent i;
            switch (v.getId()) {
                case R.id.imag_imp:
                    i = new Intent(this, project_public_design.class);
                    i.putExtra("tipo", IMP);
                    startActivity(i);
                    break;
                case R.id.imag_dis:
                    i = new Intent(this, project_public_design.class);
                    i.putExtra("tipo", DIS);
                    startActivity(i);
                    break;
                case R.id.imag_scann:
                    i = new Intent(this, project_public_design.class);
                    i.putExtra("tipo", SCAN);
                    startActivity(i);
                    break;
                case R.id.imag_video:
                    i = new Intent(this, project_public_design.class);
                    i.putExtra("tipo", VID);
                    startActivity(i);
                    break;
                case R.id.imag_real_aum:
                    i = new Intent(this, project_public_design.class);
                    i.putExtra("tipo", REAL);
                    startActivity(i);
                    break;
                case R.id.imag_merch:
                    i = new Intent(this, project_public_design.class);
                    i.putExtra("tipo", MERCH);
                    startActivity(i);
                    break;

          }
        }
}
