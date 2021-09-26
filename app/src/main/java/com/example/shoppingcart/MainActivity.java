package com.example.shoppingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import com.example.shoppingcart.databinding.ActivityMainBinding;
import com.example.shoppingcart.databinding.AddItemBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Vector;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

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

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.design_default_color_error))
                    .addActionIcon(R.drawable.ic_baseline_remove_shopping_cart_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
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