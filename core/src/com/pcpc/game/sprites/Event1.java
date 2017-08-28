package com.pcpc.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pcpc.game.IdleSurvivalMain;
import com.pcpc.game.states.PlayState;

public class Event1 {

    public float posY = IdleSurvivalMain.HEIGHT / 2 - PlayState.BASE_HEIGHT - PlayState.EVENT_HEIGHT;

    private boolean isBarUsed;

    private Texture bg, btnImg;
    private ImageButton button;
    private int k;
    private Drawable btn, btnpressed;
    private Rectangle bound;

    private Vector3 position, barPos;
    private Texture pbempty;
    private MyProgressBar pbfilled;
    private float barSpacing;
    private Texture texture;

    public Event1 (){
        bg = new Texture("item.png");
        bg.getHeight();
        position = new Vector3(0, posY, 0);
        pbempty = new Texture("scrollbarempty.png");
        texture = new Texture("scrollbarfilled.png");
        barSpacing = (float) (bg.getHeight() * 0.2);
        barPos = new Vector3(barSpacing, position.y + barSpacing, 0);
        pbfilled = new MyProgressBar(new TextureRegion(texture), 1.34f);
        isBarUsed = false;

        btnImg = new Texture("btn.png");
        btn = new TextureRegionDrawable(new TextureRegion(btnImg));
        btnpressed = new TextureRegionDrawable(new TextureRegion(new Texture("btnpressed.png")));
        button = new ImageButton(btn, btnpressed);
        button.setPosition(barPos.x + pbempty.getWidth() + btnImg.getWidth() / 2,
                position.y + bg.getHeight() - btnImg.getHeight());
        bound = new Rectangle(button.getX(), button.getY(), button.getWidth(), button.getHeight());

    }

    public void updatePosition(float dy){

        position.y -= dy*0.4;

        if (position.y < posY) position.y = posY;
        barPos.y = position.y + barSpacing;
        button.setY(position.y + bg.getHeight() - btnImg.getHeight());
        bound.setPosition(button.getX(), button.getY());

    }

    public void update(float dt){
        pbfilled.update(dt);
        isBarUsed = pbfilled.isEnded();

    }

    public Texture getBg() {
        return bg;
    }

    public Texture getPbempty() {
        return pbempty;
    }

    public TextureRegion getPbfilled() {
        return pbfilled.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getBarPos() {
        return barPos;
    }

    public boolean isBarUsed() {
        return isBarUsed;
    }

    public ImageButton getButton() {
        return button;
    }

    public void dispose() {
        bg.dispose();
    }

    public void buttonListener() {
        if(button.isPressed()){
            isBarUsed = true;
        }
    }
}
