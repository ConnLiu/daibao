package com.example.customview;

import com.example.easyshop.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GenderDialog extends Dialog{

	public GenderDialog(Context context ) {
        super(context );
    }

      public GenderDialog(Context context, int theme) {
           super(context,theme);
       }
   
      public static class Builder{
    	  private Context context;
    	  private View contentview;
    	  private String boyButtonText;
    	  private String girlButtonText;
    	  private DialogInterface.OnClickListener positiveButtonClickListener;
    	  private DialogInterface.OnClickListener negativeButtonClickListener;
    	  
    	  public Builder(Context context){
    		  this.context = context;
    	  }
    	  
    	  public Builder setContentView(View v){
    		  this.contentview = v;
    		  return this;
    	  }
    	  
    	  public Builder setPositiveButton(String positiveButtonText,DialogInterface.OnClickListener clickListener){
    		  this.boyButtonText = positiveButtonText;
    		  this.positiveButtonClickListener = clickListener;
    		  return this;
    	  }

    	  public Builder setPositiveButton(int positiveButtonText,DialogInterface.OnClickListener clickListener){
    		  this.boyButtonText = (String) context.getText(positiveButtonText);
    		  this.positiveButtonClickListener = clickListener;
    		  return this;
    	  }

    	  public Builder setNegativeButton(String negativeButtonText,DialogInterface.OnClickListener clickListener){
    		  this.girlButtonText = negativeButtonText;
    		  this.negativeButtonClickListener = clickListener;
    		  return this;
    	  }

    	  public Builder setNegativeButton(int negativeButtonText,DialogInterface.OnClickListener clickListener){
    		  this.girlButtonText = (String) context.getText(negativeButtonText);
    		  this.negativeButtonClickListener = clickListener;
    		  return this;
    	  }
    	  
    	  public GenderDialog create() {  
              LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
              // instantiate the dialog with the custom Theme  
              final GenderDialog dialog = new GenderDialog(context,R.style.Dialog); 
              View layout = inflater.inflate(R.layout.genderdialog, null);
              dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
              // set the dialog title  
              //((TextView) layout.findViewById(R.id.TvDialog_title)).setText(title);
              // set the confirm button 
              if (boyButtonText != null) {  
                  ((TextView) layout.findViewById(R.id.TvGender_boy)).setText(boyButtonText);  
                  if (positiveButtonClickListener != null) {
                      ((TextView) layout.findViewById(R.id.TvGender_boy)).setOnClickListener(new View.OnClickListener() {  
                                  public void onClick(View v) {  
                                      positiveButtonClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);  
                                  }  
                              });  
                  }  
              } else {  
                  // if no confirm button just set the visibility to GONE  
                  layout.findViewById(R.id.TvGender_boy).setVisibility(  
                          View.GONE);  
              }  
              // set the cancel button  
              if (girlButtonText != null) {  
                  ((TextView) layout.findViewById(R.id.TvGender_girl)).setText(girlButtonText);
                  if (negativeButtonClickListener != null) {  
                      ((TextView) layout.findViewById(R.id.TvGender_girl))  
                              .setOnClickListener(new View.OnClickListener(){  
                                  public void onClick(View v) {  
                                      negativeButtonClickListener.onClick(dialog,  
                                              DialogInterface.BUTTON_NEGATIVE);  
                                  }  
                             });  
                  }  
              } else {  
                  // if no confirm button just set the visibility to GONE  
                  layout.findViewById(R.id.TvGender_girl).setVisibility(  
                          View.GONE);  
              }  
              // set the content message  
            if (contentview != null){
                  // if no message set  
                  // add the contentView to the dialog body 
            	((LinearLayout) layout.findViewById(R.id.gender_content))
                          .removeAllViews();
                  ((LinearLayout) layout.findViewById(R.id.gender_content))  
                          .addView(contentview, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));  
            }
              dialog.setContentView(layout);  
              return dialog;  
          }  
      }
}