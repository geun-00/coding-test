import math
from collections import deque


def get_primes():

    limit = 10000
    primes = [True] * limit
    primes[0] = primes[1] = False

    for i in range(2, int(math.sqrt(limit)) + 1):
        if not primes[i]:
            continue

        for j in range(i * 2, limit, i):
            primes[j] = False

    return primes


def bfs(a, b, primes):
    qu = deque([(a, 0)])
    visit = [False] * len(primes)
    visit[a] = True

    while qu:
        num, change = qu.popleft()

        if num == b:
            return str(change)

        for i in range(4):
            base = int(math.pow(10, i))
            origin = num // base % 10

            for j in range(10):
                if i == 3 and j == 0:
                    continue
                if origin == j:
                    continue

                next_num = num - (origin * base) + (j * base)

                if not primes[next_num] or visit[next_num]:
                    continue

                visit[next_num] = True
                qu.append((next_num, change + 1))

    return "Impossible"


t = int(input())
ans = []
primes = get_primes()

for _ in range(t):
    a, b = map(int, input().split())
    ans.append(bfs(a, b, primes))

print("\n".join(ans))
