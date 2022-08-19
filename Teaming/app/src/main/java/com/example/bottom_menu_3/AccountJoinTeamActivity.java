package com.example.bottom_menu_3;

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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountJoinTeamActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        setContentView(R.layout.activity_account_join_team);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        sharedPreferences_destroy = AccountJoinTeamActivity.this.getSharedPreferences("tag", MODE_PRIVATE);
        sharedPreferences = AccountJoinTeamActivity.this.getSharedPreferences("join_data", MODE_PRIVATE);

        tag = sharedPreferences_destroy.getString("tag_now", "");
        if ("".equals(tag)){
            tag = "0";
        }

        listView = findViewById(R.id.join_information_list_view);
        InitializeData();
        JoinDataAdapter joinDataAdapter = new JoinDataAdapter();
        listView.setAdapter(joinDataAdapter);
        listView.setOnItemClickListener(this);

        Intent intent = getIntent();
        if (Objects.equals(intent.getAction(), "join_delete")){
            int i = intent.getIntExtra("tag_delete", 0);
            listData.remove(i);
            listView.setAdapter(new JoinDataAdapter());
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPreferences_destroy.edit();
        editor.putString("tag_now", String.valueOf(listData.size()));
        editor.apply();
    }

    private boolean judge(){
        Intent intent = getIntent();
        if ("Join".equals(intent.getAction())){
            label = intent.getStringExtra("join_label");
            number = intent.getStringExtra("join_number");
            date = intent.getStringExtra("join_date");
            time = intent.getStringExtra("join_time");
            text = intent.getStringExtra("join_text");
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
            AccountJoinTeamInformationSave.saveInformation(AccountJoinTeamActivity.this, label, number, date, time, text, tag);
            updateData();
        }
    }

    private void updateData(){
        listData.add(new MessageItem(label + "  " + date));
        listView.setAdapter(new JoinDataAdapter());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(AccountJoinTeamActivity.this, DetailedInformationJoin.class);
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

    class JoinDataAdapter extends BaseAdapter {

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

            JoinDataHolder joinDataHolder;

            if (convertView == null){
                convertView = View.inflate(AccountJoinTeamActivity.this, R.layout.message_item, null);
                joinDataHolder = new JoinDataHolder();
                joinDataHolder.textView = convertView.findViewById(R.id.message_item_textView);
                convertView.setTag(joinDataHolder);
            }else {
                joinDataHolder = (JoinDataHolder) convertView.getTag();
            }

            MessageItem messageItem = listData.get(position);
            joinDataHolder.textView.setText(messageItem.getString());

            return convertView;
        }

        class JoinDataHolder{
            TextView textView;
        }
    }
}