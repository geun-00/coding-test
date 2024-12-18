n, m = map(int, input().split())
max_possible = 0
min_used = float('inf')

arr = [0] * n

for i in range(n):
    name, song = input().split()

    for j in range(m):
        if song[j] == 'Y':
            arr[i] |= (1 << (m - j - 1))


def solve(depth, possible, used):
    global max_possible, min_used

    possible_count = bin(possible).count('1')

    if possible_count == max_possible:
        min_used = min(min_used, used)
    elif possible_count > max_possible:
        max_possible = possible_count
        min_used = used

    if depth == n:
        return

    solve(depth + 1, possible, used)
    solve(depth + 1, possible | arr[depth], used + 1)


solve(0, 0, 0)

print(min_used if not max_possible == 0 else -1)
