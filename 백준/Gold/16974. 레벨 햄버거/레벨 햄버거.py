n, x = map(int, input().split())

layer = [0] * 51
patties = [0] * 51

layer[0] = 1
patties[0] = 1

for i in range(1, n + 1):
    layer[i] = (layer[i - 1] * 2) + 3
    patties[i] = (patties[i - 1] * 2) + 1


def solve(level, x):
    if level == 0:
        return 1

    prev_layer = layer[level - 1]

    if x == 1:
        return 0
    elif x <= 1 + prev_layer:
        return solve(level - 1, x - 1)
    elif x == 1 + prev_layer + 1:
        return patties[level - 1] + 1
    else:
        return patties[level - 1] + 1 + solve(level - 1, x - 1 - prev_layer - 1)


print(solve(n, x))
