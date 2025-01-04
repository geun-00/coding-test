n = int(input())
height = list(map(int, input().split()))

ans = 0

for i in range(n):

    count = 0
    x1, y1, = i, height[i]

    min_slope = float('inf')

    for j in range(i - 1, -1, -1):
        x2, y2 = j, height[j]

        slope = (y2 - y1) / (x2 - x1)

        if min_slope > slope:
            min_slope = slope
            count += 1

    max_slope = float('-inf')

    for j in range(i + 1, n):
        x2, y2 = j, height[j]

        slope = (y2 - y1) / (x2 - x1)

        if max_slope < slope:
            max_slope = slope
            count += 1

    ans = max(ans, count)

print(ans)
