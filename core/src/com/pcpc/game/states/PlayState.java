package com.pcpc.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.pcpc.game.Constants;
import com.pcpc.game.sprites.Event1;

    public class PlayState extends State {

        public static long supplies, survivors;
        private Texture base;
        private Event1 event1;
        private Stage stage;

        private BitmapFont font;
        private FreeTypeFontGenerator generator;
        private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
        private Texture phon;

        final String FONT_CHARS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    public PlayState(GameStateManager gsm) {
        super(gsm);
        base = new Texture("base.png");
        event1 = new Event1();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage.addActor(event1.getButtonStart());
        stage.addActor(event1.getButtonPeoplePlus());
        stage.addActor(event1.getButtonUpgrade());
        supplies = 5;
        survivors = 2;

        phon = new Texture("phon.png");

        generator = new FreeTypeFontGenerator(Gdx.files.internal("ENIGMAU.TTF"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 35;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        generator.dispose();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            event1.updatePosition(Gdx.input.getDeltaY());
        }

        if(Gdx.input.justTouched()) {
            event1.buttonStartListener();
        }
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

        sb.begin();
        sb.draw(event1.getBg(), event1.getPosition().x, event1.getPosition().y, Gdx.graphics.getWidth(), Constants.EVENT_HEIGHT);
        sb.draw(event1.getPbempty(), event1.getBarPos().x, event1.getBarPos().y, Constants.PB_WIDTH, Constants.PB_HEIGHT);
        sb.draw(event1.getPbfilled(), event1.getBarPos().x, event1.getBarPos().y, event1.getPbfilled().getRegionWidth(), Constants.PB_HEIGHT);
        sb.end();

        stage.draw();

        sb.begin();
        sb.draw(base, 0, Gdx.graphics.getHeight() - Constants.BASE_HEIGHT, Gdx.graphics.getWidth(), Constants.BASE_HEIGHT);

        font.draw(sb, String.valueOf(supplies), 60, Gdx.graphics.getHeight() - 10);sb.draw(phon, 0, Gdx.graphics.getHeight() - phon.getHeight(), 0, Constants.PHON_HEIGHT);
        sb.end();

    }

    @Override
    public void dispose() {
        base.dispose();
        event1.dispose();
    }
}
