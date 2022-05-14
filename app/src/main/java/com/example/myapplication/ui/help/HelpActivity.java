package com.example.myapplication.ui.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityHelpBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.add.AddActivity;
import com.example.myapplication.ui.adduser.AddUserActivity;
import com.example.myapplication.ui.chooseuser.ChooseUserActivity;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.delete.DeleteActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;
import com.example.myapplication.ui.editprofile.EditProfileActivity;
import com.example.myapplication.ui.login.LoginActivity;
import com.example.myapplication.ui.menu.MenuActivity;

public class HelpActivity extends AppCompatActivity {
    private ActivityHelpBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);
        binding = ActivityHelpBinding.inflate(getLayoutInflater());

        String info = getIntent().getExtras().getString("user_info");
        String account = getIntent().getExtras().getString("account");
        int num_users = getIntent().getExtras().getInt("num_users");
        int accountID = getIntent().getExtras().getInt("accountID");

        final Button helpButton = findViewById(R.id.helpButton);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (getIntent().getExtras().getString("back")) {
                    case "login":
                        intent = new Intent(HelpActivity.this, LoginActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    case "choose":
                        intent = new Intent(HelpActivity.this, ChooseUserActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    case "menu":
                        intent = new Intent(HelpActivity.this, MenuActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    case "add":
                        intent = new Intent(HelpActivity.this, AddActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    case "edit":
                        intent = new Intent(HelpActivity.this, EditActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    case "delete":
                        intent = new Intent(HelpActivity.this, DeleteActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    case "editaccount":
                        intent = new Intent(HelpActivity.this, EditAccountActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    case "editprofile":
                        intent = new Intent(HelpActivity.this, EditProfileActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    case "adduser":
                        intent = new Intent(HelpActivity.this, AddUserActivity.class);
                        if (getIntent().getExtras().getString("sentTo").equals("choose")) {
                            intent.putExtra("back", "choose");
                        } else {
                            intent.putExtra("back", "config");
                        }
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                        break;
                    default:
                        intent = new Intent(HelpActivity.this, ConfigurationActivity.class);
                        intent.putExtra("num_users", num_users);
                        intent.putExtra("user_info", info);
                        intent.putExtra("account", account);
                        intent.putExtra("accountID", accountID);
                        HelpActivity.this.startActivity(intent);
                }
            }
        });

    }
}
