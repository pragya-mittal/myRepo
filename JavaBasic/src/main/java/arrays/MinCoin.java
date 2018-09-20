package arrays;

public class MinCoin {
    public static void main(String args[])
    {
        int coins[] =  {9, 6, 5, 1};
        int m = coins.length;
        int V = 11;
//        System.out.println("Minimum coins required is "+ minCoins(coins, m, V) );
        System.out.println("Minimum coins required is "+ getCoins(coins, V) );
    }

    private static int getCoins(int[] coins, int v) {
        if (v==0)
            return 0;
        int res = Integer.MAX_VALUE;

        for (int i=0; i<coins.length;i++) {
            if (v>=coins[i]) {
                int subres = getCoins(coins, v-coins[i]);

                if (subres!= Integer.MAX_VALUE && subres + 1 < res)
                    res = subres + 1;
            }
        }
        return res;
    }


//    private static int minCoins(int[] coins, int m, int v) {
//        if (v==0)
//            return 0;
//        int minCoin = Integer.MIN_VALUE;
//        for (int i=0;i<m;i++) {
//            if (coins[i]<=v) {
//                minCoin = minCoins(coins, m, v-coins[i]);
//            }
//        }
//        return minCoin;
//    }
//
//    private static int getCoins(int[] coins, int val) {
//        int min = 0;
//        int count = 0;
//        min = getCoins(coins, val, 0, min, count);
//        return min;
//
//    }
//
//    private static int getCoins(int[] coins, int val, int start, int min, int count) {
//        if (val==0) {
//            if (count>min)
//                min=count;
//            return min;
//        }
//        if (start==coins.length && val!=0)
//            return 0;
//        if (coins[start]<=val) {
//            count++;
//            if(getCoins(coins, val- coins[start], ++start, min, count)==0)
//                return getCoins(coins, val, ++start, min, count);
//            else
//                return Math.min(getCoins(coins, val- coins[start], ++start, min, count), getCoins(coins, val, ++start, min, count));
//        } else {
//            return getCoins(coins, val, ++start, min, count);
//        }
//
//    }
}
