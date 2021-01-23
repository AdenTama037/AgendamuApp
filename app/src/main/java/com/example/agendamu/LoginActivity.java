package com.example.agendamu;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendamu.model.UserModel;
import com.example.agendamu.utilities.ActivityHelper;
import com.example.agendamu.utilities.LoginHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String email, password;
    private LoginActivity currentActivity = this;
    private DatabaseReference dbUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ((TextInputEditText) findViewById(R.id.inputEmail)).getText().toString();
                password = ((TextInputEditText) findViewById(R.id.inputPassword)).getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    login(email, password);
                }
            }
        });
    }

    private void login(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        dbUsers = FirebaseDatabase.getInstance().getReference("users");
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    dbUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            DataSnapshot loggedInUser = snapshot.child(mAuth.getCurrentUser().getUid());
                            UserModel userModel = loggedInUser.getValue(UserModel.class);
                            userModel.setUid(mAuth.getCurrentUser().getUid());
                            userModel.setEmail(mAuth.getCurrentUser().getEmail());
                            LoginHelper.setCurrentUser(userModel);
                            System.out.println(LoginHelper.getCurrentUser());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            System.out.println("Sign in cancelled");
                        }
                    });

                    System.out.println("Login success");
                    ActivityHelper.getMainActivity().setupFragments();
                    currentActivity.finish();
                } else {
                    System.out.println("Login failed");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}