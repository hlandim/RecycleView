package com.example.hugolandimsantos.recyclerexemple.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.hugolandimsantos.recyclerexemple.R;
import com.example.hugolandimsantos.recyclerexemple.adapter.GridAdapter;
import com.example.hugolandimsantos.recyclerexemple.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GridAdapter.GridAdapterListener {

    private RecyclerView grid;
    private GridAdapter gridAdapter;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        grid = (RecyclerView) findViewById(R.id.grid);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//        Se quiser fazer com uma lista
//        RecyclerView.LayoutManager gridLayoutManager = new LinearLayoutManager(this);
        grid.setLayoutManager(gridLayoutManager);

        books = createBooks();
        gridAdapter = (GridAdapter) getLastCustomNonConfigurationInstance();
        if (gridAdapter == null) {
            gridAdapter = new GridAdapter(books, this);
        }

        grid.setAdapter(gridAdapter);


    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return gridAdapter;
    }

    private List<Book> createBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            Book book = new Book("Book " + i, "Author " + i, R.drawable.koala);
            books.add(book);
        }
        return books;
    }

    @Override
    public void onBookCliecked(View view) {
        int position = grid.getChildAdapterPosition(view);
        Book book = books.get(position);
        Toast.makeText(this, book.getTitle(), Toast.LENGTH_SHORT).show();

    }


}
