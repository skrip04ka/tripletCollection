package MyPackage;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        TripletCollection<Integer> triplet = new TripletCollection<>();

        for (int i = 0; i < 8; i++) {
            triplet.addLast(i);
            triplet.printAll();
        }

        System.out.println("\n");

        for (int i = 0; i < 8; i++) {
            triplet.addFirst(i);
            triplet.printAll();
        }

    }

}