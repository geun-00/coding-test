from collections import defaultdict

m = int(input())

stars = []

for _ in range(m):
    x, y = map(int, input().split())
    stars.append((x, y))

n = int(input())

my_map = defaultdict(int)

for _ in range(n):
    x, y = map(int, input().split())

    for sx, sy in stars:
        nx = x - sx
        ny = y - sy
        my_map[(nx, ny)] += 1

for point, count in my_map.items():
    if count == m:
        print(point[0], point[1])
        break