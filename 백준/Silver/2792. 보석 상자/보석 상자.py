import math
import sys
input = sys.stdin.readline

n, m = map(int, input().split())

arr = []

for _ in range(m):
    arr.append(int(input()))

low, high = 1, max(arr)

ans = 0

while low <= high:
    mid = (low + high) // 2

    count = 0
    for i in arr:
        count += math.ceil(i / mid)

    if count <= n:
        ans = mid
        high = mid - 1
    else:
        low = mid + 1

print(ans)