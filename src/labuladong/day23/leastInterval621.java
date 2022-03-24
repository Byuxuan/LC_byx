package labuladong.day23;

import java.util.*;

/**
 * DATE: 2022/3/7
 * CREATE BY: Byx
 */
public class leastInterval621 {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            hashMap.put(tasks[i] - 'A', hashMap.getOrDefault(tasks[i] - 'A', 0) + 1);
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }else{
                    return o2[1] - o1[1];
                }

            }
        });
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            priorityQueue.offer(new int[]{key, value});

        }

        int res = 0;

        while (!priorityQueue.isEmpty()){
            int currIndex = 0;
            HashSet<Integer> hashSet = new HashSet<>();
            while (!priorityQueue.isEmpty()){
                int[] curr = priorityQueue.peek();
                if (!hashSet.contains(curr[0])) {
                    priorityQueue.poll();
                    if (curr[1] > 1) {
                        hashSet.add(curr[0]);
                        hashMap.put(curr[0], curr[1] - 1);
                        priorityQueue.offer(new int[]{curr[0], curr[1] - 1});
                    }
                    currIndex++;
                    res++;
                } else {
                    while (currIndex <= n) {
                        currIndex++;
                        res++;
                    }
                    break;
                }
            }
        }

        return res;
    }

    public int leastInterval2(char[] tasks, int n) {
        int[] chars = new int[26];
        int max = 0;
        int max_num = 0;
        for (int i = 0; i < tasks.length; i++) {
            chars[tasks[i] - 'A']++;
            max = Math.max(max, chars[tasks[i] - 'A']);
        }
        for (int i = 0; i < chars.length; i++) {
            if (max == chars[i]) {
                max_num++;
            }
        }
        return Math.max(tasks.length, (max - 1) * (n+1) + max_num);


    }


    public int[][] reconstructQueue406(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) return o2[0] - o1[0];
                return o1[1] - o2[1];
            }
        });

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            if (people[i][1] >= arrayList.size()) {
                arrayList.add(i);
            }else {
                arrayList.add(people[i][1], i);
            }
        }
        int[][] result = new int[people.length][2];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i][0] = people[arrayList.get(i)][0];
            result[i][1] = people[arrayList.get(i)][1];
        }
        return result;
    }


    public int minMeetingRooms253(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; i++) {
            if (queue.isEmpty()) {
                queue.add(intervals[i][1]);
            }else {
                int curr = queue.peek();
                if (intervals[i][0] >= curr) {
                    queue.poll();
                }
                queue.offer(intervals[i][1]);
            }
        }
        return queue.size();
    }


    public static void main(String[] args) {
        //System.out.println(new leastInterval621().leastInterval2(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'},2));
        System.out.println(new leastInterval621().reconstructQueue406(new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}}));
    }
}
