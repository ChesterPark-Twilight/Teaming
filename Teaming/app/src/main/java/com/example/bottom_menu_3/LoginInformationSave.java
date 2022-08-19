package com.example.bottom_menu_3;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginInformationSave {

    public static boolean saveInformation(Context context, String account, String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(account, account);
        editor.putString(account+password, password);
        editor.apply();
        return true;
    }
}
