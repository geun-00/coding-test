import sys
sys.setrecursionlimit(10**6)

class Node:
    def __init__(self, num, x, y):
        self.num = num
        self.x = x
        self.y = y
        self.left = None
        self.right = None

        
def build_tree(nodes):
    root = nodes[0]
    
    for node in nodes[1:]:
        insert(root, node)
    
    return root


def insert(parent, node):
    if(node.x < parent.x):
        if not parent.left:
            parent.left = node
        else:
            insert(parent.left, node)
    else:
        if not parent.right:
            parent.right = node
        else:
            insert(parent.right, node)

            
def get_pre_order(node, pre_order):            
    if not node:
        return
    
    pre_order.append(node.num)
    get_pre_order(node.left, pre_order)
    get_pre_order(node.right, pre_order)
    
    
def get_post_order(node, post_order):
    if not node:
        return
    
    get_post_order(node.left, post_order)
    get_post_order(node.right, post_order)
    post_order.append(node.num)
    

def solution(nodeinfo):
    
    n = len(nodeinfo)
    
    nodes = [Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]) for i in range(n)]
    
    nodes.sort(key=lambda node: (-node.y, node.x))
    
    root = build_tree(nodes)
    
    pre_order = []
    post_order = []
    
    get_pre_order(root, pre_order)
    get_post_order(root, post_order)
    
    return [pre_order, post_order]