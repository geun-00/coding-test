def get_next_nodes(nodes, node, tree):
    next_nodes = nodes.copy()
    next_nodes.remove(node)

    for child in tree[node]:
        next_nodes.add(child)

    return next_nodes


def dfs(nodes, tree, info, sheep, wolf):
    max_sheep = sheep

    for node in nodes:
        next_sheep = sheep
        next_wolf = wolf

        if info[node] == 0:
            next_sheep += 1
        else:
            next_wolf += 1

        if next_sheep <= next_wolf:
            continue

        next_nodes = get_next_nodes(nodes, node, tree)
        max_sheep = max(max_sheep, dfs(next_nodes, tree, info, next_sheep, next_wolf))

    return max_sheep


def solution(info, edges):
    tree = [[] for _ in range(len(info))]

    for u, v in edges:
        tree[u].append(v)

    nodes = {0}

    return dfs(nodes, tree, info, 0, 0)
