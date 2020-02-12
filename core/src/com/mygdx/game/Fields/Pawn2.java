package com.mygdx.game.Fields;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.EnumOfFileds.FieldPawnEnum;

import java.io.Serializable;

public class Pawn2 implements Serializable {

    private FieldPawnEnum kindOfPawn;
    private int i, j, iLast, jLast;

    public Pawn2(FieldPawnEnum kindOfPawn, int i, int j) {
        this.kindOfPawn = kindOfPawn;
        this.i = i;
        this.j = j;
        this.iLast = this.i;
        this.jLast = this.j;
    }

    public FieldPawnEnum getKindOfPawn() {
        return kindOfPawn;
    }

    public void setKindOfPawn(FieldPawnEnum kindOfPawn) {
        this.kindOfPawn = kindOfPawn;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getiLast() {
        return iLast;
    }

    public void setiLast(int iLast) {
        this.iLast = iLast;
    }

    public int getjLast() {
        return jLast;
    }

    public void setjLast(int jLast) {
        this.jLast = jLast;
    }
}
