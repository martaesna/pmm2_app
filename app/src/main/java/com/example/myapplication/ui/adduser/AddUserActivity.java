package com.example.myapplication.ui.adduser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityAdduserBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;

public class AddUserActivity extends AppCompatActivity {
    private ActivityAdduserBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adduser);
        binding = ActivityAdduserBinding.inflate(getLayoutInflater());

        final Button addButton = findViewById(R.id.addUserButton);
        Spinner spinnerColors = findViewById(R.id.spinner_colors);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUserActivity.this.startActivity(new Intent(AddUserActivity.this, ConfigurationActivity.class));
            }
        });

    }
}
