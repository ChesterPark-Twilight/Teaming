package com.example.bottom_menu_3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        final TextView textView_to_register = findViewById(R.id.txt_to_register);
        textView_to_register.setVisibility(View.INVISIBLE);

        final Button button_login = findViewById(R.id.btn_login);
        final Button button_register = findViewById(R.id.btn_register);
        final Button btn_login = findViewById(R.id.login);
        final Button btn_register = findViewById(R.id.register);
        btn_login.setVisibility(View.INVISIBLE);
        btn_register.setVisibility(View.INVISIBLE);

        final EditText editText_account = findViewById(R.id.edt_login_account);
        final EditText editText_password = findViewById(R.id.edt_login_password);
        final EditText editText_register_account = findViewById(R.id.edt_register_account);
        final EditText editText_register_password = findViewById(R.id.edt_register_password);
        final EditText editText_register_password_again = findViewById(R.id.edt_register_password_again);
        editText_account.setVisibility(View.INVISIBLE);
        editText_password.setVisibility(View.INVISIBLE);
        editText_register_account.setVisibility(View.INVISIBLE);
        editText_register_password.setVisibility(View.INVISIBLE);
        editText_register_password_again.setVisibility(View.INVISIBLE);

        final ImageView imageView = findViewById(R.id.img_icon);
        imageView.setImageResource(R.mipmap.ic_launcher);

        textView_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator.ofFloat(imageView, "rotation",360,0).setDuration(500).start();
                editText_account.setVisibility(View.INVISIBLE);
                editText_password.setVisibility(View.INVISIBLE);
                btn_login.setVisibility(View.INVISIBLE);
                textView_to_register.setVisibility(View.INVISIBLE);
                editText_register_account.setVisibility(View.VISIBLE);
                editText_register_password.setVisibility(View.VISIBLE);
                editText_register_password_again.setVisibility(View.VISIBLE);
                btn_register.setVisibility(View.VISIBLE);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                ObjectAnimator.ofFloat(imageView, "rotation",0,360).setDuration(500).start();

                Animator animator_login = ViewAnimationUtils.createCircularReveal(button_login, button_login.getWidth()/2, button_login.getWidth()/2, button_login.getWidth(), 0);
                Animator animator_register = ViewAnimationUtils.createCircularReveal(button_register, button_register.getWidth()/2, button_register.getWidth()/2, button_register.getWidth(),  0);

                animator_login.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        button_login.setVisibility(View.INVISIBLE);
                        button_register.setVisibility(View.INVISIBLE);
                        editText_account.setVisibility(View.VISIBLE);
                        editText_password.setVisibility(View.VISIBLE);
                        btn_login.setVisibility(View.VISIBLE);
                        textView_to_register.setVisibility(View.VISIBLE);
                    }
                });

                animator_login.start();
                animator_register.start();
           }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                ObjectAnimator.ofFloat(imageView, "rotation",360,0).setDuration(500).start();

                Animator animator_login = ViewAnimationUtils.createCircularReveal(button_login, button_login.getWidth()/2, button_login.getWidth()/2, button_login.getWidth(), 0);
                Animator animator_register = ViewAnimationUtils.createCircularReveal(button_register, button_register.getWidth()/2, button_register.getWidth()/2, button_register.getWidth(),  0);

                animator_register.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        button_login.setVisibility(View.INVISIBLE);
                        button_register.setVisibility(View.INVISIBLE);
                        editText_register_account.setVisibility(View.VISIBLE);
                        editText_register_password.setVisibility(View.VISIBLE);
                        editText_register_password_again.setVisibility(View.VISIBLE);
                        btn_register.setVisibility(View.VISIBLE);
                    }
                });

                animator_login.start();
                animator_register.start();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = editText_account.getText().toString().trim();
                String password = editText_password.getText().toString();
                if (!TextUtils.isEmpty(account)){
                    if (!TextUtils.isEmpty(password)){
                        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("data", MODE_PRIVATE);
                        String account_saved = sharedPreferences.getString(account, "");
                        String password_saved = sharedPreferences.getString(account+password, "");
                        if (account.equals(account_saved)){
                            if (password.equals(password_saved)){
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(LoginActivity.this, "输入的密码有误", Toast.LENGTH_SHORT).show();
                                editText_password.setText("");
                            }
                        }else {
                            Toast.makeText(LoginActivity.this, "输入的账号有误", Toast.LENGTH_SHORT).show();
                            editText_account.setText("");
                            editText_password.setText("");
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (!TextUtils.isEmpty(password)){
                        Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                String account = editText_register_account.getText().toString().trim();
                String password = editText_register_password.getText().toString();
                String password_again = editText_register_password_again.getText().toString();
                if (!TextUtils.isEmpty(account) && account.contains(".")){
                    if (!TextUtils.isEmpty(password)){
                        if (!TextUtils.isEmpty(password_again)){
                            if (password.equals(password_again)){
                                boolean saveInformation = LoginInformationSave.saveInformation(LoginActivity.this, account, password);
                                if (saveInformation){
                                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    editText_register_account.setText("");
                                    editText_register_password.setText("");
                                    editText_register_password_again.setText("");
                                    ObjectAnimator.ofFloat(imageView, "rotation",360,0).setDuration(500).start();
                                    editText_register_account.setVisibility(View.INVISIBLE);
                                    editText_register_password.setVisibility(View.INVISIBLE);
                                    editText_register_password_again.setVisibility(View.INVISIBLE);
                                    btn_register.setVisibility(View.INVISIBLE);
                                    editText_account.setVisibility(View.VISIBLE);
                                    editText_password.setVisibility(View.VISIBLE);
                                    btn_login.setVisibility(View.VISIBLE);
                                    textView_to_register.setVisibility(View.VISIBLE);
                                }else {
                                    Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(LoginActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(LoginActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }
                }else {
                        if (TextUtils.isEmpty(account)){
                            Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
                        }else {
                            if (!account.contains("."))
                            {
                                Toast.makeText(LoginActivity.this, "输入的账号不是邮箱", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
                            }
                        }
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator.ofFloat(imageView, "rotation", 0, 360).setDuration(500).start();

                textView_to_register.setVisibility(View.INVISIBLE);
                btn_login.setVisibility(View.INVISIBLE);
                btn_register.setVisibility(View.INVISIBLE);
                button_login.setVisibility(View.VISIBLE);
                button_register.setVisibility(View.VISIBLE);
                editText_account.setVisibility(View.INVISIBLE);
                editText_password.setVisibility(View.INVISIBLE);
                editText_register_account.setVisibility(View.INVISIBLE);
                editText_register_password.setVisibility(View.INVISIBLE);
                editText_register_password_again.setVisibility(View.INVISIBLE);
            }
        });
    }
}