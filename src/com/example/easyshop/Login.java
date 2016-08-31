package com.example.easyshop;

import com.example.entity.MyUser;
import com.example.singleton.UserSingleton;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	Button btn_login;
	Button btn_register;
	EditText et_account;
	EditText et_passwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//提供以下两种方式进行初始化操作：
        //第一：默认初始化
        Bmob.initialize(this, "79f7c1d79f0db04370bf7b20720440db");
        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
		et_account=(EditText)findViewById(R.id.input_account);
		et_passwd=(EditText)findViewById(R.id.input_password);
		btn_login=(Button)findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_register=(Button)findViewById(R.id.link_signup);
		btn_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		Log.d("1","id:"+v.getId()+"regist:"+btn_register);
		switch(v.getId()){
		case R.id.btn_login:
			login();
			break;
		case R.id.link_signup:
			intent.setClass(Login.this, Register.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}
	}
	public void login(){
		MyUser user = new MyUser();
		user.setUsername(et_account.getText().toString());
		user.setPassword(et_passwd.getText().toString());
		if(user.getUsername().equals("")||et_passwd.getText().toString().equals("")){
			toast("输入信息不完整！");
			return;
		}
		user.login(new SaveListener<MyUser>() {
			public void done(MyUser s, cn.bmob.v3.exception.BmobException e) {
				if(e==null){
					toast("登陆成功！用户："+s.getNick());
					//通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
		            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
					UserSingleton.setInstance(BmobUser.getCurrentUser(MyUser.class));
					
					Intent intent = new Intent();
					intent.setClass(Login.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtra("from",	"login");
					startActivity(intent);
				}else{
					toast("登陆失败！");
				}
			};
		});
	}
	void toast(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}
