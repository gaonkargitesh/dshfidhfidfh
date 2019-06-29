package com.example.dshfidhfidfh;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class FormAcrivirt extends AppCompatActivity {

    EditText nameEditText;
    Button submitButton;

    RadioGroup radioGroup;
    RadioButton maleRadio,femaleRadio;


    CheckBox cycle,read,swim;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String genderValue="";
    String hobbyValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_acrivirt);


        nameEditText=findViewById(R.id.name);
        submitButton=findViewById(R.id.submitButton);
        radioGroup=findViewById(R.id.radiogroup);
        maleRadio=findViewById(R.id.male);
        femaleRadio=findViewById(R.id.female);
        cycle=findViewById(R.id.cycle);
        read=findViewById(R.id.read);
        swim=findViewById(R.id.swim);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitClick();
            }
        });

    }

    private void submitClick() {

        final String nameValue=nameEditText.getText().toString();



        if(maleRadio.isChecked()){
            genderValue="Male";
        }
        if(femaleRadio.isChecked()){
            genderValue="Female";
        }

        if(cycle.isChecked()){
            hobbyValue="Cycling";
        }

        if(read.isChecked()){
            hobbyValue="Reading";
        }
        if(swim.isChecked()){
            hobbyValue="Swimming";
        }

        Log.d("hobby",""+hobbyValue);

        //final String finalGenderValue = genderValue;

        databaseReference= FirebaseDatabase.getInstance().getReference();
        final DatabaseReference database =databaseReference.child("Users");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d("hobby",""+hobbyValue);
                Users users=new Users(nameValue,genderValue,hobbyValue);
                database.child(nameValue).setValue(users);

                /*database.child(nameValue).child("Name").setValue(nameValue);
                database.child(nameValue).child("Gender").setValue(genderValue);
                database.child(nameValue).child("Hobby").setValue(hobbyValue);
                */finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
