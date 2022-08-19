package com.example.bottom_menu_3;

import android.content.Context;
import android.content.SharedPreferences;

public class AccountJoinTeamInformationSave {

    public static void saveInformation(Context context, String label, String number, String date, String time, String text, String tag){
        SharedPreferences sharedPreferences = context.getSharedPreferences("join_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tag+"label", label);
        editor.putString(tag+"number", number);
        editor.putString(tag+"date", date);
        editor.putString(tag+"time", time);
        editor.putString(tag+"text", text);
        editor.apply();
    }
}
