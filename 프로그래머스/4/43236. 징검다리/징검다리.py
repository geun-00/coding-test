def solution(distance, rocks, n):
    
    rocks.append(distance)
    rocks.sort()
    
    low = 1
    high = distance
    ans = 0
    
    while low <= high:
        mid = (low + high) // 2
        
        removed = 0
        last = 0
        
        for rock in rocks:
            if rock - last < mid:
                removed += 1
            else:
                last = rock
        
        if removed <= n:
            ans = mid
            low = mid + 1
        else:
            high = mid - 1
    
    return ans