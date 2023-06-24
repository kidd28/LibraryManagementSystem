package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditUi extends AppCompatActivity {
 EditText title, author, bookid;
 Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ui);
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        bookid = findViewById(R.id.bookId);
        btn = findViewById(R.id.btn);

        String tit = getIntent().getStringExtra("Title");
        String aut = getIntent().getStringExtra("Author");
        String boo = getIntent().getStringExtra("BookId");

        title.setText(tit);
        author.setText(aut);
        bookid.setText(boo);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title, Author, BookId;
                Title = title.getText().toString();
                Author = author.getText().toString();
                BookId = bookid.getText().toString();

                DatabaseReference database = FirebaseDatabase.getInstance().getReference("Books").child(BookId);
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("Title", Title);
                            hashMap.put("Author", Author);
                            hashMap.put("BookId", BookId);
                            database.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(EditUi.this, "Saved", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(EditUi.this, MainActivity.class);
                                    startActivity(intent);
                                    EditUi.this.finish();
                                }
                            });
                        }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });

    }
}