import sys
input = sys.stdin.readline

def init(arr, start, end, node):
    if start == end:
        tree[node] = 1 if arr[start] % 2 == 0 else 0
        return

    mid = (start + end) // 2
    init(arr, start, mid, node * 2)
    init(arr, mid + 1, end, node * 2 + 1)

    tree[node] = tree[node * 2] + tree[node * 2 + 1]

def update(left, right, node, index, value):
    if left > index or index > right:
        return

    if left == right:
        tree[node] = 1 if value % 2 == 0 else 0
        return

    mid = (left + right) // 2
    update(left, mid, node * 2, index, value)
    update(mid + 1, right, node * 2 + 1, index, value)

    tree[node] = tree[node * 2] + tree[node * 2 + 1]

def query(left, right, start, end, node):
    if left > end or right < start:
        return 0

    if start >= left and right >= end:
        return tree[node]

    mid = (start + end) // 2
    return query(left, right, start, mid, node * 2) + query(left, right, mid + 1, end, node * 2 + 1)

n = int(input())
tree = [0] * (4 * n)
arr = list(map(int, input().split()))

init(arr, 0, n - 1, 1)

ans = []
for _ in range(int(input())):
    q, l, r = map(int, input().split())

    if q == 1:
        l -= 1
        update(0, n - 1, 1, l, r)
    elif q == 2:
        l -= 1
        r -= 1
        ans.append(str(query(l, r, 0, n - 1, 1)))
    elif q == 3:
        l -= 1
        r -= 1
        ans.append(str((r - l + 1) - query(l, r, 0, n - 1, 1)))

print("\n".join(ans))