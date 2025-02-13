def solution(storey):
    ans = 0
    
    while storey > 0:
        num = storey % 10
        temp = (storey // 10) % 10
        
        if num > 5 or (num == 5 and temp >= 5):
            ans += (10 - num)
            storey += (10 - num)
        else:
            ans += num
        
        storey //= 10
    
    return ans