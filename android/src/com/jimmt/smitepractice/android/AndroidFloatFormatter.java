package com.jimmt.smitepractice.android;

import com.jimmt.smitepractice.FloatFormatter;

public class AndroidFloatFormatter implements FloatFormatter {
	@Override
    public String getFormattedString(float value) {
        return String.format("%.3g", value);
    }
}
