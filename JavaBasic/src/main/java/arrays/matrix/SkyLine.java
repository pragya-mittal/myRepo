package arrays.matrix;

/*
    https://leetcode.com/problems/the-skyline-problem/discuss/61192/Once-for-all-explanation-with-clean-Java-code(O(n2)time-O(n)-space)
        for position in sorted(all start points and all end points)
       if this position is a start point
    add its height
       else if this position is a end point
    delete its height
    compare current max height with previous max height, if different, add
    current position together with this new max height to our result, at the
    same time, update previous max height to current max height;
*/

public class SkyLine {
    public static void main(String[] args)
    {
        int[][] buildings = { {2,9,10},
                              {3,6,15},
                              {5,12,12},
                              {13,16,10},
                              {15,17,5}};

        getSkyline(buildings);
    }



    private static void getSkyline(int[][] buildings) {
        int carry=0;int height =0;
        int x=0,y=0;
        for (int i=1;i<buildings.length;i++) {
//            x= Math.min(buildings[i][0],buildings[i-1][0]);
//            y = Math.min(buildings[i][2],buildings[i-1][2]);
//            System.out.print(" {" + x + ", " + y + "}, ");
            if (buildings[i][0]>buildings[i-1][0]) {
                if (buildings[i][0]>buildings[i-1][1]) {
                    System.out.print(" {" + buildings[i-1][0] + ", " + buildings[i-1][2] + "}, ");
                } else if (buildings[i][0]<buildings[i-1][1]) {
                    if (buildings[i][1]>buildings[i-1][1]) {
                        System.out.print(" {" + buildings[i-1][0] + ", " + buildings[i-1][2] + "}, ");
                        x=buildings[i][0];
                    } else {
                        System.out.print(" {" + buildings[i-1][0] + ", " + buildings[i-1][2] + "}, ");
                    }
                }

                }
            }
        }
}
