package WarcabGame.Screens;

import WarcabGame.ServerClient;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen implements Screen {
    ServerClient serwerClient;
    private static final int WIDTH_MENU_BUTTON = 250;
    private static final int HEIGHT_MENU_BUTTON = 35;
    private static final int WIDTH_SCREEN = 1400;
    private static final int HEIGHT_SCREEN = 700;
    private Texture screenTexture,new_game_t,new_game_t_c,settings_t,settings_t_c,exit_t,exit_t_c,back_t,back_t_c;

    public MenuScreen(ServerClient serwerClient){
        this.serwerClient = serwerClient;
        this.screenTexture = new Texture("menuScreen.png");
        this.new_game_t = new Texture("nowa gra.jpg");
        this.new_game_t_c = new Texture("nowa gra_c.jpg");

        this.exit_t = new Texture("wyjscie.jpg");
        this.exit_t_c = new Texture("wyjscie_c.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(16384);

        serwerClient.batch.begin();
        serwerClient.batch.draw(screenTexture,0,0);
        int x = 575;
        if(Gdx.input.getX()>575 && Gdx.input.getX()<825 && Gdx.input.getY()<204 && Gdx.input.getY()>170){
            //new_game_t.dispose();
            serwerClient.batch.draw(new_game_t_c, 700 - WIDTH_MENU_BUTTON / 2, 500, WIDTH_MENU_BUTTON, 36);
            if(Gdx.input.isTouched()) {
                serwerClient.setScreen(new CheckersGameScreen(serwerClient));
            }
        }
        else {
            serwerClient.batch.draw(new_game_t, 700 - WIDTH_MENU_BUTTON / 2, 500, WIDTH_MENU_BUTTON, 36);
        }

        if(Gdx.input.getX()>575 && Gdx.input.getX()<825 && Gdx.input.getY()<490 && Gdx.input.getY()>450){
            //new_game_t.dispose();
            serwerClient.batch.draw(exit_t_c, 700 - WIDTH_MENU_BUTTON/2,200,WIDTH_MENU_BUTTON,36);
            if(Gdx.input.isTouched())
            {
                Gdx.app.exit();
            }
        }
        else {
            serwerClient.batch.draw(exit_t, 700 - WIDTH_MENU_BUTTON/2,200,WIDTH_MENU_BUTTON,36);
        }

        //   game.batch.draw(new_game_t,700 - new_game_t.getWidth()/2,500,new_game_t.getWidth(),36);
       // serwerClient.batch.draw(back_t,700 - WIDTH_MENU_BUTTON/2,400,WIDTH_MENU_BUTTON,36);
     //   serwerClient.batch.draw(settings_t,700 - WIDTH_MENU_BUTTON/2,300,WIDTH_MENU_BUTTON,36);
        //  game.batch.draw(exit_t,700 - WIDTH_MENU_BUTTON/2,200,WIDTH_MENU_BUTTON,36);
        serwerClient.batch.end();


    }
    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
