import sys
input = sys.stdin.readline

n = int(input())
tree = [0] * (4 * n)

arr = list(map(int, input().split()))
m = int(input())

def init(node, start, end):
    if start == end:
        tree[node] = start
        return

    mid = (start + end) // 2

    init(node * 2, start, mid)
    init(node * 2 + 1, mid + 1, end)

    tree[node] = tree[node * 2] if arr[tree[node * 2 + 1]] >= arr[tree[node * 2]] else tree[node * 2 + 1]


def update(node, index, start, end):
    if index < start or index > end:
        return

    if start == end:
        return

    mid = (start + end) // 2

    update(node * 2, index, start, mid)
    update(node * 2 + 1, index, mid + 1, end)

    tree[node] = tree[node * 2] if arr[tree[node * 2 + 1]] >= arr[tree[node * 2]] else tree[node * 2 + 1]

init(1, 0, n - 1)

ans = []

for _ in range(m):
    data = list(map(int, input().split()))

    if data[0] == 1:
        index, value = data[1], data[2]
        index -= 1
        arr[index] = value
        update(1, index, 0, n - 1)
    else:
        ans.append(str(tree[1] + 1))

print("\n".join(ans))
