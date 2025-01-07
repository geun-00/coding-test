import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
arrows = [0] * 1_000_001

ans = 0
i = 0
while i < n:
    if arrows[arr[i]] > 0:
        arrows[arr[i]] -= 1
    else:
        ans += 1

    while i < n - 1 and arr[i] - 1 == arr[i + 1]:
        i += 1

    arrows[arr[i] - 1] += 1
    i += 1

print(ans)