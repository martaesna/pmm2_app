package com.example.myapplication.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
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
        editButton.setEnabled(false);
        final ImageButton deleteButton = findViewById(R.id.BotonDelete);
        deleteButton.setEnabled(false);
        final ImageButton addButton = findViewById(R.id.BotonAdd);
        addButton.setEnabled(false);
        final View phoneButton = findViewById(R.id.telefon);
        phoneButton.setEnabled(true);
        phoneButton.setClickable(true);

        final View purseButton = findViewById(R.id.cartera);
        purseButton.setEnabled(true);
        purseButton.setClickable(true);


        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneButton.setBackgroundResource(R.drawable.rectangle_clicked);
                purseButton.setBackgroundResource(R.drawable.rectangle_login_textedit);
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
                addButton.setEnabled(true);
            }
        });

        purseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purseButton.setBackgroundResource(R.drawable.rectangle_clicked);
                phoneButton.setBackgroundResource(R.drawable.rectangle_login_textedit);
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
                addButton.setEnabled(true);
            }
        });


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

    public static class MyClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //do something
        }
    }
}
