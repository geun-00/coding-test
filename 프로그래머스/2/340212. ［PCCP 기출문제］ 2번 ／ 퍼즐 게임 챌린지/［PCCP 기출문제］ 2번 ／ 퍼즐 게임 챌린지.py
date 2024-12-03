def solve(level, diff_arr, time_arr):
    
    ret = 0
    
    for i in range(1, len(diff_arr)):
        
        if diff_arr[i] <= level:
            ret += time_arr[i]
        else:
            diff = diff_arr[i] - level
            
            ret += time_arr[i - 1] * diff
            ret += time_arr[i] * (diff + 1)
    
    return ret


def solution(diffs, times, limit):
    
    n = len(diffs)
    
    diff_arr = [0] * (n + 1)
    time_arr = [0] * (n + 1)
    
    for i in range(n):
        diff_arr[i + 1] = diffs[i]
        time_arr[i + 1] = times[i]
    
    left = 1
    right = 100000
    ans = -1
    
    while left <= right:
        
        mid = (left + right) // 2
        
        result = solve(mid, diff_arr, time_arr)
        
        if result <= limit:
            ans = mid
            right = mid - 1
        else:
            left = mid + 1
    
    return ans