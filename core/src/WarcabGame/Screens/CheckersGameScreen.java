package WarcabGame.Screens;

import WarcabGame.ServerClient;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Chessboard.Chessboard;
import com.mygdx.game.Chessboard.Chessboard2;
import com.mygdx.game.EnumOfFileds.FieldPawnEnum;
import com.mygdx.game.Fields.Pawn;
import com.mygdx.game.Fields.Pawn2;

final public class CheckersGameScreen extends Thread implements Screen {

    private final static int LongField = 110;
    private final static int xFrame = 245;
    private final static int yFrame = 48;
    private final static int disXFrame = 260;
    private final static int disYFrame = 60;
    public Chessboard2 chess2 = new Chessboard2();
    private ServerClient serverClient;
    private SpriteBatch batch;
    private Chessboard chessboard;
    private Pawn checkingClickPawn;
    private int checkingFirstClickFlag;
    private int settingValuesFlag;
    private int checkingSecondClickFlag;
    private Texture textureBackgorund;
    private int ilosc;
    private int iloscB;
    private int flaga = 0;

    public CheckersGameScreen(ServerClient serverClient) {
        this.serverClient = serverClient;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        chessboard = new Chessboard();

        textureBackgorund = new Texture("warcaby_tlo.jpg");


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(textureBackgorund, 0, 0);
        batch.draw(chessboard.getFrame_t(), xFrame, yFrame);

        drawChessboard();
        update();
        batch.end();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

//    @Override
    // public void resume() {

    //  }

    @Override
    public void hide() {

    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            // this.batch.dispose();
            //  this.imgDiego.dispose();
            //  this.music.dispose();
            //  this.platformTexture.dispose();
            // this.blood_bar_pasek.dispose();
            // this.blood_bar.dispose();
            serverClient.setScreen(new MenuScreen(serverClient));
        }
        //    if(flaga!=0) {
        //   Server instance = Server.getInstance();
        // if (instance != null) {
        //    chess2 = instance.receive(instance.getSocket());
        //    aktuPoOtrzymaniu();

        //    }
        // }
        drawPawns();
        draw(ilosc);
        drawB(iloscB);
        if (settingValuesFlag == 0) {
            setPostionPawns();
            settingValuesFlag = 1;
        }
        signedNextField(Gdx.input.getX(), Gdx.input.getY());

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveUp(5, 5, 3, 3, 2);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        // chessboard.getFrame_t().dispose();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                chessboard.getFieldsOfChessboard(i, j).getTexture().dispose();
    }

    public void drawPawns() {

        for (Pawn pawn : this.chessboard.getBlackPawns())
            batch.draw(pawn.getTexture(), disXFrame + LongField * pawn.getJ(), disYFrame + LongField * pawn.getI());


        for (Pawn pawn : this.chessboard.getWhitePawns())
            batch.draw(pawn.getTexture(), disXFrame + LongField * pawn.getJ(), disYFrame + LongField * pawn.getI());

    }

    public void signedNextField(int x, int y) {

        Texture whitePawnR = new Texture("BIALY_PIONEK_R_110x110.png");
        Texture blackPawnR = new Texture("CZARNY_PIONEK_R_110x110.png");
        Texture blackPawnDamR = new Texture("CZARNY_DAM_R.png");
        Texture whitePawnDamR = new Texture("BIALY_PIONEK_DAM_R.png");
        for (Pawn pawn : chessboard.getWhitePawns()) {

            if (x >= (pawn.getWidthX()) && x <= (pawn.getWidthX() + LongField) && y <= pawn.getHeightY() && y >= pawn.getHeightY() - 110) {

                if (Gdx.input.isTouched()) {
                    if (pawn.getKindOfPawn() == FieldPawnEnum.WHITE_PAWN_DAM) {
                        batch.draw(whitePawnDamR, disXFrame + pawn.getJ() * LongField, disYFrame + pawn.getI() * LongField);
                    }
                    batch.draw(whitePawnR, disXFrame + pawn.getJ() * LongField, disYFrame + pawn.getI() * LongField);
                    //System.out.println("I : " + pawn.getWidthX());
                    //  System.out.println("J : " + pawn.getHeightY());
                    this.checkingClickPawn = pawn;
                    this.checkingFirstClickFlag = 1;
                    break;
                }

            }
        }


        for (Pawn pawn : this.chessboard.getBlackPawns()) {

            if (x >= (pawn.getWidthX()) && x <= (pawn.getWidthX() + LongField) && y <= pawn.getHeightY() && y >= pawn.getHeightY() - 110) {
                if (Gdx.input.isTouched()) {
                    batch.draw(blackPawnR, disXFrame + pawn.getJ() * LongField, disYFrame + pawn.getI() * LongField);
                    if (pawn.getKindOfPawn() == FieldPawnEnum.BLACK_PAWN_DAM) {
                        batch.draw(blackPawnDamR, disXFrame + pawn.getJ() * LongField, disYFrame + pawn.getI() * LongField);
                    }
                    //  System.out.println("I : " + pawn.getI());
                    //   System.out.println("J : " + pawn.getJ());
                    //    System.out.println(pawn.getKindOfPawn());
                    this.checkingClickPawn = pawn;
                    this.checkingFirstClickFlag = 1;
                    break;
                }
            }
        }

        if (this.checkingClickPawn != null && checkingFirstClickFlag == 1)
            chooseNextField(checkingClickPawn, x, y);
    }

