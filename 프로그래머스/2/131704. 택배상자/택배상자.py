from collections import deque

def solution(order):
    qu = deque(order)
    stack = []
    num = 1
    ans = 0

    while qu:
        now = qu.popleft()

        for i in range(num, now):
            stack.append(i)
            num += 1

        if num == now:
            num += 1
            ans += 1
        elif stack and stack[-1] == now:
            stack.pop()
            ans += 1
        else:
            break

    return ans
