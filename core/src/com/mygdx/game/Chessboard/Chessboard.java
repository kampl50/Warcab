package com.mygdx.game.Chessboard;

import ClinetServer.Client;
import ClinetServer.ClientServer;
import ClinetServer.Server;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.EnumOfFileds.FieldColorEnum;
import com.mygdx.game.EnumOfFileds.FieldPawnEnum;
import com.mygdx.game.Fields.Field;
import com.mygdx.game.Fields.Pawn;

import java.io.Serializable;
import java.util.ArrayList;

public class Chessboard implements Serializable {
    private ClientServer clientServer;
    private Texture white_t, black_t, black_t_p, white_t_p, frame_t;
    private Field fieldsOfChessboard[][] = new Field[8][8];
    private ArrayList<Pawn> blackPawns = new ArrayList();
    private ArrayList<Pawn> whitePawns = new ArrayList();


    public Chessboard() {
        loadDate();
        generateChessboard();

    }

    public Texture getFrame_t() {
        return frame_t;
    }

    public void setFrame_t(Texture frame_t) {
        this.frame_t = frame_t;
    }

    public Field getFieldsOfChessboard(int i, int j) {
        return fieldsOfChessboard[i][j];

    }

    public ArrayList<Pawn> getBlackPawns() {
        return blackPawns;
    }

    public void setBlackPawns(ArrayList<Pawn> blackPawns) {
        this.blackPawns = blackPawns;
    }

    public ArrayList<Pawn> getWhitePawns() {
        return whitePawns;
    }

    public void setWhitePawns(ArrayList<Pawn> pawns) {
        this.blackPawns = pawns;
    }

    public Texture getWhite_t() {
        return white_t;
    }

    public void setWhite_t(Texture white_t) {
        this.white_t = white_t;
    }

    public Texture getBlack_t() {
        return black_t;
    }

    public void setBlack_t(Texture black_t) {
        this.black_t = black_t;
    }

    public Texture getBlack_t_p() {
        return black_t_p;
    }

    public void setBlack_t_p(Texture black_t_p) {
        this.black_t_p = black_t_p;
    }

    public void createBlackPawn(int i, int j) {
        blackPawns.add(new Pawn(black_t_p = new Texture("CZARNY_PIONEK_110x110.png"), FieldPawnEnum.BLACK_PAWN, i, j));
    }

    public void createWhitePawn(int i, int j) {
        whitePawns.add(new Pawn(white_t_p = new Texture("BIALY_PIONEK_110x110.png"), FieldPawnEnum.WHITE_PAWN, i, j));
    }

    public void setFieldsOfChessboard(Field[][] fieldsOfChessboard) {
        this.fieldsOfChessboard = fieldsOfChessboard;
    }

    public Texture getTextureOfField(Field field) {
        return field.getTexture();
    }


    public void loadDate() {
        frame_t = new Texture("RAMKA_910x910.jpg");
    }

    public void generateChessboard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field(white_t = new Texture("BIALY_110x110.png"), FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                    } else {
                        fieldsOfChessboard[i][j] = new Field(black_t = new Texture("CZARNY_110x110.png"), FieldColorEnum.BLACK_FIELD, FieldPawnEnum.BLACK_PAWN, i, j);//CZ_P
                        createBlackPawn(i, j);
                    }
                } else {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field(black_t = new Texture("CZARNY_110x110.png"), FieldColorEnum.BLACK_FIELD, FieldPawnEnum.BLACK_PAWN, i, j);
                        createBlackPawn(i, j);
                    } else
                        fieldsOfChessboard[i][j] = new Field(white_t = new Texture("BIALY_110x110.png"), FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                }
            }
        }

        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field(white_t = new Texture("BIALY_110x110.png"), FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                    } else
                        fieldsOfChessboard[i][j] = new Field(black_t = new Texture("CZARNY_110x110.png"), FieldColorEnum.BLACK_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                } else {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field(black_t = new Texture("CZARNY_110x110.png"), FieldColorEnum.BLACK_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                    } else
                        fieldsOfChessboard[i][j] = new Field(white_t = new Texture("BIALY_110x110.png"), FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                }
            }
        }


        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field(white_t = new Texture("BIALY_110x110.png"), FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                    } else {
                        fieldsOfChessboard[i][j] = new Field(black_t = new Texture("CZARNY_110x110.png"), FieldColorEnum.BLACK_FIELD, FieldPawnEnum.WHITE_PAWN, i, j);
                        createWhitePawn(i, j);
                    }
                } else {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field(black_t = new Texture("CZARNY_110x110.png"), FieldColorEnum.BLACK_FIELD, FieldPawnEnum.WHITE_PAWN, i, j);
                        createWhitePawn(i, j);
                    } else
                        fieldsOfChessboard[i][j] = new Field(white_t = new Texture("BIALY_110x110.png"), FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                }
            }
        }
    }
/*
        Client client = Client.getInstance();
        boolean connect = client.connect();
        if (!connect) {
            Server server = Server.getInstance();
            server.dupa = "niedupa";
            server.accept();
            clientServer = server;
        }else {
            clientServer = client;
        }

    }
*/
    public FieldPawnEnum whatPawn(int i, int j) {
        if (fieldsOfChessboard[i][j].getStatusField() != FieldPawnEnum.WITHOUT_PAWN) {
            if (fieldsOfChessboard[i][j].getStatusField() == FieldPawnEnum.BLACK_PAWN)
                return FieldPawnEnum.BLACK_PAWN;
            else
                return FieldPawnEnum.WHITE_PAWN;
        } else
            return FieldPawnEnum.WITHOUT_PAWN;

    }



}
