package com.example.database1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText name,place;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        place=findViewById(R.id.place);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView3);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShowData.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 // RealTime Database:-
                String data1=name.getText().toString();
                String data2=place.getText().toString();
                if ( TextUtils.isEmpty(data1)|| TextUtils.isEmpty(data2)) {
                    Toast.makeText(MainActivity.this, "Fill the all Field.! ", Toast.LENGTH_SHORT).show();
                }

                else {
                    Map<String, Object> v = new HashMap<String, Object>();
                    v.put("Name: ", data1);
                    v.put("Place: ", data2);
                    FirebaseDatabase.getInstance().getReference().child("yes_thats_me").push().setValue(v);
                    Toast.makeText(MainActivity.this, "Data added... ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}