package com.example.jose.ejercicio1programacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    public String nombre;
    public String sexo;
    private TextView textInput ;
    public Button botonContinuar;
    public EditText edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        botonContinuar = (Button) findViewById(R.id.button);
        edad = (EditText) findViewById(R.id.edad);
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edad.getText().toString().isEmpty()) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Introdueix la teua edad", Toast.LENGTH_SHORT);
                    toast1.show();
                } else {
                    int edadI = Integer.parseInt(edad.getText().toString());
                    if (edadI <= 0 || edadI > 100) {
                        Toast toast2 = Toast.makeText(getApplicationContext(), "La edad te que ser desde 1 hasta 100 anys", Toast.LENGTH_SHORT);
                        toast2.show();

                    } else {
                        getIntent().putExtra("edad",edad.getText().toString());
                        setResult((RESULT_OK), getIntent());
                        finish();
                    }
                }
            }
        });

        textInput = (TextView) findViewById(R.id.textInput);
        Bundle bundle1 = getIntent().getExtras();
        sexo = bundle1.getCharSequence("sexo").toString();
        nombre = bundle1.getCharSequence("nom").toString();

        if (sexo.equals("hombre")){
            textInput.setText("Hola Sr. "+nombre+", indica'ns la teua edad. ");
        }else{
            textInput.setText("Hola Sra. "+nombre+", indica'ns la teua edad. ");
        }
    }
}
