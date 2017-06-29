package com.doingit3d.d3d;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.sdsmdg.tastytoast.TastyToast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cice on 29/6/17.
 */

public class project_public_design extends AppCompatActivity implements ProgressGenerator.OnCompleteListener{

    private TextInputLayout titulo, descripcion,pais;
    private Spinner tipo,formato,material;
    private RadioGroup moneda,privacidad,desplazamiento;
    private String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private TextView fecha;
    private String moneda_text, privacidad_text, desplazamiento_text;
    private CheckBox terminos;
    private ProgressGenerator progressGenerator;
    private ActionProcessButton apb;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_public_design);

        //poned en todas las actividades que querais la toolbar este codigo
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressGenerator = new ProgressGenerator(this);
        apb=(ActionProcessButton) findViewById(R.id.b_publicar_proyecto);
        apb.setMode(ActionProcessButton.Mode.PROGRESS);

        titulo=(TextInputLayout) findViewById(R.id.til_titulo_proyecto);
        descripcion=(TextInputLayout) findViewById(R.id.til_descripcion_proyecto);
        pais=(TextInputLayout) findViewById(R.id.til_pais);

        tipo=(Spinner)findViewById(R.id.spinner_tipologia);
        formato=(Spinner)findViewById(R.id.spinner_formato_archivo);
        material=(Spinner) findViewById(R.id.spinner_material);

        moneda=(RadioGroup) findViewById(R.id.radioGroup_moneda);
        privacidad=(RadioGroup) findViewById(R.id.radioGroup_privacidad);
        desplazamiento=(RadioGroup) findViewById(R.id.radioGroup_desplazamiento);

        fecha=(TextView) findViewById(R.id.tv_fecha_hora);

        terminos=(CheckBox) findViewById(R.id.checkBox_terminos);

        moneda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton_dolar:
                        moneda_text="dolar";
                        break;
                    case R.id.radioButton_euro:
                        moneda_text="euro";
                        break;
                }
            }
        });

    }

    @Override
    public void onComplete() {
        TastyToast.makeText(getApplicationContext(),"Proyecto publicado con éxito",TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    }

