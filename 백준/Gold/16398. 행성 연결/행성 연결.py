import sys
from queue import PriorityQueue

input = sys.stdin.readline

n = int(input())
qu = []
parent = [0] * n

for i in range(n):
    parent[i] = i
    costs = list(map(int, input().split()))

    for j in range(i + 1, n):
        qu.append((costs[j], i, j))

qu.sort()


def find(a):
    if parent[a] == a:
        return a
    parent[a] = find(parent[a])
    return parent[a]


def union(a, b):
    a = find(a)
    b = find(b)
    if a != b:
        parent[b] = a


ans = 0

for cost, s, e in qu:
    if find(s) != find(e):
        union(s, e)
        ans += cost

print(ans)
