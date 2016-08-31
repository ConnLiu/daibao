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
		//�ṩ�������ַ�ʽ���г�ʼ��������
        //��һ��Ĭ�ϳ�ʼ��
        Bmob.initialize(this, "79f7c1d79f0db04370bf7b20720440db");
        //�ڶ�����v3.4.7�汾��ʼ,����BmobConfig,������������ʱʱ�䡢�ļ���Ƭ�ϴ�ʱÿƬ�Ĵ�С���ļ��Ĺ���ʱ��(��λΪ��)��
        //BmobConfig config =new BmobConfig.Builder(this)
        ////����appkey
        //.setApplicationId("Your Application ID")
        ////����ʱʱ�䣨��λΪ�룩��Ĭ��15s
        //.setConnectTimeout(30)
        ////�ļ���Ƭ�ϴ�ʱÿƬ�Ĵ�С����λ�ֽڣ���Ĭ��512*1024
        //.setUploadBlockSize(1024*1024)
        ////�ļ��Ĺ���ʱ��(��λΪ��)��Ĭ��1800s
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
			toast("������Ϣ��������");
			return;
		}
		user.login(new SaveListener<MyUser>() {
			public void done(MyUser s, cn.bmob.v3.exception.BmobException e) {
				if(e==null){
					toast("��½�ɹ����û���"+s.getNick());
					//ͨ��BmobUser user = BmobUser.getCurrentUser()��ȡ��¼�ɹ���ı����û���Ϣ
		            //������Զ����û�����MyUser����ͨ��MyUser user = BmobUser.getCurrentUser(MyUser.class)��ȡ�Զ����û���Ϣ
					UserSingleton.setInstance(BmobUser.getCurrentUser(MyUser.class));
					
					Intent intent = new Intent();
					intent.setClass(Login.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtra("from",	"login");
					startActivity(intent);
				}else{
					toast("��½ʧ�ܣ�");
				}
			};
		});
	}
	void toast(String s){
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}
}
