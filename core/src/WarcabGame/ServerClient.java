package WarcabGame;

import WarcabGame.Screens.CheckersGameScreen;
import WarcabGame.Screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ServerClient extends Game {

    public SpriteBatch batch;



    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MenuScreen(this));


    }

    public void render() {
        super.render();

    }

}