package com.mygdx.game.Fields;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.EnumOfFileds.FieldColorEnum;
import com.mygdx.game.EnumOfFileds.FieldPawnEnum;

import java.io.Serializable;

public class Field2 implements Serializable {


    private FieldPawnEnum statusField;
    private FieldColorEnum fieldColor;
    private int i, j;

    public Field2(FieldColorEnum fieldColorEnum, FieldPawnEnum kindOfPawn, int i, int j) {

        this.fieldColor = fieldColorEnum;
        this.statusField = kindOfPawn;
        this.i = i;
        this.j = j;
    }

    public FieldPawnEnum getStatusField() {
        return statusField;
    }

    public void setStatusField(FieldPawnEnum statusField) {
        this.statusField = statusField;
    }

    public FieldColorEnum getFieldColor() {
        return fieldColor;
    }

    public void setFieldColor(FieldColorEnum fieldColor) {
        this.fieldColor = fieldColor;
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
}
