def solve(n, fromm, to, ans):
    
    if n == 1:
        ans.append([fromm, to])
        return
    
    empty = 6 - fromm - to
    
    solve(n - 1, fromm, empty, ans)
    solve(1, fromm, to, ans)
    solve(n - 1, empty, to, ans)

def solution(n):
    
    ans = []
    
    solve(n, 1, 3, ans)
    
    return ans