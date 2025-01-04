import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

visit = [False] * 100_001
left, right = 0, 0
ans = 0

while right < n:

    if not visit[arr[right]]:
        visit[arr[right]] = True
        ans += (right - left + 1)
        right += 1
    else:
        visit[arr[left]] = False
        left += 1

print(ans)
