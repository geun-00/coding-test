def find_pos(x, y, size, num, index):
    if index == len(num):
        return x, y

    c = num[index]
    half = size // 2

    if c == '1':
        return find_pos(x, y + size, half, num, index + 1)
    elif c == '2':
        return find_pos(x, y, half, num, index + 1)
    elif c == '3':
        return find_pos(x + size, y, half, num, index + 1)
    else:
        return find_pos(x + size, y + size, half, num, index + 1)


def find_ans(x, y, size, d):
    if d == 0:
        return ""

    half = size // 2

    if x < size <= y:
        return "1" + find_ans(x, y - size, half, d - 1)
    elif x < size and y < size:
        return "2" + find_ans(x, y, half, d - 1)
    elif x >= size > y:
        return "3" + find_ans(x - size, y, half, d - 1)
    else:
        return "4" + find_ans(x - size, y - size, half, d - 1)


data = input().split()
d = int(data[0])
num = data[1]

x, y = map(int, input().split())

size = 1 << (d - 1)
pos = find_pos(0, 0, size, num, 0)

nx = pos[0] - y
ny = pos[1] + x

if nx < 0 or ny < 0 or nx >= size * 2 or ny >= size * 2:
    print(-1)
else:
    print(find_ans(nx, ny, size, d))
