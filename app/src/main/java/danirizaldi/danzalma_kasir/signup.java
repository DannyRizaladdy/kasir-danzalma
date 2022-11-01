package danirizaldi.danzalma_kasir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class signup extends AppCompatActivity {

    EditText inputName, inputEmail, inputPassword;
    String name, email, password;
    Button btnSignup;
    private FirebaseAuth mAuth;
    ProgressDialog pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputName = findViewById(R.id.name);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        pr = new ProgressDialog(signup.this);
        pr.setTitle("Loading");
        pr.setMessage("Silahkan Menunggu!");
        pr.setCancelable(false);

        btnSignup = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekSignup();
            }
        });

        TextView a = findViewById(R.id.text3);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);

                Toast.makeText(signup.this,"Login Now!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cekSignup() {
        email = inputEmail.getText().toString();
        password =  inputPassword.getText().toString();
        pr.show();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            FirebaseUser user = task.getResult().getUser();
                            UserProfileChangeRequest prof = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            if (user != null) {
                                user.updateProfile(prof).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(signup.this , "Register Berhasil",Toast.LENGTH_LONG);
                                        startActivity(new Intent(getApplicationContext(), login.class));
                                    }
                                });
                            }else{
                                Toast.makeText(signup.this, "Register Gagal! Name Sudah ada.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            Toast.makeText(signup.this, "Register Gagal!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}