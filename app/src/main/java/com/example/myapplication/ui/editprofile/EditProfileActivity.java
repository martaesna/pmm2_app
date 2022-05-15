package com.example.myapplication.ui.editprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Account;
import com.example.myapplication.data.model.Device;
import com.example.myapplication.databinding.ActivityEditprofileBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.editaccount.EditAccountActivity;
import com.example.myapplication.ui.help.HelpActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {
    private ActivityEditprofileBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editprofile);
        binding = ActivityEditprofileBinding.inflate(getLayoutInflater());

        final Button editProfileButton = findViewById(R.id.editProfileButton);
        final Button backButton = findViewById(R.id.sortir2);

        String info = getIntent().getExtras().getString("user_info");
        String[] user_info = info.split("/");
        String account = user_info[0];
        String username = user_info[1];
        int num_users = getIntent().getExtras().getInt("num_users");
        int accountID = getIntent().getExtras().getInt("accountID");

        ImageButton helpButton = findViewById(R.id.help);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentButton = new Intent(EditProfileActivity.this, HelpActivity.class);
                intentButton.putExtra("num_users", num_users);
                intentButton.putExtra("user_info", info);
                intentButton.putExtra("account", account);
                intentButton.putExtra("accountID", accountID);
                intentButton.putExtra("back", "editprofile");
                EditProfileActivity.this.startActivity(intentButton);
            }
        });


        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
                Intent intent = new Intent(EditProfileActivity.this, ConfigurationActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                EditProfileActivity.this.startActivity(intent);            }
            private void updateProfile() {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("accounts");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds: snapshot.getChildren()) {
                            Account accountToFind = ds.getValue(Account.class);
                            assert accountToFind != null;
                            if (accountToFind.getName().equals(account)) {
                                for (int i = 0; i < accountToFind.getUsers().size(); i++) {
                                    if (accountToFind.getUsers().get(i).getName().equals(username)) {
                                        getIntent().putExtra("userID", i);
                                        EditText myEditText =  (EditText) findViewById(R.id.new_username);
                                        String newName = myEditText.getText().toString();
                                        getIntent().removeExtra("user_info");
                                        getIntent().putExtra("user_info", account + "/" + newName);
                                        accountToFind.getUsers().get(i).setName(newName);
                                        Task<Void> mDatabase = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).child("users").child(String.valueOf(i)).setValue(accountToFind.getUsers().get(i));
                                        Toast.makeText(getApplicationContext(), "Nom canviat", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ConfigurationActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                EditProfileActivity.this.startActivity(intent);
            }
        });
    }
}
