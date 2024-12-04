import sys
from queue import PriorityQueue

input = sys.stdin.readline

n = int(input())
qu = PriorityQueue()
parent = [0] * n

for i in range(n):
    parent[i] = i
    costs = list(map(int, input().split()))

    for j in range(i + 1, n):
        qu.put((costs[j], i, j))


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

while not qu.empty():
    cost, s, e = qu.get()

    if find(s) != find(e):
        union(s, e)
        ans += cost

print(ans)
