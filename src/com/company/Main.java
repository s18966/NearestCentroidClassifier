package com.company;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void findMedium(double[] arr){
        for(int i = 0 ;  i< arr.length; i++){

        }
    }
    public static double distance(double[] arr, double[] arr1){
        double distance = 0;
        for(int i = 0 ; i < arr.length; i++){
            distance += Math.pow((arr[i] - arr1[i]), 2);
        }
        Math.sqrt(distance);
        return distance;
    }
    public static void cluster(ArrayList<Point> data, ArrayList<Point> centroids, int k, int iterations){
        while(iterations != 0) {
            for (Point p : data) {
                double min = distance(centroids.get(0).arr, p.arr);
                double label = centroids.get(0).label;
                for (int i = 1; i < centroids.size(); i++) {
                    double tmp = distance(centroids.get(i).arr, p.arr);
                    if (tmp < min) {
                        min = tmp;
                        label = centroids.get(i).label;
                    }
                }
                p.label = label;
                System.out.println(p.toString());
            }
            for(int i = 0  ; i <k; i++){ // do il klas
                ArrayList<Point> tmp = new ArrayList<>();
                for(int j = 0 ; j < data.size(); j++){
                    if(data.get(j).label == i+1){
                        tmp.add(data.get(j)); // dodawamy do tmp dane sort
                    }
                }
                double[] arr = new double [tmp.get(0).arr.length]; //4
                for(int j = 0 ; j < tmp.size(); j++){ // vse tochki iter
                    for(int z = 0; z < tmp.get(i).arr.length; z++){
                        arr[z] += tmp.get(i).arr[z]; //suma tochek
                    }
                }
                for(int j = 0 ; j < arr.length; j++){
                    arr[j] = arr[j] / tmp.size(); // delim na rozmier
                }
                Point p = new Point(arr); //nowy centroid
                p.label = tmp.get(0).label;
                for(int j = 0 ; j < centroids.size(); j ++){
                    if(centroids.get(i).label == p.label){
                        centroids.get(i).arr = p.arr;
                    }
                }

            }
            iterations--;
            System.out.println("=================");
        }

    }

    public static ArrayList<Point> readCSV(File file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<Point> arr = new ArrayList<>();
            String line;
            String content;
            while((line = br.readLine()) != null){
                String[] tmp = line.split(",");
                double[] arr1 = new double[tmp.length];
                for(int i = 0 ; i < arr1.length; i++){
                    arr1[i] = Double.parseDouble(tmp[i]);
                }
                Point point = new Point(arr1);
                arr.add(point);
            }
            return arr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Point> generateCentroids(ArrayList<Point> data, int k){
        ArrayList<Point> centroids = new ArrayList<>();
        for(int i = 0; i<k; i++){
            int rand = (int)(Math.random() * data.size());
            centroids.add(data.get(rand));
            centroids.get(i).label = i+1;
            System.out.println("Our centroid : " + centroids.get(i).toString());
        }
        return centroids;

    }
    public static void main(String[] args) {
        ArrayList<Point> allData = readCSV(new File("/src/iris-data.csv"));
        for(Point p : allData){
            System.out.println(p.toString());
        }
        ArrayList<Point> centroids = generateCentroids(allData, 3);
        cluster(allData, centroids, 3, 3);
    }
}
