package com.example.easyshop;

import com.example.customview.AnswerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.DialogInterface;

public class Help extends Activity implements OnClickListener{

	private TextView TvHelp_qeust1,TvHelp_qeust2,TvHelp_qeust3,TvHelp_qeust4,TvHelp_feedback;
	private ImageView IvHelp_rb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
		TvHelp_qeust1 =(TextView) findViewById(R.id.TvHelp_qeust1);
		TvHelp_qeust2 =(TextView) findViewById(R.id.TvHelp_qeust2);
		TvHelp_qeust3 =(TextView) findViewById(R.id.TvHelp_qeust3);
		TvHelp_qeust4 =(TextView) findViewById(R.id.TvHelp_qeust4);
		TvHelp_feedback =(TextView) findViewById(R.id.TvHelp_feedback);
		IvHelp_rb =(ImageView) findViewById(R.id.IvHelp_rb);
		
		TvHelp_qeust1.setOnClickListener(this);
		TvHelp_qeust2.setOnClickListener(this);
		TvHelp_qeust3.setOnClickListener(this);
		TvHelp_qeust4.setOnClickListener(this);
		TvHelp_feedback.setOnClickListener(this);
		IvHelp_rb.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		AnswerDialog.Builder builder=new AnswerDialog.Builder(Help.this);
		String answer = "遇到这种情况jsfcggggggggggxjygtckvhgcygvkhvycghgh";
		switch(v.getId()){
		case R.id.TvHelp_qeust1:
			answer = "遇到这种情况jsfcggggggggggxjygtckvhgcygvkhvycghgh";
            builder.setPositiveButton(answer,new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            	}
            }); 
            builder.create().show();
            break;
		case R.id.TvHelp_qeust2:
			answer = "遇到这种情况jsfcggggggggggxjygtckvhgcygvkhvycghgh";
            builder.setPositiveButton(answer,new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            	}
            }); 
            builder.create().show();
            break;
		case R.id.TvHelp_qeust3:
			answer = "遇到这种情况jsfcggggggggggxjygtckvhgcygvkhvycghgh";
            builder.setPositiveButton(answer,new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            	}
            }); 
            builder.create().show();
            break;
		case R.id.TvHelp_qeust4:
			answer = "遇到这种情况jsfcggggggggggxjygtckvhgcygvkhvycghgh";
            builder.setPositiveButton(answer,new DialogInterface.OnClickListener(){
            	public void onClick( DialogInterface dialog,int which){
            		dialog.dismiss();
            	}
            }); 
            builder.create().show();
            break;
		case R.id.TvHelp_feedback:
			break;
		case R.id.IvHelp_rb:
			finish();
		}
		
	}

}
