"""
Route Between Nodes:
Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
"""

from python.chapter4.datastructures import *


def findAnyRoute_DFS(start: Vertex, end: Vertex):
    if start is None or end is None:
        return None
    path = [start]
    visited = set()

    while len(path) > 0:
        current = path[-1]
        visited.add(current)
        if current == end:
            return path
        foundNew = False
        for vertex in current.adjacent_vertices:
            if vertex not in visited:
                path.append(vertex)
                foundNew = True
                break
        if not foundNew and len(path) > 0:
            path.pop()


def findAnyRoute_BFS(start: Vertex, end: Vertex):
    if start is None or end is None:
        return None
    queue = [start]
    path = [start]
    visited = set()

    while len(queue) > 0:
        current = queue.pop(0)
        path.append(current)
        visited.add(current)
        if current == end:
            return path
        for vertex in current.adjacent_vertices:
            if vertex not in visited:
                queue.append(vertex)
        path.pop()


if __name__ == '__main__':
    v1 = Vertex(1)
    v2 = Vertex(2)
    v3 = Vertex(3)
    v4 = Vertex(4)
    v5 = Vertex(5)
    v6 = Vertex(6)
    v7 = Vertex(7)

    v1.adjacent_vertices.append(v2)
    v2.adjacent_vertices.append(v3)
    v1.adjacent_vertices.append(v3)
    v2.adjacent_vertices.append(v1)
    v3.adjacent_vertices.append(v1)

    path = findAnyRoute_DFS(v1, v3)
    if path is not None:
        print('DFS Route:', ' => '.join([str(v) for v in path]))
    else:
        print('DFS: No path found')

    path = findAnyRoute_BFS(v1, v3)
    if path is not None:
        print('BFS Route:', ' => '.join([str(v) for v in path]))
    else:
        print('BFS: No path found')
