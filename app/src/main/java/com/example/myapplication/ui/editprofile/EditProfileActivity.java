package com.example.myapplication.ui.editprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityEditprofileBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;

public class EditProfileActivity extends AppCompatActivity {
    private ActivityEditprofileBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editprofile);
        binding = ActivityEditprofileBinding.inflate(getLayoutInflater());

        final Button editProfileButton = findViewById(R.id.editProfileButton);

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileActivity.this.startActivity(new Intent(EditProfileActivity.this, ConfigurationActivity.class));
            }
        });
    }
}
