package net.golbarg.nahjolfasahe;

import java.util.*;

public class TestCode {

    public static void main(String[] args) {
//        System.out.println(findMaxLength(4, new int[]{1, 1, 0, 1}));
//        System.out.println(findMaxLength(5, new int[]{1, 1, 1, 1, 1}));

//        System.out.println(calculateT(5));
//        System.out.println(calculateT(3));

//        System.out.println("2 = " + countDifferentFlowers(5, 3, new int[]{1,1,2,1,5}));
//        System.out.println("4 = " + countDifferentFlowers(10, 7, new int[]{2,1,2,1,3,1,4,5,6,1}));

        System.out.println("1 = " + minWarehouses(2, new int[][]{{1, 2}, {3, 5}}));
        System.out.println("3 = " + minWarehouses(3, new int[][]{{2, 5, 9}, {3, 7, 10}}));
    }

    public static int minWarehouses(int startIndex, int[][] stores) {
        int n = stores[0].length;

        // Combine the start and end locations into a single array for easy sorting
        int[] locations = new int[2 * n];
        for (int i = 0; i < n; i++) {
            locations[i] = stores[0][i];
            locations[i + n] = stores[1][i];
        }

        // Sort the locations array
        Arrays.sort(locations);

        int warehouses = 0;
        int currentWarehouseEnd = Integer.MIN_VALUE;

        // Traverse the sorted locations array starting from the specified index
        for (int i = Math.max(startIndex, 0); i < 2 * n; i++) {
            if (locations[i] > currentWarehouseEnd) {
                // Found a new good location
                warehouses++;
                currentWarehouseEnd = locations[i];
            }
        }

        return warehouses;
    }


    public static int countDifferentFlowers(int N, int K, int[] P) {
        boolean[] visited = new boolean[N + 1];

        // Serena visits the first K pots
        int uniqueCount = 0;
        for (int i = 0; i < K; i++) {
            int potIndex = P[i];
            if (!visited[potIndex]) {
                visited[potIndex] = true;
                uniqueCount++;
            }
        }

        // Return the number of different flowers plucked
        return uniqueCount;
    }

//    public static int countDifferentFlowers(int N, int K, int[] P) {
//        Set<Integer> uniqueFlowers = new HashSet<>();
//
//        // Serena visits the first K pots
//        for (int i = 0; i < K; i++) {
//            uniqueFlowers.add(P[i]);
//        }
//
//        // Return the number of different flowers plucked
//        return uniqueFlowers.size();
//    }

    private static final int MOD = (int) 1e9 + 7;

    public static int calculateT(int N) {
        if (N < 4) {
            return 1;
        } else {
            int result = calculateT(N / 3) + 2 * calculateT(N / 2) + 5 * N;
            return result % MOD;
        }
    }

    public static int findMaxLength(int length, int[] nums) {
        int maxLen = 0;
        int sum = 0;
        int[] sumIndex = new int[2 * length + 1];

        Arrays.fill(sumIndex, -1);

        sumIndex[length] = -1;

        for (int i = 0; i < length; i++) {

            sum += (nums[i] == 0 ? -1 : 1);

            if (sumIndex[sum + length] != -1) {
                int startIndex = sumIndex[sum + length];
                int currentLen = i - startIndex;
                maxLen = Math.max(maxLen, currentLen);
            } else {

                sumIndex[sum + length] = i;
            }
        }

        return maxLen;
    }


}
