package com.example.mydrawingview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrawingview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final DrawingView drawingView = binding.drawingView;
        Button changeSizeButton = binding.sizeUpButton;

        changeSizeButton.setOnClickListener(v -> {
            drawingView.changeCircleSize();
        });
    }
}