package com.example.hugolandimsantos.recyclerexemple.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hugolandimsantos.recyclerexemple.R;
import com.example.hugolandimsantos.recyclerexemple.model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo.landim.santos on 08/10/2015.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> implements Serializable {

    private List<Book> books;
    private GridAdapterListener listener;
    private List<Integer> selecionados;
    private static int SELECIONADO = 1;


    public GridAdapter(List<Book> books, GridAdapterListener listener) {
        this.books = books;
        this.listener = listener;

        selecionados = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        GridViewHolder gridViewHolder = new GridViewHolder(view);
        if (viewType == SELECIONADO) {
            gridViewHolder.getImage_check().setVisibility(View.VISIBLE);
        }
        return gridViewHolder;
    }


    @Override
    public int getItemViewType(int position) {
        if (selecionados.contains(position)) {
            return SELECIONADO;
        }
        return 2;
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        Book book = books.get(position);
        holder.image.setImageResource(book.getImage());
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
    }

    public interface GridAdapterListener {

        public void onBookCliecked(View view);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView image;
        private ImageView image_check;
        private TextView title;
        private TextView author;

        private GridViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            author = (TextView) view.findViewById(R.id.author);
            image_check = (ImageView) view.findViewById(R.id.image_check);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                Integer position = Integer.valueOf(getLayoutPosition());
                if (!selecionados.remove(position)) {
                    selecionados.add(position);
                }
                listener.onBookCliecked(view);
                notifyItemChanged(position);
            }
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public ImageView getImage_check() {
            return image_check;
        }

        public void setImage_check(ImageView image_check) {
            this.image_check = image_check;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getAuthor() {
            return author;
        }

        public void setAuthor(TextView author) {
            this.author = author;
        }
    }


}