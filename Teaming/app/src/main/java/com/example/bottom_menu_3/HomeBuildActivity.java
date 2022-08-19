package com.example.bottom_menu_3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

public class HomeBuildActivity extends AppCompatActivity {

    private Calendar date_calendar;
    private Calendar time_calendar;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private String select_year = "";
    private String select_month = "";
    private String select_day = "";
    private String select_hour = "";
    private String select_minute = "";
    private String select_number = "";
    private String label_tag = "科研组队";
    private String detail = "";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_build);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        final EditText editText_number = findViewById(R.id.edt_build_team_edit_text_number);
        final EditText editText_information = findViewById(R.id.edt_build_team_edit_text_information);

        final Button button_label_research = findViewById(R.id.btn_build_join_team_label_research);
        final Button button_label_transportation = findViewById(R.id.btn_build_join_team_label_transportation);
        final Button button_label_sport = findViewById(R.id.btn_build_join_team_label_sport);
        final Button button_label_E_sport = findViewById(R.id.btn_build_join_team_label_E_sport);
        final Button button_date_picker = findViewById(R.id.btn_date_picker);
        final Button button_time_picker = findViewById(R.id.btn_time_picker);

        //创建按钮：数据存入数据库接口
        final Button button_confirm_build = findViewById(R.id.btn_confirm_build);

        button_label_research.setActivated(true);

        //类型选择按钮点击事件
        button_label_research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label_research.setActivated(true);
                label_tag = "科研组队";
            }
        });
        button_label_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label_transportation.setActivated(true);
                label_tag = "出行组队";
            }
        });
        button_label_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label_sport.setActivated(true);
                label_tag = "运动组队";
            }
        });
        button_label_E_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label_E_sport.setActivated(true);
                label_tag = "游戏组队";
            }
        });

        //时间选择按钮
        button_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date_calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(HomeBuildActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        select_year = String.valueOf(year);
                        select_month = String.valueOf(month+1);
                        select_day = String.valueOf(dayOfMonth);
                        String string = year + "/" + select_month + "/" + dayOfMonth;
                        button_date_picker.setText(string);
                    }
                }, date_calendar.get(Calendar.YEAR),date_calendar.get(Calendar.MONTH),date_calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        button_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_calendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(HomeBuildActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        select_hour = String.valueOf(hourOfDay);
                        select_minute = String.valueOf(minute);
                        if (minute < 10)
                        {
                            select_minute = "0" + minute;
                        }
                        String string = select_hour + ":" + select_minute;
                        button_time_picker.setText(string);
                    }
                }, time_calendar.get(Calendar.HOUR_OF_DAY), time_calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
        });

        //创建按钮点击事件
        button_confirm_build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_number = editText_number.getText().toString();
                if (select_year.isEmpty()){
                    if (select_hour.isEmpty()){
                        if (select_number.isEmpty()){
                                Toast.makeText(HomeBuildActivity.this, "请选择日期、时间、人数", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(HomeBuildActivity.this, "请选择日期、时间", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if (select_number.isEmpty()){
                            Toast.makeText(HomeBuildActivity.this, "请选择日期、人数", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(HomeBuildActivity.this, "请选择日期", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    if (select_hour.isEmpty()){
                        if (select_number.isEmpty()){
                            Toast.makeText(HomeBuildActivity.this, "请选择时间、人数", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(HomeBuildActivity.this, "请选择时间", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if (select_number.isEmpty()){
                            Toast.makeText(HomeBuildActivity.this, "请选择人数", Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent = new Intent(HomeBuildActivity.this, AccountBuildTeamActivity.class);
                            intent.setAction("Build");
                            String date = select_year + "." + select_month + "." +select_day;
                            String time = select_hour + ":" + select_minute;
                            if (!editText_information.getText().toString().equals("")){
                                detail = editText_information.getText().toString();
                            }
                            intent.putExtra("build_label", label_tag);
                            intent.putExtra("build_number", select_number);
                            intent.putExtra("build_date", date);
                            intent.putExtra("build_time", time);
                            intent.putExtra("build_text", detail);
                            startActivity(intent);
                            Toast.makeText(HomeBuildActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });
    }

    private void resetLabel(){
        final Button button_label_research = findViewById(R.id.btn_build_join_team_label_research);
        final Button button_label_transportation = findViewById(R.id.btn_build_join_team_label_transportation);
        final Button button_label_sport = findViewById(R.id.btn_build_join_team_label_sport);
        final Button button_label_E_sport = findViewById(R.id.btn_build_join_team_label_E_sport);

        button_label_research.setActivated(false);
        button_label_transportation.setActivated(false);
        button_label_sport.setActivated(false);
        button_label_E_sport.setActivated(false);
    }
}