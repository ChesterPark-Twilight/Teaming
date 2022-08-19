package com.example.bottom_menu_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedInformation extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_information);

        final Intent intent = getIntent();
        final TextView label = findViewById(R.id.txt_detail_label);
        final TextView number = findViewById(R.id.txt_detail_number);
        final TextView date = findViewById(R.id.txt_detail_date);
        final TextView time = findViewById(R.id.txt_detail_time);
        final TextView text = findViewById(R.id.txt_detail_information);
        label.setText(intent.getStringExtra("label"));
        number.setText(intent.getStringExtra("number"));
        date.setText(intent.getStringExtra("date"));
        time.setText(intent.getStringExtra("time"));
        text.setText(intent.getStringExtra("text"));

        Button button = findViewById(R.id.btn_confirm_join);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_intent = new Intent(DetailedInformation.this, AccountJoinTeamActivity.class);
                to_intent.setAction("Join");
                to_intent.putExtra("join_label", label.getText().toString());
                to_intent.putExtra("join_number", number.getText().toString());
                to_intent.putExtra("join_date", date.getText().toString());
                to_intent.putExtra("join_time", time.getText().toString());
                to_intent.putExtra("join_text", text.getText().toString());
                startActivity(to_intent);
                Toast.makeText(DetailedInformation.this, "加入成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}