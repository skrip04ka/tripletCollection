package MyPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.*;

class TripletCollectionTest {

    int numberOfElements = 8; // >1
    int tripletSize = 5; // >0

    @Test
    void initTest() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>();
        Assertions.assertEquals(5, myTriplet.getTripletSize());

        TripletCollection<Integer> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertEquals(tripletSize, myTriplet2.getTripletSize());

        ArrayList<Integer> col = new ArrayList<Integer>();
        for (int i = 0; i < numberOfElements; i++){
            col.add(i);
        }

        TripletCollection<Integer> myTriplet3 = new TripletCollection<>(tripletSize, col);
        Assertions.assertEquals(tripletSize, myTriplet3.getTripletSize());
    }

    @Test
    void addLastTest() {

        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());

        for (int i =0; i < numberOfElements; i++){
            myTriplet.addLast(i+"");
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertEquals((numberOfElements - 1) +"", myTriplet.getLast());
        Assertions.assertEquals(0+"", myTriplet.getFirst());
        Assertions.assertThrows(NullPointerException.class, new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        myTriplet.addLast(null);
                    }
                });
    }

    @Test
    void addFirstTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < numberOfElements; i++){
            myTriplet.addFirst(i+"");
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertEquals(0+"", myTriplet.getLast());
        Assertions.assertEquals((numberOfElements - 1) + "", myTriplet.getFirst());
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.addFirst(null);
            }
        });
    }

    @Test
    void offerFirstTest() {

        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < numberOfElements; i++){
            Assertions.assertTrue(myTriplet.offerFirst(i+""));
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertEquals(0+"", myTriplet.getLast());
        Assertions.assertEquals((numberOfElements - 1) + "", myTriplet.getFirst());
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.offerFirst(null);
            }
        });
    }

    @Test
    void offerLastTest() {

        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < numberOfElements; i++){
            myTriplet.offerLast(i+"");
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertEquals((numberOfElements - 1) +"", myTriplet.getLast());
        Assertions.assertEquals(0+"", myTriplet.getFirst());
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.offerLast(null);
            }
        });
    }

    @Test
    void removeFirstTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addFirst(i+"");
        }

        for (int i = numberOfElements - 1; i > 0; i--) {
            Assertions.assertEquals(i + "", myTriplet.removeFirst());
            Assertions.assertEquals((i - 1) + "", myTriplet.getFirst());
        }

        Assertions.assertEquals(0+"", myTriplet.removeFirst());
        Assertions.assertThrows(NoSuchElementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.removeFirst();
            }
        });
        Assertions.assertNull(myTriplet.pollFirst());
    }

    @Test
    void removeLastTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i+"");
        }

        for (int i = numberOfElements - 1; i > 0; i--) {
            Assertions.assertEquals(i + "", myTriplet.removeLast());
            Assertions.assertEquals((i - 1) + "", myTriplet.getLast());
        }
        Assertions.assertEquals(0+"", myTriplet.pollLast());
        Assertions.assertThrows(NoSuchElementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.removeLast();
            }
        });
    }

    @Test
    void pollFirstTest1() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addFirst(i+"");
        }

        for (int i = numberOfElements - 1; i > 0; i--) {
            Assertions.assertEquals(i + "", myTriplet.pollFirst());
            Assertions.assertEquals((i - 1) + "", myTriplet.getFirst());
        }

        Assertions.assertEquals(0+"", myTriplet.pollFirst());
        Assertions.assertNull(myTriplet.pollFirst());

    }

    @Test
    void pollFirstTest2() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i+"");
        }

        for (int i = 0; i < numberOfElements - 1; i++) {
            Assertions.assertEquals(i + "", myTriplet.pollFirst());
            Assertions.assertEquals((i + 1) + "", myTriplet.getFirst());
        }

        Assertions.assertEquals((numberOfElements - 1)+"", myTriplet.pollFirst());
        Assertions.assertNull(myTriplet.pollFirst());
    }

    @Test
    void pollLastTest1() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i+"");
        }

        for (int i = numberOfElements - 1; i > 0; i--) {
            Assertions.assertEquals(i + "", myTriplet.pollLast());
            Assertions.assertEquals((i - 1) + "", myTriplet.getLast());
        }
        Assertions.assertEquals(0+"", myTriplet.pollLast());
        Assertions.assertNull(myTriplet.pollLast());
    }

    @Test
    void pollLastTest2() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        Assertions.assertTrue(myTriplet.isEmpty());

        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i+"");
        }
        Assertions.assertFalse(myTriplet.isEmpty());

        for (int i = 0; i < numberOfElements - 1; i++) {
            Assertions.assertEquals(i + "", myTriplet.pollLast());
        }
        Assertions.assertEquals((numberOfElements - 1)+"", myTriplet.pollLast());

        Assertions.assertTrue(myTriplet.isEmpty());
        Assertions.assertNull(myTriplet.pollLast());
    }

    @Test
    void getFirstTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        Assertions.assertTrue(myTriplet.isEmpty());

        Assertions.assertThrows(NoSuchElementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.getFirst();
            }
        });

        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i+"");
            Assertions.assertEquals((i) + "", myTriplet.getFirst());
        }
    }

    @Test
    void getLastTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        Assertions.assertTrue(myTriplet.isEmpty());

        Assertions.assertThrows(NoSuchElementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.getLast();
            }
        });

        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addLast(i+"");
            Assertions.assertEquals((i) + "", myTriplet.getLast());
        }
    }

    @Test
    void peekFirst() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        Assertions.assertTrue(myTriplet.isEmpty());
        Assertions.assertNull(myTriplet.peekFirst());

        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i+"");
            Assertions.assertEquals((i) + "", myTriplet.peekFirst());
        }
    }

    @Test
    void peekLastTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        Assertions.assertTrue(myTriplet.isEmpty());
        Assertions.assertNull(myTriplet.peekLast());

        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addLast(i+"");
            Assertions.assertEquals((i) + "", myTriplet.peekLast());
        }
    }

    @Test
    void removeFirstOccurrenceTest1() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i);
        }
        myTriplet.addLast(numberOfElements-1);

        Assertions.assertFalse(myTriplet.removeFirstOccurrence(numberOfElements));
        Assertions.assertTrue(myTriplet.removeFirstOccurrence(numberOfElements-1));

        Assertions.assertEquals(numberOfElements-2, myTriplet.getFirst());
        Assertions.assertEquals(numberOfElements-1, myTriplet.getLast());
    }

    @Test
    void removeFirstOccurrenceTest2() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i< numberOfElements; i++){
            if (i == 2) {
                myTriplet.addFirst(numberOfElements - 1);
            } else
                myTriplet.addFirst(i);
        }
        myTriplet.addLast(numberOfElements-1);

        Assertions.assertFalse(myTriplet.removeFirstOccurrence(numberOfElements));
        Assertions.assertTrue(myTriplet.removeFirstOccurrence(numberOfElements-1));

        if (numberOfElements != 4) {
            Assertions.assertEquals(numberOfElements-2, myTriplet.getFirst());
        } else {
            Assertions.assertEquals(3, myTriplet.getFirst());
        }
        Assertions.assertEquals(numberOfElements-1, myTriplet.getLast());
    }

    @Test
    void removeFirstOccurrenceTest3() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i< 10; i++){
            myTriplet.addLast(i);
        }
        myTriplet.addLast(4);

        Assertions.assertFalse(myTriplet.removeFirstOccurrence(10));
        Assertions.assertTrue(myTriplet.removeFirstOccurrence(4));

        Iterator<Integer> iter = myTriplet.iterator();
        int i = 0;
        while (iter.hasNext()) {
            if (i != 4) {
                Assertions.assertEquals(i, iter.next());
                i++;
            } else {
                iter.next();
            }
        }

        Assertions.assertEquals(4, myTriplet.getLast());
    }

    @Test
    void removeLastOccurrenceTest1() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i);
        }
        myTriplet.addLast(numberOfElements-1);

        Assertions.assertFalse(myTriplet.removeLastOccurrence(numberOfElements));
        Assertions.assertTrue(myTriplet.removeLastOccurrence(numberOfElements-1));

        Assertions.assertEquals(numberOfElements-1, myTriplet.getFirst());
        Assertions.assertEquals(0, myTriplet.getLast());
    }

    @Test
    void removeLastOccurrenceTest2() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i< numberOfElements; i++){
            if (i == 3) {
                myTriplet.addFirst(numberOfElements - 1);
            } else
                myTriplet.addFirst(i);
        }
        myTriplet.addLast(numberOfElements-1);

        Assertions.assertFalse(myTriplet.removeLastOccurrence(numberOfElements));
        Assertions.assertTrue(myTriplet.removeLastOccurrence(numberOfElements-1));

        Assertions.assertEquals(numberOfElements-1, myTriplet.getFirst());
        Assertions.assertEquals(0, myTriplet.getLast());
    }

    @Test
    void removeLastOccurrenceTest3() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i< 10; i++){
            myTriplet.addLast(i);
        }
        myTriplet.addFirst(4);

        Assertions.assertFalse(myTriplet.removeLastOccurrence(10));
        Assertions.assertTrue(myTriplet.removeLastOccurrence(4));

        Iterator<Integer> iter = myTriplet.iterator();
        int i = 0;
        iter.next();
        while (iter.hasNext()) {
            if (i != 4) {
                Assertions.assertEquals(i, iter.next());
                i++;
            } else {
                iter.next();
            }
        }
        Assertions.assertEquals(4, myTriplet.getFirst());
    }

    @Test
    void addTest() {

        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());

        for (int i =0; i < numberOfElements; i++){
            myTriplet.add(i+"");
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertEquals((numberOfElements - 1) +"", myTriplet.getLast());
        Assertions.assertEquals(0+"", myTriplet.getFirst());
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.add(null);
            }
        });
    }

    @Test
    void offerTest() {

        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < numberOfElements; i++){
            myTriplet.offer(i+"");
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertEquals((numberOfElements - 1) +"", myTriplet.getLast());
        Assertions.assertEquals(0+"", myTriplet.getFirst());
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.offer(null);
            }
        });
    }

    @Test
    void removeTest1() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addFirst(i+"");
        }

        for (int i = numberOfElements - 1; i > 0; i--) {
            Assertions.assertEquals(i + "", myTriplet.remove());
            Assertions.assertEquals((i - 1) + "", myTriplet.getFirst());
        }

        Assertions.assertEquals(0+"", myTriplet.remove());
        Assertions.assertThrows(NoSuchElementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.remove();
            }
        });
        Assertions.assertNull(myTriplet.pollFirst());
    }

    @Test
    void removeTest2() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i);
        }
        myTriplet.addLast(numberOfElements-1);

        Assertions.assertFalse(myTriplet.remove(numberOfElements));
        Assertions.assertTrue(myTriplet.remove(numberOfElements-1));

        Assertions.assertEquals(numberOfElements-2, myTriplet.getFirst());
        Assertions.assertEquals(numberOfElements-1, myTriplet.getLast());
    }

    @Test
    void pollTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i+"");
        }

        for (int i = 0; i < numberOfElements - 1; i++) {
            Assertions.assertEquals(i + "", myTriplet.poll());
            Assertions.assertEquals((i + 1) + "", myTriplet.getFirst());
        }

        Assertions.assertEquals((numberOfElements - 1)+"", myTriplet.poll());
        Assertions.assertNull(myTriplet.poll());
    }

    @Test
    void elementTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        Assertions.assertTrue(myTriplet.isEmpty());

        Assertions.assertThrows(NoSuchElementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.element();
            }
        });

        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i+"");
            Assertions.assertEquals((i) + "", myTriplet.element());
        }
    }

    @Test
    void peekTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);

        Assertions.assertTrue(myTriplet.isEmpty());
        Assertions.assertNull(myTriplet.peek());

        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i+"");
            Assertions.assertEquals((i) + "", myTriplet.peek());
        }
    }

    @Test
    void addAllTest1() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        ArrayList<Integer> col = new ArrayList<Integer>();
        for (int i = 0; i < numberOfElements; i++){
            col.add(i);
        }

        myTriplet.addAll(col);

        for (int i = 0; i < numberOfElements; i++){
            Assertions.assertEquals(i, myTriplet.pollFirst());
        }
    }

    @Test
    void addAllTest2() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>();
        ArrayList<Integer> col = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++){
            col.add(i);
        }
        Assertions.assertFalse(myTriplet.addAll(col));
        Assertions.assertTrue(myTriplet.addAll(col));

        for (int i = 0; i < 3; i++){
            Assertions.assertEquals(i, myTriplet.pollFirst());
        }
        for (int i = 0; i < 3; i++){
            Assertions.assertEquals(i, myTriplet.pollFirst());
        }
    }

    @Test
    void addAllTest3() {
        ArrayList<Integer> col = new ArrayList<Integer>();
        for (int i = 0; i < numberOfElements; i++){
            col.add(i);
        }

        TripletCollection<Integer> myTriplet = new TripletCollection<>(col);

        for (int i = 0; i < numberOfElements; i++){
            Assertions.assertEquals(i, myTriplet.pollFirst());
        }
    }

    @Test
    void removeAllTest1() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<String> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < 10; i++){
            myTriplet.addLast(i+"");
            myTriplet.addFirst(i+"");
            myTriplet2.addLast(i+"");
        }

        Assertions.assertFalse(myTriplet.isEmpty());
        myTriplet.removeAll(myTriplet2);
        Assertions.assertTrue(myTriplet.isEmpty());
    }

    @Test
    void removeAllTest2() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<String> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < 5; i++){
            myTriplet2.addLast(i+"");
            if (i < 2)
                myTriplet.addLast(i+"");
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertTrue(myTriplet.removeAll(myTriplet2));
        Assertions.assertTrue(myTriplet.isEmpty());
    }

    @Test
    void removeAllTest3() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<Integer> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < 5; i++){
            myTriplet.addLast(i);
            if (i < 2)
                myTriplet2.addLast(i);
        }

        Assertions.assertTrue(myTriplet.removeAll(myTriplet2));
        Assertions.assertFalse(myTriplet.contains(0));
        Assertions.assertFalse(myTriplet.contains(1));
        Assertions.assertTrue(myTriplet.contains(2));
        Assertions.assertTrue(myTriplet.contains(3));
        Assertions.assertTrue(myTriplet.contains(4));
    }

    @Test
    void removeAllTest4() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<Integer> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < numberOfElements; i++){
            myTriplet.addLast(i);
            myTriplet2.addLast(i+numberOfElements);
        }

        Assertions.assertFalse(myTriplet.removeAll(myTriplet2));

        for (int i =0; i < numberOfElements; i++){
            Assertions.assertTrue(myTriplet.contains(i));
        }
    }

    @Test
    void retainAllTest1() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<String> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < numberOfElements; i++){
            myTriplet.addLast(i+"");
            myTriplet.addFirst(i+"");
            myTriplet2.addLast(i+"");
        }

        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertFalse(myTriplet.retainAll(myTriplet2));

        for (int i =0; i < numberOfElements; i++){
            Assertions.assertTrue(myTriplet.contains(i+""));
        }
     }

    @Test
    void retainAllTest2() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<String> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());

        for (int i =0; i < 5; i++){
            myTriplet2.addLast(i+"");
            if (i < 2)
                myTriplet.addLast(i+"");
        }

        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertFalse(myTriplet.retainAll(myTriplet2));
        Assertions.assertTrue(myTriplet.contains("0"));
        Assertions.assertTrue(myTriplet.contains("1"));
    }

    @Test
    void retainAllTest3() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<Integer> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());

        for (int i =0; i < 5; i++){
            myTriplet.addLast(i);
            if (i < 2)
                myTriplet2.addLast(i);
        }

        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertTrue(myTriplet.retainAll(myTriplet2));
        Assertions.assertTrue(myTriplet.contains(0));
        Assertions.assertTrue(myTriplet.contains(1));
        Assertions.assertFalse(myTriplet.contains(2));
        Assertions.assertFalse(myTriplet.contains(3));
        Assertions.assertFalse(myTriplet.contains(4));
    }

    @Test
    void retainAllTest4() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<Integer> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());

        for (int i =0; i < numberOfElements; i++){
            myTriplet.addLast(i);
            myTriplet2.addLast(i+numberOfElements);
        }

        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertTrue(myTriplet.retainAll(myTriplet2));
        Assertions.assertTrue(myTriplet.isEmpty());
    }

    @Test
    void clearTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < numberOfElements; i++){
            myTriplet.addFirst(i+"");
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        myTriplet.clear();
        Assertions.assertTrue(myTriplet.isEmpty());
    }

    @Test
    void pushTest() {
        TripletCollection<String> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i =0; i < numberOfElements; i++){
            myTriplet.addFirst(i+"");
            Assertions.assertEquals(i+"", myTriplet.getFirst());
        }
        Assertions.assertFalse(myTriplet.isEmpty());
        Assertions.assertEquals(0+"", myTriplet.getLast());
        Assertions.assertEquals((numberOfElements - 1) + "", myTriplet.getFirst());
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.addFirst(null);
            }
        });
    }

    @Test
    void popTest() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i< numberOfElements; i++){
            myTriplet.addFirst(i);
        }
        myTriplet.addLast(numberOfElements-1);

        Assertions.assertFalse(myTriplet.remove(numberOfElements));
        Assertions.assertTrue(myTriplet.remove(numberOfElements-1));

        Assertions.assertEquals(numberOfElements-2, myTriplet.getFirst());
        Assertions.assertEquals(numberOfElements-1, myTriplet.getLast());
    }

    @Test
    void containsAllTest1() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        TripletCollection<Integer> myTriplet2 = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.containsAll(myTriplet2));
        for (int i = 0; i < numberOfElements; i++) {
            myTriplet2.addLast(i);
            Assertions.assertFalse(myTriplet.containsAll(myTriplet2));
            myTriplet.addLast(i);
            Assertions.assertTrue(myTriplet.containsAll(myTriplet2));
        }
    }

    @Test
    void containsAllTest2() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        ArrayDeque<Integer> ard = new ArrayDeque<>();
        Assertions.assertTrue(myTriplet.containsAll(ard));
        for (int i = 0; i < numberOfElements; i++) {
            ard.addLast(i);
            Assertions.assertFalse(myTriplet.containsAll(ard));
            myTriplet.addLast(i);
            Assertions.assertTrue(myTriplet.containsAll(ard));
        }
    }

    @Test
    void containsTest() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertFalse(myTriplet.contains(1));
        for (int i = 0; i < numberOfElements; i++) {
            myTriplet.addLast(i);
        }
        Assertions.assertFalse(myTriplet.contains(numberOfElements));
        Assertions.assertTrue(myTriplet.contains(numberOfElements - 1));
    }

    @Test
    void sizeTest() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertEquals(0, myTriplet.size());

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i);
            Assertions.assertEquals(i+i+1, myTriplet.size());
            myTriplet.addFirst(i);
            Assertions.assertEquals(i+i+2, myTriplet.size());
        }
    }

    @Test
    void numberOfTripletTest() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertEquals(0, myTriplet.size());

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i);
        }
        Assertions.assertEquals((numberOfElements - 1)/tripletSize + 1, myTriplet.numberOfTriplet());

    }

    @Test
    void isEmptyTest() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        Assertions.assertTrue(myTriplet.isEmpty());
        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i);
            myTriplet.addFirst(i);
        }
        Assertions.assertFalse(myTriplet.isEmpty());
    }

    @Test
    void iteratorHasNextTest() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i);
        }
        int i = 0;
        Iterator<Integer> tripIter = myTriplet.iterator();
        while (tripIter.hasNext()){
            tripIter.next();
            i++;
        }
        Assertions.assertEquals(numberOfElements, i);
    }

    @Test
    void iteratorRemoveTest1() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addFirst(i);
        }
        myTriplet.addLast(10);
        int a;
        int i = numberOfElements - 1;
        Iterator<Integer> tripIter = myTriplet.iterator();

        while (tripIter.hasNext()){
            a = tripIter.next();
            if (i >= 0) {
                Assertions.assertEquals(i, a);
            } else {
                Assertions.assertEquals(10, a);
            }
            if (a == 0) {
                tripIter.remove();
                Assertions.assertEquals(10, myTriplet.getLast());
            }
            i--;
        }
    }

    @Test
    void iteratorRemoveTest2() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i);
        }
        int a;
        int i = 0;
        Iterator<Integer> tripIter = myTriplet.iterator();

        while (tripIter.hasNext()){
            a = tripIter.next();
            Assertions.assertEquals(i, a);
            if (a == 0) {
                tripIter.remove();
                Assertions.assertEquals(1, myTriplet.getFirst());
            }
            i++;
        }

        tripIter.remove();
        if (numberOfElements > 2) {
            Assertions.assertEquals(numberOfElements - 2, myTriplet.getLast());
        } else {
            Assertions.assertTrue(myTriplet.isEmpty());
        }
        Assertions.assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                tripIter.remove();
            }
        });
    }

    @Test
    void iteratorRemoveTest3() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i < 3; i++){
            myTriplet.addLast(i);
        }
        int a;
        int i = 0;
        Iterator<Integer> tripIter = myTriplet.iterator();

        while (tripIter.hasNext()){
            a = tripIter.next();
            Assertions.assertEquals(i, a);
            if (a == 2) {
                tripIter.remove();
                Assertions.assertEquals(1, myTriplet.getLast());
            }
            i++;
        }

        for (i = 2; i < 10; i++){
            myTriplet.addLast(i);
        }

        i = 0;
        tripIter = myTriplet.iterator();

        while (tripIter.hasNext()){
            a = tripIter.next();
            Assertions.assertEquals(i, a);
            if (a == 9) {
                tripIter.remove();
                Assertions.assertEquals(8, myTriplet.getLast());
            }
            i++;
        }
    }

    @Test
    void iteratorRemoveTest4() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);
        for (int i = 0; i < 3; i++){
            myTriplet.addFirst(i);
        }

        int a;
        int i = 2;
        Iterator<Integer> tripIter = myTriplet.iterator();

        while (tripIter.hasNext()){
            Assertions.assertEquals(i, a = tripIter.next());

            if (a == 0) {
                tripIter.remove();
                Assertions.assertEquals(1, myTriplet.getLast());
            }
            i--;
        }

        for (i = 3; i < 10; i++){
            myTriplet.addFirst(i);
        }

        i = 9;
        tripIter = myTriplet.iterator();

        while (tripIter.hasNext()){
            Assertions.assertEquals(i, a = tripIter.next());

            if (a == 0) {
                tripIter.remove();
                Assertions.assertEquals(1, myTriplet.getLast());
            }
            i--;
        }
    }

    @Test
    void toArrayTest1() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i);
        }

        Object[] tripletArr = myTriplet.toArray();

        Assertions.assertEquals(myTriplet.size(), tripletArr.length);

        for (int i = 0; i < numberOfElements; i++) {
            Assertions.assertEquals(i, tripletArr[i]);
        }
    }

    @Test
    void toArrayTest2() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < numberOfElements; i++){
            myTriplet.addLast(i);
        }

        Object[] tripletArr = myTriplet.toArray();

        Assertions.assertEquals(myTriplet.size(), tripletArr.length);

        for (int i = numberOfElements - 1; i >= 0; i--) {
            Assertions.assertEquals(i, tripletArr[i]);
        }
    }

    @Test
    void toArrayTest3() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < 10; i++){
            myTriplet.addLast(i);
        }

        Integer[] tripletArr = new Integer[] {10, 20, 30, 40, 50};
        Assertions.assertFalse(myTriplet.toArray(tripletArr) == tripletArr);

        tripletArr = myTriplet.toArray(tripletArr);

        Assertions.assertEquals(myTriplet.size(), tripletArr.length);

        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(i, tripletArr[i]);
        }
    }

    @Test
    void toArrayTest4() {
        TripletCollection<Integer> myTriplet = new TripletCollection<>(tripletSize);

        for (int i = 0; i < 3; i++){
            myTriplet.addLast(i);
        }

        Integer[] tripletArr = new Integer[] {10, 20, 30, 40, 50};
        Assertions.assertTrue(myTriplet.toArray(tripletArr) == tripletArr);

        Assertions.assertEquals(5, tripletArr.length);
        Assertions.assertEquals(0, tripletArr[0]);
        Assertions.assertEquals(1, tripletArr[1]);
        Assertions.assertEquals(2, tripletArr[2]);
        Assertions.assertNull(tripletArr[3]);
        Assertions.assertEquals(50, tripletArr[4]);

    }

    @Test
    void descendingIteratorTest() {
        Deque<String> myTriplet = new TripletCollection<>(tripletSize);

        Assertions.assertThrows(UnsupportedOperationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                myTriplet.descendingIterator();
            }
        });
    }
}