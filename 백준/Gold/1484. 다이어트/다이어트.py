g = int(input())

s, e = 1, 1
ans = []

while e < g:

    diff = e * e - s * s

    if diff == g:
        ans.append(e)
        e += 1
    elif diff < g:
        e += 1
    else:
        s += 1

if not ans:
    print(-1)
else:
    for a in ans:
        print(a)