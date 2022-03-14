package com.example.myapplication.ui.editaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityEditaccountBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.editprofile.EditProfileActivity;

public class EditAccountActivity extends AppCompatActivity {
    private ActivityEditaccountBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editaccount);
        binding = ActivityEditaccountBinding.inflate(getLayoutInflater());

        final Button editAccountButton = findViewById(R.id.editAccountButton);

        editAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAccountActivity.this.startActivity(new Intent(EditAccountActivity.this, ConfigurationActivity.class));
            }
        });
    }
}
