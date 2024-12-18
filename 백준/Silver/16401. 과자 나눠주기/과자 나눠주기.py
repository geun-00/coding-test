m, n = map(int, input().split())
arr = list(map(int, input().split()))

low = 1
high = 10**9
ans = 0

while low <= high:

    mid = (low + high) // 2

    sum = 0

    for i in range(n):
        sum += arr[i] // mid

    if sum >= m:
        ans = mid
        low = mid + 1
    else:
        high = mid - 1

print(ans)