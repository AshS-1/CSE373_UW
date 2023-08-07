package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ChainedHashMap<K, V> extends AbstractIterableMap<K, V> {

    private static final double DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD = 0.75;
    private static final int DEFAULT_INITIAL_CHAIN_COUNT = 5;
    private static final int DEFAULT_INITIAL_CHAIN_CAPACITY = 5;

    private double DRLFT = DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD;
    private int initialCount = DEFAULT_INITIAL_CHAIN_COUNT;
    private int initialCap = DEFAULT_INITIAL_CHAIN_CAPACITY;

    private int size;


    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    AbstractIterableMap<K, V>[] chains;

    // You're encouraged to add extra fields (and helper methods) though!

    /**
     * Constructs a new ChainedHashMap with default resizing load factor threshold,
     * default initial chain count, and default initial chain capacity. 1
     */
    public ChainedHashMap() {
        this(DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD, DEFAULT_INITIAL_CHAIN_COUNT, DEFAULT_INITIAL_CHAIN_CAPACITY);
    }

    /**
     * Constructs a new ChainedHashMap with the given parameters.
     *
     * @param resizingLoadFactorThreshold the load factor threshold for resizing. When the load factor
     *                                    exceeds this value, the hash table resizes. Must be > 0.
     * @param initialChainCount the initial number of chains for your hash table. Must be > 0.
     * @param chainInitialCapacity the initial capacity of each ArrayMap chain created by the map.
     *                             Must be > 0.
     */
    public ChainedHashMap(double resizingLoadFactorThreshold, int initialChainCount, int chainInitialCapacity) {
        DRLFT = resizingLoadFactorThreshold;
        initialCount = initialChainCount;
        initialCap = chainInitialCapacity;
        chains = new AbstractIterableMap[initialCount];

    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code AbstractIterableMap<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     * @see ArrayMap createArrayOfEntries method for more background on why we need this method
     */
    @SuppressWarnings("unchecked")
    private AbstractIterableMap<K, V>[] createArrayOfChains(int arraySize) {
        return (AbstractIterableMap<K, V>[]) new AbstractIterableMap[arraySize];
    }

    /**
     * Returns a new chain.
     *
     * This method will be overridden by the grader so that your ChainedHashMap implementation
     * is graded using our solution ArrayMaps.
     *
     * Note: You do not need to modify this method.
     */
    protected AbstractIterableMap<K, V> createChain(int initialSize) {
        return new ArrayMap<>(initialSize);
    }

    @Override
    public V get(Object key) {
        V have = null;
        int index = 0;
        if (key != null) {
            index = Math.abs((key.hashCode() % initialCount));
        }
        if (chains[index] != null) {
            have = chains[index].get(key);
        }
        return have;

    }

    private int indexing(Object key) {
        int index = 0;
        if (key != null) {
            index = Math.abs(key.hashCode() % initialCount);
        }
        return index;
    }

    @Override
    public V put(K key, V value) {
        // if (size >= (DRLFT * chains.length)) {
        //     AbstractIterableMap<K, V>[] old = chains;
        //     chains = new AbstractIterableMap[initialCap * 2];
        //     for (int i = 0; i < size; i++) {
        //         chains[i] = old[i];
        //     }
        // }
        V there = null;
        int index = indexing(key);
        if (chains[index] == null) {
            ArrayMap<K, V> added = new ArrayMap<>(initialCap);
            added.put(key, value);
            chains[index] = added;
        } else {
            there = chains[index].put(key, value);
        }
        if (there == null) {
            size+= 1;
        }
        return there;

    }

    @Override
    public V remove(Object key) {
        V rem = null;
        int index = indexing(key);
        if (chains[index] != null) {
            rem = chains[index].remove(key);
            if (chains[index].size() == 0) {
                chains[index] = null;
            }
        }
        if (rem != null) {
            size -= 1;
        }
        return rem;

    }

    @Override
    public void clear() {
        chains = new AbstractIterableMap[initialCount];
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int i = indexing(key);
        return (chains[i] != null && chains[i].containsKey(key));
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: you won't need to change this method (unless you add more constructor parameters)
        return new ChainedHashMapIterator<>(this.chains);
    }


    // // Doing so will give you a better string representation for assertion errors the debugger.
    // @Override
    // public String toString() {
    //     return super.toString();
    // }

    /*
    See the assignment webpage for tips and restrictions on implementing this iterator.
     */
    private static class ChainedHashMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private AbstractIterableMap<K, V>[] chains;
        // You may add more fields and constructor parameters
        private int index;
        private int size;

        private Iterator<Entry<K, V>> iter;
        private int move = 0;



        public ChainedHashMapIterator(AbstractIterableMap<K, V>[] chains) {
            this.index = 0;
            this.chains = chains;
            //this.size = size;
        }

        private int nextone(int i) {
            while (chains[i] == null) {
                if (i == chains.length - 1) {
                    return -1;
                }
                i++;
            }
            return i;
        }


        @Override
        public boolean hasNext() {
            if (move == chains.length - 1 | move == -1) {
                return false;
            }
            if (chains[move] == null) {
                move = nextone(move);
                if (move == -1) {
                    return false;
                }
            }
            if (this.iter == null) {
                this.iter = chains[move].iterator();
            }
            if (!iter.hasNext()) {
                move+=1;
                if (move == chains.length - 1) {
                    return false;
                }
                if (chains[move] == null) {
                    move = nextone(move);
                    if (move == -1) {
                        return false;
                    }
                }
                iter = chains[move].iterator();
            }
            return iter.hasNext();

        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                index = move;
                Entry<K, V> give = iter.next();
                return give;
            }
        }
    }
}
