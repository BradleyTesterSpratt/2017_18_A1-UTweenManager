package com.allsopg.game.actor;

import com.allsopg.game.sound.SoundLink;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.TweenDataAccessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

    /**
     * Constructor for the noodle pickup
     *
     * @param t texture file used to set the size
     * @param pos vector2 used to set the objects starting position and to be used to update position with a moving spawner
     */
    public NoodlesPickup(Texture t, Vector2 pos)
    {
        super("gfx/noodles.atlas", t, pos, Animation.PlayMode.LOOP);
        currentPos=pos;
        soundLink=new SoundLink();
    }

    /**
     * Changes which frames and what speed to use for the animation
     *
     * This could be moved into the animated sprite class, so that the
     * regions variable doesnt need to be global. It will also likely be needed
     * by a player character.
     *
     * @param startFrame identifier of the first frame to be used
     * @param endFrame identifier of the last frame
     * @param speedModifier float used to speed up or slow down the animation speed
     * @param loopType ype of loop to use
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

    /**
     * Method to spawn the Noodles
     *
     * When spawned with a mobile spawner will need to use the
     * newX and newY methods
     */
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

    /**
     * method to set the sprite to its main state
     * only to be called by spawn method
     */
    private void idle()
    {
        chooseFrames(3, 5, 4, Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).target(10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,20f).delay(10f).target(-10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).delay(30f).target(0f).start(tweenManager);
    }

    /**
     * method to start the consume animation. To be used
     * when the player collides under certain circumstances
     *
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
     * method to start the discard animation. To be used
     * when the player collides under certain circumstances
     *
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
                    public void onEvent(int type, BaseTween<?> source)
                    {
                        soundLink.play(1);
                    }
                }).start(tweenManager)
             .to(tweenData,TweenDataAccessor.TYPE_POS,60f).delay(7f).targetRelative(0f,-Constants.SCENE_HEIGHT*2).start(tweenManager);
    }

    /**
     * method to be used with a moving spawner
     * use in place of .targetReleative
     * eg .target(newX,newY)
     *
     * @param offset float to set where to offset the X
    **/
    private Float newX(float offset)
    {
        float newX = currentPos.x+offset;
        currentPos.set(newX,currentPos.y);
        return newX;
    }

    /**
     * method to be used with a moving spawner
     * use in place of .targetReleative
     * eg .target(newX,newY)
     *
     * @param offset float to set where to offset the Y
     **/
    private Float newY(float offset)
    {
        float newY = currentPos.y+offset;
        currentPos.set(currentPos.x,newY);
        return newY;
    }
}
