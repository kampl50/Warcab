package com.mygdx.game.Chessboard;


import com.mygdx.game.EnumOfFileds.FieldColorEnum;
import com.mygdx.game.EnumOfFileds.FieldPawnEnum;
import com.mygdx.game.Fields.Field2;
import com.mygdx.game.Fields.Pawn2;

import java.io.Serializable;
import java.util.ArrayList;

public class Chessboard2 implements Serializable {

    private Field2 fieldsOfChessboard[][] = new Field2[8][8];
    private ArrayList<Pawn2> blackPawns = new ArrayList();
    private ArrayList<Pawn2> whitePawns = new ArrayList();

    public Chessboard2(){
            generateChessboard();
    }
    public Field2[][] getFieldsOfChessboard() {
        return fieldsOfChessboard;
    }

    public void setFieldsOfChessboard(Field2[][] fieldsOfChessboard) {
        this.fieldsOfChessboard = fieldsOfChessboard;
    }

    public ArrayList<Pawn2> getBlackPawns() {
        return blackPawns;
    }

    public void setBlackPawns(ArrayList<Pawn2> blackPawns) {
        this.blackPawns = blackPawns;
    }

    public ArrayList<Pawn2> getWhitePawns() {
        return whitePawns;
    }

    public void setWhitePawns(ArrayList<Pawn2> whitePawns) {
        this.whitePawns = whitePawns;
    }

    public Field2 getFieldsOfChessboard(int i, int j) {
        return fieldsOfChessboard[i][j];

    }

    public void generateChessboard() {


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                    } else {
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.BLACK_FIELD, FieldPawnEnum.BLACK_PAWN, i, j);//CZ_P
                        createBlackPawn(i, j);
                    }
                } else {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.BLACK_FIELD, FieldPawnEnum.BLACK_PAWN, i, j);
                        createBlackPawn(i, j);
                    } else
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                }
            }
        }

        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                    } else
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.BLACK_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                } else {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.BLACK_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                    } else
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                }
            }
        }


        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                    } else {
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.BLACK_FIELD, FieldPawnEnum.WHITE_PAWN, i, j);
                        createWhitePawn(i, j);
                    }
                } else {
                    if (j % 2 == 0) {
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.BLACK_FIELD, FieldPawnEnum.WHITE_PAWN, i, j);
                        createWhitePawn(i, j);
                    } else
                        fieldsOfChessboard[i][j] = new Field2(FieldColorEnum.WHITE_FIELD, FieldPawnEnum.WITHOUT_PAWN, i, j);
                }
            }
        }

    }
    public void createBlackPawn(int i, int j) {
        blackPawns.add(new Pawn2(FieldPawnEnum.BLACK_PAWN, i, j));
    }

    public void createWhitePawn(int i, int j) {
        whitePawns.add(new Pawn2(FieldPawnEnum.WHITE_PAWN, i, j));
    }
}
