package com.jimmt.smitepractice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class UI {
	public static TextButtonStyle buttonStyle;
	public static TextButtonStyle startButtonStyle;
	public static TextButtonStyle selectButtonStyle;
	public static ImageButtonStyle smiteStyle, redSmiteStyle, blueSmiteStyle;
	public static LabelStyle largeLabelStyle, smallLabelStyle, smiteLabelStyle;

	public static BitmapFont rajdhani_large, rajdhani, arial;
	
	public static Skin skin;

	public static void initialize() {
		rajdhani_large = new BitmapFont(Gdx.files.internal("fonts/rajdhani_large.fnt"));
		rajdhani = new BitmapFont(Gdx.files.internal("fonts/rajdhani.fnt"));
		arial = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"));

		largeLabelStyle = new LabelStyle();
		largeLabelStyle.font = rajdhani_large;
		largeLabelStyle.fontColor = Color.WHITE;

		smallLabelStyle = new LabelStyle();
		smallLabelStyle.font = rajdhani;
		smallLabelStyle.fontColor = Color.BLACK;
		
		smiteLabelStyle = new LabelStyle();
		smiteLabelStyle.font = rajdhani;
		smiteLabelStyle.fontColor = Color.WHITE;

		buttonStyle = new TextButtonStyle();
		buttonStyle.up = new Image(Textures.getTex("button.png")).getDrawable();
		buttonStyle.down = new Image(Textures.getTex("button_clicked.png")).getDrawable();
		buttonStyle.font = rajdhani;

		startButtonStyle = new TextButtonStyle();
		startButtonStyle.up = new Image(Textures.getTex("startButton.png")).getDrawable();
		startButtonStyle.down = new Image(Textures.getTex("startButton_clicked.png")).getDrawable();
		startButtonStyle.font = rajdhani;

		selectButtonStyle = new TextButtonStyle();
		selectButtonStyle.up = new Image(Textures.getTex("button.png")).getDrawable();
		selectButtonStyle.down = new Image(Textures.getTex("button_clicked.png")).getDrawable();
		selectButtonStyle.checked = new Image(Textures.getTex("button_clicked.png")).getDrawable();
		selectButtonStyle.font = rajdhani;

		smiteStyle = new ImageButtonStyle();
		Image smite = new Image(Textures.getTex("smite.png"));
		smiteStyle.up = smite.getDrawable();
		smiteStyle.down = new Image(Textures.getTex("smite.png")).getDrawable();
		redSmiteStyle = new ImageButtonStyle();
		redSmiteStyle.up = new Image(Textures.getTex("redsmite.png")).getDrawable();
		redSmiteStyle.down = new Image(Textures.getTex("redsmite.png")).getDrawable();
		blueSmiteStyle = new ImageButtonStyle();
		blueSmiteStyle.up = new Image(Textures.getTex("bluesmite.png")).getDrawable();
		blueSmiteStyle.down = new Image(Textures.getTex("bluesmite.png")).getDrawable();
		
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

	}

}
