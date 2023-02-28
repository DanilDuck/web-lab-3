package com.web.lab.weblab3;

import java.io.Serializable;

public class Dot implements Serializable {
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    private boolean error = false;
    private String errorString = "";
    private double x;

    public void setY(String y) {
        this.y = y;
        try {
            double yParse;
            if(y.contains(",")) {
                this.y = this.y.replace(",", ".");
                String yReplace = this.y;
                yParse = Double.parseDouble(yReplace);
            }
            else{
                yParse = Double.parseDouble(y);
                this.y =  y;
            }
            yDouble = yParse;
        }catch (NumberFormatException e){
            error = true;
            this.y = "0";
        }

    }
    private boolean check;
    private String y;

    public double getyDouble() {
        return yDouble;
    }

    public void setyDouble(double yDouble) {
        this.yDouble = yDouble;
    }

    private double yDouble;
    private float r = 1;
    private String res;
    private String owner;

    public String getTimework() {
        return timework;
    }

    public void setTimework(String timework) {
        this.timework = timework;
    }

    private String timework;

    private final int[] valueX = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
    private final boolean[] booleanX = new boolean[11];
    private Double[] readX;
    public Double[] getReadX() {
        return readX;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getY() {
        return y;
    }



    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + this.x + "</td>" +
                "<td>" + this.y + "</td>" +
                "<td>" + this.r + "</td>" +
                "<td>" + this.res + "</td>" +
                "<td>" + this.timework + "</td>" +
                "</tr>";
    }

    public void isHit() {
        if ((x * x + yDouble * yDouble <= Math.pow(r / 2, 2) && x <= 0 && yDouble <= 0) ||
                (yDouble - x >= -r/2 && x >= 0 && yDouble <= 0) ||
                (yDouble <= r/2 && x >= -r &&  x <= 0 && yDouble >= 0)) {
            res = "True";
        } else {
            res = "False";
        }
    }
    public void createValueX(){
        readX = new Double[11];
        if(x != 0){
            check = true;
            readX[0] = x;
            for (int i = 1; i < 11; ++i){
                readX[i] = null;
            }
            return;
        }
        for (int i = 0; i < 11; ++i) {
            if (booleanX[i]) {
                readX[i] = (double) valueX[i];
            }
            else {
                readX[i] = null;
            }
        }
    }
    public boolean[] getBooleanX() {
        return booleanX;
    }

    public int[] getValueX() {
        return valueX;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}