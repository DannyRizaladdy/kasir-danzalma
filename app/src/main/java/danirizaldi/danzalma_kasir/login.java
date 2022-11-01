package danirizaldi.danzalma_kasir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    String email,password;
    Button btnLogin;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.button);

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekLogin();
            }
        });

        TextView a = findViewById(R.id.text3);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);

                Toast.makeText(login.this,"Sign Up Now!",Toast.LENGTH_SHORT).show();
            }
        });

        TextView b = findViewById(R.id.text4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, MainActivity .class);
                startActivity(intent);

                Toast.makeText(login.this,"Sign Up Now!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cekLogin() {
        email = inputEmail.getText().toString();
        password =  inputPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(login.this , "Login Berhasil",Toast.LENGTH_SHORT);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(login.this, "Login Gagal! Email atau Password salah.",
                        Toast.LENGTH_SHORT).show();
                inputPassword.setText("");
            }
        }
    });
    }
}