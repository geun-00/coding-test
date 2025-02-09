n, k = map(int, input().split())

arr = [0] * 1_000_001

for _ in range(n):
    g, x = map(int, input().split())
    arr[x] = g

sum = sum(arr[:min(len(arr), 2 * k + 1)])

ans = sum

left, right = 0, 2 * k + 1
while right < len(arr):
    sum -= arr[left]
    sum += arr[right]
    ans = max(ans, sum)
    left += 1
    right += 1

print(ans)
