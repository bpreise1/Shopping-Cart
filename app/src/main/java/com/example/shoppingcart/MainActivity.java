package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.shoppingcart.databinding.ActivityMainBinding;
import com.example.shoppingcart.databinding.AddItemBinding;
import com.example.shoppingcart.databinding.ItemLayoutBinding;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AddItemBinding addItemBinding;

    Vector<String> list = new Vector<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        addItemBinding = AddItemBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);
    }

    public void displayAddItem(View view) {
        View v = addItemBinding.getRoot();
        setContentView(v);
    }

    public void addItemToList(View view) {
        list.add(addItemBinding.item.getText().toString());
        View v = binding.getRoot();
        setContentView(v);
        logList();
    }

    public void logList() {
        for(String item : list) {
            System.out.println(item);
        }
    }
}