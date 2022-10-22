package MyPackage;

import java.util.*;

public class TripletCollection<T> implements Deque<T> {

    private int tripletSize = 5;

    private Node<T> firstTriplet;

    private Node<T> lastTriplet;

    private int queueVolume = 1000;

    public TripletCollection() {
        this (5);
    }

    public TripletCollection(int tripletSize) {
        this.tripletSize = tripletSize;
        lastTriplet = new Node<>(null, null);
    }

    public TripletCollection (Collection<? extends T> c) {
        this (5, c);
    }

    public TripletCollection (int tripletSize, Collection<? extends T> c) {
        this(tripletSize);
        addAll(c);
    }

    public TripletCollection (int tripletSize, Collection<? extends T> c, int queueVolume) {
        this(tripletSize, queueVolume);
        addAll(c);
    }

    public TripletCollection (int tripletSize, int queueVolume) {
        this(tripletSize);
        this.queueVolume = queueVolume;
    }

    private class Node<T> {
        private final Object[] data;
        private Node<T> nextTriplet;
        private Node<T> prevTriplet;
        private int index;

        Node(Node<T> prevTriplet, Node<T> nextTriplet) {
            this.prevTriplet = prevTriplet;
            this.nextTriplet = nextTriplet;
            this.data = new Object[tripletSize];
        }
    }

    private void newLastTriplet() {

        if (firstTriplet == null && lastTriplet != null){
            firstTriplet = lastTriplet;
            lastTriplet = new Node<>(firstTriplet, null);

            firstTriplet.nextTriplet = lastTriplet;
            firstTriplet.index = -1;

        } else {
            Node<T> newNode = new Node<>(lastTriplet, null);
            lastTriplet.nextTriplet = newNode;
            lastTriplet = newNode;
            lastTriplet.index = 0;
        }
    }

    private void newFirstTriplet() {

        if (firstTriplet == null && lastTriplet != null){
            firstTriplet = new Node<>(null, lastTriplet);
            lastTriplet.prevTriplet = firstTriplet;
        } else {
            Node<T> newNode = new Node<>(null, firstTriplet);
            firstTriplet.prevTriplet = newNode;
            firstTriplet = newNode;
        }
        firstTriplet.index = tripletSize - 1;

    }

    private void delFirstTriplet() {

        if (firstTriplet != null) {
            if (firstTriplet.nextTriplet != lastTriplet) {
                firstTriplet = firstTriplet.nextTriplet;
                firstTriplet.prevTriplet = null;
                firstTriplet.index = -1;
            } else {
                firstTriplet = null;
                lastTriplet.prevTriplet = null;
            }
        }
    }

    private void delLastTriplet() {

        int idx = 0;

        if (firstTriplet != null) {
            lastTriplet = lastTriplet.prevTriplet;
            lastTriplet.nextTriplet = null;
            idx = lastTriplet.index;
            lastTriplet.index = tripletSize;
        }

        if(firstTriplet == lastTriplet) {
            firstTriplet = null;

            if (lastTriplet.data[0] == null && lastTriplet.data[tripletSize-1] != null) {
                System.arraycopy(lastTriplet.data,idx + 1,lastTriplet.data,0,tripletSize - (idx+1));
                lastTriplet.index = tripletSize - (idx + 1);
                for (int i = lastTriplet.index; i < tripletSize; i++)
                    lastTriplet.data[i] = null;
            }
        }
    }

    public int numberOfTriplet() {
        return (size() - ((firstTriplet != null) ? (tripletSize - firstTriplet.index - 1) : 0) - lastTriplet.index + 1)/tripletSize + ((firstTriplet != null) ? 2 : 1);
    }

    public int getTripletSize() {
        return tripletSize;
    }

    public int getQueueVolume() {
        return queueVolume;
    }

    @Override
    public void addFirst(T t) {
        if (t == null)
            throw new NullPointerException("Nothing to add");

        if (size()+1>queueVolume)
            throw new IllegalStateException("Queue size exceeded");

        if (firstTriplet != null){
            if (firstTriplet.data[0] != null) {
                newFirstTriplet();
            }
            firstTriplet.data[firstTriplet.index] = t;
            firstTriplet.index--;

        } else {
            if (lastTriplet.data[0] == null) {
                addLast(t);
            } else {
                newFirstTriplet();
                addFirst(t);
            }
        }
    }

