import sys


def solve(n, k, length):
    if k == 0:
        return 'm' if n == 1 else 'o'

    prev_len = (length - (k + 3)) // 2
    ms = prev_len + 1
    me = prev_len + (k + 3)

    if n <= prev_len:
        return solve(n, k - 1, prev_len)
    elif ms <= n <= me:
        return 'm' if n == ms else 'o'
    else:
        return solve(n - me, k - 1, prev_len)


n = int(input())

k, length = 0, 3

while length < n:
    k += 1
    length = length * 2 + (k + 3)

print(solve(n, k, length))
