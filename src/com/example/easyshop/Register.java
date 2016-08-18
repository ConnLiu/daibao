package com.example.easyshop;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

import com.example.entity.MyUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener{
	Button btn_login;
	Button btn_register;
	EditText et_account;
	EditText et_nickname;
	EditText et_passwd;
	EditText et_cfm_passwd;
	EditText et_numberID;
	EditText et_realname;
	Spinner spn_school;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		Bmob.initialize(this, "79f7c1d79f0db04370bf7b20720440db");
		et_account=(EditText)findViewById(R.id.account);
		et_nickname=(EditText)findViewById(R.id.nickname);
		et_passwd=(EditText)findViewById(R.id.input_passwd);
		et_cfm_passwd = (EditText)findViewById(R.id.confirm_passwd);
		et_nickname=(EditText)findViewById(R.id.nickname);
		et_numberID=(EditText)findViewById(R.id.numberID);
		et_realname=(EditText)findViewById(R.id.realname);
		spn_school=(Spinner)findViewById(R.id.input_school);
		btn_login=(Button)findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_register=(Button)findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch(v.getId()){
		case R.id.btn_login:
			intent.setClass(Register.this, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.btn_register:
			if(!check()){
				return ;
			}
			register();
			intent.setClass(Register.this, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
	}
	public boolean check(){
		if(et_account.getText().toString().equals("")
				||et_passwd.getText().toString().equals("")
				||et_cfm_passwd.getText().toString().equals("")
				||et_nickname.getText().toString().equals("")
				||et_numberID.getText().toString().equals("")
				||et_realname.getText().toString().equals("")){
			toast("信息未填写完全！");
			return false;
		}else if(!et_passwd.getText().toString().equals(et_cfm_passwd.getText().toString())){
			toast("两次输入的密码不一致！");
			return false;
		}
		return true;
	}
	public void register(){
		MyUser user = new MyUser();
		user.setUsername(et_account.getText().toString());
		user.setPassword(et_passwd.getText().toString());
		user.setNick(et_nickname.getText().toString());
		user.setSchool(spn_school.getSelectedItem().toString());
		user.setRealname(et_realname.getText().toString());
		user.setNumberID(et_numberID.getText().toString());
		user.signUp(new SaveListener<MyUser>() {
			public void done(MyUser s, cn.bmob.v3.exception.BmobException e) {
				if(e==null){
					toast("注册成功！用户："+s.getNick());
				}else{
					toast("注册失败！");
				}
			};
		});
	}
	void toast(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}
