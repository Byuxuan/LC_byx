package labuladong.day13ListNode;

import labuladong.ListNode;

import java.awt.*;
import java.util.PriorityQueue;

/**
 * DATE: 2022/2/7
 * CREATE BY: Byx
 */
public class MergeTwoLists {
    public ListNode successor;
    public ListNode mergeTwoLists21(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
                curr = curr.next;
            }else{
                curr.next = l2;
                l2 = l2.next;
                curr = curr.next;
            }

        }
        if(l1 != null) curr.next = l1;
        if(l2 != null) curr.next = l2;
        return dummy.next;
    }
    public ListNode mergeKLists23(ListNode[] lists) {
        if (lists.length == 0) return null;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int i = 0; i < lists.length; i++) {
            if(lists[i] != null) priorityQueue.offer(lists[i]);
        }
        while (!priorityQueue.isEmpty()) {
            ListNode currNode = priorityQueue.poll();
            curr.next = currNode;
            curr = curr.next;
            if(currNode.next != null) priorityQueue.offer(currNode.next);
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd19(ListNode head, int n) {
        if(head == null) return null;
        ListNode first = head;
        ListNode second = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = second;
        while (n > 0 && first != null) {
            first = first.next;
            n--;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
            dummy = dummy.next;
        }
        dummy.next = second.next;

        return second == head ? head.next : head;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        ListNode curr = head;
        ListNode end = null;
        dummy.next = head;
        int gap = right - left;
        while(left > 1){
            curr = curr.next;
            dummy = dummy.next;
            left --;
        }
        end = curr;
        while(gap>0){
            end = end.next;
            gap --;
        }
        successor = end.next;
        dummy.next = null;
        end.next = null;
        ListNode begin =  reverseList(curr);
        dummy.next = begin;
        curr.next = successor;
        return left == 1? end : head;



    }
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head;
        ListNode succ = head.next;
        while(succ != null){
            dummy.next = succ;
            succ = succ.next;
            dummy.next.next = curr;
            curr = dummy.next;
        }
        head.next = null;
        return dummy.next;
    }



    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode root2 = new ListNode(2);
        ListNode root3 = new ListNode(3);
        ListNode root4 = new ListNode(4);
        ListNode root5 = new ListNode(5);
//        root.next = root2;
//        root2.next = root3;
//        root3.next = root4;
//        root4.next = root5;
        //root.next = root2;
        //System.out.println(new MergeTwoLists().removeNthFromEnd19(root, 1).val);
        System.out.println(new MergeTwoLists().reverseBetween(root,1,1));
    }
}
