package com.example.ashanwithfirebase.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.ashanwithfirebase.R;
import com.example.ashanwithfirebase.databinding.ActivityProductBinding;
import com.example.ashanwithfirebase.model.Product;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
public class ProductActivity extends AppCompatActivity {

    private ActivityProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductBinding.inflate(getLayoutInflater());

        String json = getIntent().getStringExtra("Product");
        Gson gson = new Gson();

        Product product  = gson.fromJson(json, Product.class);

        setUI(product);
        setContentView(binding.getRoot());

    }

    private void setUI(Product product){

        Picasso.get()
                .load(product.getImageUrl())
                .resize(180, 130)
                .into(binding.imageView);

        StringBuilder sb = new StringBuilder();

        binding.textViewCategory.setText(binding.textViewCategory.getText() + product.getCategory());
        sb.append(product.getInStock());
        binding.textViewInStock.setText(binding.textViewInStock.getText() + " " + sb.toString() + product.getUnit());
        binding.textViewPrice.setText(product.getPrice() + " руб.");
        binding.textViewTitle.setText(binding.textViewTitle.getText() + product.getTitle());
        binding.textViewDescription.setText(product.getDescription());

        if(product.isInSchowcase()){
            binding.textViewInShowcaseValue.setTextColor(ContextCompat.getColor(this, R.color.green_color));
            binding.textViewInShowcaseValue.setText( " Да");
        } else {
            binding.textViewInShowcaseValue.setTextColor(ContextCompat.getColor(this, R.color.main));
            binding.textViewInShowcaseValue.setText(" Нет");
        }

    }
}