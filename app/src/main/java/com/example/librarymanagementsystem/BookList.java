package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;

import com.example.librarymanagementsystem.Adapter.BookListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookList extends AppCompatActivity {
    ArrayList<BookModel> bookModelArrayList;
    BookListAdapter bookListAdapter;
    SearchView searchView;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        searchView = findViewById(R.id.search);
        rv = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(BookList.this);
        rv.setLayoutManager(layoutManager);
        bookModelArrayList = new ArrayList<>();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!TextUtils.isEmpty(s.trim())){
                    searchGroup(s);
                }else{
                    loadbooks();                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!TextUtils.isEmpty(s.trim())){
                    searchGroup(s);
                }else{
                    loadbooks();                }
                return false;
            }
        });

        loadbooks();
    }
    private void searchGroup(String s) {
        bookModelArrayList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookModelArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    BookModel model = ds.getValue(BookModel.class);
                    if (ds.child("Title").toString().toLowerCase().contains(s.toLowerCase()) || ds.child("Author").toString().toLowerCase().contains(s.toLowerCase()) ||
                            ds.child("BookId").toString().toLowerCase().contains(s.toLowerCase())) {
                        bookModelArrayList.add(model);

                        //sort book title using bubble sort
                        int size = bookModelArrayList.size();
                        for (int i = 0; i < size - 1; i++) {
                            for (int j = 0; j < size - i - 1; j++) {
                                if (bookModelArrayList.get(j).getTitle().compareTo(bookModelArrayList.get(j + 1).getTitle()) > 0) {
                                    BookModel temp = bookModelArrayList.get(j);
                                    bookModelArrayList.set(j, bookModelArrayList.get(j + 1));
                                    bookModelArrayList.set(j + 1, temp);
                                }
                            }
                        }
                    }
                }
                bookListAdapter = new BookListAdapter(BookList.this, bookModelArrayList);
                rv.setAdapter(bookListAdapter);
                bookListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void loadbooks() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookModelArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    BookModel model = ds.getValue(BookModel.class);
                    bookModelArrayList.add(model);

                    int size = bookModelArrayList.size();
                    for (int i = 0; i < size - 1; i++) {
                        for (int j = 0; j < size - i - 1; j++) {
                            if (bookModelArrayList.get(j).getTitle().compareTo(bookModelArrayList.get(j + 1).getTitle()) > 0) {
                                BookModel temp = bookModelArrayList.get(j);
                                bookModelArrayList.set(j, bookModelArrayList.get(j + 1));
                                bookModelArrayList.set(j + 1, temp);
                            }
                        }
                    }
                }
                bookListAdapter = new BookListAdapter(BookList.this, bookModelArrayList);
                rv.setAdapter(bookListAdapter);
                bookListAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}