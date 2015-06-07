package com.jimmt.smitepractice;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameModeDialog extends Dialog {
	
	public GameModeDialog(Skin skin){
		super("", skin);
		
		Image background = new Image(Textures.getTex("dialogBackground.png"));
		background(background.getDrawable());
		
		Image solo = new Image(Textures.getTex("solo.png"));
		Image ai = new Image(Textures.getTex("AI.png"));
		
		Table table = getContentTable();
		table.add(solo);
		table.add(ai);
		
	}

}
