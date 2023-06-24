package com.example.librarymanagementsystem.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.BookModel;
import com.example.librarymanagementsystem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeleteBookListAdapter extends RecyclerView.Adapter<DeleteBookListAdapter.HolderAdapter> {
    Context context;
    ArrayList<BookModel> bookModels;

    public DeleteBookListAdapter(Context context , ArrayList<BookModel> bookModels){
        this.context= context;
        this.bookModels=bookModels;
    }


    @NonNull
    @Override
    public DeleteBookListAdapter.HolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list, parent, false);



        return new DeleteBookListAdapter.HolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteBookListAdapter.HolderAdapter holder, int position) {
        BookModel bookModel = bookModels.get(position);
        String title = bookModel.getTitle();
        String author = bookModel.getAuthor();
        String bookid = bookModel.getBookId();

        holder.title.setText("Title: "+title);
        holder.author.setText("Author: "+author);
        holder.bookId.setText("Book Id: "+bookid);

        holder.Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose Action");
                builder.setMessage("Are you sure you want to delete this book?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Books").child(bookid);
                        databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return bookModels.size();
    }

    public class HolderAdapter extends RecyclerView.ViewHolder {
        TextView title, author, bookId;
        CardView Card;

        public HolderAdapter(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            bookId = itemView.findViewById(R.id.bookid);
            Card = itemView.findViewById(R.id.layout);

        }
    }
}