    public void chooseNextField(Pawn pawn, int x, int y) {
        int ilePol = 0;

        Texture blackR = new Texture("CZARNY_P_110x110.png");
        Texture damCz = new Texture("CZARNY_DAM.png");
        Texture damB = new Texture("BIALY_PIONEK_DAM.png");
        int i = pawn.getI();
        int j = pawn.getJ();


        if (pawn.getKindOfPawn() == FieldPawnEnum.BLACK_PAWN) {
            if (j != 0 && j != 7) {
                ilePol = 1;
                if (j - ilePol < 0 || i + ilePol >= 8) {
                    return;
                }
                if (checkStatusField(this.chessboard, i + ilePol, j - ilePol)) {
                    ruszSieWLewo(pawn, i, j, x, y, ilePol);
                } else {
                    ilePol = 2;
                    if (j - ilePol < 0 || i + ilePol >= 8) {
                        ilePol = 1;

                        if (j + ilePol >= 8 || i + ilePol >= 8) {
                            return;
                        }
                        if (checkStatusField(this.chessboard, i + ilePol, j + ilePol)) {
                            ruszSieWPrawo(pawn, i, j, x, y, ilePol);
                        } else {
                            ilePol = 2;
                            if (j + ilePol >= 8 || i + ilePol >= 8) {
                                return;
                            }

                            if (checkStatusField(this.chessboard, i + ilePol, j + ilePol) &&
                                    this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.WHITE_PAWN ||
                                    this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {
                                bijNaPrawo(pawn, i, j, x, y, ilePol);
                            }
                        }
                        return;
                    }

                    if (i + ilePol - 1 >= 8 || j - ilePol - 1 < 0) {

                        ilePol = 1;
                        if (j + ilePol >= 8 || i + ilePol >= 8) {
                            return;
                        }
                        if (checkStatusField(this.chessboard, i + ilePol, j + ilePol)) {
                            ruszSieWPrawo(pawn, i, j, x, y, ilePol);
                        } else {
                            ilePol = 2;
                            if (j + ilePol >= 8 || i + ilePol >= 8) {
                                return;
                            }

                            if (checkStatusField(this.chessboard, i + ilePol, j + ilePol) &&
                                    this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.WHITE_PAWN ||
                                    this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {
                                bijNaPrawo(pawn, i, j, x, y, ilePol);
                            }
                        }

                        ilePol = 2;
                        if (checkStatusField(this.chessboard, i + ilePol, j - ilePol) &&
                                this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.WHITE_PAWN ||
                                this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {
                            bijNaLewo(pawn, i, j, x, y, ilePol);
                        }
                        return;
                    }
                    if (checkStatusField(this.chessboard, i + ilePol, j - ilePol) &&
                            this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.WHITE_PAWN ||
                            this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {
                        bijNaLewo(pawn, i, j, x, y, ilePol);
                    }
                }
                ilePol = 1;
                if (j + ilePol >= 8 || i + ilePol >= 8) {
                    return;
                }
                if (checkStatusField(this.chessboard, i + ilePol, j + ilePol)) {
                    ruszSieWPrawo(pawn, i, j, x, y, ilePol);
                } else {

                    ilePol = 2;
                    if (j + ilePol >= 8 || i + ilePol >= 8) {
                        if (i + ilePol + 1 >= 8)
                            return;
                        if (checkStatusField(this.chessboard, i + ilePol, j - ilePol) &&
                                this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.WHITE_PAWN ||
                                this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {
                            bijNaLewo(pawn, i, j, x, y, ilePol);
                        }
                        return;
                    }

                    if (checkStatusField(this.chessboard, i + ilePol, j + ilePol) &&
                            this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.WHITE_PAWN ||
                            this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {
                        bijNaPrawo(pawn, i, j, x, y, ilePol);
                    }
                }
            }

            if (j == 0) {
                ilePol = 1;
                if (j + ilePol >= 8 || i + ilePol >= 8) {
                    return;
                }
                if (checkStatusField(this.chessboard, i + ilePol, j + ilePol)) {
                    ruszSieWPrawo(pawn, i, j, x, y, ilePol);
                } else {
                    ilePol = 2;
                    if (j + ilePol >= 8 || i + ilePol >= 8) {
                        return;
                    }
                    if (checkStatusField(this.chessboard, i + ilePol, j + ilePol) &&
                            this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.WHITE_PAWN ||
                            this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {
                        bijNaPrawo(pawn, i, j, x, y, ilePol);
                    }
                }
            }
            if (j == 7) {
                ilePol = 1;
                if (j - ilePol < 0 || i + ilePol >= 8) {
                    return;
                }
                if (checkStatusField(this.chessboard, i + ilePol, j - ilePol)) {
                    ruszSieWLewo(pawn, i, j, x, y, ilePol);

                } else {

                    ilePol = 2;
                    if (j - ilePol < 0 || i + ilePol >= 8) {
                        return;
                    }
                    if (checkStatusField(this.chessboard, i + ilePol, j - ilePol) &&
                            this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.WHITE_PAWN ||
                            this.chessboard.getFieldsOfChessboard(i + ilePol - 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.WHITE_PAWN) {
                        bijNaLewo(pawn, i, j, x, y, ilePol);
                    }
                }
            }
        }
        if (pawn.getKindOfPawn() == FieldPawnEnum.WHITE_PAWN || pawn.getKindOfPawn() == FieldPawnEnum.WHITE_PAWN_DAM) {
            ilePol = 1;
            int licznik = 0;
            if (pawn.getKindOfPawn() == FieldPawnEnum.WHITE_PAWN_DAM) {


                //DO GORY W PRAWO
                licznik = dolLewo(pawn, i, j);
                System.out.println(licznik);
                if (licznik != -1) {
                    System.out.println("HALOOOOOOOOOOOOOOOOOOO");
                    batch.draw(blackR, disXFrame + (j - licznik) * LongField, disYFrame + (i - licznik) * LongField);
                    int xNext = pawn.getWidthX() - licznik * LongField;
                    int yNext = pawn.getHeightY() + licznik * LongField;

                    System.out.println(xNext);
                    System.out.println(yNext);
                    if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                        System.out.println("siemaaaaaaa");
                        if (Gdx.input.isTouched()) {
                            this.checkingSecondClickFlag = 1;
                        }
                        if (checkingSecondClickFlag == 1) {
                            moveUp(pawn.getI(), pawn.getJ(), i - licznik, j - licznik, licznik);
                            chessboard.getBlackPawns().remove(searchPawnIndexList(i - licznik + 1, j - licznik + 1));
                            chessboard.getFieldsOfChessboard(i - licznik + 1, j - licznik + 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                            this.iloscB++;
                            checkingSecondClickFlag = 0;
                        }
                    }
                }
                licznik = goraPrawo(pawn, i, j);
                System.out.println(licznik);
                if (licznik != -1) {

                    batch.draw(blackR, disXFrame + (j + licznik) * LongField, disYFrame + (i + licznik) * LongField);
                    int xNext = pawn.getWidthX() + licznik * LongField;
                    int yNext = pawn.getHeightY() - licznik * LongField;

                    //  System.out.println(xNext);
                    //  System.out.println(yNext);
                    if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                        if (Gdx.input.isTouched()) {
                            this.checkingSecondClickFlag = 1;
                        }
                        if (checkingSecondClickFlag == 1) {
                            moveUp(pawn.getI(), pawn.getJ(), i + licznik, j + licznik, licznik);
                            chessboard.getBlackPawns().remove(searchPawnIndexList(i + licznik - 1, j + licznik - 1));
                            chessboard.getFieldsOfChessboard(i + licznik - 1, j + licznik - 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                            this.iloscB++;
                            checkingSecondClickFlag = 0;
                        }
                    }
                } else {


                    licznik = goraLewo(pawn, i, j);

                    if (licznik != -1) {
                        batch.draw(blackR, disXFrame + (j - licznik) * LongField, disYFrame + (i + licznik) * LongField);
                        int xNext = pawn.getWidthX() - licznik * LongField;
                        int yNext = pawn.getHeightY() - licznik * LongField;
                        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                            if (Gdx.input.isTouched()) {
                                this.checkingSecondClickFlag = 1;
                            }
                            if (checkingSecondClickFlag == 1) {
                                moveUp(pawn.getI(), pawn.getJ(), i + licznik, j - licznik, licznik);
                                chessboard.getBlackPawns().remove(searchPawnIndexList(i + licznik - 1, j - licznik + 1));
                                chessboard.getFieldsOfChessboard(i + licznik - 1, j - licznik + 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                                this.iloscB++;
                                checkingSecondClickFlag = 0;
                            }
                        }
                    } else {
                        licznik = dolPrawo(pawn, i, j);
                        System.out.println(licznik);
                        if (licznik != -1) {

                            batch.draw(blackR, disXFrame + (j + licznik) * LongField, disYFrame + (i - licznik) * LongField);
                            int xNext = pawn.getWidthX() + licznik * LongField;
                            int yNext = pawn.getHeightY() + licznik * LongField;

                            System.out.println(xNext);
                            System.out.println(yNext);
                            if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {

                                if (Gdx.input.isTouched()) {
                                    this.checkingSecondClickFlag = 1;
                                }
                                if (checkingSecondClickFlag == 1) {
                                    moveUp(pawn.getI(), pawn.getJ(), i - licznik, j + licznik, licznik);
                                    chessboard.getBlackPawns().remove(searchPawnIndexList(i - licznik + 1, j + licznik - 1));
                                    chessboard.getFieldsOfChessboard(i - licznik + 1, j + licznik - 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                                    this.iloscB++;
                                    checkingSecondClickFlag = 0;
                                }
                            }
                        }
                    }
                    return;

                }


                //DO GORY W LEWO
                licznik = goraLewo(pawn, i, j);

                if (licznik != -1) {
                    batch.draw(blackR, disXFrame + (j - licznik) * LongField, disYFrame + (i + licznik) * LongField);
                    int xNext = pawn.getWidthX() - licznik * LongField;
                    int yNext = pawn.getHeightY() - licznik * LongField;
                    if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                        if (Gdx.input.isTouched()) {
                            this.checkingSecondClickFlag = 1;
                        }
                        if (checkingSecondClickFlag == 1) {
                            moveUp(pawn.getI(), pawn.getJ(), i + licznik, j - licznik, licznik);
                            chessboard.getBlackPawns().remove(searchPawnIndexList(i + licznik - 1, j - licznik + 1));
                            chessboard.getFieldsOfChessboard(i + licznik - 1, j - licznik + 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                            this.iloscB++;
                            checkingSecondClickFlag = 0;
                        }
                    }
                } else {
                    licznik = dolPrawo(pawn, i, j);
                    System.out.println(licznik);
                    if (licznik != -1) {

                        batch.draw(blackR, disXFrame + (j + licznik) * LongField, disYFrame + (i - licznik) * LongField);
                        int xNext = pawn.getWidthX() + licznik * LongField;
                        int yNext = pawn.getHeightY() + licznik * LongField;

                        System.out.println(xNext);
                        System.out.println(yNext);
                        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {

                            if (Gdx.input.isTouched()) {
                                this.checkingSecondClickFlag = 1;
                            }
                            if (checkingSecondClickFlag == 1) {
                                moveUp(pawn.getI(), pawn.getJ(), i - licznik, j + licznik, licznik);
                                chessboard.getBlackPawns().remove(searchPawnIndexList(i - licznik + 1, j + licznik - 1));
                                chessboard.getFieldsOfChessboard(i - licznik - 1, j + licznik - 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                                this.iloscB++;
                                checkingSecondClickFlag = 0;
                            }
                        }
                    }
                }


            }


            if (j != 0 && j != 7) {
                if (j + ilePol >= 8 || i - ilePol < 0) {
                    //   System.out.println("Wychodze za zakres");
                    return;
                }


                if (checkStatusField(this.chessboard, i - ilePol, j + ilePol)) {

                    batch.draw(blackR, disXFrame + (j + ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                    int xNext = pawn.getWidthX() + ilePol * LongField;
                    int yNext = pawn.getHeightY() + ilePol * LongField;

                    if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                        if (Gdx.input.isTouched()) {
                            this.checkingSecondClickFlag = 1;
                        }
                        if (checkingSecondClickFlag == 1) {
                            moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j + ilePol, ilePol);
                            checkingSecondClickFlag = 0;
                        }
                    }

                } else {
                    ilePol = 2;
                    if (j + ilePol >= 8 || i - ilePol < 0) {
                        if (j - ilePol >= 0) {
                            if (i - ilePol < 0)
                                return;
                            System.out.println("mogę bic na lewo");
                            if (checkStatusField(this.chessboard, i - ilePol, j - ilePol) &&
                                    this.chessboard.getFieldsOfChessboard(i - ilePol + 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.BLACK_PAWN) {

                                batch.draw(blackR, disXFrame + (j - ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                                int xNext = pawn.getWidthX() - ilePol * LongField;
                                int yNext = pawn.getHeightY() + ilePol * LongField;

                                if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                                    if (Gdx.input.isTouched()) {
                                        this.checkingSecondClickFlag = 1;
                                    }
                                    if (checkingSecondClickFlag == 1) {
                                        moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j - ilePol, ilePol);
                                        chessboard.getBlackPawns().remove(searchPawnIndexList(pawn.getI() + 1, pawn.getJ() + 1));
                                        chessboard.getFieldsOfChessboard(pawn.getI() + 1, pawn.getJ() + 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                                        this.iloscB++;
                                        checkingSecondClickFlag = 0;
                                    }
                                }
                            }
                        }
                        return;
                    }
                    if (checkStatusField(this.chessboard, i - ilePol, j + ilePol) &&
                            this.chessboard.getFieldsOfChessboard(i - ilePol + 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.BLACK_PAWN) {
                        //  System.out.println("siemka");
                        batch.draw(blackR, disXFrame + (j + ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                        int xNext = pawn.getWidthX() + ilePol * LongField;
                        int yNext = pawn.getHeightY() + ilePol * LongField;

                        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {

                            if (Gdx.input.isTouched()) {
                                this.checkingSecondClickFlag = 1;

                            }
                            if (checkingSecondClickFlag == 1) {
                                System.out.println("bije w prawo");
                                moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j + ilePol, ilePol);
                                chessboard.getBlackPawns().remove(searchPawnIndexList(pawn.getI() + 1, pawn.getJ() - 1));
                                chessboard.getFieldsOfChessboard(pawn.getI() + 1, pawn.getJ() - 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                                this.iloscB++;
                                checkingSecondClickFlag = 0;
                            }
                        }
                    }

                }
                ilePol = 1;
                if (j - ilePol < 0 || i - ilePol < 0) {
                    // System.out.println("Wychodze za zakres");
                    return;
                }
                if (checkStatusField(this.chessboard, i - ilePol, j - ilePol)) {

                    batch.draw(blackR, disXFrame + (j - ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                    int xNext = pawn.getWidthX() - ilePol * LongField;
                    int yNext = pawn.getHeightY() + ilePol * LongField;
                    if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                        if (Gdx.input.isTouched()) {
                            this.checkingSecondClickFlag = 1;
                        }
                        if (checkingSecondClickFlag == 1) {
                            moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j - ilePol, ilePol);
                            checkingSecondClickFlag = 0;
                        }
                    }
                } else {
                    ilePol = 2;
                    if (j - ilePol < 0 || i - ilePol < 0) {
                        //   System.out.println("Wychodze za zakres");
                        return;
                    }
                    if (checkStatusField(this.chessboard, i - ilePol, j - ilePol) &&
                            this.chessboard.getFieldsOfChessboard(i - ilePol + 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.BLACK_PAWN) {

                        batch.draw(blackR, disXFrame + (j - ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                        int xNext = pawn.getWidthX() - ilePol * LongField;
                        int yNext = pawn.getHeightY() + ilePol * LongField;

                        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                            if (Gdx.input.isTouched()) {
                                this.checkingSecondClickFlag = 1;
                            }
                            if (checkingSecondClickFlag == 1) {
                                moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j - ilePol, ilePol);
                                chessboard.getBlackPawns().remove(searchPawnIndexList(pawn.getI() + 1, pawn.getJ() + 1));
                                chessboard.getFieldsOfChessboard(pawn.getI() + 1, pawn.getJ() + 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                                this.iloscB++;
                                checkingSecondClickFlag = 0;
                            }
                        }
                    }
                }
            }

            if (j == 0) {
                if (j + ilePol >= 8 || i - ilePol < 0) {
                    // System.out.println("Wychodze za zakres");
                    return;
                }
                if (checkStatusField(this.chessboard, i - ilePol, j + ilePol)) {

                    batch.draw(blackR, disXFrame + (j + ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                    int xNext = pawn.getWidthX() + ilePol * LongField;
                    int yNext = pawn.getHeightY() + ilePol * LongField;

                    if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                        if (Gdx.input.isTouched()) {
                            this.checkingSecondClickFlag = 1;
                        }
                        if (checkingSecondClickFlag == 1) {
                            moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j + ilePol, ilePol);
                            checkingSecondClickFlag = 0;
                        }
                    }
                } else {
                    ilePol = 2;
                    if (j + ilePol >= 8 || i - ilePol < 0) {
                        //     System.out.println("Wychodze za zakres");
                        return;
                    }
                    if (checkStatusField(this.chessboard, i - ilePol, j + ilePol) &&
                            this.chessboard.getFieldsOfChessboard(i - ilePol + 1, j + ilePol - 1).getStatusField() == FieldPawnEnum.BLACK_PAWN) {

                        batch.draw(blackR, disXFrame + (j + ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                        int xNext = pawn.getWidthX() + ilePol * LongField;
                        int yNext = pawn.getHeightY() + ilePol * LongField;

                        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                            if (Gdx.input.isTouched()) {
                                this.checkingSecondClickFlag = 1;
                            }
                            if (checkingSecondClickFlag == 1) {
                                moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j + ilePol, ilePol);
                                chessboard.getBlackPawns().remove(searchPawnIndexList(pawn.getI() + 1, pawn.getJ() - 1));
                                chessboard.getFieldsOfChessboard(pawn.getI() + 1, pawn.getJ() - 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                                this.iloscB++;
                                checkingSecondClickFlag = 0;
                            }
                        }
                    }
                }
            }
            if (j == 7) {
                if (j - ilePol < 0 || i - ilePol < 0) {
                    //   System.out.println("Wychodze za zakres");
                    return;
                }
                if (checkStatusField(this.chessboard, i - ilePol, j - ilePol)) {

                    batch.draw(blackR, disXFrame + (j - ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                    int xNext = pawn.getWidthX() - ilePol * LongField;
                    int yNext = pawn.getHeightY() + ilePol * LongField;

                    if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                        if (Gdx.input.isTouched()) {
                            this.checkingSecondClickFlag = 1;
                        }
                        if (checkingSecondClickFlag == 1) {
                            moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j - ilePol, ilePol);
                            checkingSecondClickFlag = 0;
                        }
                    }
                } else {
                    ilePol = 2;
                    if (j - ilePol < 0 || i - ilePol < 0) {
                        //     System.out.println("Wychodze za zakres");
                        return;
                    }
                    if (checkStatusField(this.chessboard, i - ilePol, j - ilePol) &&
                            this.chessboard.getFieldsOfChessboard(i - ilePol + 1, j - ilePol + 1).getStatusField() == FieldPawnEnum.BLACK_PAWN) {

                        batch.draw(blackR, disXFrame + (j - ilePol) * LongField, disYFrame + (i - ilePol) * LongField);
                        int xNext = pawn.getWidthX() - ilePol * LongField;
                        int yNext = pawn.getHeightY() + ilePol * LongField;

                        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
                            if (Gdx.input.isTouched()) {
                                this.checkingSecondClickFlag = 1;
                            }
                            if (checkingSecondClickFlag == 1) {
                                moveUp(pawn.getI(), pawn.getJ(), i - ilePol, j - ilePol, ilePol);
                                chessboard.getBlackPawns().remove(searchPawnIndexList(pawn.getI() + 1, pawn.getJ() + 1));
                                chessboard.getFieldsOfChessboard(pawn.getI() + 1, pawn.getJ() + 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                                this.iloscB++;
                                checkingSecondClickFlag = 0;

                            }
                        }
                    }
                }
            }
        }
    }

    public void drawChessboard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    } else {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    }
                } else {
                    if (j % 2 != 0) {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    } else {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    }
                }
            }
        }

        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    } else {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    }
                } else {
                    if (j % 2 != 0) {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    } else {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    }
                }
            }
        }

        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    } else {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    }
                } else {
                    if (j % 2 != 0) {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());
                    } else {
                        batch.draw(chessboard.getFieldsOfChessboard(i, j).getTexture(),
                                disXFrame + chessboard.getFieldsOfChessboard(i, j).getTexture().getWidth() * j, disYFrame + i * chessboard.getFieldsOfChessboard(i, j).getTexture().getHeight());

                    }
                }
            }
        }
    }

    public boolean moveUp(int i, int j, int i1, int j1, int howMuch) {
        if (checkDate(i, j, i1, j1)) {
            if (checkStatusField(chessboard, i1, j1)) {
                int indexPawns = searchPawnIndexList(i, j);

                if (indexPawns <= 11 && indexPawns >= 0) {
                    //  System.out.println(indexPawns);
                    updateChessboard(i, j, i1, j1, indexPawns, chessboard, howMuch);
                    //  Server instance = Server.getInstance();
                    //tutaj aktualizuje z chess1 na chess2 i przesylam chess 2
                    //  aktuChess2();
                    //  instance.send(instance.getSocket(),chess2);
                    //    flaga++;
                    //    try {
                    //        Thread.sleep(15000);
                    //  } catch (InterruptedException e) {}
                    return true;
                } else
                    return false;
            } else
                return false;
        } else
            return false;
    }

    public int searchPawnIndexList(int i, int j) {
        if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN) {
            for (int k = 0; k < 12; k++) {
                if (chessboard.getBlackPawns().get(k).getI() == chessboard.getFieldsOfChessboard(i, j).getI() &&
                        chessboard.getBlackPawns().get(k).getJ() == chessboard.getFieldsOfChessboard(i, j).getJ())
                    return k;
            }
        } else if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.WHITE_PAWN) {
            for (int k = 0; k < 12; k++) {
                if (chessboard.getWhitePawns().get(k).getI() == chessboard.getFieldsOfChessboard(i, j).getI() &&
                        chessboard.getWhitePawns().get(k).getJ() == chessboard.getFieldsOfChessboard(i, j).getJ())
                    return k;
            }
        } else if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {
            for (int k = 0; k < 12; k++) {
                if (chessboard.getWhitePawns().get(k).getI() == chessboard.getFieldsOfChessboard(i, j).getI() &&
                        chessboard.getWhitePawns().get(k).getJ() == chessboard.getFieldsOfChessboard(i, j).getJ())
                    return k;
            }
        }
        return -1;

    }

    public boolean checkDate(int i, int j, int i1, int j1) {
        if (i1 != i && j1 != j)
            return true;
        else {
            System.out.println("Zle i1 lub j1");
            return false;
        }
    }

    public boolean checkStatusField(Chessboard chessboard, int i1, int j1) {

        return chessboard.getFieldsOfChessboard(i1, j1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN;
    }

    public boolean updateChessboard(int i, int j, int i1, int j1, int indexPawns, Chessboard chessboard, int howMuch) {
        Texture blackPawnT = new Texture("CZARNY_PIONEK_110x110.png");
        Texture whitePawnT = new Texture("BIALY_PIONEK_110x110.png");
        Texture damCz = new Texture("CZARNY_DAM.png");
        Texture damB = new Texture("BIALY_PIONEK_DAM.png");


        int ile = 0;
        if (howMuch > 1) {
            ile = howMuch * LongField;
        } else
            ile = LongField;

        if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.WHITE_PAWN_DAM) {

            chessboard.getWhitePawns().get(indexPawns).getTexture().dispose();
            chessboard.getWhitePawns().get(indexPawns).setTexture(damB);

            chessboard.getWhitePawns().get(indexPawns).setiLast(i);
            chessboard.getWhitePawns().get(indexPawns).setjLast(j);

            chessboard.getWhitePawns().get(indexPawns).setI(i1);
            chessboard.getWhitePawns().get(indexPawns).setJ(j1);


            chessboard.getFieldsOfChessboard(i1, j1).setStatusField(FieldPawnEnum.WHITE_PAWN_DAM);
            chessboard.getFieldsOfChessboard(i, j).setStatusField(FieldPawnEnum.WITHOUT_PAWN);

            if (i1 > i && j1 > j) {
                this.chessboard.getWhitePawns().get(indexPawns).setWidthX(chessboard.getWhitePawns().get(indexPawns).getWidthX() + ile);
                this.chessboard.getWhitePawns().get(indexPawns).setHeightY(chessboard.getWhitePawns().get(indexPawns).getHeightY() - ile);
            }

            if (i1 > i && j1 < j) {
                this.chessboard.getWhitePawns().get(indexPawns).setWidthX(chessboard.getWhitePawns().get(indexPawns).getWidthX() - ile);
                this.chessboard.getWhitePawns().get(indexPawns).setHeightY(chessboard.getWhitePawns().get(indexPawns).getHeightY() - ile);
            }

            if (i1 < i && j1 > j) {
                this.chessboard.getWhitePawns().get(indexPawns).setWidthX(chessboard.getWhitePawns().get(indexPawns).getWidthX() + ile);
                this.chessboard.getWhitePawns().get(indexPawns).setHeightY(chessboard.getWhitePawns().get(indexPawns).getHeightY() + ile);
            }

            if (i1 < i && j1 < j) {
                this.chessboard.getWhitePawns().get(indexPawns).setWidthX(chessboard.getWhitePawns().get(indexPawns).getWidthX() - ile);
                this.chessboard.getWhitePawns().get(indexPawns).setHeightY(chessboard.getWhitePawns().get(indexPawns).getHeightY() + ile);
            }

            return true;
        }

        if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN) {

            chessboard.getBlackPawns().get(indexPawns).getTexture().dispose();
            chessboard.getBlackPawns().get(indexPawns).setTexture(blackPawnT);

            chessboard.getBlackPawns().get(indexPawns).setiLast(i);
            chessboard.getBlackPawns().get(indexPawns).setjLast(j);

            chessboard.getBlackPawns().get(indexPawns).setI(i1);
            chessboard.getBlackPawns().get(indexPawns).setJ(j1);
            if (i1 == 7) {
                chessboard.getBlackPawns().get(indexPawns).setKindOfPawn(FieldPawnEnum.BLACK_PAWN_DAM);
                chessboard.getBlackPawns().get(indexPawns).setTexture(damCz);
            }

            chessboard.getFieldsOfChessboard(i1, j1).setStatusField(FieldPawnEnum.BLACK_PAWN);
            chessboard.getFieldsOfChessboard(i, j).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
            if (i1 > i && j1 > j) {
                this.chessboard.getBlackPawns().get(indexPawns).setWidthX(chessboard.getBlackPawns().get(indexPawns).getWidthX() + ile);
                this.chessboard.getBlackPawns().get(indexPawns).setHeightY(chessboard.getBlackPawns().get(indexPawns).getHeightY() - ile);
            }

            if (i1 > i && j1 < j) {
                this.chessboard.getBlackPawns().get(indexPawns).setWidthX(chessboard.getBlackPawns().get(indexPawns).getWidthX() - ile);
                this.chessboard.getBlackPawns().get(indexPawns).setHeightY(chessboard.getBlackPawns().get(indexPawns).getHeightY() - ile);
            }
            return true;
        } else if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.WHITE_PAWN) {
            System.out.println("HEJ");
            chessboard.getWhitePawns().get(indexPawns).getTexture().dispose();
            chessboard.getWhitePawns().get(indexPawns).setTexture(whitePawnT);

            chessboard.getWhitePawns().get(indexPawns).setiLast(i);
            chessboard.getWhitePawns().get(indexPawns).setjLast(j);

            chessboard.getWhitePawns().get(indexPawns).setI(i1);
            chessboard.getWhitePawns().get(indexPawns).setJ(j1);
            if (i1 == 0) {
                chessboard.getWhitePawns().get(indexPawns).setKindOfPawn(FieldPawnEnum.WHITE_PAWN_DAM);
                chessboard.getFieldsOfChessboard(i1, j1).setStatusField(FieldPawnEnum.WHITE_PAWN_DAM);
                chessboard.getFieldsOfChessboard(i, j).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                chessboard.getWhitePawns().get(indexPawns).setTexture(damB);
            } else {

                chessboard.getFieldsOfChessboard(i1, j1).setStatusField(FieldPawnEnum.WHITE_PAWN);
                chessboard.getFieldsOfChessboard(i, j).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
            }
            if (i1 < i && j1 > j) {
                this.chessboard.getWhitePawns().get(indexPawns).setWidthX(chessboard.getWhitePawns().get(indexPawns).getWidthX() + ile);
                this.chessboard.getWhitePawns().get(indexPawns).setHeightY(chessboard.getWhitePawns().get(indexPawns).getHeightY() + ile);
            }

            if (i1 < i && j1 < j) {
                this.chessboard.getWhitePawns().get(indexPawns).setWidthX(chessboard.getWhitePawns().get(indexPawns).getWidthX() - ile);
                this.chessboard.getWhitePawns().get(indexPawns).setHeightY(chessboard.getWhitePawns().get(indexPawns).getHeightY() + ile);
            }
            return true;
        } else {
            return false;
        }
    }

