package com.example.bottom_menu_3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountBuildTeamActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private List<MessageItem> listData = new ArrayList<>();

    private String label;
    private String number;
    private String date;
    private String time;
    private String text;
    private String tag;

    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences_destroy;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_build_team);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        sharedPreferences_destroy = AccountBuildTeamActivity.this.getSharedPreferences("tag", MODE_PRIVATE);
        sharedPreferences = AccountBuildTeamActivity.this.getSharedPreferences("build_data", MODE_PRIVATE);

        tag = sharedPreferences_destroy.getString("tag_now_build", "");
        if ("".equals(tag)){
            tag = "0";
        }

        listView = findViewById(R.id.build_information_list_view);
        InitializeData();
        BuildDataAdapter buildDataAdapter = new BuildDataAdapter();
        listView.setAdapter(buildDataAdapter);
        listView.setOnItemClickListener(this);

        Intent intent = getIntent();
        if (Objects.equals(intent.getAction(), "build_delete")){
            int i = intent.getIntExtra("tag_delete", 0);
            listData.remove(i);
            listView.setAdapter(new BuildDataAdapter());
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPreferences_destroy.edit();
        editor.putString("tag_now_build", String.valueOf(listData.size()));
        editor.apply();
    }

    private boolean judge(){
        Intent intent = getIntent();
        if ("Build".equals(intent.getAction())){
            label = intent.getStringExtra("build_label");
            number = intent.getStringExtra("build_number");
            date = intent.getStringExtra("build_date");
            time = intent.getStringExtra("build_time");
            text = intent.getStringExtra("build_text");
            return true;
        }else {
            return false;
        }
    }

    private void InitializeData(){
        for (int i=0; i<Integer.parseInt(tag); i++){
            String label_temp = sharedPreferences.getString(i+"label", "");
            String date_temp = sharedPreferences.getString(i+"date", "");
            listData.add(new MessageItem(label_temp + "  " + date_temp));
        }
        if (judge()){
            AccountBuildTeamInformationSave.saveInformation(AccountBuildTeamActivity.this, label, number, date, time, text, tag);
            updateData();
        }
    }

    private void updateData(){
        listData.add(new MessageItem(label + "  " + date));
        listView.setAdapter(new BuildDataAdapter());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(AccountBuildTeamActivity.this, DetailedInformationBuild.class);
        for (int i=0; i<Integer.parseInt(tag); i++){
            if (position == i){
                String label_temp = sharedPreferences.getString(i+"label", "");
                String number_temp = sharedPreferences.getString(i+"number", "");
                String date_temp = sharedPreferences.getString(i+"date", "");
                String time_temp = sharedPreferences.getString(i+"time", "");
                String text_temp = sharedPreferences.getString(i+"text", "");
                intent.putExtra("label", label_temp);
                intent.putExtra("number", number_temp);
                intent.putExtra("date", date_temp);
                intent.putExtra("time", time_temp);
                intent.putExtra("text", text_temp);
                intent.putExtra("tag", i);
                startActivity(intent);
                finish();
            }
        }
    }

    class BuildDataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            BuildDataHolder buildDataHolder;

            if (convertView == null){
                convertView = View.inflate(AccountBuildTeamActivity.this, R.layout.message_item, null);
                buildDataHolder = new BuildDataHolder();
                buildDataHolder.textView = convertView.findViewById(R.id.message_item_textView);
                convertView.setTag(buildDataHolder);
            }else {
                buildDataHolder = (BuildDataHolder) convertView.getTag();
            }

            MessageItem messageItem = listData.get(position);
            buildDataHolder.textView.setText(messageItem.getString());

            return convertView;
        }

        class BuildDataHolder{
            TextView textView;
        }
    }
}