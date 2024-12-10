def solution(arr):
    
    n = len(arr) // 2 + 1
    
    dp = [[[0, 0] for _ in range(n)] for _ in range(n)]
    
    for i in range(n):
        dp[i][i][0] = dp[i][i][1] = int(arr[i * 2])
        
    
    for length in range(1, n):
        for i in range(n - length):
            j = i + length
            
            dp[i][j][0] = float('-inf')
            dp[i][j][1] = float('inf')
            
            for k in range(i, j):
                left_max = dp[i][k][0]
                left_min = dp[i][k][1]
                right_max = dp[k + 1][j][0]
                right_min = dp[k + 1][j][1]
                
                if arr[2 * k + 1] == "+":
                    dp[i][j][0] = max(dp[i][j][0], left_max + right_max)
                    dp[i][j][1] = min(dp[i][j][1], left_min + right_max)
                else:
                    dp[i][j][0] = max(dp[i][j][0], left_max - right_min)
                    dp[i][j][1] = min(dp[i][j][1], left_min - right_max)
    
    return dp[0][n - 1][0]