package com.example.myapplication.ui.chooseuser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.data.SSHConnection;
import com.example.myapplication.databinding.ActivityChooseuserBinding;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.ui.login.LoginActivity;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

public class ChooseUserActivity extends AppCompatActivity {

    private ActivityChooseuserBinding binding;
    TextView hola;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chooseuser);
        binding = ActivityChooseuserBinding.inflate(getLayoutInflater());

        final Button martaButton = binding.martaButton;
        martaButton.setEnabled(true);
        martaButton.setClickable(true);

        final Button marioButton = binding.marioButton;
        marioButton.setEnabled(true);
        marioButton.setClickable(true);

        final Button laiaButton = binding.laiaButton;
        laiaButton.setEnabled(true);
        laiaButton.setClickable(true);

        final Button silviaButton = binding.silviaButton;
        silviaButton.setEnabled(true);
        silviaButton.setClickable(true);

        martaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        marioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        laiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        silviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
