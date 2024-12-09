import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n, s, d = map(int, input().split())
s -= 1
tree = [[] for _ in range(n)]
total = 0

for _ in range(n - 1):
    x, y = map(int, input().split())
    x -= 1
    y -= 1
    tree[x].append(y)
    tree[y].append(x)


def dfs(node, parent, d):
    max_depth = 0

    for next in tree[node]:
        if next != parent:
            child_depth = dfs(next, node, d) + 1

            if child_depth > d:
                global total
                total += 2

            max_depth = max(max_depth, child_depth)

    return max_depth


dfs(s, -1, d)

print(total)
