from collections import defaultdict

def solution(orders, course):
    
    result = []
    
    for size in course:
        
        combs_count = defaultdict(int)
        
        for order in orders:
            items = list(order)
            
            if len(items) < size:
                continue
            
            items.sort()
            solve(items, size, combs_count)
        
        max_count = 0
        for count in combs_count.values():
            if count >= 2 and count > max_count:
                max_count = count
        
        for comb, count in combs_count.items():
            if count == max_count:
                result.append(comb)
    
    result.sort()
    return result

def solve(items, size, combs_count):
    combs = []
    get_combs(items, size, 0, "", combs)

    for comb in combs:
        combs_count[comb] += 1

def get_combs(items, size, start, now, combs):
    if size == 0:
        combs.append(now)
        return

    for i in range(start, len(items)):
        get_combs(items, size - 1, i + 1, now + items[i], combs)