package com.jimmt.smitepractice;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class SelectButton extends Table {
	Array<TextButton> buttons;
	String[] names;
	ButtonGroup<TextButton> group;

	public SelectButton(int initialSelection, String... names) {
		buttons = new Array<TextButton>();
		this.names = names;
		group = new ButtonGroup<TextButton>();

		for (String name : names) {
			final TextButton button = new TextButton(name, UI.selectButtonStyle);
			buttons.add(button);
			group.add(button);
			add(button).width(button.getLabel().getWidth() + 70).height(
					button.getLabel().getHeight() + 20);

			button.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					button.setChecked(!button.isChecked());
				}
			});
		}
		
		group.getButtons().get(initialSelection).setChecked(true);


	}
	

	public TextButton getButton(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name)) {
				return buttons.get(i);
			}
		}
		return null;
	}
	
	public int getSelectionIndex(){
		return group.getCheckedIndex();
	}

	public String getSelectionName() {
		return names[group.getCheckedIndex()];

	}
}
