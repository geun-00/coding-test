n = int(input())
dist = [[10000] * n for _ in range(n)]

for i in range(n):
    dist[i][i] = 0

while True:
    a, b = map(int, input().split())

    if a == -1 and b == -1:
        break

    a -= 1
    b -= 1
    dist[a][b] = 1
    dist[b][a] = 1

for k in range(n):
    for s in range(n):
        for e in range(n):
            dist[s][e] = min(dist[s][e], dist[s][k] + dist[k][e])

min_score = min(max(row) for row in dist)

result = [i + 1 for i in range(n) if max(dist[i]) == min_score]

print(min_score, len(result))
print(" ".join(map(str, result)))
