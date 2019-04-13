package com.example.androideatit1;

import android.app.ProgressDialog;
import android.os.Binder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androideatit1.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText edtEmail,edtName,edtPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        this.edtName = (EditText)findViewById(R.id.edtName);
        this.edtEmail = (EditText)findViewById(R.id.edtEmail);
        this.edtPassword = (EditText)findViewById(R.id.edtPassword);

        this.btnSignUp = (Button)findViewById(R.id.btnSignUp);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        this.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Pleas waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check if already user Phone
                        if(dataSnapshot.child(edtEmail.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this,"Phone Number already register",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            mDialog.dismiss();
                            User user = new User(edtName.getText().toString(),
                                    edtPassword.getText().toString());
                            table_user.child(edtEmail.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"Sign up successfully!",
                                    Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
