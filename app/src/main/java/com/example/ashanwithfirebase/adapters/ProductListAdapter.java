package com.example.ashanwithfirebase.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashanwithfirebase.AdapterItemClickListener;
import com.example.ashanwithfirebase.R;
import com.example.ashanwithfirebase.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> products = new ArrayList<>();
    private AdapterItemClickListener listener;

    public ProductListAdapter(AdapterItemClickListener listener){
        this.listener = listener;
    }

    public void setItems(Collection<Product> tweets) {
        products.addAll(tweets);
        notifyDataSetChanged();
    }

    public void clearItems() {
        products.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_product,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bindToView(products.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(products.get(position) );
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView inStock;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            inStock = itemView.findViewById(R.id.inCount);
            imageView = itemView.findViewById(R.id.imageProduct);
            title = itemView.findViewById(R.id.productTitle);
            cardView = itemView.findViewById(R.id.cardView);
        }

        public void bindToView(Product product){
            title.setText(product.getTitle());
            inStock.setText("В наличии: " + product.getInStock() + " " + product.getUnit());

            if(product.getImageUrl() != null) {
                Picasso.get()
                        .load(product.getImageUrl())
                        .resize(180, 130)
                        .into(imageView);
            }
        }

    }
}
