package com.company;

public class Point {
    public double arr[];
    public double label;
    public Point(double arr[]){
        this.arr = arr;
    }
    public String toString(){
        String ret = "";
        for(int i = 0 ; i < arr.length; i++){
            ret += this.arr[i] + " ";
        }
        ret+= label;
        return ret;
    }
}
