package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addbook, deletebook,searchbook, viewbook, editbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addbook = findViewById(R.id.button);
        deletebook = findViewById(R.id.button2);
        searchbook = findViewById(R.id.button3);
        viewbook = findViewById(R.id.button4);
        editbook = findViewById(R.id.button5);

        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBook.class);
                startActivity(intent);
            }
        });
        deletebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DeleteBook.class);
                startActivity(intent);

            }
        });
        searchbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchBook.class);
                startActivity(intent);
            }
        });
        viewbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookList.class);
                startActivity(intent);
            }
        });
        editbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditBook.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.this.finish();
    }
}