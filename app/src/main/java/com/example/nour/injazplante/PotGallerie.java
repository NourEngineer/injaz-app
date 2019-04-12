package com.example.nour.injazplante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class PotGallerie extends AppCompatActivity {

    ArrayList<Pot> list_pot = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pot_gallerie);

    }
}