    public void draw(int licznik) {
        int i = 0;
        int y = 110;
        int x = 0;
        int help = 1;
        for (i = 1; i <= licznik; i++) {

            y = 110 * i;
            if (i >= 7) {
                x = -120;
                y = 110 * help;
                help++;
            }
            batch.draw(new Texture("BIALY_PIONEK_110x110.png"), 1288 + x, 993 - y);
        }

    }

    public void drawB(int licznik) {
        int i = 0;
        int y = 110;
        int x = 0;
        int help = 1;
        for (i = 1; i <= licznik; i++) {

            y = 110 * i;
            if (i >= 7) {
                x = 113;
                y = 110 * help;
                help++;
            }
            batch.draw(new Texture("CZARNY_PIONEK_110x110.png"), 4 + x, 993 - y);
        }

    }


    public void setPostionPawns() {
        this.chessboard.getBlackPawns().get(0).setWidthX(disXFrame);
        this.chessboard.getBlackPawns().get(0).setHeightY(940);

        this.chessboard.getBlackPawns().get(1).setWidthX(480);
        this.chessboard.getBlackPawns().get(1).setHeightY(940);

        this.chessboard.getBlackPawns().get(2).setWidthX(700);
        this.chessboard.getBlackPawns().get(2).setHeightY(940);

        this.chessboard.getBlackPawns().get(3).setWidthX(920);
        this.chessboard.getBlackPawns().get(3).setHeightY(940);

        this.chessboard.getBlackPawns().get(4).setWidthX(370);
        this.chessboard.getBlackPawns().get(4).setHeightY(830);

        this.chessboard.getBlackPawns().get(5).setWidthX(590);
        this.chessboard.getBlackPawns().get(5).setHeightY(830);

        this.chessboard.getBlackPawns().get(6).setWidthX(810);
        this.chessboard.getBlackPawns().get(6).setHeightY(830);

        this.chessboard.getBlackPawns().get(7).setWidthX(1030);
        this.chessboard.getBlackPawns().get(7).setHeightY(830);

        this.chessboard.getBlackPawns().get(8).setWidthX(disXFrame);
        this.chessboard.getBlackPawns().get(8).setHeightY(720);

        this.chessboard.getBlackPawns().get(9).setWidthX(480);
        this.chessboard.getBlackPawns().get(9).setHeightY(720);

        this.chessboard.getBlackPawns().get(10).setWidthX(700);
        this.chessboard.getBlackPawns().get(10).setHeightY(720);

        this.chessboard.getBlackPawns().get(11).setWidthX(920);
        this.chessboard.getBlackPawns().get(11).setHeightY(720);

        this.chessboard.getWhitePawns().get(0).setWidthX(370);
        this.chessboard.getWhitePawns().get(0).setHeightY(390);

        this.chessboard.getWhitePawns().get(1).setWidthX(590);
        this.chessboard.getWhitePawns().get(1).setHeightY(390);

        this.chessboard.getWhitePawns().get(2).setWidthX(810);
        this.chessboard.getWhitePawns().get(2).setHeightY(390);

        this.chessboard.getWhitePawns().get(3).setWidthX(1030);
        this.chessboard.getWhitePawns().get(3).setHeightY(390);

        this.chessboard.getWhitePawns().get(4).setWidthX(disXFrame);
        this.chessboard.getWhitePawns().get(4).setHeightY(280);

        this.chessboard.getWhitePawns().get(5).setWidthX(480);
        this.chessboard.getWhitePawns().get(5).setHeightY(280);

        this.chessboard.getWhitePawns().get(6).setWidthX(700);
        this.chessboard.getWhitePawns().get(6).setHeightY(280);

        this.chessboard.getWhitePawns().get(7).setWidthX(920);
        this.chessboard.getWhitePawns().get(7).setHeightY(280);

        this.chessboard.getWhitePawns().get(8).setWidthX(370);
        this.chessboard.getWhitePawns().get(8).setHeightY(170);

        this.chessboard.getWhitePawns().get(9).setWidthX(590);
        this.chessboard.getWhitePawns().get(9).setHeightY(170);

        this.chessboard.getWhitePawns().get(10).setWidthX(810);
        this.chessboard.getWhitePawns().get(10).setHeightY(170);

        this.chessboard.getWhitePawns().get(11).setWidthX(1030);
        this.chessboard.getWhitePawns().get(11).setHeightY(170);


    }

