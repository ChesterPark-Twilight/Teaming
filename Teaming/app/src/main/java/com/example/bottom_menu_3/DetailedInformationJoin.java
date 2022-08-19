package com.example.bottom_menu_3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class DetailedInformationJoin extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_information_join);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        final TextView label = findViewById(R.id.txt_detail_label_join);
        final TextView number = findViewById(R.id.txt_detail_number_join);
        final TextView date = findViewById(R.id.txt_detail_date_join);
        final TextView time = findViewById(R.id.txt_detail_time_join);
        final TextView text = findViewById(R.id.txt_detail_information_join);
        label.setText(intent.getStringExtra("label"));
        number.setText(intent.getStringExtra("number"));
        date.setText(intent.getStringExtra("date"));
        time.setText(intent.getStringExtra("time"));
        text.setText(intent.getStringExtra("text"));
        final int tag = intent.getIntExtra("tag", 0);

        Button button = findViewById(R.id.btn_cancel_join);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_intent = new Intent(DetailedInformationJoin.this, AccountJoinTeamActivity.class);
                to_intent.setAction("join_delete");
                to_intent.putExtra("tag_delete", tag);
                startActivity(to_intent);
                finish();
            }
        });
    }
}