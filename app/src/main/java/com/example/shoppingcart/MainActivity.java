package com.example.shoppingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.shoppingcart.databinding.ActivityMainBinding;
import com.example.shoppingcart.databinding.AddItemBinding;
import com.example.shoppingcart.databinding.ItemLayoutBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AddItemBinding addItemBinding;
    private RecyclerView recyclerView;
    private ShoppingListAdapter adapter;

    Vector<String> list = new Vector<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        addItemBinding = AddItemBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        recyclerView = binding.recyclerView;
        adapter = new ShoppingListAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    String deletedItem = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();

            deletedItem = list.get(position);
            list.remove(position);
            adapter.notifyItemRemoved(position);

            Snackbar.make(recyclerView, deletedItem, Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            list.add(position, deletedItem);
                            adapter.notifyItemInserted(position);
                        }
                    }).show();
        }
    };

    public void displayAddItem(View view) {
        View v = addItemBinding.getRoot();
        setContentView(v);
    }

    public void addItemToList(View view) {
        list.add(addItemBinding.item.getText().toString());
        adapter.notifyItemInserted(list.size()-1);
        View v = binding.getRoot();
        setContentView(v);
        logList();//display list of items currently in shopping list
    }

    public void logList() {
        for(String item : list) {
            System.out.println(item);
        }
    }
}