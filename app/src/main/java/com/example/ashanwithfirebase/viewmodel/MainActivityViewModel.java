package com.example.ashanwithfirebase.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ashanwithfirebase.model.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Product>> productList = new MutableLiveData<List<Product>>();


    private FirebaseFirestore db;


    public MainActivityViewModel(){
        db = FirebaseFirestore.getInstance();

        db.collection("product")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>(){
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Product> products = queryDocumentSnapshots.toObjects(Product.class);
                        productList.setValue(products);
                    }
                });
    }

    public MutableLiveData<List<Product>> getProductList() {
        return productList;
    }
}
