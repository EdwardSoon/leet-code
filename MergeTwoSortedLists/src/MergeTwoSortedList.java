/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing (linked) together the nodes of the first two lists.

Return the head of the merged linked list.
e.g:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

 */


import java.util.List;

public class MergeTwoSortedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // base case
        if (list1 == null && list2 == null){
            return null;
        }
        else if (list1 == null){
            return list2;
        }
        else if (list2 == null){
            return list1;
        }

        ListNode mergedList, temp1, temp2;

        // the first case if list1 && list2 are not null
        if (list1.val <= list2.val) {
            mergedList = new ListNode(list1.val, null);  // next is always null
            list1 = list1.next; // traverse
        } else {
            mergedList = new ListNode(list2.val, null);
            list2 = list2.next; // traverse
        }

        ListNode lastMerged = mergedList; // for traversa;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp1 = new ListNode(list1.val, null);
                lastMerged.next = temp1;
                lastMerged = lastMerged.next; //traverse
                list1 = list1.next; // traverse

            } else {
                temp2 = new ListNode(list2.val, null);
                lastMerged.next = temp2;
                lastMerged = lastMerged.next; //traverse
                list2 = list2.next; // traverse
            }
        }

        if (list1 != null) {
            lastMerged.next = list1;
        }

        if (list2 != null) {
            lastMerged.next = list2;
        }

        return mergedList;
    }

    public static void printList(ListNode list) {
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode ls1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode ls2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));

        ListNode ls3 = new ListNode(2, null);
        ListNode ls4 = new ListNode(1, null);


        printList(ls1);
        printList(ls2);
        printList(mergeTwoLists(ls3, ls4));
    }
}