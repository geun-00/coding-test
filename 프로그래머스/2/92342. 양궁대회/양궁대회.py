from copy import deepcopy

IMPOSSIBLE = [-1]
ans = IMPOSSIBLE
maxDiff = 0

def get_diff(ryan, apeach):

    diff = 0

    for i in range(len(apeach)):
        if ryan[i] == 0 and apeach[i] == 0:
            continue

        if ryan[i] <= apeach[i]:
            diff -= (10 - i)
        else:
            diff += (10 - i)

    return diff


def check(ryan):

    for i in range(10, -1, -1):
        if ans[i] == ryan[i]:
            continue

        return ryan[i] > ans[i]

    return False


def solve(index, ryan, n, apeach):

    if index == len(ryan):
        if n > 0:
            return
        
        global maxDiff, ans

        diff = get_diff(ryan, apeach)

        if diff <= 0 or diff < maxDiff:
            return

        if diff > maxDiff or check(ryan):            
            maxDiff = diff
            ans = deepcopy(ryan)
        
        return


    for i in range(0, n + 1):
        ryan[index] = i
        solve(index + 1, ryan, n - i, apeach)
        ryan[index] = 0

def solution(n, info):

    solve(0, [0] * 11, n, info)
    return ans
