package com.allsopg.game;

import com.allsopg.game.actor.NoodlesPickup;
import com.allsopg.game.utility.BitmapText;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
	private NoodlesPickup noodles;
    private float animationTime;
	private BitmapText bText;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		view = new FitViewport(800,600,camera);
		batch = new SpriteBatch();
		Texture small = new Texture(Gdx.files.internal("gfx/smallSize.png"));
		Texture medium = new Texture(Gdx.files.internal("gfx/mediumSize.png"));
		noodles = new NoodlesPickup(small,
				new Vector2(250,200));
		//noodles.spawn();
		noodles.discard();
		bText= new BitmapText("test", Color.BLACK, 200f,1,true);
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		animationTime +=Gdx.graphics.getDeltaTime();
		UniversalResource.getInstance().tweenManager.update(animationTime);
		batch.begin();
        noodles.update(animationTime);
        noodles.draw(batch);
		bText.getFont().draw(batch, bText.getGlyphLayout(),
				Gdx.graphics.getWidth()/20, //-(bText.getGlyphLayout().width/2),
				Gdx.graphics.getHeight()*9/10); //-(bText.getGlyphLayout().height/2));
		batch.end();
	}
	@Override
	public void dispose () { batch.dispose(); }
}
