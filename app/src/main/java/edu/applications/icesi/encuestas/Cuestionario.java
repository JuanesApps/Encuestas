package edu.applications.icesi.encuestas;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.applications.icesi.encuestas.model.Pregunta;
import edu.applications.icesi.encuestas.model.Registro;

public class Cuestionario extends AppCompatActivity {

    private Button btn_enviar;
    private TextView tv_pregunta1;
    private TextView tv_enunciado1;
    private RadioGroup radio_group1;
    private TextView tv_pregunta2;
    private TextView tv_enunciado2;
    private RadioGroup radio_group2;
    private TextView tv_pregunta3;
    private TextView tv_enunciado3;
    private RadioGroup radio_group3;
    private TextView tv_pregunta4;
    private TextView tv_enunciado4;
    private RadioGroup radio_group4;
    private TextView tv_pregunta5;
    private TextView tv_enunciado5;
    private RadioGroup radio_group5;

    private FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuestionario);

        db = FirebaseDatabase.getInstance();

        tv_pregunta1 = findViewById(R.id.tv_pregunta1);
        tv_enunciado1 = findViewById(R.id.tv_enunciado1);
        radio_group1 = findViewById(R.id.radio_group1);

        tv_pregunta2 = findViewById(R.id.tv_pregunta2);
        tv_enunciado2 = findViewById(R.id.tv_enunciado2);
        radio_group2 = findViewById(R.id.radio_group2);

        tv_pregunta3 = findViewById(R.id.tv_pregunta3);
        tv_enunciado3 = findViewById(R.id.tv_enunciado3);
        radio_group3 = findViewById(R.id.radio_group3);

        tv_pregunta4 = findViewById(R.id.tv_pregunta4);
        tv_enunciado4 = findViewById(R.id.tv_enunciado4);
        radio_group4 = findViewById(R.id.radio_group4);

        tv_pregunta5 = findViewById(R.id.tv_pregunta5);
        tv_enunciado5 = findViewById(R.id.tv_enunciado5);
        radio_group5 = findViewById(R.id.radio_group5);

        btn_enviar = findViewById(R.id.btn_enviar);

        btn_enviar.setOnClickListener(e -> {
            enviarRegistro();
        });

        cargarPreguntas();

    }

    private void cargarPreguntas(){
        try {
            Log.e("----------->", "Holi");
            AssetManager assetManager = getAssets();
            BufferedReader in = new BufferedReader(new InputStreamReader(assetManager.open("preguntas.txt")));
            String line = in.readLine();
            ArrayList<Pregunta> preguntas = new ArrayList<>();
            while (line!=null){
                String nombre = line;
                String enunciado = in.readLine();
                Pregunta pregunta = new Pregunta();
                pregunta.setNombre(nombre);
                pregunta.setEnunciado(enunciado);
                for (int i = 0; i < 4; i++ ){
                    pregunta.getOpciones()[i] = in.readLine();
                }

                preguntas.add(pregunta);
                in.readLine();
                line = in.readLine();
            }

            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{

                            new int[]{-android.R.attr.state_enabled}, //disabled
                            new int[]{android.R.attr.state_enabled} //enabled
                    },
                    new int[] {

                            Color.BLACK //disabled
                            ,R.color.colorPrimaryDark //enabled

                    }
            );

            tv_pregunta1.setText(preguntas.get(0).getNombre());
            tv_enunciado1.setText(preguntas.get(0).getEnunciado());
            for (int i = 0; i < 4; i++ ){
                RadioButton opt = new RadioButton(this);
                opt.setText(preguntas.get(0).getOpciones()[i]);
                opt.setTextColor(Color.BLACK);
                opt.setButtonTintList(colorStateList);
                radio_group1.addView(opt);
            }
            tv_pregunta2.setText(preguntas.get(1).getNombre());
            tv_enunciado2.setText(preguntas.get(1).getEnunciado());
            for (int i = 0; i < 4; i++ ){
                RadioButton opt = new RadioButton(this);
                opt.setText(preguntas.get(1).getOpciones()[i]);
                opt.setTextColor(Color.BLACK);
                opt.setButtonTintList(colorStateList);
                radio_group2.addView(opt);
            }
            tv_pregunta3.setText(preguntas.get(2).getNombre());
            tv_enunciado3.setText(preguntas.get(2).getEnunciado());
            for (int i = 0; i < 4; i++ ){
                RadioButton opt = new RadioButton(this);
                opt.setText(preguntas.get(2).getOpciones()[i]);
                opt.setTextColor(Color.BLACK);
                opt.setButtonTintList(colorStateList);
                radio_group3.addView(opt);
            }
            tv_pregunta4.setText(preguntas.get(3).getNombre());
            tv_enunciado4.setText(preguntas.get(3).getEnunciado());
            for (int i = 0; i < 4; i++ ){
                RadioButton opt = new RadioButton(this);
                opt.setText(preguntas.get(3).getOpciones()[i]);
                opt.setTextColor(Color.BLACK);
                opt.setButtonTintList(colorStateList);
                radio_group4.addView(opt);
            }
            tv_pregunta5.setText(preguntas.get(4).getNombre());
            tv_enunciado5.setText(preguntas.get(4).getEnunciado());
            for (int i = 0; i < 4; i++ ){
                RadioButton opt = new RadioButton(this);
                opt.setText(preguntas.get(4).getOpciones()[i]);
                opt.setTextColor(Color.BLACK);
                opt.setButtonTintList(colorStateList);
                radio_group5.addView(opt);
            }


        } catch (Exception e) {
            Log.e("----------->", getApplicationContext().getFilesDir().toString());

            Log.e("----------->", e.getMessage());
            e.printStackTrace();
        }


    }

    private void enviarRegistro(){

        if (radio_group1.getCheckedRadioButtonId()!=-1 &&
            radio_group2.getCheckedRadioButtonId()!=-1 &&
            radio_group3.getCheckedRadioButtonId()!=-1 &&
            radio_group4.getCheckedRadioButtonId()!=-1 &&
            radio_group5.getCheckedRadioButtonId()!=-1){

            Registro registro = new Registro();
            RadioButton rb1 = (RadioButton) findViewById(radio_group1.getCheckedRadioButtonId());
            registro.setR1(rb1.getText().toString());

            RadioButton rb2 = (RadioButton) findViewById(radio_group2.getCheckedRadioButtonId());
            registro.setR2(rb2.getText().toString());

            RadioButton rb3 = (RadioButton) findViewById(radio_group3.getCheckedRadioButtonId());
            registro.setR3(rb3.getText().toString());

            RadioButton rb4 = (RadioButton) findViewById(radio_group4.getCheckedRadioButtonId());
            registro.setR4(rb4.getText().toString());

            RadioButton rb5 = (RadioButton) findViewById(radio_group5.getCheckedRadioButtonId());
            registro.setR5(rb5.getText().toString());

            crearRegistro(registro);

        } else {
            Toast.makeText(this, "Debe contestar todas las preguntas", Toast.LENGTH_SHORT).show();
        }

    }

    public void crearRegistro(final Registro registro){

        DatabaseReference reference = db.getReference().child("registros").push();
        String idRegistro = reference.getKey();

        registro.setId(idRegistro);

        reference.setValue(registro);

        Toast.makeText(this, "Registro completo", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),ActivityInicio.class);
        startActivity(i);
        finish();
    }

}
