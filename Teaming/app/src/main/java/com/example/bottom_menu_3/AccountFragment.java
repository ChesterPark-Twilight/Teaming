package com.example.bottom_menu_3;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class AccountFragment extends Fragment {

//    public static AccountFragment newInstance() {
//        return new AccountFragment();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        AccountViewModel mViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);

        ImageButton btn_account_information = (ImageButton) Objects.requireNonNull(getActivity()).findViewById(R.id.btn_account_information);
        Button btn_account_build_team = (Button) getActivity().findViewById(R.id.btn_account_build_team);
        Button btn_account_join_team = (Button) getActivity().findViewById(R.id.btn_account_join_team);
        Button btn_account_preference = (Button) getActivity().findViewById(R.id.btn_account_preference);

        btn_account_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AccountInformationActivity.class);
                startActivity(intent);
            }
        });

        btn_account_build_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AccountBuildTeamActivity.class);
                startActivity(intent);
            }
        });

        btn_account_join_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AccountJoinTeamActivity.class);
                startActivity(intent);
            }
        });

        btn_account_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AccountPreferenceActivity.class);
                startActivity(intent);
            }
        });
    }
}