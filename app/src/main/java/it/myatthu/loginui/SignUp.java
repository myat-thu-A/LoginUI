package it.myatthu.loginui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import it.myatthu.loginui.Database.MyDatabase;
import it.myatthu.loginui.Database.User;
import it.myatthu.loginui.Database.UserDao;
import it.myatthu.loginui.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;

    private MyDatabase myDatabase;
    private UserDao userDao;

    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDatabase();
        initUI();
        initListener();
    }

    private void initDatabase() {
        userDao = MyDatabase.getInstance(this).getUserDao();
    }

    private void initListener() {
        binding.btSignUp.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            String confirmPwd = binding.etComfirmPassword.getText().toString();

            User user = new User(email, password);
            userDao.addUser(user);
            finish();


        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {

        binding.imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                if (count == 0) {
                    binding.imageView.setImageResource(R.drawable.good_night_img);
                    binding.textView.setText("Night");
                    count = 1;
                } else {
                    binding.imageView.setImageResource(R.drawable.good_morning_img);
                    binding.textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeLeft() {
                if (count == 0) {
                    binding.imageView.setImageResource(R.drawable.good_night_img);
                    binding.textView.setText("Night");
                    count = 1;
                } else {
                    binding.imageView.setImageResource(R.drawable.good_morning_img);
                    binding.textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeBottom() {
            }

        });
    }
}