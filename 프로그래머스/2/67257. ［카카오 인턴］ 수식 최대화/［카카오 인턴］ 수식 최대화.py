import re

arr = [
    ["+", "-", "*"],
    ["+", "*", "-"],
    ["-", "+", "*"],
    ["-", "*", "+"],
    ["*", "+", "-"],
    ["*", "-", "*"],
]

def calculate(left, right, op):
    if op == "+":
        return left + right
    elif op == "-":
        return left - right
    else:
        return left * right

def solve(ops, tokens):

    for op in ops:
        
        i = 0
        while i < len(tokens):

            if tokens[i] == op:
                left = int(tokens[i - 1])
                right = int(tokens[i + 1])
                result = calculate(left, right, op)

                tokens[i - 1:i + 2] = [str(result)]
                i -= 2

            i += 1

    return abs(int(tokens[0]))

def solution(expression):
    
    tokens = re.split(r'([+\-*])', expression)

    ans = 0

    for ops in arr:
        num = solve(ops, tokens[:])
        ans = max(ans, num)

    return ans