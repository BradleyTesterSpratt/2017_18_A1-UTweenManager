package com.allsopg.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Created by bst19 on 18/02/2018.
 */

public class BitmapText
{
    private BitmapFont font;
    private String text;
    private static GlyphLayout glyphLayout = new GlyphLayout();

    public BitmapText(String text, Color color, float tWidth, int hAlign, boolean singleLine)
    {
        font = new BitmapFont(Gdx.files.internal("font/infinite.fnt"));
        this.text=text;
        glyphLayout.setText(font,text, color,tWidth,hAlign,singleLine);
    }
    public BitmapFont getFont() { return font; }
    public GlyphLayout getGlyphLayout() { return glyphLayout; }
}
