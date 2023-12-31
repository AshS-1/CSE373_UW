package problems;

import datastructures.LinkedIntList;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.LinkedIntList.ListNode;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `LinkedIntList` objects.
 * - do not construct new `ListNode` objects for `reverse3` or `firstToLast`
 *      (though you may have as many `ListNode` variables as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the list only by modifying
 *      links between nodes.
 */

public class LinkedIntListProblems {

    /**
     * Reverses the 3 elements in the `LinkedIntList` (assume there are exactly 3 elements).
     */
    public static void reverse3(LinkedIntList list) {
        ListNode current = list.front.next.next;
        current.next = list.front.next;
        current.next.next = list.front;
        list.front = current;
        current.next.next.next = null;
    }

    /**
     * Moves the first element of the input list to the back of the list.
     */
    public static void firstToLast(LinkedIntList list) {
        if (list.front != null) {
            ListNode current = list.front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = list.front;
            list.front = list.front.next;
            current = current.next;
            current.next = null;
        }
    }

    /**
     * Returns a list consisting of the integers of a followed by the integers
     * of n. Does not modify items of A or B.
     */

    public static LinkedIntList concatenate(LinkedIntList a, LinkedIntList b) {
        LinkedIntList result = new LinkedIntList();
        if (a.front == null && b.front == null) {
            result.front = null;
        } else if (a.front == null) {
            result.front = b.front;
        } else if (b.front == null) {
            result.front = a.front;
        } else {
            result.front = a.front;
            ListNode current = a.front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = b.front;
        }
        return result;
    }
}
