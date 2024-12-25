import sys
from bisect import bisect_left

input = sys.stdin.readline

n, m = map(int, input().split())

my_map = {}
arr = []

for i in range(n):
    s, num = input().split()
    num = int(num)

    if num not in my_map:
        my_map[num] = s

    arr.append(num)

ans = []

for _ in range(m):
    num = int(input())

    low = bisect_left(arr, num)
    ans.append(my_map[arr[low]])

sys.stdout.write("\n".join(ans))