    public void ruszSieWLewo(Pawn pawn, int i, int j, int x, int y, int ilePol) {
        Texture blackR = new Texture("CZARNY_P_110x110.png");
        batch.draw(blackR, disXFrame + (j - ilePol) * LongField, disYFrame + (i + ilePol) * LongField);
        int xNext = pawn.getWidthX() - LongField;
        int yNext = pawn.getHeightY() - LongField;

        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
            if (Gdx.input.isTouched()) {
                this.checkingSecondClickFlag = 1;
            }
            if (checkingSecondClickFlag == 1) {
                moveUp(pawn.getI(), pawn.getJ(), i + ilePol, j - ilePol, ilePol);
                checkingSecondClickFlag = 0;
            }
        }
    }

    public void ruszSieWPrawo(Pawn pawn, int i, int j, int x, int y, int ilePol) {
        Texture blackR = new Texture("CZARNY_P_110x110.png");
        batch.draw(blackR, disXFrame + (j + ilePol) * LongField, disYFrame + (i + ilePol) * LongField);
        int xNext = pawn.getWidthX() + LongField;
        int yNext = pawn.getHeightY() - LongField;

        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
            if (Gdx.input.isTouched()) {
                this.checkingSecondClickFlag = 1;
            }
            if (checkingSecondClickFlag == 1) {
                moveUp(pawn.getI(), pawn.getJ(), i + ilePol, j + ilePol, ilePol);
                checkingSecondClickFlag = 0;
            }
        }
    }

    public void bijNaPrawo(Pawn pawn, int i, int j, int x, int y, int ilePol) {
        Texture blackR = new Texture("CZARNY_P_110x110.png");
        batch.draw(blackR, disXFrame + (j + ilePol) * LongField, disYFrame + (i + ilePol) * LongField);
        int xNext = pawn.getWidthX() + ilePol * LongField;
        int yNext = pawn.getHeightY() - ilePol * LongField;

        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
            if (Gdx.input.isTouched()) {
                this.checkingSecondClickFlag = 1;
            }
            if (checkingSecondClickFlag == 1) {
                moveUp(pawn.getI(), pawn.getJ(), i + ilePol, j + ilePol, ilePol);
                chessboard.getWhitePawns().remove(searchPawnIndexList(pawn.getI() - 1, pawn.getJ() - 1));
                chessboard.getFieldsOfChessboard(pawn.getI() - 1, pawn.getJ() - 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                this.ilosc++;
                System.out.println(ilosc);
                this.checkingSecondClickFlag = 0;
            }

        }
    }

    public void bijNaLewo(Pawn pawn, int i, int j, int x, int y, int ilePol) {
        Texture blackR = new Texture("CZARNY_P_110x110.png");
        batch.draw(blackR, disXFrame + (j - ilePol) * LongField, disYFrame + (i + ilePol) * LongField);
        int xNext = pawn.getWidthX() - ilePol * LongField;
        int yNext = pawn.getHeightY() - ilePol * LongField;

        if (x >= (xNext) && x <= (xNext + LongField) && y <= yNext && y >= yNext - LongField) {
            if (Gdx.input.isTouched()) {
                this.checkingSecondClickFlag = 1;
            }
            if (checkingSecondClickFlag == 1) {
                moveUp(pawn.getI(), pawn.getJ(), i + ilePol, j - ilePol, ilePol);
                chessboard.getWhitePawns().remove(searchPawnIndexList(pawn.getI() - 1, pawn.getJ() + 1));
                chessboard.getFieldsOfChessboard(pawn.getI() - 1, pawn.getJ() + 1).setStatusField(FieldPawnEnum.WITHOUT_PAWN);
                this.ilosc++;
                System.out.println(ilosc);
                this.checkingSecondClickFlag = 0;
            }

        }
    }


    public void aktuChess2() {
        //ustawienie pionkow czarnych
        int i = 0;
        for (Pawn pawn : chessboard.getBlackPawns()) {
            chess2.getBlackPawns().get(i).setI(pawn.getI());
            chess2.getBlackPawns().get(i).setJ(pawn.getJ());
            chess2.getBlackPawns().get(i).setiLast(pawn.getiLast());
            chess2.getBlackPawns().get(i).setjLast(pawn.getjLast());
            i++;
        }

        int j = 0;
        for (Pawn pawn : chessboard.getWhitePawns()) {
            chess2.getWhitePawns().get(j).setI(pawn.getI());
            chess2.getWhitePawns().get(j).setJ(pawn.getJ());
            chess2.getWhitePawns().get(j).setiLast(pawn.getiLast());
            chess2.getWhitePawns().get(j).setjLast(pawn.getjLast());
            j++;
        }

        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                chess2.getFieldsOfChessboard(k, l).setFieldColor(chessboard.getFieldsOfChessboard(k, l).getFieldColor());
                chess2.getFieldsOfChessboard(k, l).setStatusField(chessboard.getFieldsOfChessboard(k, l).getStatusField());
                chess2.getFieldsOfChessboard(k, l).setI(chessboard.getFieldsOfChessboard(k, l).getI());
                chess2.getFieldsOfChessboard(k, l).setJ(chessboard.getFieldsOfChessboard(k, l).getJ());
            }

        }
    }

    public void aktuPoOtrzymaniu() {
        //ustawienie pionkow czarnych
        int i = 0;
        for (Pawn2 pawn : chess2.getBlackPawns()) {
            chessboard.getBlackPawns().get(i).setI(pawn.getI());
            chessboard.getBlackPawns().get(i).setJ(pawn.getJ());
            chessboard.getBlackPawns().get(i).setiLast(pawn.getiLast());
            chessboard.getBlackPawns().get(i).setjLast(pawn.getjLast());
            i++;
        }

        int j = 0;
        for (Pawn pawn : chessboard.getWhitePawns()) {
            chessboard.getWhitePawns().get(j).setI(pawn.getI());
            chessboard.getWhitePawns().get(j).setJ(pawn.getJ());
            chessboard.getWhitePawns().get(j).setiLast(pawn.getiLast());
            chessboard.getWhitePawns().get(j).setjLast(pawn.getjLast());
            i++;
        }

        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                chessboard.getFieldsOfChessboard(k, l).setFieldColor(chess2.getFieldsOfChessboard(k, l).getFieldColor());
                chessboard.getFieldsOfChessboard(k, l).setStatusField(chess2.getFieldsOfChessboard(k, l).getStatusField());
                chessboard.getFieldsOfChessboard(k, l).setI(chess2.getFieldsOfChessboard(k, l).getI());
                chessboard.getFieldsOfChessboard(k, l).setJ(chess2.getFieldsOfChessboard(k, l).getJ());
            }

        }
    }


    public int goraPrawo(Pawn pawn, int i, int j) {
        int licznik = 0;
        for (j = pawn.getJ(); j < 8; ++j, ++i) {
            if (i + 1 >= 8)
                return -1;
            if (j + 1 >= 8)
                return -1;


            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.WITHOUT_PAWN) {
                licznik++;
                continue;
            }
            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN && licznik != 0 &&
                    chessboard.getFieldsOfChessboard(i + 1, j + 1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN) {
                return licznik + 2;
            }

            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN &&
                    chessboard.getFieldsOfChessboard(i + 1, j + 1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN)
                return 2;

            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN &&
                    chessboard.getFieldsOfChessboard(i + 1, j + 1).getStatusField() == FieldPawnEnum.BLACK_PAWN)
                return -1;

        }

        return -1;
    }


    public int goraLewo(Pawn pawn, int i, int j) {
        int licznik = 0;
        for (j = pawn.getJ(); j < 8; j--, ++i) {
            if (j - 1 < 0 || i + 1 >= 8)
                return -1;


            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.WITHOUT_PAWN) {
                licznik++;
                continue;
            }
            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN && licznik != 0 &&
                    chessboard.getFieldsOfChessboard(i + 1, j - 1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN) {
                return licznik + 2;
            }

            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN &&
                    chessboard.getFieldsOfChessboard(i + 1, j - 1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN)
                return 2;

            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN &&
                    chessboard.getFieldsOfChessboard(i + 1, j - 1).getStatusField() == FieldPawnEnum.BLACK_PAWN)
                break;

        }
        return -1;
    }

    public int dolPrawo(Pawn pawn, int i, int j) {
        int licznik = 0;
        for (j = pawn.getJ(); j < 8; ++j, --i) {
            if (i - 1 < 0 || j + 1 >= 8) {
                System.out.println("blad");
                return -1;
            }

            System.out.println("HALO");
            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.WITHOUT_PAWN) {
                licznik++;
                continue;
            }
            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN && licznik != 0 &&
                    chessboard.getFieldsOfChessboard(i - 1, j + 1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN) {
                return licznik + 2;
            }

            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN &&
                    chessboard.getFieldsOfChessboard(i - 1, j + 1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN)
                return 2;

            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN &&
                    chessboard.getFieldsOfChessboard(i - 1, j + 1).getStatusField() == FieldPawnEnum.BLACK_PAWN)
                return -1;

        }

        return -1;
    }

    public int dolLewo(Pawn pawn, int i, int j) {
        int licznik = 0;
        for (j = pawn.getJ(); j < 8; --j, --i) {
            if (i - 1 < 0 || j - 1 < 0) {
                System.out.println("blad");
                return -1;
            }

            System.out.println("HALO");
            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.WITHOUT_PAWN) {
                licznik++;
                continue;
            }
            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN && licznik != 0 &&
                    chessboard.getFieldsOfChessboard(i - 1, j - 1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN) {
                return licznik + 2;
            }

            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN &&
                    chessboard.getFieldsOfChessboard(i - 1, j - 1).getStatusField() == FieldPawnEnum.WITHOUT_PAWN)
                return 2;

            if (chessboard.getFieldsOfChessboard(i, j).getStatusField() == FieldPawnEnum.BLACK_PAWN &&
                    chessboard.getFieldsOfChessboard(i - 1, j - 1).getStatusField() == FieldPawnEnum.BLACK_PAWN)
                return -1;

        }

        return -1;
    }


}