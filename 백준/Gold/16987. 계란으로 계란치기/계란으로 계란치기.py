n = int(input())
eggs = [list(map(int, input().split())) for _ in range(n)]

ans = 0


def solve(index):
    if index == len(eggs):
        global ans
        count = 0
        for row in eggs:
            if row[0] <= 0:
                count += 1
        ans = max(ans, count)

        return

    if eggs[index][0] <= 0:
        solve(index + 1)
        return

    flag = False

    for i in range(len(eggs)):
        if i == index or eggs[i][0] <= 0:
            continue

        flag = True

        eggs[i][0] -= eggs[index][1]
        eggs[index][0] -= eggs[i][1]

        solve(index + 1)

        eggs[i][0] += eggs[index][1]
        eggs[index][0] += eggs[i][1]

    if not flag:
        solve(index + 1)


solve(0)
print(ans)
