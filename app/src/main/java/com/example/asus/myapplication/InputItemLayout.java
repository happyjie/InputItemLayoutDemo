package com.example.asus.myapplication;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InputItemLayout extends LinearLayout{

	private TextView tvDisplay;
	private TextView tvName;
	private ImageView ivArrow;
	private String value;
	private String name;
	private float keyTextWidth;

	private final int GRAVITY_LEFT = 0;
	private final int GRAVITY_RIGHT = 1;

	public InputItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.layout_input_item, this);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputItemLayout);
		tvName = (TextView) findViewById(R.id.tv_name);
		if(!isInEditMode()){
			ivArrow = (ImageView) findViewById(R.id.iv_arrow);
		}
		TextView tv_star_start = (TextView) findViewById(R.id.tv_star_start);
		TextView tv_star_end = (TextView) findViewById(R.id.tv_star_end);
		name = a.getString(R.styleable.InputItemLayout_keytext);
		if(name != null && name.startsWith("*")){
			tv_star_start.setVisibility(View.VISIBLE);
			tvName.setText(name.substring(1));
		} else if(name != null && name.endsWith("*")){
			tv_star_end.setVisibility(View.VISIBLE);
			tvName.setText(name.substring(0, name.length() - 1));
		} else {
			tvName.setText(name);
		}

		tvDisplay = (TextView) findViewById(R.id.tv_value);
		if(!TextUtils.isEmpty(a.getString(R.styleable.InputItemLayout_texthint))){
			tvDisplay.setHint(a.getString(R.styleable.InputItemLayout_texthint));
		}

		int color1 = a.getColor(R.styleable.InputItemLayout_keytextcolor, Color.parseColor("#666666"));
		int color2 = a.getColor(R.styleable.InputItemLayout_contenttextcolor, Color.parseColor("#999999"));

		keyTextWidth = a.getDimension(R.styleable.InputItemLayout_keytextwidth, dp2px(context, 70));
		ViewGroup.LayoutParams layoutParams = tvName.getLayoutParams();
		layoutParams.width = (int) keyTextWidth;

		int contenttextgravity = a.getInt(R.styleable.InputItemLayout_contenttextgravity, GRAVITY_LEFT);
		if(GRAVITY_RIGHT == contenttextgravity){
			tvDisplay.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
		} else {
			tvDisplay.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
		}

		tvName.requestLayout();
		tvName.setTextColor(color1);
		tvDisplay.setTextColor(color2);
		a.recycle();
	}

	public void display(CharSequence txt){
		if(null == txt || TextUtils.isEmpty(txt.toString())){
			tvDisplay.setText("");
		} else {
			tvDisplay.setText(txt);
		}
	}
	
	public String getDisplayTxt(){
		return tvDisplay.getText().toString().trim();
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	public TextView getDisplayView(){
		return tvDisplay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		tvName.setText(name);
	}

	public void setArrowShow(boolean isShow){
		ivArrow.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
	}

	public boolean isEmpty(){
		return TextUtils.isEmpty(tvDisplay.getText().toString().trim());
	}


	private int dp2px(Context context, float dipValue) {
		if (context != null) {

			if ((float) ViewGroup.LayoutParams.FILL_PARENT == dipValue) {
				return ViewGroup.LayoutParams.FILL_PARENT;
			}

			if ((float) ViewGroup.LayoutParams.WRAP_CONTENT == dipValue) {
				return ViewGroup.LayoutParams.WRAP_CONTENT;
			}

			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dipValue * scale + 0.5f);
		}
		return (int) dipValue;
	}
}
