package com.example.myapplication.ui.editaccount;

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
import com.example.myapplication.databinding.ActivityEditaccountBinding;
import com.example.myapplication.databinding.ActivityMenuBinding;
import com.example.myapplication.ui.configuration.ConfigurationActivity;
import com.example.myapplication.ui.edit.EditActivity;
import com.example.myapplication.ui.editprofile.EditProfileActivity;
import com.example.myapplication.ui.help.HelpActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditAccountActivity extends AppCompatActivity {
    private ActivityEditaccountBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editaccount);
        binding = ActivityEditaccountBinding.inflate(getLayoutInflater());

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
                Intent intentButton = new Intent(EditAccountActivity.this, HelpActivity.class);
                intentButton.putExtra("num_users", num_users);
                intentButton.putExtra("user_info", info);
                intentButton.putExtra("account", account);
                intentButton.putExtra("accountID", accountID);
                intentButton.putExtra("back", "editaccount");
                EditAccountActivity.this.startActivity(intentButton);
            }
        });

        final Button editAccountButton = findViewById(R.id.editAccountButton);
        final Button backButton = findViewById(R.id.sortir2);


        editAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: edit account firebase
                updatePassword();
                Intent intent = new Intent(EditAccountActivity.this, ConfigurationActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                EditAccountActivity.this.startActivity(intent);
            }

            private void updatePassword() {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("accounts");
                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Account accountToFind = ds.getValue(Account.class);
                            assert accountToFind != null;
                            if (accountToFind.getName().equals(account)) {
                                EditText myEditText = (EditText) findViewById(R.id.old_password);
                                String oldPassword = myEditText.getText().toString();
                                EditText myEditText2 = (EditText) findViewById(R.id.new_password);
                                String newPassword = myEditText2.getText().toString();
                                if (accountToFind.getPassword().equals(DigestUtils.md5Hex(oldPassword))) {
                                    getIntent().putExtra("accountID", accountToFind.getAccountID());
                                    accountToFind.setPassword(DigestUtils.md5Hex(newPassword));
                                    Task<Void> mDatabase = FirebaseDatabase.getInstance().getReference("accounts").child(String.valueOf(accountID)).setValue(accountToFind);
                                    Toast.makeText(getApplicationContext(), "Contrasenya canviada", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Contrasenya incorrecte", Toast.LENGTH_LONG).show();
                                }
                                mDatabase.removeEventListener(this);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                };
                mDatabase.addValueEventListener(listener);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAccountActivity.this, ConfigurationActivity.class);
                intent.putExtra("num_users", num_users);
                intent.putExtra("user_info", info);
                intent.putExtra("account", account);
                intent.putExtra("accountID", accountID);
                EditAccountActivity.this.startActivity(intent);
            }
        });
    }
}
