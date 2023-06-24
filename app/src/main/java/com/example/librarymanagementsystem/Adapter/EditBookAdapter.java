package com.example.librarymanagementsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.BookModel;
import com.example.librarymanagementsystem.EditUi;
import com.example.librarymanagementsystem.R;

import java.util.ArrayList;

public class EditBookAdapter  extends RecyclerView.Adapter<EditBookAdapter.HolderAdapter> {


    Context context;
    ArrayList<BookModel> bookModels;

    public EditBookAdapter(Context context , ArrayList<BookModel> bookModels){
        this.context= context;
        this.bookModels=bookModels;
    }
    @NonNull
    @Override
    public EditBookAdapter.HolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list, parent, false);


        return new EditBookAdapter.HolderAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EditBookAdapter.HolderAdapter holder, int position) {
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
                Intent intent = new Intent( context, EditUi.class);
                intent.putExtra("Title", title);
                intent.putExtra("Author", author);
                intent.putExtra("BookId", bookid);
                context.startActivity(intent);
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
