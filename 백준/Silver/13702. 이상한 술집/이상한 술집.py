n, k = map(int, input().split())
arr = [int(input()) for _ in range(n)]

left, right = 1, max(arr)
ans = 0

while left <= right:
    mid = (left + right) // 2
    count = 0

    for i in arr:
        count += (i // mid)

    if count >= k:
        ans = mid
        left = mid + 1
    else:
        right = mid - 1

print(ans)
