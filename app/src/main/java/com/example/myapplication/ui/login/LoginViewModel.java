package com.example.myapplication.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.util.Log;
import android.util.Patterns;

import com.example.myapplication.data.LoginRepository;
import com.example.myapplication.R;
import com.example.myapplication.data.model.Account;
import com.example.myapplication.ui.chooseuser.ChooseUserActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public interface OnGetDataListener {
        //this is for callbacks
        void onSuccess(DataSnapshot dataSnapshot);
        void onStart();
        void onFailure();
    }
    public void readData(DatabaseReference reference, final OnGetDataListener listener, ArrayList<Account> accounts) {
        listener.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailure();
            }
        });
    }
    public void login(String username, String password, LoginActivity loginActivity) {
        // can be launched in a separate asynchronous job
        java.util.ArrayList<Account> accounts = new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("accounts");
        readData(reference, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                boolean loggedIn = false;
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    Account account = ds.getValue(Account.class);
                    accounts.add(account);
                }
                String encrypt = DigestUtils.md5Hex(password);
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getName().equals(username) && accounts.get(i).getPassword().equals(encrypt)) {
                        Intent intentMain = new Intent(loginActivity , ChooseUserActivity.class);
                        intentMain.putExtra("username", username);
                        loginActivity.startActivity(intentMain);
                        loggedIn = true;
                    }
                }
                if (!loggedIn) {
                    Intent intentMain = new Intent(loginActivity , LoginActivity.class);
                    loginActivity.startActivity(intentMain);
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }
            }
            @Override
            public void onStart() {
                //when starting
                Log.d("ONSTART", "Started");
            }

            @Override
            public void onFailure() {
                Log.d("onFailure", "Failed");
            }
        }, accounts);
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() >= 5;
    }
}