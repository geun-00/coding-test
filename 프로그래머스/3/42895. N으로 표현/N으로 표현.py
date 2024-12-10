def solution(N, number):
    
    dp = [set() for _ in range(8)]
    
    for i in range(len(dp)):
        dp[i].add(int(str(N) * (i + 1)))
    
    for i in range(len(dp)):
        for j in range(i):
            for a in dp[j]:
                for b in dp[i - j - 1]:
                    dp[i].add(a + b)
                    dp[i].add(a - b)
                    dp[i].add(b - a)
                    dp[i].add(a * b)
                    
                    if a != 0:
                        dp[i].add(b // a)
                    if b != 0:
                        dp[i].add(a // b)
                        
            if j == i - j - 1:
                break
        
        if number in dp[i]:
            return i + 1

    
    return -1