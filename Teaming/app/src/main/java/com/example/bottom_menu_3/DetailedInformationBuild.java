package com.example.bottom_menu_3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class DetailedInformationBuild extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_information_build);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        final TextView label = findViewById(R.id.txt_detail_label_build);
        final TextView number = findViewById(R.id.txt_detail_number_build);
        final TextView date = findViewById(R.id.txt_detail_date_build);
        final TextView time = findViewById(R.id.txt_detail_time_build);
        final TextView text = findViewById(R.id.txt_detail_information_build);
        label.setText(intent.getStringExtra("label"));
        number.setText(intent.getStringExtra("number"));
        date.setText(intent.getStringExtra("date"));
        time.setText(intent.getStringExtra("time"));
        text.setText(intent.getStringExtra("text"));
        final int tag = intent.getIntExtra("tag", 0);

        Button button = findViewById(R.id.btn_cancel_build);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_intent = new Intent(DetailedInformationBuild.this, AccountBuildTeamActivity.class);
                to_intent.setAction("build_delete");
                to_intent.putExtra("tag_delete", tag);
                startActivity(to_intent);
                finish();
            }
        });
    }
}