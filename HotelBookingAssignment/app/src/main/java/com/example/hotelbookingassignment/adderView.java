package com.example.hotelbookingassignment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import butterknife.OnClick;

public class adderView extends ConstraintLayout implements View.OnClickListener {


    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;
    private final TextView number;

    public adderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TextView minus = (TextView) findViewById(R.id.minus);
        number = (TextView) findViewById(R.id.number);
        TextView plus = (TextView) findViewById(R.id.plus);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        //Set the default value
        int value = getValue();
        setValue(value);
    }

    @OnClick({R.id.minus, R.id.plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.minus://Less
                reduce();
                break;
            case R.id.plus://plus
                add();
                break;
        }
    }

    /**
     * If the current value is greater than the minimum value minus
     */
    private void reduce() {
        if (value > minValue) {
            value--;
        }
        setValue(value);
        if (onValueChangeListene != null) {
            onValueChangeListene.onValueChange(value);
        }
    }

    /**
     * If the current value is less than the minimum value, add
     */
    private void add() {
        if (value < maxValue) {
            value++;
        }
        setValue(value);
        if (onValueChangeListene != null) {
            onValueChangeListene.onValueChange(value);
        }
    }

    //Get specific value
    public int getValue() {
        String countStr = number.getText().toString().trim();
        if (countStr != null) {
            value = Integer.valueOf(countStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        number.setText(value + "");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minus://Less
                reduce();
                break;
            case R.id.plus://plus
                add();
                break;
        }
    }


    //Monitor callback
    public interface OnValueChangeListener {
        public void onValueChange(int value);
    }

    private OnValueChangeListener onValueChangeListene;

    public void setOnValueChangeListene(OnValueChangeListener onValueChangeListene) {
        this.onValueChangeListene = onValueChangeListene;
    }
}