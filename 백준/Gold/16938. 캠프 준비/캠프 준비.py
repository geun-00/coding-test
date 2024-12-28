from itertools import combinations


def check(arr, l, r, x):
    total = sum(arr)
    min_val = min(arr)
    max_val = max(arr)

    diff = max_val - min_val

    return l <= total <= r and diff >= x


n, l, r, x = map(int, input().split())
arr = list(map(int, input().split()))

ans = 0

for size in range(2, n + 1):
    for pick in combinations(arr, size):
        if check(pick, l, r, x):
            ans += 1

print(ans)
