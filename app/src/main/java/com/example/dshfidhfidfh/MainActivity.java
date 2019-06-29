package com.example.dshfidhfidfh;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add, update, delete;
    EditText search, change;

    Context context;

    RecyclerView recyclerView;
    ArrayList<Users> userlist;
    DatabaseReference databaseReference;
    UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        search = findViewById(R.id.search);
        change = findViewById(R.id.change);
        recyclerView = findViewById(R.id.userRecyclerview);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        userlist = new ArrayList<Users>();

        DatabaseReference newDatabase = databaseReference.child("Users");
        newDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(userAdapter != null){
                    userAdapter.clearCollection();
                }

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Users users = dataSnapshot1.getValue(Users.class);
                    userlist.add(0,users);
                }
                //Log.d("DATA",""+dataSnapshot1);

                userAdapter = new UserAdapter(userlist, context);
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

                recyclerView.setAdapter(userAdapter);
                userAdapter.setCreateComplaints(userlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FormAcrivirt.class);
                startActivity(intent);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String searchDataValue = search.getText().toString();
                final String changeDataValue = change.getText().toString();

                final DatabaseReference updatedb = FirebaseDatabase.getInstance().getReference().child("Users");
                updatedb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //DIrect operations
                        updatedb.child(searchDataValue).child("hobby").setValue(changeDataValue);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String deleteDataValue=search.getText().toString();

                final DatabaseReference deletedb=FirebaseDatabase.getInstance().getReference().child("Users");
                deletedb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        deletedb.child(deleteDataValue).removeValue();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
