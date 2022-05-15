package com.example.myapplication.ui.chooseuser;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Account;
import com.example.myapplication.data.model.User;
import com.example.myapplication.databinding.ActivityChooseuserBinding;
import com.example.myapplication.ui.adduser.AddUserActivity;
import com.example.myapplication.ui.help.HelpActivity;
import com.example.myapplication.ui.login.LoginActivity;
import com.example.myapplication.ui.menu.MenuActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChooseUserActivity extends AppCompatActivity {

    private ActivityChooseuserBinding binding;
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chooseuser);
        binding = ActivityChooseuserBinding.inflate(getLayoutInflater());

        String account = getIntent().getExtras().getString("account");
        int accountID = getIntent().getExtras().getInt("accountID");

        Intent intent = new Intent(ChooseUserActivity.this, AddUserActivity.class);

        ImageButton helpButton = findViewById(R.id.help);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentButton = new Intent(ChooseUserActivity.this, HelpActivity.class);
                intentButton.putExtra("account", account);
                intentButton.putExtra("accountID", accountID);
                intentButton.putExtra("back", "choose");
                ChooseUserActivity.this.startActivity(intentButton);
            }
        });

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("accounts");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Account accountToFind = ds.getValue(Account.class);
                    assert accountToFind != null;
                    if (accountToFind.getName().equals(account)) {
                        users = accountToFind.getUsers();
                    }
                }
                intent.putExtra("num_users", users.size());
                for (User user: users) {
                    Button button = new Button(getApplicationContext());
                    button.setEnabled(true);
                    int color = Color.parseColor(user.getColor());
                    button.setBackgroundColor(color);
                    button.setGravity(Gravity.CENTER);
                    button.setText(user.getName());
                    button.setTextSize(24);
                    if (color != Color.WHITE) {
                        button.setTextColor(Color.WHITE);
                    } else {
                        button.setTextColor(Color.BLACK);
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(21, 0, 0, 100);
                    button.setWidth(1200);
                    button.setHeight(300);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intentButton = new Intent(ChooseUserActivity.this, MenuActivity.class);
                            intentButton.putExtra("num_users", users.size());
                            intentButton.putExtra("user_info", account + "/" + user.getName());
                            intentButton.putExtra("account", account);
                            intentButton.putExtra("accountID", accountID);
                            ChooseUserActivity.this.startActivity(intentButton);
                        }
                    });

                    LinearLayout ll = (LinearLayout)findViewById(R.id.chooseuserll);
                    ll.addView(button, params);

                    mDatabase.removeEventListener(this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        };
        mDatabase.addValueEventListener(listener);

        Button addButton = findViewById(R.id.addNewUserButton);
        addButton.setEnabled(true);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButton.setEnabled(false);
                intent.putExtra("back", "choose");
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                ChooseUserActivity.this.startActivity(intent);
            }
        });
    }

}
