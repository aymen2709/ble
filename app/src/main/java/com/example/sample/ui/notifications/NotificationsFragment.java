package com.example.sample.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.sample.Main_ble;
import com.example.sample.R;
import com.example.sample.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_notifications,container, false);
        configureImageButton();
        return v;
    }
    private void configureImageButton() {
        // TODO Auto-generated method stub
        ImageButton btn = (ImageButton) v.findViewById(R.id.imageButton);
        Intent intent=getActivity().getIntent();
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), Main_ble.class);
                startActivity(intentLoadNewActivity);
//                Toast.makeText(getActivity(), "You Clicked the button!", Toast.LENGTH_LONG).show();
            }
        });}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }}
