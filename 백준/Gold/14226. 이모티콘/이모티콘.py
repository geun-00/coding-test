from collections import deque

s = int(input())

visit = [[False] * (s + 1) for _ in range(s + 1)]
visit[1][0] = True

qu = deque([(1, 0, 0)])

while qu:

    emoticon, clipboard, time = qu.popleft()

    if emoticon == s:
        print(time)
        break

    if not visit[emoticon][emoticon]:
        visit[emoticon][emoticon] = True
        qu.append((emoticon, emoticon, time + 1))

    next_emoticon = emoticon + clipboard

    if clipboard > 0 and next_emoticon <= s and not visit[next_emoticon][clipboard]:
        visit[next_emoticon][clipboard] = True
        qu.append((next_emoticon, clipboard, time + 1))

    next_emoticon = emoticon - 1

    if next_emoticon > 0 and not visit[next_emoticon][clipboard]:
        visit[next_emoticon][clipboard] = True
        qu.append((next_emoticon, clipboard, time + 1))
