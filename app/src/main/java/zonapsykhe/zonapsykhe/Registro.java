package zonapsykhe.zonapsykhe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private Button btnregre;
    private EditText TextEmail;
    private EditText TextPass;
    private EditText TextPassR;
    private ProgressDialog progressDialog;

    //Objeto fire base
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //referenciar los views
        TextEmail = (EditText) findViewById(R.id.TxtEmail);
        TextPass = (EditText) findViewById(R.id.TxtPass);
        TextPassR = (EditText) findViewById(R.id.TxtPassR);

        //este regre
        btnregre = (Button) findViewById(R.id.btn_reg);

        progressDialog = new ProgressDialog(this);

        btnregre.setOnClickListener(this);
    }

    private void registratUsuario() {

        String email = TextEmail.getText().toString().trim();
        String password = TextPass.getText().toString().trim();
        String passwordR = TextPassR.getText().toString().trim();

        //cajas no vacias
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un correo", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(passwordR)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //Nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if(task.isSuccessful()){
                            Intent goTo = new Intent(Registro.this, Login.class);
                            Registro.this.startActivity(goTo);
                            Toast.makeText(Registro.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Registro.this, "El usuario ya existe ", Toast.LENGTH_SHORT).show();
                                Intent goTo = new Intent(Registro.this, Login.class);
                                Registro.this.startActivity(goTo);
                            } else {
                                Toast.makeText(Registro.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();

                            }
                        }
                        progressDialog.dismiss();

                    }
                });
    }

    public void onClick(View view){
        registratUsuario();


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {


    }
}