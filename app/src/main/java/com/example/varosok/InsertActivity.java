package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText editTextNev, editTextOrszag, editTextLakossag;
    private Button buttonFelvetel, buttonVissza;
    private DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        init();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intentMain);
                finish();
            }
        });

        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatRogzites();
            }
        });
    }

    public void adatRogzites(){
        String nev = editTextNev.getText().toString().trim();
        String orszag = editTextOrszag.getText().toString().trim();
        String lakossag = editTextLakossag.getText().toString().trim();

        if(nev.isEmpty()){
            Toast.makeText(this, "Név megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextNev.setError("Név megadása kötelező");
            buttonFelvetel.setEnabled(false);
            return;
        }
        if(orszag.isEmpty()){
            Toast.makeText(this, "Elkészítés megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextOrszag.setError("Ország megadása kötelező");
            buttonFelvetel.setEnabled(false);
            return;
        }
        if(lakossag.isEmpty()){
            Toast.makeText(this, "Ár megadása kötelező", Toast.LENGTH_SHORT).show();
            editTextLakossag.setError("Lakosság megadása kötelező");
            buttonFelvetel.setEnabled(false);
            return;
        }


        if(adatbazis.adatRogzites(nev,orszag,lakossag)){
            Toast.makeText(this, "Sikeres adatrögzítés", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Sikertelen adatrögzítés", Toast.LENGTH_SHORT).show();
        }



    }

    public void init(){
        editTextNev = findViewById(R.id.editTextNev);
        editTextOrszag = findViewById(R.id.editTextOrszag);
        editTextLakossag = findViewById(R.id.editTextLakossag);
        buttonFelvetel = findViewById(R.id.buttonFelvetel);
        buttonVissza = findViewById(R.id.buttonVissza);
        adatbazis = new DBhelper(InsertActivity.this);
    }
}