import sys
from collections import defaultdict
sys.setrecursionlimit(10**6)

input = sys.stdin.readline


def find_giga_node(node, parent):
    children = 0

    for to, d in tree[node]:
        if to != parent:
            children += 1
            dist[to] = dist[node] + d
            find_giga_node(to, node)

    if children == 0:
        leaf_nodes.append(node)

    if children >= 2:
        global giga_node
        giga_node = node


n, r = map(int, input().split())
r -= 1
tree = defaultdict(list)
dist = [0] * n
leaf_nodes = []

giga_node = -1

for _ in range(n - 1):
    a, b, c = map(int, input().split())
    a -= 1
    b -= 1

    tree[a].append((b, c))
    tree[b].append((a, c))

find_giga_node(r, -1)

if len(leaf_nodes) == 1:
    giga_node = leaf_nodes[0]

ans1 = dist[giga_node]
ans2 = max((dist[node] - dist[giga_node] for node in leaf_nodes), default=0)

print(ans1, ans2)
