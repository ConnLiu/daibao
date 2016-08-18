package com.example.easyshop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Me extends Activity implements OnClickListener{

	private ImageView IvMe_rb,IvMe_edit,IvMe_head;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me);
        FileInputStream localstream = null;
		try {
			localstream = openFileInput("temphead.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        final Bitmap bm = BitmapFactory.decodeStream(localstream);

		IvMe_rb =(ImageView) findViewById(R.id.IvMe_rb);
		IvMe_edit =(ImageView) findViewById(R.id.IvMe_edit);
		IvMe_head =(ImageView) findViewById(R.id.IvMe_head);
		
		IvMe_rb.setOnClickListener(this);
		IvMe_edit.setOnClickListener(this);
		IvMe_head.setOnClickListener(this);

		if(bm != null){
			IvMe_head.setImageBitmap(bm);
		}
		else IvMe_head.setImageResource(R.drawable.tip_selected);
	}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	Intent intent = new Intent();
	switch(v.getId()){
	case R.id.IvMe_rb:
		finish();
		break;
	case R.id.IvMe_edit:
		intent.setClass(Me.this, Myinfo.class);
		break;
	case R.id.IvMe_head:
		break;
	}
}

}
