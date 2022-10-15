package MyPackage;


public class Main {
    public static void main(String[] args) {

        TripletCollection<Integer> triplet = new TripletCollection<>();

        for (int i = 0; i < 8; i++) {
            triplet.addLast(i);
            triplet.addFirst(i);
        }

        triplet.printAll();
    }

}