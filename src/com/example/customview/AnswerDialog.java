package com.example.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyshop.R;

public class AnswerDialog extends Dialog{

	public AnswerDialog(Context context ) {
        super(context );
    }

      public AnswerDialog(Context context, int theme) {
           super(context,theme);
       }
   
      public static class Builder{
    	  private Context context;
    	  private View contentview;
    	  private String answerText;
    	  private DialogInterface.OnClickListener positiveButtonClickListener;
    	  
    	  public Builder(Context context){
    		  this.context = context;
    	  }
    	  
    	  public Builder setContentView(View v){
    		  this.contentview = v;
    		  return this;
    	  }
    	  
    	  public Builder setPositiveButton(String positiveButtonText,DialogInterface.OnClickListener clickListener){
    		  this.answerText = positiveButtonText;
    		  this.positiveButtonClickListener = clickListener;
    		  return this;
    	  }

    	  public Builder setPositiveButton(int positiveButtonText,DialogInterface.OnClickListener clickListener){
    		  this.answerText = (String) context.getText(positiveButtonText);
    		  this.positiveButtonClickListener = clickListener;
    		  return this;
    	  }

    	  public AnswerDialog create() {  
              LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
              // instantiate the dialog with the custom Theme  
              final AnswerDialog dialog = new AnswerDialog(context,R.style.Dialog); 
              View layout = inflater.inflate(R.layout.answerdialog, null);
              dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
              if (answerText != null) {  
                  ((TextView) layout.findViewById(R.id.TvAnswer_answer)).setText(answerText);  
                  if (positiveButtonClickListener != null) {
                      ((TextView) layout.findViewById(R.id.TvAnswer_answer)).setOnClickListener(new View.OnClickListener() {  
                                  public void onClick(View v) {  
                                      positiveButtonClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);  
                                  }  
                              });  
                  }  
              } else {  
                  // if no confirm button just set the visibility to GONE  
                  layout.findViewById(R.id.TvAnswer_answer).setVisibility(  
                          View.GONE);  
              }  
              dialog.setContentView(layout);  
              return dialog;  
          }  
      }
}