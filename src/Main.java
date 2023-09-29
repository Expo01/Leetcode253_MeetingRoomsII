import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//recall a priority queue is a form of heap data structure and itis essentially a sorted queue


class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {return 0;}

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0])); // this is yet an even more concise way to write a lambda
        //[1,10], [[2,7], [3,19], [8,12], [10,20], [11,30]

        PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(); // this, unless otherwise specified, will sort ascending when new element added
        allocator.add(intervals[0][1]); // allocator: 10

        for (int i = 1; i < intervals.length; i++) {
            if (allocator.peek() <= intervals[i][0]) { // if no overlap soonest end vs start, then remove the end time since no conflict
                allocator.poll();
            }
            allocator.add(intervals[i][1]); // if start time overlaps with soonest end time, will need a new room
        }

        return allocator.size(); // will = 4 in this example
    }
}
/*
1: [1,10], [10,20]
2: [2,7], [8,12]
3: [3,19]
4: [11,30]

// allocator: 7,10,12,19,20,30      although some will have been removed already by the time later elements added


 */

//IDEA
// sort by end time. extract an overlapping interval
// into a new arrayList. problem is that every time
// there is an overlap with the new arraylist we will
// have to create another arrayList,. space wise, this
// could be very inefficient with many many arraylists
// could map key as current Room# and arrayList<int[]> as
// value but would still have to loop check last end time
// in all meetings at keys 1 through x
// [5,10] [15,20] [0,30] [6,32]