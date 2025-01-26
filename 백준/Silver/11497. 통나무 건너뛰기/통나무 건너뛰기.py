t = int(input())

for _ in range(t):

    n = int(input())
    arr = list(map(int, input().split()))
    temp = [0] * n

    arr.sort()

    left, right = 0, n - 1

    for i in range(n):
        if i % 2 == 0:
            temp[left] = arr[i]
            left += 1
        else:
            temp[right] = arr[i]
            right -= 1

    ans = abs(temp[n - 1] - temp[0])

    for i in range(n - 1):
        ans = max(ans, abs(temp[i + 1] - temp[i]))

    print(ans)