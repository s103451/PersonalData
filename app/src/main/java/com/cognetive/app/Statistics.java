package com.cognetive.app;
import com.example.uahsuashs.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Statistics extends Fragment {
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View ios = inflater.inflate(R.layout.ios_frag, container, false);
	        ((TextView)ios.findViewById(R.id.textView)).setText("PROGRESS");
         ios.setBackgroundColor(Color.parseColor("#d3d3d3"));
         ImageView img= (ImageView) ios.findViewById(R.id.imageView);
        // img.setImageResource(R.drawable.test);

         ImageView img2= (ImageView) ios.findViewById(R.id.imageView2);
         //img2.setImageResource(R.drawable.menu3);

	      return ios;
}}
