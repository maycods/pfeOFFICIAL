package com.example.fronttttttttttttttttttt;

import static com.example.fronttttttttttttttttttt.Menu.SCROLL_DELTA;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Formatter;

public class AdminPage extends Activity  implements AdapterView.OnItemSelectedListener {
    private ImageButton L,r;
    private HorizontalScrollView scrollView;
    public static  int SCROLL;
    private Button cfrm;
    String ambulance=null,Ambulance;
    DisplayMetrics displayMetric = new DisplayMetrics();
    //TODO AFFICHER HOPITEAU INFO DE BDD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        cfrm=findViewById(R.id.inscritt);
        scrollView = (HorizontalScrollView) findViewById(R.id.scrl);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetric);
        SCROLL = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, displayMetric.widthPixels/2-50 , getResources().getDisplayMetrics());
       Spinner spinner = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.IDamb, R.layout.spinn);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        r = (ImageButton) findViewById(R.id.droite);
        L = (ImageButton) findViewById(R.id.gauche);
        cfrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Ambulance=ambulance;
Toast.makeText(getApplicationContext(),String.valueOf(Ambulance),Toast.LENGTH_LONG).show();
            }
        });

        L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = scrollView.getScrollX();
                if(x - SCROLL_DELTA >= 0) {
                    scrollView.scrollTo(x-SCROLL, scrollView.getScrollY());
                }return;
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maxAmount = 4000;

                int x =scrollView.getScrollX();
                if(x + SCROLL_DELTA <= maxAmount) {
                    scrollView.scrollTo(x+SCROLL, scrollView.getScrollY());
                }
                return;
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

//TODO AFFECTATION

        Spinner t2 =(Spinner) adapterView;
        ambulance = (String) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
