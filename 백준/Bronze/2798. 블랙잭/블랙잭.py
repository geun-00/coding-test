from itertools import combinations

N, M = map(int, input().split())

arr = list(map(int, input().split()))

my_max = 0

for selected in combinations(arr, 3):
    my_sum = sum(selected)

    if my_max < my_sum <= M:
        my_max = my_sum

print(my_max)