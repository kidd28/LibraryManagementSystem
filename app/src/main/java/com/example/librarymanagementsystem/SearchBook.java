package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchBook extends AppCompatActivity {
EditText edt;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        edt = findViewById(R.id.editText);
        btn = findViewById(R.id.search_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBook(edt.getText().toString());
            }
        });
    }

    private void searchBook(String s) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("Title").getValue().toString().toLowerCase().contains(s.toLowerCase()) || ds.child("Author").getValue().toString().toLowerCase().contains(s.toLowerCase()) ||
                            ds.child("BookId").getValue().toString().toLowerCase().contains(s.toLowerCase())) {

                        View view = getLayoutInflater().inflate( R.layout.dialog, null);
                        TextView title = view.findViewById(R.id.title);
                        TextView author = view.findViewById(R.id.author);
                        TextView bookid = view.findViewById(R.id.bookId);

                        AlertDialog.Builder dialog = new AlertDialog.Builder( SearchBook.this );

                        title.setText("Title : "+ ds.child("Title").getValue().toString());
                        author.setText("Author : " +ds.child("Author").getValue().toString());
                        bookid.setText("Book Id : "+ds.child("BookId").getValue().toString());
                        dialog.setTitle("Book Details");
                        dialog.setView(view).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        dialog.show();


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}