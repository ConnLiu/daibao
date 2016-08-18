package com.example.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customview.GenderDialog.Builder;
import com.example.easyshop.R;

public class DomitoryDialog extends Dialog{

	public DomitoryDialog(Context context ) {
        super(context );
    }

      public DomitoryDialog(Context context, int theme) {
           super(context,theme);
       }
   
      public static class Builder{
    	  private Context context;
    	  private View contentview;
    	  private DialogInterface.OnClickListener YiClickListener;
    	  private DialogInterface.OnClickListener BoClickListener;
    	  private DialogInterface.OnClickListener HuiClickListener;
    	  private DialogInterface.OnClickListener SuiClickListener;
    	  private DialogInterface.OnClickListener HeClickListener;
    	  
    	  public Builder(Context context){
    		  this.context = context;
    	  }
    	  
    	  public Builder setContentView(View v){
    		  this.contentview = v;
    		  return this;
    	  }
    	  
    	  public Builder setYiButton(DialogInterface.OnClickListener clickListener){
    		  this.YiClickListener = clickListener;
    		  return this;
    	  }

    	  public Builder setBoButton(DialogInterface.OnClickListener clickListener){
    		  this.BoClickListener = clickListener;
    		  return this;
    	  }
    	  
    	  public Builder setHuiButton(DialogInterface.OnClickListener clickListener){
    		  this.HuiClickListener = clickListener;
    		  return this;
    	  }
    	  
    	  public Builder setHeButton(DialogInterface.OnClickListener clickListener){
    		  this.HeClickListener = clickListener;
    		  return this;
    	  }
    	  
    	  public Builder setSuiButton(DialogInterface.OnClickListener clickListener){
    		  this.SuiClickListener = clickListener;
    		  return this;
    	  }
    	  
    	  public GenderDialog create() {  
              LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
              final GenderDialog dialog = new GenderDialog(context,R.style.Dialog); 
              View layout = inflater.inflate(R.layout.domitorydialog, null);
              dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                  if (YiClickListener != null) {
                      ((TextView) layout.findViewById(R.id.TvDom_yi))
                      .setOnClickListener(new View.OnClickListener() {  
                                  public void onClick(View v) {  
                                      YiClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);  
                                  }  
                              });  
                  }  
                  if (BoClickListener != null) {  
                      ((TextView) layout.findViewById(R.id.TvDom_bo))  
                              .setOnClickListener(new View.OnClickListener(){  
                                  public void onClick(View v) {  
                                      BoClickListener.onClick(dialog,  
                                              DialogInterface.BUTTON_NEGATIVE);  
                                  }  
                             });  
                  }  
                  if (HeClickListener != null) {  
                      ((TextView) layout.findViewById(R.id.TvDom_he))  
                              .setOnClickListener(new View.OnClickListener(){  
                                  public void onClick(View v) {  
                                	  HeClickListener.onClick(dialog,  
                                              DialogInterface.BUTTON1);  
                                  }  
                             });  
                  }  
                  if (HuiClickListener != null) {  
                      ((TextView) layout.findViewById(R.id.TvDom_hui))  
                              .setOnClickListener(new View.OnClickListener(){  
                                  public void onClick(View v) {  
                                	  HuiClickListener.onClick(dialog,  
                                              DialogInterface.BUTTON2);  
                                  }  
                             });  
                  }  
                  if (SuiClickListener != null) {  
                      ((TextView) layout.findViewById(R.id.TvDom_sui))  
                              .setOnClickListener(new View.OnClickListener(){  
                                  public void onClick(View v) {  
                                	  SuiClickListener.onClick(dialog,  
                                              DialogInterface.BUTTON3);  
                                  }  
                             });  
                  }  
                  
            if (contentview != null){
            	((LinearLayout) layout.findViewById(R.id.LlDom))
                          .removeAllViews();
                  ((LinearLayout) layout.findViewById(R.id.LlDom))  
                          .addView(contentview, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));  
            }
              dialog.setContentView(layout);  
              return dialog;  
          }  
      }
}