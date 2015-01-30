##Longest Increasing Sequence

>Let `L` be an array of `n` distinct integers. Write and implement an efficient algorithm to find the length of a longest increasing subsequence of entries in `L`. For example, if the entries are `11, 17, 5, 8, 6, 4, 7, 12, 3`, a longest increasing subsequence is `5, 6, 7, 12`.

###Solution:

If we want to find the Longest Increasing Sequence of an array, one possibility is that we try to compute all the different subsequences on `n` elements. Since there are 2<sup>n</sup> such sequences, it is not practical to solve using brute-force technique because O(2<sup>n</sup>) is not acceptable.

The other approach is Dynamic Programming. We build our way towards the solution, as we traverse through the array.

Consider the following example where `A = {11, 17, 5, 8, 6, 7, 12, 3}`

**i** | 0 | 1| 2 | 3 | 4 | 5 | 6 | 7 | 8
---|---|---|---|---|---|---|---|---|---|
**LIS[i]** | 1 | 2 | 1 | 2 | 2 | 1 | 3 | 4 | 1
**A[i]** | 11 | 17 | 5 | 8 | 6 | 4 | 7 | 12 | 3

Instead of solving LIS at every index, we can use the solution of the previous sub problem to find the solution on the `ith` index. We use an Array, `LIS`, to track the sub problems and their solutions.

  We can assume, the default value for `LIS(i) = 1`, unless otherwise noted.

  If we are at index 3, `A[3] = 8`. The LIS is dependent on the previous solution of the sub problem. Hence, we have to traverse backwards, and compare `A[i]` with `5, 17, 11` (in this order). We can do this with another index, `j`, which goes from `i-1` to `0`. If we find an `A[j] < A[i]`, and `LIS[j] + 1 > LIS[i]` (We want our LIS to be increasing), then our `LIS[i] = LIS[j] + 1`. In this case, our condition is satisfied when `j = 2` (5 < 8), and since our `LIS[3]` is initialized to 1, `LIS[2] + 1 > LIS[3]` or `2 > 1`, so `LIS[3] = 2`.

We can represent the recurrence of our solution as,
> `LIS[i] = 1`

>`LIS[i] = 1 + Max(L[j])`, `array[j] < array[i] ; j < i; j ≥ 0`

If we continue this process, we will have maintained LIS for every index `(i)`. Although this determines the max length of LIS, it does not help us to determine the longest increasing subsequence.

For this, we will need to create another array, `PREV`, of length `n`, that records the position `j`, the index of the sub problem, used to solve the LIS on the `ith` index. If no sub problem is found, then we record -1`, which denotes the start of a sequence. This gives us a solution of O*(n<sup>2</sup>)*.
