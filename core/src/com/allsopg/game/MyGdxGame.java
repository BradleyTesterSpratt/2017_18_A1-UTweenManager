package com.allsopg.game;

import com.allsopg.game.actor.NoodlesPickup;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
		batch.end();
	}
	@Override
	public void dispose () { batch.dispose(); }
}
