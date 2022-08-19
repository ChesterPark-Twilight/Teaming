package com.example.bottom_menu_3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<ListItem> listData;

//    public static HomeFragment newInstance() {
//        return new HomeFragment();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        HomeViewModel mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        //定义按钮事件
        Button btn_build_team = Objects.requireNonNull(getActivity()).findViewById(R.id.btn_build_team);
        Button btn_join_team = getActivity().findViewById(R.id.btn_join_team);

        btn_build_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeBuildActivity.class);
                startActivity(intent);
            }
        });

        btn_join_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeJoinActivity.class);
                startActivity(intent);
            }
        });

        //定义List_view
        InitializeData();
        ListView listView = getActivity().findViewById(R.id.home_list_view);
        HomeListDataAdapter homeListDataAdapter =new  HomeListDataAdapter();
        listView.setAdapter(homeListDataAdapter);
        listView.setOnItemClickListener(this);
    }

    //数据库数据初始化接口
    private void InitializeData(){
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
        listData.add(new ListItem("出行组队", "1人","2021.3.9","18:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("运动组队", "1人","2021.3.8","18:00","详细信息", R.drawable.ic_baseline_sport));
        listData.add(new ListItem("游戏组队", "1人","2021.3.8","18:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("游戏组队", "1人","2021.3.8","13:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("科研组队", "1人","2021.3.7","13:00","详细信息", R.drawable.ic_baseline_research));
        listData.add(new ListItem("出行组队", "1人","2021.3.7","13:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("出行组队", "1人","2021.3.6","13:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("游戏组队", "1人","2021.3.2","13:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("游戏组队", "1人","2021.3.5","20:00","详细信息", R.drawable.ic_baseline_esprot));
        listData.add(new ListItem("科研组队", "1人","2021.3.2","19:00","详细信息", R.drawable.ic_baseline_research));
        listData.add(new ListItem("出行组队", "1人","2021.3.6","13:00","详细信息", R.drawable.ic_baseline_transportation));
        listData.add(new ListItem("出行组队", "1人","2021.3.4","13:00","详细信息", R.drawable.ic_baseline_transportation));
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView_label = view.findViewById(R.id.list_view_item_label);
        TextView textView_number = view.findViewById(R.id.list_view_item_number);
        TextView textView_date = view.findViewById(R.id.list_view_item_date);
        TextView textView_time = view.findViewById(R.id.list_view_item_time);
        TextView textView_text = view.findViewById(R.id.list_view_item_text);

        Intent intent = new Intent(getActivity(), DetailedInformation.class);
        intent.putExtra("label", textView_label.getText().toString());
        intent.putExtra("number", textView_number.getText().toString());
        intent.putExtra("date", textView_date.getText().toString());
        intent.putExtra("time", textView_time.getText().toString());
        intent.putExtra("text", textView_text.getText().toString());
        startActivity(intent);
    }

    //设置适配器
    class HomeListDataAdapter extends BaseAdapter {

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

            HomeListDataHolder homeListDataHolder;

            if(convertView == null){
                convertView = View.inflate(getActivity(), R.layout.listview_item, null);
                homeListDataHolder = new HomeListDataHolder();
                homeListDataHolder.label = convertView.findViewById(R.id.list_view_item_label);
                homeListDataHolder.number = convertView.findViewById(R.id.list_view_item_number);
                homeListDataHolder.date = convertView.findViewById(R.id.list_view_item_date);
                homeListDataHolder.time = convertView.findViewById(R.id.list_view_item_time);
                homeListDataHolder.text = convertView.findViewById(R.id.list_view_item_text);
                homeListDataHolder.icon = convertView.findViewById(R.id.list_view_item_image);
                convertView.setTag(homeListDataHolder);
            }else{
                homeListDataHolder = (HomeListDataHolder) convertView.getTag();
            }

            ListItem listItem = listData.get(position);
            homeListDataHolder.label.setText(listItem.getLabel());
            homeListDataHolder.number.setText(listItem.getNumber());
            homeListDataHolder.date.setText(listItem.getDate());
            homeListDataHolder.time.setText(listItem.getTime());
            homeListDataHolder.text.setText(listItem.getText());
            homeListDataHolder.icon.setImageResource(listItem.getIcon());

            return convertView;
        }

        class HomeListDataHolder{
            TextView label;
            TextView number;
            TextView date;
            TextView time;
            TextView text;
            ImageView icon;
        }
    }
}