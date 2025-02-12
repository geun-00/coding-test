import heapq

n = int(input())
parent = list(range(n + 1))

qu = []
for i in range(1, n + 1):
    cost = int(input())
    heapq.heappush(qu, (cost, 0, i))

for i in range(1, n + 1):
    data = list(map(int, input().split()))
    for j in range(1, n + 1):
        if i != j:
            heapq.heappush(qu, (data[j - 1], i, j))

ans = 0


def find(a):
    if parent[a] == a:
        return a
    parent[a] = find(parent[a])
    return parent[a]


def union(u, v):
    u = find(u)
    v = find(v)
    if u != v:
        parent[v] = u


while qu:
    cost, u, v = heapq.heappop(qu)
    if find(u) != find(v):
        union(u, v)
        ans += cost
print(ans)
