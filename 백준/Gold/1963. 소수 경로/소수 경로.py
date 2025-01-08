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


def check(num, i):
    count = 0

    while num > 0:
        if num % 10 != i % 10:
            count += 1
        num //= 10
        i //= 10

    return count == 1


def bfs(a, b, primes):
    qu = deque([(a, 0)])
    visit = [False] * len(primes)
    visit[a] = True

    while qu:
        num, change = qu.popleft()

        if num == b:
            return str(change)

        for i in range(1001, len(primes)):
            if not primes[i] or visit[i]:
                continue
            if not check(num, i):
                continue

            visit[i] = True
            qu.append((i, change + 1))

    return "Impossible"


t = int(input())
ans = []
primes = get_primes()

for _ in range(t):
    a, b = map(int, input().split())
    ans.append(bfs(a, b, primes))

print("\n".join(ans))
