n = int(input())

arr = list(map(int, input().split()))

ans = float('inf')
left = 0
right = n - 1

while left < right:
    diff = arr[right] + arr[left]

    if abs(diff) < abs(ans):
        ans = diff

    if diff < 0:
        left += 1
    else:
        right -= 1

print(ans)