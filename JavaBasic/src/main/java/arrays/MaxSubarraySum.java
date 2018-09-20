package arrays;

public class MaxSubarraySum {
    public static int findMaxSubarraySum(int[] input) {
        int curSum = 0;
        int maxSum = 0;
        boolean hasAllNegativeNumbers = true;
        int maxNegativeSum = Integer.MIN_VALUE;
        for(int i = 0; i < input.length; i++) {
            if(hasAllNegativeNumbers && input[i] >= 0) {
                hasAllNegativeNumbers = false;
            } else if(hasAllNegativeNumbers && input[i] < 0 && maxNegativeSum < input[i]) {
                maxNegativeSum = input[i];
            }

            curSum += input[i];
            if(curSum < 0) {
                curSum = 0;
            }
            if(maxSum < curSum) {
                maxSum = curSum;
            }
        }
        return hasAllNegativeNumbers ? maxNegativeSum : maxSum;
    }

    public static void main(String[] args) {
        int[] input = {2, -9, 5, 1, -4, 6, 0, -7, 8};
        System.out.println("Maximum subarray sum is " + findMaxSubarraySum(input));
    }

    //https://www.quora.com/What-is-the-logic-used-in-the-HackerRank-Maximise-Sum-problem
    /*
    To solve it in a better way, the problem requires some knowledge of modular arithmetic.
Here are some basic formula for this problem:
(a+b)%M=(a%M+b%M)%M --- 1
(a−b)%M=(a%M−b%M)%M --- 2

This link: Modular arithmetic, explains 1, 2 in a straightforward way.

Now, let's solve the problem with the simple math formula!

Usually, a great many problems related to "subarray computation" could be solved with prefix array, which saves time for repeating computation.
Define:
prefix[n]=(a[0]+a[1]+...+a[n])%M

To construct a prefix table, we could use the following code. (It is a generalization of the 1, 2 formula I mentioned before)
int curr = 0;
for(int i = 0; i < n; i ++) {
  curr = (arr[i] % M + curr) % M;
  prefix[i] = curr;
}


The we have to find a subarray. Any subarray can be expressed in the following way:
sumModular[i,j]=(prefix[j]−prefix[i−1]+M)%M --3

which is very simple and efficient.
The correctness of 3 require some math based on 1 and 2. And 3 is the key to the efficient solution!

Let's write the solution of the BF algorithm first:
int ret = 0;
for(int i = 0; i < n; i ++) {
  for(int j = i-1; j >= 0; j --) {
    ret = max(ret, (prefix[i] - prefix[j] + M) % M)
  }
  ret = max(ret, prefix[i]); // Don't forget sum from beginning.
}


     */
}
