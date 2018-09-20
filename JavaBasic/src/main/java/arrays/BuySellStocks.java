package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuySellStocks {
// https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/

    public static void main(String[] args) {
//        int[] stocks = {100, 80, 70, 65, 60, 55, 50};
        int[] stocks = {100, 80, 120, 130, 70, 60, 100, 125};
        getMaxProfit(stocks);
        getMultipleMaxProfit(stocks);
        getKProfit(stocks);
    }

    private static void getMaxProfit(int[] stocks) {
        int profit = 0;
        int min = stocks[0];

        for (int i=1; i<stocks.length; i++) {
            profit = Math.max(profit, stocks[i]-min);
            min = Math.min(min, stocks[i]);
        }
        System.out.println(profit);
    }

    private static void getMultipleMaxProfit(int[] stocks) {
        int profit = 0;

        for (int i=1; i<stocks.length; i++) {
            if (stocks[i]>stocks[i-1])
            profit = profit + stocks[i]-stocks[i-1];
        }
        System.out.println(profit);
    }

    private static void getKProfit(int[] stocks) {
        int profit = 0;
        int min = stocks[0];
//        List profits = new ArrayList();
        List<Integer> profits = new ArrayList();
        for (int i=1; i<stocks.length; i++) {
            if (stocks[i]>stocks[i-1]) {
                profit = Math.max(profit, stocks[i]-min);
                if (stocks[i]<stocks[i-1])  {
                    min = stocks[i];
                    profits.add(profit);
                    profit = 0;
                }
            }
        }

        System.out.println(profits);

    }

}
