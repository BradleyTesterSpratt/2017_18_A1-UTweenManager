package com.allsopg.game.actor;

import com.allsopg.game.sound.SoundLink;
import com.allsopg.game.utility.BitmapText;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.TweenData;
import com.allsopg.game.utility.TweenDataAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

/**
 * Created by bst19 on 14/02/2018.
 */

public class NoodlesPickup extends BonusSprite
{
    //updated by a moving spawner
    private Vector2 currentPos;
    private SoundLink soundLink;
    //for text
    private SpriteBatch batch;
    private BitmapText bText;

    public NoodlesPickup(Texture t, Vector2 pos)
    {
        super("gfx/noodles.atlas", t, pos, Animation.PlayMode.LOOP);
        currentPos=pos;
        soundLink=new SoundLink();
        //for text
        batch = new SpriteBatch();
        bText= new BitmapText("SPLAT", Color.BLACK, 200f,1,true);
    }

    public void render()
    {
        batch.begin();
        bText.getFont().draw(batch, bText.getGlyphLayout(),
                (Gdx.graphics.getWidth()/2)-(bText.getGlyphLayout().width/2),
        		Gdx.graphics.getHeight()*9/10);
        batch.end();
    }

    /**
     * This could be moved into the animated sprite class, so that the
     * regions variable doesnt need to be global. It will also likely be needed
     * by a player character.
     */
    private void chooseFrames(int startFrame, int endFrame, int speedModifier, Animation.PlayMode loopType)
    {
        Array<TextureAtlas.AtlasRegion> animatedRegions = new Array<TextureAtlas.AtlasRegion>();
        for (int i=startFrame; i<=endFrame; i++)
        {
            animatedRegions.add(regions.get(i));
        }
        animation = new Animation(Constants.FRAME_DURATION*speedModifier,animatedRegions,loopType);
    }

    public void spawn()
    {
        chooseFrames(0,2,12,Animation.PlayMode.NORMAL);
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 6f).targetRelative(0, 10).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS, 6f).delay(6f).targetRelative(0, -10)
             .setCallback(new TweenCallback()
             {
                 @Override
                 public void onEvent(int type, BaseTween<?> source)
                 {
                     idle();
                 }
             }).start(tweenManager);
    }

    private void idle()
    {
        chooseFrames(3, 5, 4, Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).target(10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,20f).delay(10f).target(-10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).delay(30f).target(0f).start(tweenManager);
    }

    /**
     * should be private to be called only by collision
     */
    public void consume()
    {
        chooseFrames(6,7,5, Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS,2f).targetRelative(-7,10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(2f).targetRelative(-10,12f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(2f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(4f).targetRelative(-9,10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(4f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(6f).targetRelative(-8,9f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(6f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(8f).targetRelative(-7,0f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(8f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(10f).targetRelative(-7,-9f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(10f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(12f).targetRelative(-7,-9f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(12f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(16f).targetRelative(-6,-11f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(16f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(18f).targetRelative(-6,-13f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(18f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(20f).targetRelative(-5,-16f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(20f).targetRelative(180f)
             .setCallback(new TweenCallback()
             {
                 @Override
                 public void onEvent(int type, BaseTween<?> source)
                 {
                     chooseFrames(8,8,1,Animation.PlayMode.NORMAL);
                 }
             }).start(tweenManager);
    }

    /**
     * should be private to be called only by collision
     */
    public void discard()
    {
        chooseFrames(9,10,10,Animation.PlayMode.LOOP);
        Tween.to(tweenData,TweenDataAccessor.TYPE_POS,7f).targetRelative(0f,20f).start(tweenManager)
             .to(tweenData,TweenDataAccessor.TYPE_SCALE, 7f).target(2f)
                .setCallback(new TweenCallback()
                {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        soundLink.play(1);
                        render();
                    }
                })   .start(tweenManager)
             //use scene height so it always has to fall off screen
             .to(tweenData,TweenDataAccessor.TYPE_POS,60f).delay(7f).targetRelative(0f,-Constants.SCENE_HEIGHT).start(tweenManager);
    }
    /**
     * newX and newY methods can be used with a moving spawner
     * use in place of .targetReleative
     * eg .target(newX,newY)
     * only needs to be used with spawn()
    **/
    private Float newX(float offset)
    {
        float newX = currentPos.x+offset;
        currentPos.set(newX,currentPos.y);
        return newX;
    }

    private Float newY(float offset)
    {
        float newY = currentPos.y+offset;
        currentPos.set(currentPos.x,newY);
        return newY;
    }
}
