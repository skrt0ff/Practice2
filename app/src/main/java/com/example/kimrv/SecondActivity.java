package com.example.kimrv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kimrv.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void BackToMainActivity(View view) {
        Intent intent = new Intent();
        //Передаем содержимое текстового поля:
        intent.putExtra("Name", binding.editText1.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}