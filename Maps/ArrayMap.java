package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @see AbstractIterableMap
 * @see Map
 */
@SuppressWarnings("checkstyle:TodoComment")
public class ArrayMap<K, V> extends AbstractIterableMap<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    SimpleEntry<K, V>[] entries;

    // You may add extra fields or helper methods though!
    private int size;

    /**
     * Constructs a new ArrayMap with default initial capacity.
     */
    public ArrayMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Constructs a new ArrayMap with the given initial capacity (i.e., the initial
     * size of the internal array).
     *
     * @param initialCapacity the initial capacity of the ArrayMap. Must be > 0.
     */
    public ArrayMap(int initialCapacity) {
        this.entries = this.createArrayOfEntries(initialCapacity);
        this.size = 0;
    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code Entry<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     */
    @SuppressWarnings("unchecked")
    private SimpleEntry<K, V>[] createArrayOfEntries(int arraySize) {
        /*
        It turns out that creating arrays of generic objects in Java is complicated due to something
        known as "type erasure."

        We've given you this helper method to help simplify this part of your assignment. Use this
        helper method as appropriate when implementing the rest of this class.

        You are not required to understand how this method works, what type erasure is, or how
        arrays and generics interact.
        */
        return (SimpleEntry<K, V>[]) (new SimpleEntry[arraySize]);
    }

    @SuppressWarnings("checkstyle:WhitespaceAfter")
    @Override
    public V get(Object key) { //completed
        SimpleEntry<K, V> give = null;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(entries[i].getKey(), key)) {
                give = entries[i];
            }
        }
        if (give != null) {
            return give.getValue();
        } //sdkfjlsdf
        return null;

    }

    @Override
    public V put(K key, V value) {        //completed
        SimpleEntry<K, V> run = null;
        boolean alrthere = false;
        if (size == entries.length) {
            SimpleEntry<K, V>[] extra = entries;
            entries = createArrayOfEntries(size * 2);
            for (int i = 0; i < size; i++) {
                entries[i] = extra[i];
                if (Objects.equals(entries[i].getKey(), key)) {
                    alrthere = true;
                    run = extra[i];

                    entries[i] = new SimpleEntry<K, V>(key, value);
                }
            }

            if (!alrthere) {
                entries[size] = new SimpleEntry<K, V>(key, value);
                size += 1;
            }
        } else {
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    if (Objects.equals(entries[i].getKey(), key)) {
                        alrthere = true;
                        run = entries[i];
                        entries[i] = new SimpleEntry<K, V>(key, value);
                    }
                }
                if (!alrthere) {
                    entries[size] = new SimpleEntry<K, V>(key, value);
                    size += 1;
                }
            } else {
                entries[0] = new SimpleEntry<K, V>(key, value);
                size +=1;
            }
        }
        if (run != null) {
            return run.getValue();
        }
        return null;
    }



    @SuppressWarnings("checkstyle:CommentsIndentation")
    @Override
    public V remove(Object key) {  //completed
        SimpleEntry<K, V> giveback = null;
        if (entries[0] != null) {
            SimpleEntry<K, V> finalValue = entries[size -1];
            if (Objects.equals(entries[0].getKey(), key)) {
                giveback = entries[0];
                entries[0] = finalValue;
                entries[size-1] = null;
                size -=1;
            } else {
                for (int i = 0; i < size; i++) {
                    if (Objects.equals(key, entries[i].getKey())) {
                        giveback = entries[i];
                        entries[i] = finalValue;
                        entries[size -1] = null;
                        size -= 1;
                    }

                }
            }
        }
        if (giveback != null) {
            return giveback.getValue();
        }
        return null;

    }

    @Override
    public void clear() {  //completed
        entries = createArrayOfEntries(DEFAULT_INITIAL_CAPACITY);
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {   //completed
        for (int i = 0; i < size; i++) {
            if (Objects.equals(entries[i].getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() { //completed
        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: You may or may not need to change this method, depending on whether you
        // add any parameters to the ArrayMapIterator constructor.
        return new ArrayMapIterator<>(this.entries);
    }

    // @Override
    // public String toString() {
    //     return super.toString();
    // }

    private static class ArrayMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private final SimpleEntry<K, V>[] entries;
        // You may add more fields and constructor parameters
        private int index;
        private int size;

        public ArrayMapIterator(SimpleEntry<K, V>[] entries) {
            this.entries = entries;
            this.index = index;
            for (SimpleEntry<K, V> nextone : entries) {   //completed
                if (nextone != null) {
                    this.size++;
                }
            }
        }

        @Override
        public boolean hasNext() { //completed
            if (entries[0] == null) {
                return false;
            }
            if (index < size) {
                return true;
            }
            return false;
        }

        @Override
        public Map.Entry<K, V> next() { //completed
            if (this.hasNext()) {
                index += 1;
                return entries[index - 1];
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
