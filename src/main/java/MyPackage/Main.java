package MyPackage;


import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        TripletCollection<Integer> triplet = new TripletCollection<>(5,null);
        triplet.printAll();

        System.out.println(triplet.getLast());
    }

}