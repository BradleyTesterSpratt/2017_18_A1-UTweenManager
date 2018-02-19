package com.allsopg.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by bst19 on 17/02/2018.
 */

public class SoundLink
{
    private IntMap<Sound> soundKeys;

    /**
     * Constructor for SoundLink
     * assign new sounds here
     */
    public SoundLink()
    {
        soundKeys = new IntMap<Sound>();
        Sound splat = Gdx.audio.newSound(Gdx.files.internal("sfx/splat.ogg"));
        soundKeys.put(1,splat);
    }

    /**
     * method to play a sound
     * @param keyCode int of the IntMap position for the sound to play
     * @return true if the sound at IntMap keyCode position exists
     */
    public boolean play(int keyCode)
    {
        Sound sound = soundKeys.get(keyCode);
        if(sound !=  null)
        {
            sound.play();
            return true;
        }
        return false;
    }

    /**
     * method to stop all sounds playing
     */
    public void dispose()
    {
        for(Sound sound : soundKeys.values())
        {
            sound.dispose();
        }
    }
}


