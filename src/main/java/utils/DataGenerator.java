package utils;

/** Utility class.
 *
 */
public class DataGenerator {

    /** Generation of List of ListNode's.
     *
     */
    public static ListNode createObj(int... nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode pre = new ListNode(-1);
        ListNode head = new ListNode(nums[0]);
        pre.next = head;
        for (int i = 1; i < nums.length; i++) {
            head.next = new ListNode(nums[i]);
            head = head.next;
        }
        return pre.next;
    }
}