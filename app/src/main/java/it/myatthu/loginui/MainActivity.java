package it.myatthu.loginui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.myatthu.loginui.Database.MyDatabase;
import it.myatthu.loginui.Database.User;
import it.myatthu.loginui.Database.UserDao;

public class MainActivity extends AppCompatActivity {
    private UserDao userDao;
    private Map<String, String> dbUserMap = new HashMap<>();
    private ImageView imageView;
    private TextView textView, tvForgotPwd;
    private Button btSignUp, btSignIn;
    private EditText etEmail, etPassword;
    int count = 0;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initUI();
        initData();
        initListeners();
    }

    private void initData() {
        userDao = MyDatabase.getInstance(this).getUserDao();
        List<User> userList = userDao.getAllUsers();
        Log.d("myat", String.valueOf(userList.size()));
        for (User user : userList) {
            String mail = user.getEmail();
            String pwd = user.getPassword();
            Log.d("myat", mail + pwd);
            dbUserMap.put(mail, pwd);
        }
        Log.d("myat", dbUserMap.toString());

    }

    private void initListeners() {
        btSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

        btSignIn.setOnClickListener(v -> {
            String inputEmail = etEmail.getText().toString();
            String inputPwd = etPassword.getText().toString();
            User user = new User(inputEmail, inputPwd);

            Log.d("myat", dbUserMap.entrySet().toString());

            if (dbUserMap.containsKey(inputEmail) && dbUserMap.containsValue(inputPwd)) {
                Log.d("myat", user.getEmail() + user.getPassword());
                Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("myat", user.getEmail() + user.getPassword());
                Toast.makeText(this, "Wrong email and password!", Toast.LENGTH_SHORT).show();
            }

        });

        tvForgotPwd.setOnClickListener(v -> {

        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        tvForgotPwd = findViewById(R.id.tv_main_forgot_pwd);
        btSignIn = findViewById(R.id.bt_main_sign_in);
        btSignUp = findViewById(R.id.bt_main_sign_up);
        etEmail = findViewById(R.id.et_main_email);
        etPassword = findViewById(R.id.et_main_password);


        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.good_night_img);
                    textView.setText("Night");
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.good_morning_img);
                    textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeLeft() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.good_night_img);
                    textView.setText("Night");
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.good_morning_img);
                    textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeBottom() {
            }

        });
    }


}