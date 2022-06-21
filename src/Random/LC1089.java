package Random;

/**
 * DATE: 2022/6/17
 * CREATE BY: Byx
 */
public class LC1089 {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int top = 0;
        int i = -1;
        while (top < n) {
            i++;
            if (arr[i] != 0) {
                top++;
            } else {
                top += 2;
            }
        }
        int j = n - 1;
        if (top == n + 1) {
            arr[j] = 0;
            j--;
            i--;
        }
        while (j >= 0) {
            arr[j] = arr[i];
            j--;
            if (arr[i] == 0) {
                arr[j] = arr[i];
                j--;
            }
            i--;
        }
    }

    public static void main(String[] args) {
        new LC1089().duplicateZeros(new int[]{1, 0, 2, 3, 0, 4, 5, 0});
    }
}
