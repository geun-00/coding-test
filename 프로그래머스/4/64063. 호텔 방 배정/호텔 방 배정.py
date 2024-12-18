import sys
sys.setrecursionlimit(10**6)

parent = {}

def find(num):
    
    if num not in parent:
        return num
    
    root = find(parent[num])
    parent[num] = root
    return root

def union(a, b):
    a = find(a)
    b = find(b)
    
    if a != b:
        parent[a] = b

def solution(k, room_number):
    answer = []
    
    for num in room_number:
        found = find(num)
        answer.append(found)
        
        union(num, found + 1)
    
    return answer