from collections import deque


def solution(queue1, queue2):

    n = len(queue1)

    current = 0
    total = 0

    qu1 = deque(queue1)
    qu2 = deque(queue2)

    for i in range(n):
        current += queue1[i]
        total += queue1[i] + queue2[i]

    if total % 2 != 0:
        return -1

    target = total // 2
    ans = 0

    while ans <= n * 4:

        if current == target:
            return ans

        if current < target:
            current += qu2[0]
            qu1.append(qu2.popleft())
        else:
            current -= qu1[0]
            qu2.append(qu1.popleft())

        ans += 1

    return -1
