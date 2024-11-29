import sys
input = sys.stdin.readline

def solve(mid, arr):

    now = mid
    count = 1

    for num in arr:

        now -= num

        if now < 0:
            now = mid - num
            count += 1

    return count


n, m = map(int, input().split())

arr = [int(input()) for _ in range(n)]

left = max(arr)
right = sum(arr)
ans = 0

while left <= right:

    mid = (left+right) // 2

    withdraw = solve(mid, arr)

    if withdraw <= m:
       ans = mid
       right = mid - 1
    else:
        left = mid + 1

print(ans)
