package com.example.ashanwithfirebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.ashanwithfirebase.adapters.ProductListAdapter;
import com.example.ashanwithfirebase.databinding.ActivityMainBinding;
import com.example.ashanwithfirebase.model.Product;
import com.example.ashanwithfirebase.view.ProductActivity;
import com.example.ashanwithfirebase.viewmodel.MainActivityViewModel;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterItemClickListener{

    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private ProductListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.getProductList().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.clearItems();
                adapter.setItems(products);
            }
        });

        adapter = new ProductListAdapter(this);


        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.setAdapter(adapter);
    }



    @Override
    public void onItemClicked(Product product) {
        Intent intent = new Intent(this, ProductActivity.class);

        Gson gson = new Gson();
        String gsonObject = gson.toJson(product);
        intent.putExtra("Product", gsonObject);
        startActivity(intent);
    }
}