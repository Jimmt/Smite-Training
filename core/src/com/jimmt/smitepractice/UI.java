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
	public static ImageButtonStyle smiteStyle, redSmiteStyle, blueSmiteStyle, homeStyle, gpgsStyle, highscoresStyle;
	public static LabelStyle largeLabelStyle, smallLabelStyle, smallLabelStyle_red, smallLabelStyle_blue, smiteLabelStyle, rankLabelStyle, rankLabelStyle_black, rankLabelStyle_blue, rankLabelStyle_red;

	public static BitmapFont rajdhani_large, rajdhani, arial, hemihead;
	
	public static Skin skin;

	public static void initialize() {
		rajdhani_large = new BitmapFont(Gdx.files.internal("fonts/rajdhani_large.fnt"));
		rajdhani = new BitmapFont(Gdx.files.internal("fonts/rajdhani.fnt"));
		arial = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"));
		hemihead = new BitmapFont(Gdx.files.internal("fonts/hemihead.fnt"));

		largeLabelStyle = new LabelStyle();
		largeLabelStyle.font = rajdhani_large;
		largeLabelStyle.fontColor = Color.WHITE;
		
		rankLabelStyle = new LabelStyle();
		rankLabelStyle.font = hemihead;
		rankLabelStyle.fontColor = Color.WHITE;
		
		rankLabelStyle_black = new LabelStyle();
		rankLabelStyle_black.font = hemihead;
		rankLabelStyle_black.fontColor = Color.BLACK;
		
		rankLabelStyle_blue = new LabelStyle();
		rankLabelStyle_blue.font = hemihead;
		rankLabelStyle_blue.fontColor = Color.BLUE;
		
		rankLabelStyle_red = new LabelStyle();
		rankLabelStyle_red.font = hemihead;
		rankLabelStyle_red.fontColor = Color.RED;

		smallLabelStyle = new LabelStyle();
		smallLabelStyle.font = rajdhani;
		smallLabelStyle.fontColor = Color.BLACK;
		
		smallLabelStyle_red = new LabelStyle();
		smallLabelStyle_red.font = rajdhani;
		smallLabelStyle_red.fontColor = Color.RED;
		
		smallLabelStyle_blue = new LabelStyle();
		smallLabelStyle_blue.font = rajdhani;
		smallLabelStyle_blue.fontColor = Color.BLUE;
		
		
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
		smiteStyle.up = new Image(Textures.getTex("smite/smite.png")).getDrawable();
		smiteStyle.down = new Image(Textures.getTex("smite/smite_clicked.png")).getDrawable();
		redSmiteStyle = new ImageButtonStyle();
		redSmiteStyle.up = new Image(Textures.getTex("smite/redsmite.png")).getDrawable();
		redSmiteStyle.down = new Image(Textures.getTex("smite/redsmite_clicked.png")).getDrawable();
		blueSmiteStyle = new ImageButtonStyle();
		blueSmiteStyle.up = new Image(Textures.getTex("smite/bluesmite.png")).getDrawable();
		blueSmiteStyle.down = new Image(Textures.getTex("smite/bluesmite_clicked.png")).getDrawable();
		homeStyle = new ImageButtonStyle();
		homeStyle.up = new Image(Textures.getTex("home.png")).getDrawable();
		homeStyle.down = new Image(Textures.getTex("home.png")).getDrawable();
		gpgsStyle = new ImageButtonStyle();
		gpgsStyle.up = new Image(Textures.getTex("gpgs.png")).getDrawable();
		gpgsStyle.down = new Image(Textures.getTex("gpgs.png")).getDrawable();
		highscoresStyle = new ImageButtonStyle();
		highscoresStyle.up = new Image(Textures.getTex("leaderboards.png")).getDrawable();
		highscoresStyle.down = new Image(Textures.getTex("leaderboards.png")).getDrawable();
		
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

	}

}
