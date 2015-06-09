package com.jimmt.smitepractice;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameFinishedDialog extends Dialog {

	public GameFinishedDialog(final SmitePractice smiteGame, final GameConfiguration config, final Stats stats) {
		super("", UI.skin);

		Image background = new Image(Textures.getTex("dialogBackground.png"));
		background(background.getDrawable());

		Table table = getContentTable();

		Label smitesHit = new Label("Smites hit: " + stats.smitesHit + " / " + stats.totalSmites, UI.smallLabelStyle);
		TextButton retryButton = new TextButton("Start", UI.startButtonStyle);
		retryButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				smiteGame.setScreen(new GameScreen(smiteGame, config));
			}
		});
		TextButton backButton = new TextButton("Back", UI.startButtonStyle);
		backButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				smiteGame.setScreen(new MenuScreen(smiteGame));
			}
		});
		table.add(smitesHit).colspan(2);
		table.row();
		table.add(retryButton).width(retryButton.getWidth() / 2)
				.height(retryButton.getHeight() / 2);
		table.add(backButton).width(retryButton.getWidth() / 2).height(retryButton.getHeight() / 2);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

	}

}
