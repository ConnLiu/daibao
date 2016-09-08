package com.example.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.easyshop.R;

public class AddPriceDialog extends Dialog{
	   
		public AddPriceDialog(Context context ) {
	        super(context );
	    }

	      public AddPriceDialog(Context context, int theme) {
	           super(context,theme);
	       }
	   
	      public static class Builder{
	    	  private Context context;
	    	  private View contentview;
	    	  private DialogInterface.OnClickListener positiveButtonClickListener;
	    	  private DialogInterface.OnClickListener negativeButtonClickListener;
	    	  private DialogInterface.OnClickListener ClickListener10;
	    	  private DialogInterface.OnClickListener ClickListener20;
	    	  
	    	  public Builder(Context context){
	    		  this.context = context;
	    	  }
	    	  public Builder setContentView(View v){
	    		  this.contentview = v;
	    		  return this;
	    	  }
	    	  
	    	  public Builder setPositiveButton(DialogInterface.OnClickListener clickListener){
	    		  this.positiveButtonClickListener = clickListener;
	    		  return this;
	    	  }

	    	  public Builder setNegativeButton(DialogInterface.OnClickListener clickListener){
	    		  this.negativeButtonClickListener = clickListener;
	    		  return this;
	    	  }
	    	  
	    	  public Builder setButton10(DialogInterface.OnClickListener clickListener){
	    		  this.ClickListener10 = clickListener;
	    		  return this;
	    	  }

	    	  public Builder setButton20(DialogInterface.OnClickListener clickListener){
	    		  this.ClickListener20 = clickListener;
	    		  return this;
	    	  }
	    	  
	    	  public ConfirmDialog create() {  
	              LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	              // instantiate the dialog with the custom Theme  
	              final ConfirmDialog dialog = new ConfirmDialog(context,R.style.Dialog); 
	              View layout = inflater.inflate(R.layout.addprice_dialog, null);
	              dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	                
	              if (positiveButtonClickListener != null) {
	                      ((TextView) layout.findViewById(R.id.TvAddp_1)).setOnClickListener(new View.OnClickListener() {  
	                                  public void onClick(View v) {  
	                                      positiveButtonClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);  
	                                  }  
	                              });  
	                  }    
	                  
	              if (negativeButtonClickListener != null) {  
	                      ((TextView) layout.findViewById(R.id.TvAddp_5))  
	                              .setOnClickListener(new View.OnClickListener(){  
	                                  public void onClick(View v) {  
	                                      negativeButtonClickListener.onClick(dialog,  
	                                              DialogInterface.BUTTON_NEGATIVE);  
	                                  }  
	                             });  
	                  }  
	              if (ClickListener10 != null) {
                      ((TextView) layout.findViewById(R.id.TvAddp_10)).setOnClickListener(new View.OnClickListener() {  
                                  public void onClick(View v) {  
                                	  ClickListener10.onClick(dialog,DialogInterface.BUTTON1);  
                                  }  
                              });  
                  }    
                  
                  if (ClickListener20 != null) {  
                      ((TextView) layout.findViewById(R.id.TvAddp_20))  
                              .setOnClickListener(new View.OnClickListener(){  
                                  public void onClick(View v) {  
                                	  ClickListener20.onClick(dialog,DialogInterface.BUTTON2);  
                                  }  
                             });  
                  }  
	              // set the content message  
	            if (contentview != null){
	                  // if no message set  
	                  // add the contentView to the dialog body 
	            	((LinearLayout) layout.findViewById(R.id.LlAddp))
	                          .removeAllViews();
	                  ((LinearLayout) layout.findViewById(R.id.LlAddp))  
	                          .addView(contentview, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));  
	            }
	              dialog.setContentView(layout);  
	              return dialog;  
	          }  
	      }
	}