package com.doingit3d.d3d;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by cice on 29/6/17.
 */

public class project_public_design extends AppCompatActivity implements ProgressGenerator.OnCompleteListener{

    private ImageView img;
    private BBDD_Controller controller = new BBDD_Controller(this);
    private TextInputLayout titulo, descripcion,pais;
    private Spinner tipo,formato,material;
    private RadioGroup moneda,privacidad,desplazamiento;
    private String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private Bitmap bitmap,bm;
    private byte[] img_proyecto;
    private TextView fecha;
    private String moneda_text, privacidad_text, desplazamiento_text;
    private CheckBox terminos;
    private ProgressGenerator progressGenerator;
    private ActionProcessButton apb;
    private TextInputLayout til_titulo, til_descripciones;
    private TextView til_fecha;
    private double latitud, longitud;
    private String lugar;

    //Constantes que nos dicen si la imagene es de la camara o de galeria
    private static final int RESULT_LOAD_IMAGE = 22;
    private static final int REQUEST_IMAGE_CAPTURE = 33;


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

        img = (ImageView)findViewById(R.id.img_proyect);
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



        til_titulo=(TextInputLayout) findViewById(R.id.til_titulo_proyecto);
        til_descripciones=(TextInputLayout) findViewById(R.id.til_descripcion_proyecto);
        til_fecha = (TextView) findViewById(R.id.fecha);



      /*  PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                LatLng coordeadas=place.getLatLng();
                latitud=coordeadas.latitude;
                longitud=coordeadas.longitude;

                lugar=place.getName().toString();


            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                System.out.print("An error occurred: " + status);
            }
        });*/

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

        privacidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton_publico:
                        privacidad_text="publico";
                        break;
                    case R.id.radioButton_privado:
                        privacidad_text="privado";
                        break;
                }
            }
        });

        desplazamiento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton_sidespl:
                        desplazamiento_text="si";
                        break;
                    case R.id.radioButton_nodespl:
                        desplazamiento_text="no";
                        break;
                }
            }
        });




    }

    public void abrirCalendario(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");
    }

    @Override
    public void onComplete() {
        TastyToast.makeText(getApplicationContext(),"Proyecto publicado con éxito",TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }




    public void publicar_proyecto(View v){

        if (titulo.getEditText().getText().toString().trim().isEmpty()){
            Toast.makeText(this,getString(R.string.campo_requerido),Toast.LENGTH_SHORT).show();
            til_titulo.setError(getString(R.string.campo_requerido));
            til_descripciones.setError("");
            til_fecha.setError("");

        }else if (descripcion.getEditText().getText().toString().trim().isEmpty()){
            Toast.makeText(this,getString(R.string.campo_requerido),Toast.LENGTH_SHORT).show();
            til_titulo.setError("");
            til_descripciones.setError(getString(R.string.campo_requerido));
            til_fecha.setError("");

        }else if (fecha.getText().toString().trim().isEmpty()){
            Toast.makeText(this,getString(R.string.campo_requerido),Toast.LENGTH_SHORT).show();
            til_titulo.setError("");
            til_descripciones.setError("");
            til_fecha.setError(getString(R.string.campo_requerido));


        }else{

            if (img_proyecto == null) {
                // civ.setImageResource(R.drawable.nofoto);
                // bitmap = ((BitmapDrawable)civ.getDrawable()).getBitmap();
                bitmap = controller.obtener_imagen();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                img_proyecto = stream.toByteArray();

                controller.publicar_proyecto(tipo.getSelectedItem().toString(), titulo.getEditText().getText().toString(), descripcion.getEditText().getText().toString(), fecha.getText().toString(),
                        pais.getEditText().getText().toString(), moneda_text, date, controller.obtener_id_conectado(), desplazamiento_text, formato.getSelectedItem().toString(), privacidad_text, material.getSelectedItem().toString(), controller.username_conectado());
                if (controller.obtener_proyectos_presentados() == 0) {
                    controller.aumentar_proyectos_presentados();
                } else if (controller.obtener_proyectos_presentados() > 0) {
                    controller.aumentar_proyectos_presentados2();
                }

            }

            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText(getString(R.string.enhorabuena))
                    .setContentText(getString(R.string.cambios_guardados))
                    .setConfirmText(getString(R.string.aceptar))
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }
                            startActivity(new Intent(getApplicationContext(),Profile.class));
                        }
                    })
                    .show();
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();//llama a este metodo
        return true;
    }

    public void onBackPressed(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        startActivity(new Intent(this,MainActivity.class));
    }



}

