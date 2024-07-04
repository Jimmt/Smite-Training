package com.jimmt;

import com.jimmt.smitepractice.FloatFormatter;

public class DesktopFloatFormatter implements FloatFormatter {

	@Override
	public String getFormattedString(float value) {
		return String.format("%.3g", value);
	}
}
