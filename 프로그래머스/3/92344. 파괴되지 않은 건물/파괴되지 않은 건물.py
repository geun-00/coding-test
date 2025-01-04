def solution(board, skill):

    n = len(board)
    m = len(board[0])

    arr = [[0] * (m + 2) for _ in range(n + 2)]

    for sk in skill:
        my_type, r1, c1, r2, c2, degree = sk
        r1 += 1
        c1 += 1
        r2 += 1
        c2 += 1

        if my_type == 1:
            degree = -degree

        arr[r1][c1] += degree
        arr[r1][c2 + 1] -= degree
        arr[r2 + 1][c1] -= degree
        arr[r2 + 1][c2 + 1] += degree

    for i in range(1, n + 1):
        for j in range(1, m + 1):
            arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + arr[i][j]

    ans = 0
    for i in range(n):
        for j in range(m):
            if board[i][j] + arr[i + 1][j + 1] > 0:
                ans += 1

    return ans
