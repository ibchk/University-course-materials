from queue import Queue, PriorityQueue
import time


def bfs(kaart, start):
    iter = 0
    diamond = None
    frontier = Queue()
    frontier.put(start)
    came_from = {}
    came_from[start] = None

    while not frontier.empty():
        current = frontier.get()
        iter += 1
        if kaart[current[0]][current[1]] == "D":
            diamond = current
            break
        if kaart[current[0]][current[1]] == "*":
            continue
        for next in find_neighbors(kaart, current):
            if next not in came_from:
                frontier.put(next)
                came_from[next] = current
    # Uncomment to print iterations
    # print("BFS iterations: " + str(iter))
    return add_road(kaart, came_from, diamond)


def greedy(kaart, start, goal):
    iter = 0
    frontier = PriorityQueue()
    frontier.put((0, start))
    came_from = {start: None}

    while not frontier.empty():
        iter += 1
        _, current = frontier.get()
        if kaart[current[0]][current[1]] == "D":
            break
        if kaart[current[0]][current[1]] == "*":
            continue
        for next in find_neighbors(kaart, current):
            if next not in came_from:
                priority = abs(next[0] - goal[0] + next[1] - goal[1])
                frontier.put((priority, next))
                came_from[next] = current
    # Uncomment to print iterations
    # print("Greedy iterations: " + str(iter))
    return add_road(kaart, came_from, goal)


def astar(kaart, start, goal):
    iter = 0
    cost_so_far = {}
    cost_so_far[start] = 0
    frontier = PriorityQueue()
    frontier.put((0, start))
    came_from = {start: None}
    while not frontier.empty():
        iter += 1
        _, current = frontier.get()
        if kaart[current[0]][current[1]] == "D":
            break
        if kaart[current[0]][current[1]] == "*":
            continue
        for next in find_neighbors(kaart, current):
            new_cost = cost_so_far[current] + 1
            if next not in cost_so_far or new_cost < cost_so_far[next]:
                cost_so_far[next] = new_cost
                priority = new_cost + abs(next[0] - goal[0] + next[1] - goal[1])  # g(n) + h(n)
                frontier.put((priority, next))
                came_from[next] = current
    # Uncomment to print iterations
    # print("Astar iterations: " + str(iter))
    return add_road(kaart, came_from, goal)


def find_neighbors(kaart, point):
    neighbors = []
    height = len(kaart)
    length = len(kaart[0])
    if point[0] + 1 < height:
        neighbors.append((point[0] + 1, point[1]))
    if 0 <= (point[0] - 1) < height:
        neighbors.append((point[0] - 1, point[1]))
    if point[1] + 1 < length:
        neighbors.append((point[0], point[1] + 1))
    if 0 <= (point[1] - 1) < length:
        neighbors.append((point[0], point[1] - 1))
    return neighbors


def add_road(kaart, roads, start):
    new_kaart = kaart
    point = start
    road = []
    road.insert(0, point)
    while roads[point] is not None:
        new_point = roads[point]
        road.insert(0, new_point)

        # Uncomment this for making road on map
        # if new_kaart[new_point[0]][new_point[1]] != "s":
        #     new_kaart[new_point[0]] = new_kaart[new_point[0]][:new_point[1]] + "." + new_kaart[new_point[0]][
        #                                                                              new_point[1] + 1:]
        point = new_point
    # Uncomment this to visualize map
    # for line in new_kaart:
    #     print(line)
    return road
