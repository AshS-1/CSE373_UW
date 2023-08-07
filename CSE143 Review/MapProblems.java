package problems;

import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * See the spec on the website for example behavior.
 */
public class MapProblems {

    /**
     * Returns true if any string appears at least 3 times in the given list; false otherwise.
     */
    public static boolean contains3(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String a : list) {
            if (map.containsKey(a)) {
                int value = map.get(a);
                map.put(a, value + 1);
            } else {
                map.put(a, 1);
            }
        }
        for (String a: map.keySet()) {
            if (map.get(a) >= 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a map containing the intersection of the two input maps.
     * A key-value pair exists in the output iff the same key-value pair exists in both input maps.
     */
    public static Map<String, Integer> intersect(Map<String, Integer> m1, Map<String, Integer> m2) {
        Map<String, Integer> result = new HashMap<>();
        for (String a: m1.keySet()) {
            if (m2.containsKey(a) && m1.get(a) == m2.get(a)) {
                result.put(a, m1.get(a));
            }
        }
        return result;
    }
}
