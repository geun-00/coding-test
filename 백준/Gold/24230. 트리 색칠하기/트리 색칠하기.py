import sys
sys.setrecursionlimit(10**6)
from collections import defaultdict

input = sys.stdin.readline

def dfs(parent, node):
    count = 1

    for child in tree[node]:
        if child != parent:
            count += dfs(node, child)

    if parent != -1 and color[parent] == color[node]:
        count -= 1

    return count

n = int(input())
color = list(map(int, input().split()))
tree = defaultdict(list)

for _ in range(n - 1):
    a, b = map(int, input().split())
    a -= 1
    b -= 1
    tree[a].append(b)
    tree[b].append(a)

ans = dfs(-1, 0)
if color[0] != 0:
    ans += 1

print(ans - 1)
