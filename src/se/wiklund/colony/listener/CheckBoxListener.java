package se.wiklund.colony.listener;

import se.wiklund.colony.component.CheckBox;

public interface CheckBoxListener {
	
	public void onChangeValue(CheckBox box, boolean checked);
}
