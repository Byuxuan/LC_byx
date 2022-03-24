package labuladong.day22;
import java.util.*;
/**
 * DATE: 2022/3/1
 * CREATE BY: Byx
 */
public class removeInvalidParentheses301 {
    public List<String> removeInvalidParentheses(String s) {
        // 先统计需要删除的左括号数和右括号数
        int leftExtra = 0, rightExtra = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftExtra++;
            } else if (c == ')') {
                if (leftExtra == 0) {
                    rightExtra++;
                } else {
                    leftExtra--;
                }
            }
        }

        // 如果字符串本身就是合法的，就不用删除了
        if (leftExtra == 0 && rightExtra == 0) {
            return Arrays.asList(s);
        }

        Set<String> ans = new HashSet<>();

        // 回溯
        dfs(ans, s, 0, new StringBuilder(), leftExtra, rightExtra);

        return new ArrayList<>(ans);
    }

    private void dfs(Set<String> ans, String s, int i, StringBuilder sb, int leftExtra, int rightExtra) {
        if (i == s.length()) {
            if (leftExtra == 0 && rightExtra == 0 && isValid(sb)) {
                ans.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(i);
        if (c == '(') {
            // 要这个左括号
            sb.append(c);
            dfs(ans, s, i + 1, sb, leftExtra, rightExtra);
            sb.deleteCharAt(sb.length() - 1);

            // 不要这个左括号
            if (leftExtra > 0) {
                dfs(ans, s, i + 1, sb, leftExtra - 1, rightExtra);
            }
        } else if (c == ')') {
            // 要这个右括号
            sb.append(c);
            dfs(ans, s, i + 1, sb, leftExtra, rightExtra);
            sb.deleteCharAt(sb.length() - 1);

            // 不要这个右括号
            if (rightExtra > 0) {
                dfs(ans, s, i + 1, sb, leftExtra, rightExtra - 1);
            }
        } else {
            sb.append(c);
            dfs(ans, s, i + 1, sb, leftExtra, rightExtra);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    private boolean isValid(StringBuilder sb) {
        // 判断括号是否合法
        int left = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left == 0) {
                    return false;
                } else {
                    left--;
                }
            }
        }
        return left == 0;
    }

    public int[] topKFrequent347(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            queue.offer(new int[]{entry.getKey(),entry.getValue()});
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll()[0];
        }
        return result;
    }

        public String decodeString394(String s) {
            StringBuilder res = new StringBuilder();
            int multi = 0;
            LinkedList<Integer> stack_multi = new LinkedList<>();
            LinkedList<String> stack_res = new LinkedList<>();
            for(Character c : s.toCharArray()) {
                if(c == '[') {
                    stack_multi.addLast(multi);
                    stack_res.addLast(res.toString());
                    multi = 0;
                    res = new StringBuilder();
                }
                else if(c == ']') {
                    StringBuilder tmp = new StringBuilder();
                    int cur_multi = stack_multi.removeLast();
                    for(int i = 0; i < cur_multi; i++) tmp.append(res);
                    res = new StringBuilder(stack_res.removeLast() + tmp);
                }
                else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
                else res.append(c);
            }
            return res.toString();
        }



    public static void main(String[] args) {
        //System.out.println(new removeInvalidParentheses301().decodeString394("3[a2[c]]"));
        System.out.println(Arrays.toString(new removeInvalidParentheses301().topKFrequent347(new int[]{1, 2, 1, 3}, 2)));
        //System.out.println(Arrays.deepToString(new removeInvalidParentheses301().reconstructQueue406(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}})));
    }



}
