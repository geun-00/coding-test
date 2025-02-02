n = int(input())
m = int(input())

arr = list(map(int, input().split()))

left, right = 1, n
ans = 0

while left <= right:

    mid = (left + right) // 2

    light = 0

    for pos in arr:
        if light < pos - mid:
            break
        light = pos + mid

    if light >= n:
        ans = mid
        right = mid - 1
    else:
        left = mid + 1

print(ans)