    @Override
    public void addLast(T t) {
        if (t == null)
            throw new NullPointerException("Nothing to add");

        if (size()+1>queueVolume)
            throw new IllegalStateException("Queue size exceeded");

        if (lastTriplet.data[tripletSize - 1] != null) {
            newLastTriplet();
        }

        lastTriplet.data[lastTriplet.index] = t;
        lastTriplet.index++;
    }

    @Override
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }

    @Override
    public boolean offerLast(T t) {
        addLast(t);
        return true;
    }

    @Override
    public T removeFirst() {
        T t = pollFirst();
        if (t == null)
            throw new NoSuchElementException("No element");
        return t;
    }

    @Override
    public T removeLast() {
        T t = pollLast();
        if (t == null)
            throw new NoSuchElementException("No element");
        return t;
    }

    @Override
    public T pollFirst() {
        if(isEmpty()) {
            return null;
        }

        Object t;

        if (firstTriplet == null) {
            t = lastTriplet.data[0];
            System.arraycopy(lastTriplet.data, 1, lastTriplet.data, 0, tripletSize - 1);
            lastTriplet.data[lastTriplet.index - 1] = null;
            lastTriplet.index--;
        } else {
            t = firstTriplet.data[firstTriplet.index + 1];
            firstTriplet.data[firstTriplet.index + 1] = null;
            firstTriplet.index++;
            if (firstTriplet.data[tripletSize - 1] == null) {
                delFirstTriplet();
            }
        }
        return (T) t;
    }

    @Override
    public T pollLast() {
        if(isEmpty()) {
            return null;
        }

        Object t = lastTriplet.data[lastTriplet.index - 1];

        lastTriplet.data[lastTriplet.index - 1] = null;
        lastTriplet.index--;
        if (lastTriplet.data[0] == null) {
            delLastTriplet();
        }
        return (T) t;
    }

    @Override
    public T getFirst() {
        T t = peekFirst();
        if (t == null)
            throw new NoSuchElementException("No element");
        return t;
    }

    @Override
    public T getLast() {
        T t = peekLast();
        if (t == null)
            throw new NoSuchElementException("No element");
        return t;
    }

    @Override
    public T peekFirst() {
        if (firstTriplet != null)
            return (T) firstTriplet.data[firstTriplet.index + 1];
        else
            return (T) lastTriplet.data[0];
    }

    @Override
    public T peekLast() {
        if (lastTriplet.index != 0)
            return (T) lastTriplet.data[lastTriplet.index - 1];
        else
            return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (o == null)
            throw new NullPointerException("No element");
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            if (o.equals(iter.next())) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null)
            throw new NullPointerException("No element");
        Iterator<T> iter = iterator();
        int idx = -1;
        int i = 0;
        while (iter.hasNext()) {
            if (o.equals(iter.next())) {
                idx = i;
            }
            i++;
        }

        if (idx == -1) {
            return false;
        } else {
            iter = iterator();
            for (i = 0; i<=idx; i++) iter.next();
            iter.remove();
            return true;
        }
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public boolean offer(T t) {
        return offerLast(t);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() + size() > queueVolume)
            throw new IllegalStateException("Queue size exceeded");

        int idx = lastTriplet.index;
        c.forEach(this::addLast);
        return c.size() > tripletSize - idx;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemove = false;
        for (Object e : c) {
            while (removeFirstOccurrence(e)) {
                isRemove = true;
            }
        }
        return isRemove;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemove = false;
        boolean isNotEquals;
        T element;

        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            element = iter.next();
            isNotEquals = true;
            for (Object e : c) {
                if (e.equals(element)) {
                    isNotEquals = false;
                    break;
                }
            }
            if (isNotEquals) {
                iter.remove();
                isRemove = true;
            }
        }
        return isRemove;
    }

    @Override
    public void clear() {
        lastTriplet = new Node<>(null,null);
        lastTriplet.index = 0;
        firstTriplet = null;
    }

    @Override
    public void push(T t) {
        addFirst(t);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null)
            throw new NullPointerException("No element");

        for (T t : this) {
            if (o.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        if (firstTriplet == null) {
            return lastTriplet.index;
        } else {
            int count = 0;
            Node<T> nextNode = firstTriplet;
            while (nextNode != null) {
                count++;
                nextNode = nextNode.nextTriplet;
            }
            return (count - 2) * tripletSize + lastTriplet.index + (tripletSize - firstTriplet.index - 1) ;
        }
    }

    @Override
    public boolean isEmpty() {
        return lastTriplet.data[0] == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new TripletIterator();
    }

    private class TripletIterator implements Iterator<T> {

        int cursor;
        Node<T> newNode;
        int remaining = size();
        int lastRet = -1;

        TripletIterator() {
            if (firstTriplet == null) {
                cursor = 0;
            } else {
                newNode = firstTriplet;
                cursor = firstTriplet.index + 1;
            }
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public T next() {
            if (remaining <= 0)
                throw new NoSuchElementException();

            if (cursor == tripletSize) {
                newNode = newNode.nextTriplet;
                cursor = 0;
            }
            Object t;
            if (firstTriplet == null) {
                t = lastTriplet.data[cursor];
            } else {
                t = newNode.data[cursor];
            }
            lastRet = cursor;
            cursor++;
            remaining--;

            return (T) t;
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            if (firstTriplet == null || newNode==lastTriplet) {
                if (lastRet != lastTriplet.index - 1) {
                    System.arraycopy(lastTriplet.data, lastRet + 1, lastTriplet.data, lastRet, tripletSize - lastRet - 1);
                }
                pollLast();
                cursor--;
            } else if (newNode == firstTriplet) {
                if (lastRet != firstTriplet.index + 1) {
                    System.arraycopy(firstTriplet.data, firstTriplet.index + 1, firstTriplet.data, firstTriplet.index + 2, lastRet - firstTriplet.index - 1);
                }
                pollFirst();
            } else {
                Node<T> copyNode = newNode;
                if (lastRet != tripletSize - 1) {
                    System.arraycopy(copyNode.data, lastRet + 1, copyNode.data, lastRet, tripletSize - lastRet - 1);
                }

                while (copyNode.nextTriplet != lastTriplet) {
                    copyNode.data[tripletSize - 1] = copyNode.nextTriplet.data[0];
                    copyNode = copyNode.nextTriplet;
                    System.arraycopy(copyNode.data, 1, copyNode.data, 0, tripletSize - 1);
                }
                copyNode.data[tripletSize - 1] = copyNode.nextTriplet.data[0];
                copyNode = copyNode.nextTriplet;
                if (copyNode.index - 1 > 0) {
                    System.arraycopy(copyNode.data, 1, copyNode.data, 0, copyNode.index - 1);
                }
                pollLast();
                cursor--;
            }
            lastRet = -1;
        }
    }

    public void printAll() {
        if (firstTriplet == null) {
            System.out.print("[ ");
            for (int i = 0; i < tripletSize; i++) {
                System.out.print(lastTriplet.data[i] + " ");
            }
            System.out.print("]\n");
        } else {
            Node<T> nextNode = firstTriplet;
            while (nextNode != null) {

                System.out.print("[ ");
                for (int i = 0; i < tripletSize; i++) {
                    System.out.print(nextNode.data[i] + " ");
                }
                System.out.print("]");
                nextNode = nextNode.nextTriplet;
            }
            System.out.print("\n");
        }
    }

    @Override
    public <T> T[] toArray(T[] t) {
        int size = size();
        Object[] tripletArray = toArray();
        if (size <= t.length) {
            System.arraycopy(tripletArray, 0, t, 0, size);
            if (size != t.length) {
                t[size] = null;
            }
            return t;
        } else {
            return (T[]) Arrays.copyOfRange(tripletArray, 0, size, t.getClass());
        }
    }

    @Override
    public Object[] toArray() {

        Object[] tripletArray = new Object [size()];

        if (firstTriplet == null) {
            System.arraycopy(lastTriplet.data, 0, tripletArray,0,lastTriplet.index);

        } else {
            System.arraycopy(firstTriplet.data, firstTriplet.index + 1, tripletArray, 0, tripletSize - firstTriplet.index - 1);
            int idx = tripletSize - firstTriplet.index - 1;
            Node<T> nextNode = firstTriplet.nextTriplet;
            while (nextNode != null && nextNode != lastTriplet) {
                System.arraycopy(nextNode.data, 0, tripletArray, idx, tripletSize);
                idx = idx + tripletSize;
                nextNode = nextNode.nextTriplet;
            }
            System.arraycopy(lastTriplet.data, 0, tripletArray, idx, lastTriplet.index);
        }
        return tripletArray;
    }

    @Override
    public Iterator<T> descendingIterator() {
        throw new UnsupportedOperationException("Not supported operation");
    }
}
