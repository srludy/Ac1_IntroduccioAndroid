package com.example.jose.ejercicio1programacion;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final int subActivity2 = 2;
    private EditText textNom;
    public Button btn;
    public RadioButton sexoHombre;
    public RadioButton sexoMujer;
    public Button btnReset;
    TextView textoFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textNom = (EditText) findViewById(R.id.textNom);
        btn = (Button) findViewById(R.id.button2);
        btnReset = (Button) findViewById(R.id.button3);
        btnReset.setVisibility(View.GONE);
        sexoHombre = (RadioButton) findViewById(R.id.hombre);
        sexoMujer = (RadioButton) findViewById(R.id.mujer);
        textoFinal = (TextView) findViewById(R.id.textoFinal);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textNom.setEnabled(true);
                sexoHombre.setEnabled(true);
                sexoMujer.setEnabled(true);
                btn.setEnabled(true);
                btnReset.setVisibility(View.GONE);
                textoFinal.setText("");
                textNom.setText("");


            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(textNom.getText().toString().isEmpty()) {

                    Toast toast2 = Toast.makeText(getApplicationContext(), "Rellena el campo de nombre", Toast.LENGTH_SHORT);
                    toast2.show();

                }else{
                        String sexo = sexoSelected();
                        if (sexo == null) {
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Tria el teu sexe", Toast.LENGTH_SHORT);
                            toast1.show();
                        } else {
                            textNom = (EditText) findViewById(R.id.textNom);
                            Intent i = new Intent(v.getContext(), Activity2.class);
                            i.putExtra("sexo", sexo);
                            i.putExtra("nom", textNom.getText());
                            startActivityForResult(i, subActivity2);
                        }
                }


            }
        });
    }

   /* private boolean encuentraNumero() {
        boolean numero = true;
        char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
        String nombre =textNom.getText().toString();
        for (int i = 0 ; i < nombre.length(); i++){

            for(int e = 0 ; e < alfabeto.length ; e++){
                if(nombre.charAt(i)){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "trova", Toast.LENGTH_SHORT);
                    toast1.show();
                }else {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "No trova", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        }

        return numero;
    }
    */
    public String sexoSelected(){
        String sexo = "";

        if(sexoHombre.isChecked()) {
            sexo = "hombre";
        }else{
            if(sexoMujer.isChecked()){
                sexo = "mujer";
            }else{
                sexo = null;
            }

        }
        return sexo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case subActivity2:

                switch (resultCode){
                    case RESULT_OK:
                        textNom.setEnabled(false);
                        sexoHombre.setEnabled(false);
                        sexoMujer.setEnabled(false);
                        btn.setEnabled(false);
                        btnReset.setVisibility(View.VISIBLE);
                        Bundle bundle = data.getExtras();
                        String edadS = bundle.getCharSequence("edad").toString();
                        int edad = Integer.parseInt(edadS);
                        if(edad < 18 && edad > 0){
                            textoFinal.setText(edad+" anys?, encara estas creixquent!");
                        }
                        if(edad > 18 && edad < 25){
                            textoFinal.setText(edad+" anys, ja eres major de edad");
                        }
                        if(edad >= 25 && edad < 35) {
                            textoFinal.setText(edad + " Estas en la flor de la vida");
                        }
                        if(edad >= 35 && edad < 100){

                            textoFinal.setText(edad+" anys?, ai ai ai... hehe :)");
                        }

                    break;

                }

            break;
    }
}}
