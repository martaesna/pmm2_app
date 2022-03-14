package com.example.myapplication.ui.configuration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityConfigurationBinding;
import com.example.myapplication.ui.adduser.AddUserActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;
import com.example.myapplication.ui.editprofile.EditProfileActivity;
import com.example.myapplication.ui.help.HelpActivity;
import com.example.myapplication.ui.login.LoginActivity;
import com.example.myapplication.ui.menu.MenuActivity;

public class ConfigurationActivity extends AppCompatActivity {
    private ActivityConfigurationBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configuration);
        binding = ActivityConfigurationBinding.inflate(getLayoutInflater());

        final Button editProfileButton = findViewById(R.id.changeName);
        final Button addProfileButton = findViewById(R.id.addUser);
        final Button editAccountButton = findViewById(R.id.account);
        final Button helpButton = findViewById(R.id.help);
        final Button exitButton = findViewById(R.id.sortir2);

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigurationActivity.this.startActivity(new Intent(ConfigurationActivity.this, EditProfileActivity.class));
            }
        });

        addProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigurationActivity.this.startActivity(new Intent(ConfigurationActivity.this, AddUserActivity.class));
            }
        });

        editAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigurationActivity.this.startActivity(new Intent(ConfigurationActivity.this, EditAccountActivity.class));
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigurationActivity.this.startActivity(new Intent(ConfigurationActivity.this, HelpActivity.class));
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigurationActivity.this.startActivity(new Intent(ConfigurationActivity.this, LoginActivity.class));
            }
        });

    }
}