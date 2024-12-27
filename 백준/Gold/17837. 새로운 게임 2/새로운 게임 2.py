from collections import deque

RED = 1
BLUE = 2

dx = (0, 0, -1, 1)
dy = (1, -1, 0, 0)


class Unit:
    def __init__(self, x, y, dir):
        self.x = x
        self.y = y
        self.dir = dir

    def move(self, x, y):
        self.x = x
        self.y = y

    def set_dir(self, dir):
        self.dir = dir


def is_blocked(x, y):
    return x < 0 or y < 0 or x >= n or y >= n or board[x][y] == BLUE


def horses_move(target, nx, ny, is_red):

    while target:
        unit_index = target.popleft() if is_red else target.pop()
        unit_arr[unit_index].move(nx, ny)
        unit_list[nx][ny].appendleft(unit_index)

    return len(unit_list[nx][ny]) >= 4


def get_target(unit_index, x, y):

    units = unit_list[x][y]
    target = deque()

    while units and units[0] != unit_index:
        target.append(units.popleft())

    target.append(units.popleft())
    return target


def solve(unit, unit_index):
    x, y, dir = unit.x, unit.y, unit.dir
    nx = x + dx[dir]
    ny = y + dy[dir]

    target = get_target(unit_index, x, y)

    if is_blocked(nx, ny):
        dir = dir + 1 if dir % 2 == 0 else dir - 1
        nx = x + dx[dir]
        ny = y + dy[dir]
        unit_arr[unit_index].set_dir(dir)

        if is_blocked(nx, ny):
            return horses_move(target, x, y, False)

    return horses_move(target, nx, ny, board[nx][ny] == RED)


n, k = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
unit_list = [[deque() for _ in range(n)] for _ in range(n)]
unit_arr = []

for i in range(k):
    x, y, d = map(int, input().split())
    x -= 1
    y -= 1
    d -= 1

    unit_arr.append(Unit(x, y, d))
    unit_list[x][y].append(i)

turns = 0

while turns <= 1000:
    turns += 1
    for i in range(k):
        if solve(unit_arr[i], i):
            print(turns)
            exit()

print(-1)
