package com.hiskytech.feastfleet;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hiskytech.feastfleet.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirestoreRecyclerAdapter<ModelImage, AdapterImage.ImageViewHolder> adapterHeader;
    private FirestoreRecyclerAdapter<ModelImage, AdapterImage.ImageViewHolder> adapterSection1;
    private FirestoreRecyclerAdapter<ModelImage, AdapterImage.ImageViewHolder> adapterSection2;
    private FirestoreRecyclerAdapter<ModelImage, AdapterImage.ImageViewHolder> adapterSection3;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userName = getIntent().getStringExtra("userName");
        binding.textView5.setText(userName);

        db = FirebaseFirestore.getInstance();

        setupRecyclerView(binding.rvHeader, "headerCollection");
        setupRecyclerView(binding.rvSection1, "section1Collection");
        setupRecyclerView(binding.rvSection2, "section2Collection");
        setupRecyclerView(binding.rvSection3, "section3Collection");
    }

    private void setupRecyclerView(RecyclerView recyclerView, String collection) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirestoreRecyclerOptions<ModelImage> options = new FirestoreRecyclerOptions.Builder<ModelImage>()
                .setQuery(db.collection(collection), ModelImage.class)
                .build();

        AdapterImage adapter = new AdapterImage(options, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        switch (collection) {
            case "headerCollection":
                adapterHeader = adapter;
                break;
            case "section1Collection":
                adapterSection1 = adapter;
                break;
            case "section2Collection":
                adapterSection2 = adapter;
                break;
            case "section3Collection":
                adapterSection3 = adapter;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapterHeader != null) adapterHeader.stopListening();
        if (adapterSection1 != null) adapterSection1.stopListening();
        if (adapterSection2 != null) adapterSection2.stopListening();
        if (adapterSection3 != null) adapterSection3.stopListening();
    }
}
