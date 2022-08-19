package com.example.bottom_menu_3;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommunicationActivity extends AppCompatActivity {

    private List<CommunicationItem> listData;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        InitializeData();
        final ListView listView = findViewById(R.id.communication_list_view);
        final CommunicationAdapter communicationAdapter = new CommunicationAdapter();
        listView.setAdapter(communicationAdapter);

        final Button button_send = findViewById(R.id.btn_send);

        final EditText editText_send = findViewById(R.id.edt_send_information);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText_send.getText().toString();
                if (!string.equals("")){
                    CommunicationItem communicationItem = new CommunicationItem(1,string,R.drawable.ic_baseline_right_smile_face);
                    listData.add(communicationItem);
                    communicationAdapter.notifyDataSetChanged();
                    listView.setSelection(listData.size());
                    editText_send.setText("");
                }
            }
        });
    }

    private void InitializeData(){
        listData = new ArrayList<>();
        listData.add(new CommunicationItem(0,"你好鸭",R.drawable.ic_baseline_left_smile_face));
        listData.add(new CommunicationItem(0,"请问您这边是几个人呢",R.drawable.ic_baseline_left_smile_face));
        listData.add(new CommunicationItem(0,"?",R.drawable.ic_baseline_left_smile_face));
    }

    class CommunicationAdapter extends BaseAdapter {

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

            CommunicationHolder communicationHolder;

            if (convertView == null){
                convertView = View.inflate(CommunicationActivity.this, R.layout.communication_item, null);
                communicationHolder = new CommunicationHolder();
                communicationHolder.left_textView = convertView.findViewById(R.id.communication_item_text_receive);
                communicationHolder.right_textView = convertView.findViewById(R.id.communication_item_text_send);
                communicationHolder.left_imageView = convertView.findViewById(R.id.communication_item_image_receive);
                communicationHolder.right_imageView = convertView.findViewById(R.id.communication_item_image_send);
                convertView.setTag(communicationHolder);
            }else {
                communicationHolder = (CommunicationHolder) convertView.getTag();
            }

            CommunicationItem communicationItem = listData.get(position);
            if (communicationItem.getType() == CommunicationItem.TYPE_RECEIVE){
                communicationHolder.left_textView.setVisibility(View.VISIBLE);
                communicationHolder.left_imageView.setVisibility(View.VISIBLE);
                communicationHolder.right_textView.setVisibility(View.GONE);
                communicationHolder.right_imageView.setVisibility(View.GONE);
                communicationHolder.left_textView.setText(communicationItem.getString());
                communicationHolder.left_imageView.setImageResource(communicationItem.getInteger());
            }
            if (communicationItem.getType() == CommunicationItem.TYPE_SEND){
                communicationHolder.left_textView.setVisibility(View.GONE);
                communicationHolder.left_imageView.setVisibility(View.GONE);
                communicationHolder.right_textView.setVisibility(View.VISIBLE);
                communicationHolder.right_imageView.setVisibility(View.VISIBLE);
                communicationHolder.right_textView.setText(communicationItem.getString());
                communicationHolder.right_imageView.setImageResource(communicationItem.getInteger());
            }
            return convertView;
        }
    }

    static class CommunicationHolder{
        TextView left_textView;
        TextView right_textView;
        ImageView left_imageView;
        ImageView right_imageView;
    }
}