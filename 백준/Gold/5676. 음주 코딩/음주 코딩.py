import sys

input = sys.stdin.readline

POSITIVE = '+'
NEGATIVE = '-'
ZERO = '0'

tree = []


def get_sign(value):
    if value > 0:
        return 1
    elif value < 0:
        return -1
    return 0


def get_ans(result):
    if result > 0:
        return POSITIVE
    elif result < 0:
        return NEGATIVE
    return ZERO


def query(node, start, end, left, right):
    if left > end or right < start:
        return 1

    if left <= start and right >= end:
        return tree[node]

    mid = (start + end) // 2

    return query(node * 2, start, mid, left, right) * query(node * 2 + 1, mid + 1, end, left, right)


def update(node, left, right, index, value):
    global tree

    if index < left or index > right:
        return

    if left == right:
        tree[node] = get_sign(value)
        return

    mid = (left + right) // 2

    update(node * 2, left, mid, index, value)
    update(node * 2 + 1, mid + 1, right, index, value)

    tree[node] = tree[node * 2] * tree[node * 2 + 1]


def init(node, start, end, arr):
    global tree
    if start == end:
        tree[node] = get_sign(arr[start])
        return

    mid = (start + end) // 2

    init(node * 2, start, mid, arr)
    init(node * 2 + 1, mid + 1, end, arr)

    tree[node] = tree[node * 2] * tree[node * 2 + 1]


ans = []

while True:
    try:
        n, k = map(int, input().split())
        arr = list(map(int, input().split()))

        tree = [0] * (4 * n)

        init(1, 0, n - 1, arr)

        for _ in range(k):

            data = input().split()

            if data[0] == 'C':
                index = int(data[1]) - 1
                value = int(data[2])

                update(1, 0, n - 1, index, value)
            else:
                start = int(data[1]) - 1
                end = int(data[2]) - 1

                result = query(1, 0, n - 1, start, end)
                ans.append(get_ans(result))

        ans.append("\n")

    except Exception:
        break

print("".join(ans))
