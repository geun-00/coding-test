def solution(arrows):
    
    dx = (-1, -1, 0, 1, 1, 1, 0, -1)
    dy = (0, 1, 1, 1, 0, -1, -1, -1)
    
    class Node:
        def __init__(self, x, y):
            self.x = x
            self.y = y
            self.id = f"{x},{y}"
            self.connected_node = set()
    
    count = 0
    map = {}
    v = Node(0, 0)
    map[v.id] = v
    
    for d in arrows:
        for _ in range(2):
            
            x = v.x + dx[d]
            y = v.y + dy[d]
            id = f"{x},{y}"
            
            if id not in map:
                map[id] = Node(x, y)
            elif id not in v.connected_node:
                count += 1
            
            u = map[id]
            
            v.connected_node.add(u.id)
            u.connected_node.add(v.id)
            
            v = u
    
    return count