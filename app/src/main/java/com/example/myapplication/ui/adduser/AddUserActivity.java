package com.example.myapplication.ui.adduser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.User;
import com.example.myapplication.databinding.ActivityAdduserBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.chooseuser.ChooseUserActivity;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserActivity extends AppCompatActivity {
    private ActivityAdduserBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adduser);
        binding = ActivityAdduserBinding.inflate(getLayoutInflater());
        EditText usernameEditText =  (EditText) findViewById(R.id.userToAdd);


        String username = getIntent().getExtras().getString("account");
        int num_users = getIntent().getExtras().getInt("num_users");


        final Button addButton = findViewById(R.id.addUserButton);
        Spinner spinnerColors = findViewById(R.id.spinner_colors);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userToAdd = usernameEditText.getText().toString();
                String color = "#F89501"; //TODO: AGAFAR COLOR DEL SPINNER
                User user = new User(num_users, userToAdd, color);
                int accountID = getIntent().getExtras().getInt("accountID");
                Task<Void> mDatabase = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).child("users").child(String.valueOf(num_users)).setValue(user);
                Intent intent = new Intent(AddUserActivity.this, ChooseUserActivity.class);
                intent.putExtra("account", username);
                startActivity(intent);
            }
        });

    }
}
