import sys

INF = int(1e9)

n = int(input())
m = int(input())

dist = [[INF] * (n + 1) for _ in range(n + 1)]
path = [[0] * (n + 1) for _ in range(n + 1)]

for i in range(1, n + 1):
    dist[i][i] = 0

for _ in range(m):
    a, b, c = map(int, input().split())
    if dist[a][b] > c:
        dist[a][b] = c
        path[a][b] = b

for k in range(1, n + 1):
    for s in range(1, n + 1):
        for e in range(1, n + 1):
            if dist[s][e] > dist[s][k] + dist[k][e]:
                dist[s][e] = dist[s][k] + dist[k][e]
                path[s][e] = path[s][k]

ans = []

for i in range(1, n + 1):
    for j in range(1, n + 1):
        if dist[i][j] == INF:
            ans.append("0")
        else:
            ans.append(str(dist[i][j]))
        ans.append(" ")
    ans.append("\n")


for s in range(1, n + 1):
    for e in range(1, n + 1):
        if s == e or dist[s][e] == INF:
            ans.append("0\n")
            continue

        cities = []
        now = s
        while now != e:
            cities.append(now)
            now = path[now][e]
        cities.append(e)

        ans.append(str(len(cities)))
        ans.append(" ")
        ans.append(" ".join(map(str, cities)))
        ans.append("\n")

print("".join(ans))
