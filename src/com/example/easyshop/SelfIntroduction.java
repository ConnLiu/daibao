package com.example.easyshop;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SelfIntroduction extends Activity {

	private EditText EtSelf;
	private Button BtSelf_save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.self_introduction);
		
		EtSelf =(EditText) findViewById(R.id.EtSelf);
		BtSelf_save =(Button) findViewById(R.id.BtSelf_save);
		BtSelf_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
	}


}
