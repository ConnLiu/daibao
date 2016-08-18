package com.example.customview;

import com.example.easyshop.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditDialog extends Dialog {
	   
		public EditDialog(Context context ) {
	        super(context );
	    }

	      public EditDialog(Context context, int theme) {
	           super(context,theme);
	       }
	   
	      public static class Builder{
	    	  private Context context;
	    	  private View contentview;
	    	  private String edittext;
	    	  private EditText edit;
	    	  private String positiveButtonText;
	    	  private String negativeButtonText;
	    	  private DialogInterface.OnClickListener positiveButtonClickListener;
	    	  private DialogInterface.OnClickListener negativeButtonClickListener;
	    	  
	    	  public Builder(Context context){
	    		  this.context = context;
	    	  }
	    	  /*
	    	  public Builder setMessage(String message){
	    		  this.message = message;
	    		  return this;
	    	  }
	    	  
	    	  public Builder setMessage(int message){
	    		  this.message =(String) context.getText(message);
	    		  return this;
	    	  }

	    	  public Builder setTitle(String title){
	    		  this.title = title;
	    		  return this;
	    	  }*/
	    	  
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
	    	  public String getEdit(){
	    		  return edittext;
	    	  }
	    	  
	    	  public EditDialog create() {  
	              LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	              // instantiate the dialog with the custom Theme  
	              final EditDialog dialog = new EditDialog(context,R.style.Dialog); 
	              View layout = inflater.inflate(R.layout.edit_dialog, null);
	              dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	              edit = (EditText)layout.findViewById(R.id.edit);
	              // set the confirm button 
	              if (positiveButtonText != null) {  
	                  ((Button) layout.findViewById(R.id.BtDialog_ok)).setText(positiveButtonText);  
	                  if (positiveButtonClickListener != null) {
	                      ((Button) layout.findViewById(R.id.BtDialog_ok)).setOnClickListener(new View.OnClickListener() {  
	                                  public void onClick(View v) {
	                                	  edittext = edit.getText().toString();
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
	            	((LinearLayout) layout.findViewById(R.id.LlEdit_content))
	                          .removeAllViews();
	                  ((LinearLayout) layout.findViewById(R.id.LlEdit_content))  
	                          .addView(contentview, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));  
	            }
	              dialog.setContentView(layout);  
	              return dialog;  
	          }  
	      }
}
