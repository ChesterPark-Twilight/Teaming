package com.example.bottom_menu_3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageFragment extends Fragment implements AdapterView.OnItemClickListener{

    private List<MessageItem> listData;

//    public static MessageFragment newInstance() { return new MessageFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        MessageViewModel mViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);

        InitializeData();
        ListView listView = Objects.requireNonNull(getActivity()).findViewById(R.id.message_list_view);
        MessageDataAdapter messageDataAdapter = new MessageDataAdapter();
        listView.setAdapter(messageDataAdapter);
        listView.setOnItemClickListener(this);
    }

    private void InitializeData(){
        listData = new ArrayList<>();
        listData.add(new MessageItem("Central South University的消息"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CommunicationActivity.class);
        startActivity(intent);
    }

    class MessageDataAdapter extends BaseAdapter{

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

            MessageDataHolder messageDataHolder;

            if (convertView == null){
                convertView = View.inflate(getActivity(), R.layout.message_item, null);
                messageDataHolder = new MessageDataHolder();
                messageDataHolder.textView = convertView.findViewById(R.id.message_item_textView);
                convertView.setTag(messageDataHolder);
            }else {
                messageDataHolder = (MessageDataHolder) convertView.getTag();
            }

            MessageItem messageItem = listData.get(position);
            messageDataHolder.textView.setText(messageItem.getString());

            return convertView;
        }

        class MessageDataHolder{
            TextView textView;
        }
    }
}