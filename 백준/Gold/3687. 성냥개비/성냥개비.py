dp = [""] * 101
for i in range(101):
    dp[i] = str(10**18)

dp[2] = "1"
dp[3] = "7"
dp[4] = "4"
dp[5] = "2"
dp[6] = "6"
dp[7] = "8"
dp[8] = "10"

arr = ["", "", "1", "7", "4", "2", "0", "8"]

for i in range(2, 101):
    for j in range(2, 8):

        original = dp[i]
        new_num = dp[i - j] + arr[j]

        if len(original) < len(new_num):
            continue

        if len(original) > len(new_num) or original > new_num:
            dp[i] = new_num

t = int(input())

ans = []

for _ in range(t):
    n = int(input())

    min_val = dp[n]
    max_val = "1" * (n // 2) if n % 2 == 0 else "7" + "1" * ((n - 3) // 2)

    ans.append(f"{min_val} {max_val}")

print("\n".join(ans))