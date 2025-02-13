import sys

input = sys.stdin.readline


def update(p, x, node, start, end):
    if start > p or end < p:
        return

    if start == end:
        tree[node] += x
        return

    mid = (start + end) // 2

    update(p, x, node * 2, start, mid)
    update(p, x, node * 2 + 1, mid + 1, end)

    tree[node] = tree[node * 2] + tree[node * 2 + 1]


def get_result(left, right, node, start, end):
    if right < start or left > end:
        return 0

    if start >= left and end <= right:
        return tree[node]

    mid = (start + end) // 2

    return get_result(left, right, node * 2, start, mid) + get_result(left, right, node * 2 + 1, mid + 1, end)


n, q = map(int, input().split())

tree = [0] * (4 * n)

ans = []

for _ in range(q):

    query, p, x = map(int, input().split())

    if query == 1:
        update(p, x, 1, 1, n)
    else:
        ans.append(str(get_result(p, x, 1, 1, n)))

print("\n".join(ans))
