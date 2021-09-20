package com.example.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.databinding.ItemLayoutBinding;

import java.util.Vector;

public class ShoppingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Vector<String> list;
    private ItemLayoutBinding binding;

    public ShoppingListAdapter(Vector<String> list_) {
        list = list_;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_layout, parent, false);

        RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String item = list.get(position);

        TextView textView = ((ViewHolder)holder).getTextView();
        textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
