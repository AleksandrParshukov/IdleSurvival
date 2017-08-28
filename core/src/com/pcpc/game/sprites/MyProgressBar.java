package com.pcpc.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class MyProgressBar {

    public static final int FRAME_COUNT = 100;

    private boolean ended;
    private Array<TextureRegion> bar;
    private float currentTime;
    private int frame;
    private float maxFrameTime;

    public MyProgressBar(TextureRegion region, float time){
        bar = new Array<TextureRegion>();
        for (int i = 0; i < FRAME_COUNT; i++){
            bar.add(new TextureRegion(region, 0, 0, region.getRegionWidth()*i/FRAME_COUNT, region.getRegionHeight()));
        }
        maxFrameTime = time / FRAME_COUNT;
        frame = 0;
        ended = true;
    }

    public void update(float dt){
        currentTime += dt;
        if (currentTime > maxFrameTime){
            ended = true;
            frame++;
            currentTime = 0;
        }

        if (frame >= FRAME_COUNT) {
            frame = 0;
            ended = false;

        }
    }

    public TextureRegion getFrame(){
        return bar.get(frame);
    }

    public boolean isEnded() {
        return ended;
    }
}