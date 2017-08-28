package com.pcpc.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.pcpc.game.IdleSurvivalMain;
import com.pcpc.game.sprites.Event1;

    public class PlayState extends State {

        public static final int BASE_HEIGHT = (new Texture("base.png")).getHeight();
        public static final int EVENT_HEIGHT = (new Texture("item.png")).getHeight();

        private Texture base;
        private Event1 event1;
        private Stage stage;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        base = new Texture("base.png");
        event1 = new Event1();
        camera.setToOrtho(false, IdleSurvivalMain.WIDTH / 2, IdleSurvivalMain.HEIGHT / 2);
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3));
        stage.addActor(event1.getButton());
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        if(event1.isBarUsed())
            event1.update(dt);

        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        if(Gdx.input.isTouched()) {
            event1.updatePosition(Gdx.input.getDeltaY());
        }
        sb.begin();
        sb.draw(event1.getBg(), event1.getPosition().x, event1.getPosition().y);
        sb.draw(event1.getPbempty(), event1.getBarPos().x, event1.getBarPos().y);
        sb.draw(event1.getPbfilled(), event1.getBarPos().x, event1.getBarPos().y);
        sb.end();
        stage.draw();
        if(Gdx.input.justTouched()) {
            event1.buttonListener();
        }
        sb.begin();
        sb.draw(base, 0, IdleSurvivalMain.HEIGHT / 2 - BASE_HEIGHT);
        sb.end();

    }

    @Override
    public void dispose() {
        base.dispose();
        event1.dispose();
    }
}
