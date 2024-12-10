import sys

input = sys.stdin.readline

n, m, k = map(int, input().split())
level = 0
while (1 << level) < n:
    level += 1

tree_size = 1 << (level + 1)
tree = [0] * tree_size
lazy = [0] * tree_size

arr = [int(input()) for _ in range(n)]


def propagation(node, start, end):
    if lazy[node] != 0:
        tree[node] += lazy[node] * (end - start + 1)

        if start != end:
            lazy[node * 2] += lazy[node]
            lazy[node * 2 + 1] += lazy[node]

        lazy[node] = 0


def init(node, start, end, arr):
    if start == end:
        tree[node] = arr[start]
        return

    mid = (start + end) // 2
    init(node * 2, start, mid, arr)
    init(node * 2 + 1, mid + 1, end, arr)

    tree[node] = tree[node * 2] + tree[node * 2 + 1]


def update(left, right, node, start, end, num):
    propagation(node, start, end)

    if start > right or left > end:
        return

    if left <= start and end <= right:
        tree[node] += num * (end - start + 1)
        if start != end:
            lazy[node * 2] += num
            lazy[node * 2 + 1] += num
        return

    mid = (start + end) // 2

    update(left, right, node * 2, start, mid, num)
    update(left, right, node * 2 + 1, mid + 1, end, num)

    tree[node] = tree[node * 2] + tree[node * 2 + 1]


def query(left, right, node, start, end):
    propagation(node, start, end)

    if left > end or right < start:
        return 0

    if left <= start and end <= right:
        return tree[node]

    mid = (start + end) // 2
    return query(left, right, node * 2, start, mid) + query(left, right, node * 2 + 1, mid + 1, end)


init(1, 0, n - 1, arr)

ans = []

for _ in range(m + k):
    data = list(map(int, input().split()))

    left = data[1] - 1
    right = data[2] - 1

    if data[0] == 1:
        num = data[3]
        update(left, right, 1, 0, n - 1, num)
    else:
        ans.append(str(query(left, right, 1, 0, n - 1)))

print("\n".join(ans))
