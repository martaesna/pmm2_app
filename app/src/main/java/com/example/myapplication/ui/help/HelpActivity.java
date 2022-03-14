package com.example.myapplication.ui.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityHelpBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;

public class HelpActivity extends AppCompatActivity {
    private ActivityHelpBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);
        binding = ActivityHelpBinding.inflate(getLayoutInflater());

        final Button helpButton = findViewById(R.id.helpButton);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpActivity.this.startActivity(new Intent(HelpActivity.this, ConfigurationActivity.class));
            }
        });

    }
}
