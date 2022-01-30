package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity extends AppCompatActivity {

    private TextView textViewOrszagKereses, textViewVarosok;
    private Button buttonVissza;
    private DBhelper adatbazis;
    private String keres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();
        Bundle extras = getIntent().getExtras();
        keres = extras.getString("OrszágKeresés");
        textViewOrszagKereses.setText(getIntent().getExtras().getString("OrszágKeresés"));
        adatLekerdezes();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(intentMain);
                finish();
            }
        });
    }

    public void adatLekerdezes(){
        Cursor adatok = adatbazis.adatkerdezes();

        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()){
            if(adatok.getString(2).contains(keres) || adatok.getString(1).contains(keres) || adatok.getString(3).contains(keres)){
                builder.append("ID: ").append(adatok.getInt(0)).append("\n");
                builder.append("Név: ").append(adatok.getString(1)).append("\n");
                builder.append("Ország: ").append(adatok.getString(2)).append("\n");
                builder.append("Lakosság: ").append(adatok.getInt(3)).append(" fő").append("\n\n");

                textViewVarosok.setText(builder);
                Toast.makeText(this, "Sikeres adatlekérdezés", Toast.LENGTH_SHORT).show();
            }/*else {
                textViewVarosok.setText("Nem található rekord a következő adattal: " + keres);
            }*/
        }
    }

    public void init(){

        textViewOrszagKereses = findViewById(R.id.textViewOrszagKereses);
        textViewVarosok = findViewById(R.id.textViewVarosok);
        buttonVissza = findViewById(R.id.buttonVissza);
        adatbazis = new DBhelper(SearchResultActivity.this);

        textViewVarosok.setMovementMethod(new ScrollingMovementMethod());
    }
}