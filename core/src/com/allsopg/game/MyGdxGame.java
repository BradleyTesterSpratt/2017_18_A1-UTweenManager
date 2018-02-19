package com.allsopg.game;

import com.allsopg.game.actor.NoodlesPickup;
import com.allsopg.game.utility.BitmapText;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;

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
				new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2)
				);
		//noodles.spawn();
		//bText= new BitmapText("Spawn", Color.BLACK, 200f,1,true);
		//noodles.consume();
		//bText= new BitmapText("Consume", Color.BLACK, 200f,1,true);
		noodles.discard();
		bText= new BitmapText("Discard", Color.BLACK, 200f,1,true);
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		animationTime +=Gdx.graphics.getDeltaTime();
		UniversalResource.getInstance().tweenManager.update(animationTime);
		//delay to capture video
		delay(10f);
		batch.begin();
        noodles.update(animationTime);
        noodles.draw(batch);
		bText.getFont().draw(batch, bText.getGlyphLayout(),
				Gdx.graphics.getWidth()/20,
				Gdx.graphics.getHeight()*9/10);
		batch.end();
	}
	@Override
	public void dispose () { batch.dispose(); }
}
