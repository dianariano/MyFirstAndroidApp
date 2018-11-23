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


public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnReg;
    Button btnLogin;
    private EditText TextEmail;
    private EditText TextPass;
    private ProgressDialog progressDialog;



    //Objeto fire base
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //pasar a registrarse
        btnReg = (Button) findViewById(R.id.btn_registro);

        btnReg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent op = new Intent(getApplicationContext(), Registro.class);
                startActivity(op);
            }
        });


        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //referenciar los views
        TextEmail = (EditText) findViewById(R.id.TxtEmail1);
        TextPass = (EditText) findViewById(R.id.TxtPass1);

        //este regre
        btnLogin = (Button) findViewById(R.id.Boton_login);


        btnLogin.setOnClickListener(this);



    }

    private void goMainScreen() {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void loguearUsuario() {

        final String email = TextEmail.getText().toString().trim();
        String password = TextPass.getText().toString().trim();


        //cajas no vacias
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un correo", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Ingresando...");
        progressDialog.show();

        //Logear usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);

                            Toast.makeText(Login.this,"Bienvenido "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(),Inicio.class );

                            startActivity(intencion);

                        }else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Login.this, "El usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "El usuario o la contraseña no son correctos ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view){
        loguearUsuario();

    }

}




