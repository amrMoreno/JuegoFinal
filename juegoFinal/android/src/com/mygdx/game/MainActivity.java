package com.mygdx.game;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Jugar(View view) {
        Intent i= new Intent(this,AndroidLauncher.class);
        this.startActivity(i);
    }

    public void salir(View view) {
        FragmentManager fm = this.getSupportFragmentManager();
        Alerta miAlerta = new Alerta();
        miAlerta.show(fm,"alerta");

    }
}
