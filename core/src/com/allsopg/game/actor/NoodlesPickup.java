package com.allsopg.game.actor;

import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.TweenData;
import com.allsopg.game.utility.TweenDataAccessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

/**
 * Created by bst19 on 14/02/2018.
 */

public class NoodlesPickup extends BonusSprite
{
    //updated by a moving spawner
    private Vector2 currentPos;


    public NoodlesPickup(Texture t, Vector2 pos)
    {
        super("gfx/noodles.atlas", t, pos, Animation.PlayMode.LOOP);
        currentPos=pos;
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
             .to(tweenData, TweenDataAccessor.TYPE_POS, 6f).delay(10f).targetRelative(0, -10).setCallback(new TweenCallback()
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
        chooseFrames(2, 4, 4, Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).target(10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,20f).delay(10f).target(-10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).delay(30f).target(0f).start(tweenManager);
    }

    /**
     * should be private to be called only by collision
     *
     */
    public void discard()
    {
        //add additional frame
        chooseFrames(5,5,4, Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS,2f).targetRelative(-7,10f).start(tweenManager)
                //.to(tweenData, TweenDataAccessor.TYPE_ROTATION, 4f).target(-18f).start(tweenManager)
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
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(18f).targetRelative(200f).start(tweenManager).setCallback(new TweenCallback()
        {
            @Override
            public void onEvent(int type, BaseTween<?> source)
            {
                chooseFrames(6,6,1,Animation.PlayMode.NORMAL);
            }
        }).start(tweenManager);
    }

    /**
     * newX and newY methods can be used with a moving spawner
     * use in place of .targetReleative
     * eg .target(newX,newY)
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
