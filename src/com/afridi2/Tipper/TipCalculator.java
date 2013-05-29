package com.afridi2.Tipper;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class TipCalculator extends Activity {

	private static final String currentBill = "bill_amount"; //bill without tip
	private static final String currentTipPercent = "tip_percent"; //percent of tip to give
	private static final String currentTipAmount = "tip_amount"; //value of tip
	private static final String currentFinalBill = "final_bill"; //total amount of bill
	
	private double billAmount;
	private double tipPercent;
	private double tipAmount;
	private double finalBill;
	
	EditText billAmountET;
	EditText tipPercentET;
	EditText tipAmountET;
	EditText finalBillET;
	
	SeekBar tipSeekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		
		if (savedInstanceState == null){
			billAmount = 0.0;
			tipPercent = 15.0;
			tipAmount = 0.0;
			finalBill = 0.0;
		} 
		else {
			billAmount = savedInstanceState.getDouble(currentBill);
			tipPercent = savedInstanceState.getDouble(currentTipPercent);
			tipAmount = savedInstanceState.getDouble(currentTipAmount);
			finalBill = savedInstanceState.getDouble(currentFinalBill);			
		}
		
		billAmountET = (EditText) findViewById(R.id.billAmountEdit);
		tipPercentET = (EditText) findViewById(R.id.tipPercentEdit);
		tipAmountET = (EditText) findViewById(R.id.tipAmountEdit);
		finalBillET = (EditText) findViewById(R.id.finalAmountEdit);
		
		tipSeekBar = (SeekBar) findViewById(R.id.tipSeekChange);
		
		tipSeekBar.setOnSeekBarChangeListener(tipSeekBarListener);
		
		billAmountET.addTextChangedListener(billBeforeTipListener);
		
	}
	
	private TextWatcher billBeforeTipListener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			try {
				billAmount = Double.parseDouble(s.toString());
			}
			catch(NumberFormatException e) {
				billAmount = 0.0;
			}
			update();
		}
	};
	
	private void update() {
		double tipPerc = Double.parseDouble(tipPercentET.getText().toString());
		double finalTip = billAmount*tipPerc/100;
		double finBill = billAmount + finalTip;
		tipAmountET.setText(String.format("%.02f",finalTip));
		finalBillET.setText(String.format("%.02f",finBill));
	}
	
	protected void onSaveInstanceState(Bundle outState){
		
		super.onSaveInstanceState(outState);
		outState.putDouble(currentBill, billAmount);
		outState.putDouble(currentTipPercent, tipPercent);
		outState.putDouble(currentTipAmount, tipAmount);
		outState.putDouble(currentFinalBill, finalBill);
		
	}
	
	private OnSeekBarChangeListener tipSeekBarListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			tipPercent = tipSeekBar.getProgress();
			tipPercentET.setText(""+tipPercent);
			update();
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tip_calculator, menu);
		return true;
	}

}
