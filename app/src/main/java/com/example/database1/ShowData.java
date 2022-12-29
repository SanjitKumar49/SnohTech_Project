package com.example.database1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {
    private ListView listView;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdata);
        listView=findViewById(R.id.list);

        button2=findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> a=new ArrayList<>();
             ArrayAdapter ad=new ArrayAdapter(ShowData.this, R.layout.showalldata ,a);
              listView.setAdapter(ad);

              FirebaseDatabase.getInstance().getReference().child("yes_thats_me").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                           a.clear();
                            for (DataSnapshot f: snapshot.getChildren())
                            {
                                a.add(f.getValue().toString());
                            }
                            ad.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });
    }
}