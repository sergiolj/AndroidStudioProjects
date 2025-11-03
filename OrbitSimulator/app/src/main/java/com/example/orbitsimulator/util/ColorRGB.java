package com.example.orbitsimulator.util;

import androidx.annotation.NonNull;

public class ColorRGB {
    private final int r;
    private final int g;
    private final int b;

    public ColorRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public ColorRGB(){
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    /**
     * O format() com o valor X ou x formata o inteiro indicado em Hexadecimal
     *  "%02X" significa:
     *            "X" = Hexadecimal Maiúsculo
     *            "2" = Ter no mínimo 2 dígitos
     *            "0" = Preencher com zeros à esquerda se for menor que 2 dígitos
     * @return valor Hexadecinal
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("#%02X%02X%02X", r, g, b);
    }


}
