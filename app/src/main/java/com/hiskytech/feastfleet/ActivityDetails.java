package com.hiskytech.feastfleet;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.hiskytech.feastfleet.databinding.ActivityDetailsBinding;

public class ActivityDetails extends AppCompatActivity {
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String imageUrl = getIntent().getStringExtra("imageUrl");

        binding.tvFoodName.setText(getIntent().getStringExtra("name"));
        binding.tvDescription.setText(getIntent().getStringExtra("description"));

        Glide.with(this).load(imageUrl).into(binding.imageViewDetail);

        binding.btnCart.setOnClickListener(v -> {
            Toast.makeText(ActivityDetails.this, "Added to cart", Toast.LENGTH_SHORT).show();
        });
    }
}
