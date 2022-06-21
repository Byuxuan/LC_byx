package Random;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * DATE: 2022/5/26
 * CREATE BY: Byx
 */
public class LC729 {



    public static void main(String[] args) {
//        MyCalendar myCalendar = new MyCalendar();
        MyCalendar2 myCalendar = new MyCalendar2();
        myCalendar.book(10, 15);
        myCalendar.book(1, 3);
        myCalendar.book(0, 1);

    }

}
class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    MyCalendar() {

        calendar = new TreeMap();
    }

    public boolean book(int start, int end) {
        Integer pre = calendar.floorKey(start);
        Integer tail = calendar.ceilingKey(start);
        if ((pre == null || calendar.get(pre) <= start) && (tail == null ||  end <= tail)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }

}


class MyCalendar2 {
    class Node {
        // ls 和 rs 分别代表当前节点的左右子节点在 tr 的下标
        // val 代表当前节点有多少数
        // add 为懒标记
        int ls, rs, add, val;
    }
    int N = (int)1e9, M = 120010, cnt = 1;
    Node[] tr = new Node[M];
    void update(int u, int lc, int rc, int l, int r, int v) {
        if (l <= lc && rc <= r) {
            tr[u].val += (rc - lc + 1) * v;
            tr[u].add += v;
            return ;
        }
        lazyCreate(u);
        pushdown(u, rc - lc + 1);
        int mid = lc + rc >> 1;
        if (l <= mid) update(tr[u].ls, lc, mid, l, r, v);
        if (r > mid) update(tr[u].rs, mid + 1, rc, l, r, v);
        pushup(u);
    }
    int query(int u, int lc, int rc, int l, int r) {
        if (l <= lc && rc <= r) return tr[u].val;
        lazyCreate(u);
        pushdown(u, rc - lc + 1);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans = query(tr[u].ls, lc, mid, l, r);
        if (r > mid) ans += query(tr[u].rs, mid + 1, rc, l, r);
        return ans;
    }
    void lazyCreate(int u) {
        if (tr[u] == null) tr[u] = new Node();
        if (tr[u].ls == 0) {
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }
    }
    void pushdown(int u, int len) {
        tr[tr[u].ls].add += tr[u].add; tr[tr[u].rs].add += tr[u].add;
        tr[tr[u].ls].val += (len - len / 2) * tr[u].add; tr[tr[u].rs].val += len / 2 * tr[u].add;
        tr[u].add = 0;
    }
    void pushup(int u) {
        tr[u].val = tr[tr[u].ls].val + tr[tr[u].rs].val;
    }
    public boolean book(int start, int end) {
        if (query(1, 1, N + 1, start + 1, end) > 0) return false;
        update(1, 1, N + 1, start + 1, end, 1);
        return true;
    }
}



class MyCalendar3 {
    SegmentNode root;

    public MyCalendar3() {
        root=new SegmentNode(0,0);
    }

    public boolean book(int start, int end) {
        return updateSegmentTree(root,start,end);
    }

    public boolean updateSegmentTree(SegmentNode root,int start,int end) {

        SegmentNode node =root;
        while (true) {
            if (end<=node.start){
                if (node.left==null) {
                    node.left=new SegmentNode(start,end);
                    return true;
                }
                node=node.left;
            }else if (node.end<=start) {
                if (node.right==null) {
                    node.right=new SegmentNode(start,end);
                    return true;
                }
                node=node.right;
            }else {
                return false;
            }
        }
    }
}


class SegmentNode {
    int start;
    int end;
    SegmentNode left;
    SegmentNode right;

    SegmentNode(int start,int end) {
        this.start=start;
        this.end=end;
    }
}


