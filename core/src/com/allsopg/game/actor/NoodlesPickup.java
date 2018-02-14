package com.allsopg.game.actor;

import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.TweenDataAccessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import aurelienribon.tweenengine.Tween;

/**
 * Created by themo on 14/02/2018.
 */

public class NoodlesPickup extends BonusSprite
{
    public NoodlesPickup(Texture t, Vector2 pos)
    {
        super("gfx/noodles.atlas", t, pos, Animation.PlayMode.LOOP);
    }

    /**
     * This should be moved into the animated sprite class, so that the
     * regions variable doesnt need to be global.
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

    public void destroyRoutine()
    {
        chooseFrames(3,4,4,Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS,250f)
                .target(200,100).start(tweenManager);
        //.to(tweenData, TweenDataAccessor.TYPE_ROTATION,250f)
        //      .target(180).start().start(tweenManager).to(tweenData,TweenDataAccessor.TYPE_SCALE,250f)
         //     .target(.15f).start(tweenManager).to(tweenData,TweenDataAccessor.TYPE_COLOUR,500f)
         //     .target(.15f,.15f,.15f,.0f).start(tweenManager);
    }

    public void spawn()
    {
        chooseFrames(0,2, 8,Animation.PlayMode.NORMAL);
        idle();
    }

    public void idle()
    {
        chooseFrames(2,3, 4,Animation.PlayMode.LOOP);
    }
}
