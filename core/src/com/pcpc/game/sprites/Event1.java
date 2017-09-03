package com.pcpc.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pcpc.game.Constants;
import com.pcpc.game.states.PlayState;

public class Event1 {

    public float posY = Gdx.graphics.getHeight() - Constants.BASE_HEIGHT - Constants.EVENT_HEIGHT;

    private boolean isBarUsed;

    private Texture bg;
    private ImageButton buttonStart, buttonPeoplePlus, buttonUpgrade;
    private Drawable btnStart, btnpressedStart;
    private Drawable btnPeoplePlus, btnpressedPeoplePlus;
    private Drawable btnUpgrade, btnpressedUpgrade;

    private Vector3 position, barPos;
    private Texture pbempty;
    private MyProgressBar pbfilled;
    private float barSpacing;

    public Event1 (){
        bg = new Texture("item.png");
        position = new Vector3(0, posY, 0);
        pbempty = new Texture("scrollbarempty.png");
        barSpacing = (float) (Constants.EVENT_HEIGHT * 0.2);
        barPos = new Vector3(barSpacing, position.y + barSpacing, 0);
        pbfilled = new MyProgressBar(new TextureRegion( new Texture("scrollbarfilled.png")), 1.34f);
        isBarUsed = false;

        btnStart = new TextureRegionDrawable(new TextureRegion(new Texture("btnStart.png")));
        btnpressedStart = new TextureRegionDrawable(new TextureRegion(new Texture("btnpressedStart.png")));
        buttonStart = new ImageButton(btnStart, btnpressedStart);
        buttonStart.setSize(Constants.BTN_WIDTH, Constants.BTNPRES_HEIGHT);
        buttonStart.setPosition(barPos.x + Constants.PB_WIDTH + Constants.BTN_WIDTH / 2,
                position.y + Constants.EVENT_HEIGHT - 2*Constants.BTN_HEIGHT);

        btnPeoplePlus = new TextureRegionDrawable(new TextureRegion(new Texture("btnPeoplePlus.png")));
        btnpressedPeoplePlus = new TextureRegionDrawable(new TextureRegion(new Texture("btnpressedPeoplePlus.png")));
        buttonPeoplePlus = new ImageButton(btnPeoplePlus, btnpressedPeoplePlus);
        buttonPeoplePlus.setSize(Constants.BTN_WIDTH, Constants.BTNPRES_HEIGHT);
        buttonPeoplePlus.setPosition(barPos.x + Constants.PB_WIDTH + Constants.BTN_WIDTH * 2,
                position.y + Constants.EVENT_HEIGHT - 2*Constants.BTN_HEIGHT);

        btnUpgrade = new TextureRegionDrawable(new TextureRegion(new Texture("btnUpgrade.png")));
        btnpressedUpgrade = new TextureRegionDrawable(new TextureRegion(new Texture("btnpressedUpgrade.png")));
        buttonUpgrade = new ImageButton(btnUpgrade, btnpressedUpgrade);
        buttonUpgrade.setSize((float) (2.5*Constants.BTN_WIDTH), Constants.BTNPRES_HEIGHT);
        buttonUpgrade.setPosition(barPos.x + Constants.PB_WIDTH + Constants.BTN_WIDTH / 2, barPos.y);



    }

    public void updatePosition(float dy){

        position.y -= dy*0.4;

        if (position.y < posY) position.y = posY;
        barPos.y = position.y + barSpacing;
        buttonStart.setY(position.y + Constants.EVENT_HEIGHT - 2*Constants.BTN_HEIGHT);
        buttonPeoplePlus.setY(position.y + Constants.EVENT_HEIGHT - 2*Constants.BTN_HEIGHT);
        buttonUpgrade.setY(barPos.y);

    }

    public void update(float dt){
        pbfilled.update(dt);
        isBarUsed = pbfilled.isEnded();
        if (!isBarUsed) complete();

    }

    private void complete(){
        int k = MathUtils.random(1, 100);
        if (k <= 70){
            PlayState.supplies += 10;
        }
    }

    public Texture getBg() {
        return bg;
    }


    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getBarPos() {
        return barPos;
    }

    public Texture getPbempty() {
        return pbempty;
    }

    public TextureRegion getPbfilled() {
        return pbfilled.getFrame();
    }

    public boolean isBarUsed() {
        return isBarUsed;
    }

    public ImageButton getButtonStart() {
        return buttonStart;
    }

    public ImageButton getButtonPeoplePlus() {
        return buttonPeoplePlus;
    }

    public ImageButton getButtonUpgrade() {
        return buttonUpgrade;
    }

    public void dispose() {
        bg.dispose();
    }

    public void buttonStartListener() {
        if(buttonStart.isPressed() && !isBarUsed){
            PlayState.supplies--;
            isBarUsed = true;
        }

        if(buttonPeoplePlus.isPressed()){
            PlayState.supplies--;
            isBarUsed = true;
        }
    }

}
