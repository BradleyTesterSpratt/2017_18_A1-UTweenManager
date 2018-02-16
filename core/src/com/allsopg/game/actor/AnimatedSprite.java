package com.allsopg.game.actor;

import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.util.Comparator;


/**
 * Created by gja10 on 13/11/2017.
 * Updated by bst19 on 14/02/2018 - changed global variables to protected, moved regions variable to global and protected.
 */

public class AnimatedSprite extends Sprite {
    protected Animation animation;
    protected TextureAtlas atlas;
    //move to global for use with inherited classes
    protected Array<TextureAtlas.AtlasRegion> regions;

    public AnimatedSprite(String atlasString, Texture t,Animation.PlayMode loopType){
        super(t);
        atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animation = new Animation(Constants.FRAME_DURATION,regions,loopType);
    }

    public void update(float deltaTime){
        this.setRegion((TextureAtlas.AtlasRegion) animation.getKeyFrame(deltaTime));
    }

    protected static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion>{

        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2) {
            return region1.name.compareTo(region2.name);
        }
    }
}
