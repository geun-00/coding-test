import math

n = int(input())

data = list(map(int, input().split()))

if data[0] == 1:
    k = data[1] - 1
    numbers = list(range(1, n + 1))

    result = []

    for i in range(n, 0, -1):
        fact = math.factorial(i - 1)
        index = k // fact
        result.append(numbers.pop(index))
        k %= fact

    print(" ".join(map(str, result)))
else:
    numbers = list(range(1, n + 1))
    order = 1
    arr = data[1:]

    for i in range(n):
        index = numbers.index(arr[i])
        order += index * math.factorial(n - 1 - i)
        numbers.pop(index)

    print(order)