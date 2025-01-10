import sys
input = sys.stdin.readline

n, q = map(int, input().split())

tree = [False] * (n + 1)
ans = []

for _ in range(q):
    x = int(input())
    temp = x
    num = 0

    while temp > 0:
        if tree[temp]:
            num = temp
        temp //= 2

    if num == 0:
        tree[x] = True
        ans.append(str(0))
    else:
        ans.append(str(num))


print("\n".join(ans))
