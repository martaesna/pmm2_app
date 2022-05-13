package com.example.myapplication.ui.adduser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.google.firebase.database.collection.LLRBNode;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColors.setAdapter(adapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userToAdd = usernameEditText.getText().toString();
                String color = getColorByName(spinnerColors.getSelectedItem().toString());
                User user = new User(num_users, userToAdd, color);
                int accountID = getIntent().getExtras().getInt("accountID");
                Task<Void> mDatabase = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).child("users").child(String.valueOf(num_users)).setValue(user);
                Intent intent = new Intent(AddUserActivity.this, ChooseUserActivity.class);
                intent.putExtra("account", username);
                startActivity(intent);
            }
        });
        
    }

    private String getColorByName(String color) {
        switch (color) {
            case "BLAU":
            case "AZUL":
            case "BLUE":
                return getResources().getString(R.string.blue);
            case "VERMELL":
            case "ROJO":
            case "RED":
                return getResources().getString(R.string.red);
            case "TARONJA":
            case "NARANJA":
            case "ORANGE":
                return getResources().getString(R.string.menu_orange);
            case "GROC":
            case "AMARILLO":
            case "YELLOW":
                return getResources().getString(R.string.yellow);
            case "LILA":
            case "PURPLE":
                return getResources().getString(R.string.purple);
            case "GRIS":
            case "GREY":
                return getResources().getString(R.string.grey);
            case "ROSA":
            case "PINK":
                return getResources().getString(R.string.pink);
            case "VERD":
            case "VERDE":
            case "GREEN":
                return getResources().getString(R.string.green);
            case "CIAN":
            case "CYAN":
                return getResources().getString(R.string.cyan);
            case "MARRÓ":
            case "MARRÓN":
            case "BROWN":
                return getResources().getString(R.string.brown);
            case "BLANC":
            case "BLANCO":
            case "WHITE":
                return getResources().getString(R.string.white);
            case "NEGRE":
            case "NEGRO":
            case "BLACK":
                return getResources().getString(R.string.black);
        }
        return null;
    }
}
