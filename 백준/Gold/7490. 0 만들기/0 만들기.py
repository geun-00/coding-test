import re

num_pattern = re.compile(r'\d+')
oper_pattern = re.compile(r'[+-]')
ans = []

def get_numbers(expr):
    numbers = []
    for match in num_pattern.finditer(expr):
        numbers.append(int(match.group()))
    return numbers


def get_operators(expr):
    operators = []
    for match in oper_pattern.finditer(expr):
        operators.append(match.group())
    return operators


def get_result(numbers, operators):
    result = numbers[0]

    for i in range(len(operators)):
        operation = operators[i]
        num = numbers[i + 1]

        if operation == "+":
            result += num
        else:
            result -= num

    return result


def test(expr):
    expr = expr.replace(" ", "")

    numbers = get_numbers(expr)
    operators = get_operators(expr)

    return get_result(numbers, operators) == 0


def solve(depth, expr, n):
    if depth == n:
        if test(expr):
            ans.append(expr + "\n")
        return

    solve(depth + 1, expr + " " + str(depth + 1), n)
    solve(depth + 1, expr + "+" + str(depth + 1), n)
    solve(depth + 1, expr + "-" + str(depth + 1), n)


t = int(input())

for _ in range(t):
    n = int(input())

    solve(1, "1", n)
    ans.append("\n")

print("".join(ans))
