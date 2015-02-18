package com.cognetive.app;

import com.example.uahsuashs.R;
import com.example.uahsuashs.ReactionTimeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Startpage extends Fragment {
	Button button;
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {


	        View android = inflater.inflate(R.layout.android_frag, container, false);
	        button=(Button)android.findViewById(R.id.round_button);
	        button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	Intent intent = new Intent(getActivity(), ReactionTimeActivity.class);
                	startActivity(intent);
                }
            });

            //ImageView img= (ImageView) android.findViewById(R.id.imageView3);
            //img.setImageResource(R.drawable.brain);
            //img.getLayoutParams().width = 290;


        return android;
}}
