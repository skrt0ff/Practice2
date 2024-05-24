package com.example.kimrv;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.kimrv.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MyLogs";
    private ActivityMainBinding binding;


    ActivityResultLauncher<Intent> startSecondActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();
                if(intent != null) {
                    String name = intent.getStringExtra("Name");
                    binding.textView.setText(name);
                    saveText();
                }
            }
            else {
                String textError = "Error!";
                binding.textView.setText(textError);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Toast toast = Toast.makeText(this, "Kim Rodion Valentinovich", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, (int) (width / 2));
        toast.show();
        loadText();
        Intent intent = new Intent(this, SecondActivity.class);
        binding.buttonGoToActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivityForResult.launch(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); // метод родительского класса
        saveText(); //Сохранение текста
        Log.d(TAG, "MainActivity: onDestroy"); // добавляем логи
    }

    SharedPreferences sPref;
    final String keyName = "my_saved_text"; //Ключ
    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        //Добавление сохраняемых данных
        editor.putString(keyName, binding.textView.getText().toString());
        editor.commit();
        Toast.makeText(this, "Текст сохранен", Toast.LENGTH_LONG).show();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        //Извлечение сохраненных данных
        String savedText = sPref.getString(keyName, "");
        binding.textView.setText(savedText);
        Toast.makeText(this, "Текст загружен", Toast.LENGTH_LONG).show();
    }

}