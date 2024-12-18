import sys
from queue import PriorityQueue

input = sys.stdin.readline

n, m = map(int, input().split())
schools = input().split()
parent = [i for i in range(n)]


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


qu = PriorityQueue()

for _ in range(m):
    start, end, cost = map(int, input().split())

    start -= 1
    end -= 1

    if schools[start] == schools[end]:
        continue

    qu.put((cost, start, end))

ans = 0
link = 0

while not qu.empty():
    cost, start, end = qu.get()

    if find(start) != find(end):
        union(start, end)
        ans += cost
        link += 1

print(ans if link == n - 1 else -1)