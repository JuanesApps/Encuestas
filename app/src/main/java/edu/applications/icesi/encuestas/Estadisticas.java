package edu.applications.icesi.encuestas;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import edu.applications.icesi.encuestas.model.Registro;
import edu.applications.icesi.encuestas.model.Statistics;

public class Estadisticas extends AppCompatActivity {

    private Spinner spinner_preguntas;
    private ArrayAdapter<CharSequence> adapter;
    private int preguntaActual;

    private ArrayList<Registro> registros = new ArrayList<>();

    private String itemSeleccionado;

    private Statistics statistics;
    private int opcionA;
    private int opcionB;
    private int opcionC;
    private int opcionD;
    private int totalOpciones;
    private PieChart chart;

    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        preguntaActual = 1;
        db = FirebaseDatabase.getInstance();

        spinner_preguntas = findViewById(R.id.spinner_preguntas);
        adapter = ArrayAdapter.createFromResource(this, R.array.preguntas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_preguntas.setAdapter(adapter);
        spinner_preguntas.setSelection(1);

        chart = findViewById(R.id.chart);

        itemSeleccionado = "";

        opcionA = 0;
        opcionB = 0;
        opcionC = 0;
        opcionD = 0;
        totalOpciones = 0;

        extraerInfoByDB();

        spinner_preguntas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSeleccionado = parent.getItemAtPosition(position).toString();
                if (statistics != null){
                    setupPieChart(itemSeleccionado);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_preguntas.setSelection(1);
            }
        });

    }


    public PieDataSet getDataSet(String spinner){
        List<PieEntry> pieEntries = new ArrayList<>();

        if (spinner.equals("Pregunta 1")){
            pieEntries.add(new PieEntry((float) statistics.getPregunta1().getPorcentajeA(), "A"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta1().getPorcentajeB(), "B"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta1().getPorcentajeC(), "C"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta1().getPorcentajeD(), "D"));
        } else if (spinner.equals("Pregunta 2")){
            pieEntries.add(new PieEntry((float) statistics.getPregunta2().getPorcentajeA(), "A"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta2().getPorcentajeB(), "B"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta2().getPorcentajeC(), "C"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta2().getPorcentajeD(), "D"));
        } else if (spinner.equals("Pregunta 3")){
            pieEntries.add(new PieEntry((float) statistics.getPregunta3().getPorcentajeA(), "A"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta3().getPorcentajeB(), "B"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta3().getPorcentajeC(), "C"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta3().getPorcentajeD(), "D"));
        } else if (spinner.equals("Pregunta 4")){
            pieEntries.add(new PieEntry((float) statistics.getPregunta4().getPorcentajeA(), "A"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta4().getPorcentajeB(), "B"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta4().getPorcentajeC(), "C"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta4().getPorcentajeD(), "D"));
        } else {
            pieEntries.add(new PieEntry((float) statistics.getPregunta5().getPorcentajeA(), "A"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta5().getPorcentajeB(), "B"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta5().getPorcentajeC(), "C"));
            pieEntries.add(new PieEntry((float) statistics.getPregunta5().getPorcentajeD(), "D"));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setSliceSpace(2);
        dataSet.setValueTextSize(20);
        dataSet.setFormSize(20);

        return dataSet;
    }

    // https://github.com/PhilJay/MPAndroidChart
    // https://material.io/tools/color/#!/?view.left=0&view.right=0&primary.color=2196F3
    private void setupPieChart(String spinner) {
        chart.clear();

        PieDataSet dataSet = getDataSet(spinner);

        // Get the chart

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);

        dataSet.setColors(colors);

        Legend legend = chart.getLegend();
        legend.setTextSize(20);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData data = new PieData(dataSet);

        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
        chart.setHoleRadius(25f);
        chart.setCenterText(spinner);
    }

    public void extraerInfoByDB() {

        DatabaseReference registros_ref = db.getReference().child("registros");

        registros_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> ds = dataSnapshot.getChildren();
                for (DataSnapshot data : ds){
                    Registro r = data.getValue(Registro.class);
                    agregarRegistro(r);
                }
                MostrarEstadisticas();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public  void MostrarEstadisticas(){

        statistics = new Statistics(registros);

        setupPieChart("Pregunta 1");

    }

    private void agregarRegistro(Registro r) {
        registros.add(r);
        MostrarEstadisticas();
    }

    private void eliminarRegistro(Registro r){
        registros.remove(r);

    }

}