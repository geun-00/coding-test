import heapq

def solution(n, k, enemy):
    qu = []
    
    for i, e in enumerate(enemy):
        heapq.heappush(qu, -e)
        n -= e
        
        if n < 0:
            if k > 0:
                n += -heapq.heappop(qu)
                k -= 1
            else:
                return i
    
    return len(enemy)