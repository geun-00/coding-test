n = int(input())
work = []

for _ in range(n):
    t, s = map(int, input().split())
    work.append((t, s))

work.sort(key=lambda x: -x[1])

start = work[0][1]

for t, s in work:
    start = min(start, s) - t

print(max(-1, start))