package com.example.customview;


import com.example.easyshop.R;

import android.os.Bundle;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public class ConfirmDialog extends Dialog {
   
	public ConfirmDialog(Context context ) {
        super(context );
    }

      public ConfirmDialog(Context context, int theme) {
           super(context,theme);
       }
   
      public static class Builder{
    	  private Context context;
    	  private String title;
    	  private View contentview;
    	  private String positiveButtonText;
    	  private String negativeButtonText;
    	  private DialogInterface.OnClickListener positiveButtonClickListener;
    	  private DialogInterface.OnClickListener negativeButtonClickListener;
    	  
    	  public Builder(Context context){
    		  this.context = context;
    	  }

    	  public Builder setTitle(String title){
    		  this.title = title;
    		  return this;
    	  }
    	  
    	  public Builder setContentView(View v){
    		  this.contentview = v;
    		  return this;
    	  }
    	  
    	  public Builder setPositiveButton(String positiveButtonText,DialogInterface.OnClickListener clickListener){
    		  this.positiveButtonText = positiveButtonText;
    		  this.positiveButtonClickListener = clickListener;
    		  return this;
    	  }

    	  public Builder setPositiveButton(int positiveButtonText,DialogInterface.OnClickListener clickListener){
    		  this.positiveButtonText = (String) context.getText(positiveButtonText);
    		  this.positiveButtonClickListener = clickListener;
    		  return this;
    	  }

    	  public Builder setNegativeButton(String negativeButtonText,DialogInterface.OnClickListener clickListener){
    		  this.negativeButtonText = negativeButtonText;
    		  this.negativeButtonClickListener = clickListener;
    		  return this;
    	  }

    	  public Builder setNegativeButton(int negativeButtonText,DialogInterface.OnClickListener clickListener){
    		  this.negativeButtonText = (String) context.getText(negativeButtonText);
    		  this.negativeButtonClickListener = clickListener;
    		  return this;
    	  }
    	  
    	  public ConfirmDialog create() {  
              LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
              // instantiate the dialog with the custom Theme  
              final ConfirmDialog dialog = new ConfirmDialog(context,R.style.Dialog); 
              View layout = inflater.inflate(R.layout.confirmdialog, null);
              dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
              // set the dialog title  
              ((TextView) layout.findViewById(R.id.TvDialog_title)).setText(title);
              // set the confirm button 
              if (positiveButtonText != null) {  
                  ((Button) layout.findViewById(R.id.BtDialog_ok)).setText(positiveButtonText);  
                  if (positiveButtonClickListener != null) {
                      ((Button) layout.findViewById(R.id.BtDialog_ok)).setOnClickListener(new View.OnClickListener() {  
                                  public void onClick(View v) {  
                                      positiveButtonClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);  
                                  }  
                              });  
                  }  
              } else {  
                  // if no confirm button just set the visibility to GONE  
                  layout.findViewById(R.id.BtDialog_ok).setVisibility(  
                          View.GONE);  
              }  
              // set the cancel button  
              if (negativeButtonText != null) {  
                  ((Button) layout.findViewById(R.id.BtDialog_cancel))  
                          .setText(negativeButtonText);  
                  if (negativeButtonClickListener != null) {  
                      ((Button) layout.findViewById(R.id.BtDialog_cancel))  
                              .setOnClickListener(new View.OnClickListener(){  
                                  public void onClick(View v) {  
                                      negativeButtonClickListener.onClick(dialog,  
                                              DialogInterface.BUTTON_NEGATIVE);  
                                  }  
                             });  
                  }  
              } else {  
                  // if no confirm button just set the visibility to GONE  
                  layout.findViewById(R.id.BtDialog_cancel).setVisibility(  
                          View.GONE);  
              }  
              // set the content message  
            if (contentview != null){
                  // if no message set  
                  // add the contentView to the dialog body 
            	((LinearLayout) layout.findViewById(R.id.LlConfirmDialog_content))
                          .removeAllViews();
                  ((LinearLayout) layout.findViewById(R.id.LlConfirmDialog_content))  
                          .addView(contentview, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));  
            }
              dialog.setContentView(layout);  
              return dialog;  
          }  
      }
}