package com.example.bottom_menu_3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class HomeJoinActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<ListItem> listData;
    private List<ListItem> tempListData;

    private Calendar date_calendar;
    private Calendar time_calendar;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private String select_year = "";
    private String select_month = "";
    private String select_day = "";
    private String select_hour = "";
    private String select_minute = "";
    private String select_number = "人";

    private int label_number = 0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_join);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        InitializeData();
        final ListView listView = findViewById(R.id.join_team_list_view);
        final JoinListDataAdapter joinListDataAdapter = new JoinListDataAdapter();
        listView.setAdapter(joinListDataAdapter);
        listView.setOnItemClickListener(this);

        final EditText editText_number = findViewById(R.id.edt_join_team_edit_text_number);

        final Button button_label = findViewById(R.id.btn_build_join_team_label);
        final Button button_label_research = findViewById(R.id.btn_build_join_team_label_research);
        final Button button_label_transportation = findViewById(R.id.btn_build_join_team_label_transportation);
        final Button button_label_sport = findViewById(R.id.btn_build_join_team_label_sport);
        final Button button_label_E_sport = findViewById(R.id.btn_build_join_team_label_E_sport);
        final Button button_date_picker = findViewById(R.id.btn_date_picker);
        final Button button_time_picker = findViewById(R.id.btn_time_picker);

        editText_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_number = editText_number.getText().toString() + "人";
                updateData();
            }
        });

        button_label.setActivated(true);

        button_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label.setActivated(true);
                label_number = 0;
                updateData();
            }
        });
        button_label_research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label_research.setActivated(true);
                label_number = 1;
                updateData();
            }
        });
        button_label_transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label_transportation.setActivated(true);
                label_number = 2;
                updateData();
            }
        });
        button_label_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label_sport.setActivated(true);
                label_number = 3;
                updateData();
            }
        });
        button_label_E_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetLabel();
                button_label_E_sport.setActivated(true);
                label_number = 4;
                updateData();
            }
        });

        button_date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date_calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(HomeJoinActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        select_year = String.valueOf(year);
                        select_month = String.valueOf(month+1);
                        select_day = String.valueOf(dayOfMonth);
                        String string = year + "/" + select_month + "/" + dayOfMonth;
                        button_date_picker.setText(string);

                        updateData();

                    }
                }, date_calendar.get(Calendar.YEAR),date_calendar.get(Calendar.MONTH),date_calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        button_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_calendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(HomeJoinActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

                        updateData();

                    }
                }, time_calendar.get(Calendar.HOUR_OF_DAY), time_calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
        });
    }

    private void resetLabel(){
        final Button button_label = findViewById(R.id.btn_build_join_team_label);
        final Button button_label_research = findViewById(R.id.btn_build_join_team_label_research);
        final Button button_label_transportation = findViewById(R.id.btn_build_join_team_label_transportation);
        final Button button_label_sport = findViewById(R.id.btn_build_join_team_label_sport);
        final Button button_label_E_sport = findViewById(R.id.btn_build_join_team_label_E_sport);

        button_label.setActivated(false);
        button_label_research.setActivated(false);
        button_label_transportation.setActivated(false);
        button_label_sport.setActivated(false);
        button_label_E_sport.setActivated(false);
    }

    private void traverseData(String string){
        if (!string.equals("")){
            for (int i=0 ; i<listData.size(); i++){
                ListItem listItem = listData.get(i);
                if (listItem.getLabel().equals(string)){
                    tempListData.add(new ListItem(listItem.getLabel(),listItem.getNumber(),listItem.getDate(),listItem.getTime(),listItem.getText(),listItem.getIcon()));
                }
            }
        }
        if (!select_year.equals("")){
            List<ListItem> data = tempListData;
            tempListData = new ArrayList<>();
            for (int i=0 ; i<data.size(); i++){
                ListItem listItem = data.get(i);
                String string_date = select_year + "." + select_month + "." + select_day;
                if (string_date.equals(listItem.getDate())){
                    tempListData.add(new ListItem(listItem.getLabel(),listItem.getNumber(),listItem.getDate(),listItem.getTime(),listItem.getText(),listItem.getIcon()));
                }
            }
        }
        if (!select_hour.equals("")){
            List<ListItem> data = tempListData;
            tempListData = new ArrayList<>();
            for (int i=0; i<data.size(); i++){
                ListItem listItem = data.get(i);
                String string_time = select_hour + ":" + select_minute;
                if (string_time.equals(listItem.getTime())){
                    tempListData.add(new ListItem(listItem.getLabel(),listItem.getNumber(),listItem.getDate(),listItem.getTime(),listItem.getText(),listItem.getIcon()));
                }
            }
        }
        if (!select_number.equals("人")){
            List<ListItem> data = tempListData;
            tempListData = new ArrayList<>();
            for (int i=0; i<data.size(); i++){
                ListItem listItem = data.get(i);
                if (select_number.equals(listItem.getNumber())){
                    tempListData.add(new ListItem(listItem.getLabel(),listItem.getNumber(),listItem.getDate(),listItem.getTime(),listItem.getText(),listItem.getIcon()));
                }
            }
        }
    }

    private void updateData(){
        final ListView listView = findViewById(R.id.join_team_list_view);
        final JoinListDataAdapter joinListDataAdapter = new JoinListDataAdapter();
        tempListData = new ArrayList<>();
        switch (label_number){
            case 0:
                InitializeData();
                traverseData("");
                listView.setAdapter(joinListDataAdapter);
                break;
            case 1:
                traverseData("科研组队");
                listView.setAdapter(joinListDataAdapter);
                break;
            case 2:
                traverseData("出行组队");
                listView.setAdapter(joinListDataAdapter);
                break;
            case 3:
                traverseData("运动组队");
                listView.setAdapter(joinListDataAdapter);
                break;
            case 4:
                traverseData("游戏组队");
                listView.setAdapter(joinListDataAdapter);
                break;
        }
    }

    //数据库数据初始化接口
    private void InitializeData() {
        listData = new ArrayList<>();
        listData.add(new ListItem("运动组队", "1人","2021.3.2","10:00","详细信息", R.drawable.ic_baseline_sport));
        listData.add(new ListItem("科研组队", "2人","2021.3.3","10:00","详细信息", R.drawable.ic_baseline_research));
        listData.add(new ListItem("出行组队", "1人","2021.3.2","10:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("运动组队", "1人","2021.3.3","12:00","详细信息", R.drawable.ic_baseline_sport));
        listData.add(new ListItem("游戏组队", "1人","2021.3.3","12:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("科研组队", "1人","2021.3.4","12:00","详细信息", R.drawable.ic_baseline_research));
        listData.add(new ListItem("出行组队", "1人","2021.3.5","12:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("游戏组队", "1人","2021.3.2","13:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("游戏组队", "1人","2021.3.5","12:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("科研组队", "1人","2021.3.2","12:00","详细信息", R.drawable.ic_baseline_research));
        listData.add(new ListItem("出行组队", "1人","2021.3.9","12:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("运动组队", "1人","2021.3.8","12:00","详细信息", R.drawable.ic_baseline_sport));
        listData.add(new ListItem("游戏组队", "1人","2021.3.8","13:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("游戏组队", "1人","2021.3.8","13:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("科研组队", "1人","2021.3.7","13:00","详细信息", R.drawable.ic_baseline_research));
        listData.add(new ListItem("出行组队", "1人","2021.3.7","13:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("出行组队", "1人","2021.3.6","13:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("出行组队", "1人","2021.3.6","13:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("出行组队", "1人","2021.3.4","13:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("运动组队", "1人","2021.3.3","12:00","详细信息", R.drawable.ic_baseline_sport));
        listData.add(new ListItem("运动组队", "3人","2021.3.3","12:00","详细信息", R.drawable.ic_baseline_sport));
        listData.add(new ListItem("运动组队", "3人","2021.3.3","12:00","详细信息", R.drawable.ic_baseline_sport));
        listData.add(new ListItem("运动组队", "3人","2021.3.3","12:00","详细信息", R.drawable.ic_baseline_sport));
        listData.add(new ListItem("运动组队", "3人","2021.3.3","12:00","详细信息", R.drawable.ic_baseline_sport));
        tempListData = listData;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView_label = view.findViewById(R.id.list_view_item_label);
        TextView textView_number = view.findViewById(R.id.list_view_item_number);
        TextView textView_date = view.findViewById(R.id.list_view_item_date);
        TextView textView_time = view.findViewById(R.id.list_view_item_time);
        TextView textView_text = view.findViewById(R.id.list_view_item_text);

        Intent intent = new Intent(HomeJoinActivity.this, DetailedInformation.class);
        intent.putExtra("label", textView_label.getText().toString());
        intent.putExtra("number", textView_number.getText().toString());
        intent.putExtra("date", textView_date.getText().toString());
        intent.putExtra("time", textView_time.getText().toString());
        intent.putExtra("text", textView_text.getText().toString());
        startActivity(intent);
    }

    class JoinListDataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return tempListData.size();
        }

        @Override
        public Object getItem(int position) {
            return tempListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            JoinListDataHolder joinListDataHolder;

            if(convertView == null){
                convertView = View.inflate(HomeJoinActivity.this, R.layout.listview_item, null);
                joinListDataHolder = new JoinListDataHolder();
                joinListDataHolder.label = convertView.findViewById(R.id.list_view_item_label);
                joinListDataHolder.number = convertView.findViewById(R.id.list_view_item_number);
                joinListDataHolder.date = convertView.findViewById(R.id.list_view_item_date);
                joinListDataHolder.time = convertView.findViewById(R.id.list_view_item_time);
                joinListDataHolder.text = convertView.findViewById(R.id.list_view_item_text);
                joinListDataHolder.icon = convertView.findViewById(R.id.list_view_item_image);
                convertView.setTag(joinListDataHolder);
            }else{
                joinListDataHolder = (JoinListDataHolder) convertView.getTag();
            }

            ListItem listItem = tempListData.get(position);
            joinListDataHolder.label.setText(listItem.getLabel());
            joinListDataHolder.number.setText(listItem.getNumber());
            joinListDataHolder.date.setText(listItem.getDate());
            joinListDataHolder.time.setText(listItem.getTime());
            joinListDataHolder.text.setText(listItem.getText());
            joinListDataHolder.icon.setImageResource(listItem.getIcon());

            return convertView;
        }
    }

    static class JoinListDataHolder{
        TextView label;
        TextView number;
        TextView date;
        TextView time;
        TextView text;
        ImageView icon;
    }
}