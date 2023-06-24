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
import java.util.Objects;

public class AddBook extends AppCompatActivity {
    EditText title, author, bookid;
    Button add;
    Boolean loop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        bookid = findViewById( R.id.bookid);
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Title, Author, BookId;
                Title = title.getText().toString();
                Author = author.getText().toString();
                BookId = bookid.getText().toString();

                DatabaseReference database = FirebaseDatabase.getInstance().getReference("Books");
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (!snapshot.child(BookId).exists() ) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("Title", Title);
                                hashMap.put("Author", Author);
                                hashMap.put("BookId", BookId);
                                database.child(BookId).setValue(hashMap);
                                Toast.makeText(AddBook.this, "Added", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(AddBook.this, MainActivity.class);
                                startActivity(intent);
                                AddBook.this.finish();
                            }else{
                                Toast.makeText(AddBook.this, "Book Id Exists, please use different Id", Toast.LENGTH_SHORT).show();
                            }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AddBook.this.finish();
    }
}