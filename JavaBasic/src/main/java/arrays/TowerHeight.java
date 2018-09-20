package arrays;

public class TowerHeight {

    public static void main(String[] args) {
//        int[] arr = {2,5,2,3,1,7,2};
        int[] arr = { 1, 5, 2, 3, 1, 7, 2, 4 };
        System.out.println(getMaxRainwaterBetweenTowers(arr));
    }

    public static int getMaxRainwaterBetweenTowers(int[] towerHeight) {
        int maxSeenSoFar = 0;

        int[] maxSeenRight = new int[towerHeight.length];
        int maxSeenLeft = 0;

        int rainwater = 0;

        for (int i = towerHeight.length - 1; i >= 0; i--) {
            if (towerHeight[i] > maxSeenSoFar) {
                maxSeenSoFar = towerHeight[i];
                maxSeenRight[i] = maxSeenSoFar;
            } else {
                maxSeenRight[i] = maxSeenSoFar;
            }
        }

        for (int i = 0; i < towerHeight.length; i++) {
            rainwater = rainwater + Math.max(Math.min(maxSeenLeft, maxSeenRight[i]) - towerHeight[i], 0);
            if (towerHeight[i] > maxSeenLeft) {
                maxSeenLeft = towerHeight[i];
            }
        }

        return rainwater;
    }
}
