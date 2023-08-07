package deques;

/**
 * @see Deque
 */
public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;

    // Feel free to add any additional fields you may need, though.

    public LinkedDeque() {
        size = 0;
        front = new Node<>(null);
        back = new Node<>(null);
        front.next = back;
        back.prev = front;

    }

    public void addFirst(T item) {
        Node<T> b = new Node<>(item, front, front.next);
        b.next = front.next;
        b.prev = front;
        front.next = b;
        b.next.prev = b;
        size += 1;
    }

    public void addLast(T item) {
        Node<T> a = new Node<>(item);
        a.next = back;
        back.prev.next = a;
        a.prev = back.prev;
        back.prev = a;


        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T value = front.next.value;
        front.next = front.next.next;
        front.next.prev = front;
        //front.next.next.prev = front;
        return value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        T number = back.prev.value;
        back.prev = back.prev.prev;
        back.prev.next = back;
        return number;
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        Node<T> pointer = front;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.next.value;
    }

    public int size() {
        return size;
    }
}
