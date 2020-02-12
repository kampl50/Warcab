package com.mygdx.game.Fields;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.EnumOfFileds.FieldPawnEnum;

import java.io.Serializable;

public class Pawn implements Serializable {
    private Texture texture;
    private int width;
    private int height;
    private int widthX;
    private int heightY;
    private FieldPawnEnum kindOfPawn;
    private int i, j, iLast, jLast;


    public Pawn(Texture texture, FieldPawnEnum kindOfPawn, int i, int j) {
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.kindOfPawn = kindOfPawn;
        this.i = i;
        this.j = j;
        this.iLast = this.i;
        this.jLast = this.j;
    }

    public int getWidthX() {
        return this.widthX;
    }

    public void setWidthX(int widthX) {
        this.widthX = widthX;
    }

    public int getHeightY() {
        return this.heightY;
    }

    public void setHeightY(int heightY) {
        this.heightY = heightY;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getI() {
        return this.i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return this.j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public FieldPawnEnum getKindOfPawn() {
        return kindOfPawn;
    }

    public void setKindOfPawn(FieldPawnEnum kindOfPawn) {
        this.kindOfPawn = kindOfPawn;
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
