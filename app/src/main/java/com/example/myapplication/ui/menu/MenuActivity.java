package com.example.myapplication.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.add.AddActivity;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.delete.DeleteActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.login.LoginActivity;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());

        final Button exitButton = findViewById(R.id.sortir);
        final ImageButton configButton = findViewById(R.id.ButtonConfig);
        final ImageButton editButton = findViewById(R.id.BotonEdit);
        final ImageButton deleteButton = findViewById(R.id.BotonDelete);
        final ImageButton addButton = findViewById(R.id.BotonAdd);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.this.startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            }
        });
        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.this.startActivity(new Intent(MenuActivity.this, ConfigurationActivity.class));
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.this.startActivity(new Intent(MenuActivity.this, EditActivity.class));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.this.startActivity(new Intent(MenuActivity.this, DeleteActivity.class));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.this.startActivity(new Intent(MenuActivity.this, AddActivity.class));
            }
        });

    }
}
