import random
import time
from humanfriendly import format_timespan


class NQPosition:

    def __init__(self, N):
        # N - number of queens
        self.num = N
        # iter - how much times code refresh till gets the right positions
        self.iter = 1
        # list of tuples with positions of queens (x, y)
        self.queens = []
        # make random position
        for i in range(N):
            self.queens.append((i, random.randint(0, N - 1)))

    # choose some internal representation of the NxN board
    # put queens on it

    def valueCalc(self, listOfQueens):
        # 4 dictionaries: vertical, horizontal and 2 diagonals
        lines = [dict(), dict(), dict(), dict()]
        value = 0
        for i in listOfQueens:
            if i[0] in lines[0]:
                lines[0][i[0]] += 1
            else:
                lines[0][i[0]] = 0
            if i[1] in lines[1]:
                lines[1][i[1]] += 1
            else:
                lines[1][i[1]] = 0
            if i[0] - i[1] in lines[2]:
                lines[2][i[0] - i[1]] += 1
            else:
                lines[2][i[0] - i[1]] = 0
            if i[0] + i[1] in lines[3]:
                lines[3][i[0] + i[1]] += 1
            else:
                lines[3][i[0] + i[1]] = 0
        # calculating value with triangular number formula
        for i in lines:
            for j in i:
                value += (i[j] * (i[j] + 1) // 2)
        return value

    def value(self):
        # calculate number of conflicts (queens that can capture each other)
        value = self.valueCalc(self.queens)
        return value

    def make_move(self, move):
        for i in range(len(self.queens)):
            if self.queens[i] == move[0]:
                self.queens[i] = move[1]

    # actually execute a move (change the board)

    def best_move(self):
        move = None
        value = self.value()
        for i in range(len(self.queens)):
            for j in range(len(self.queens)):
                queens_copy = self.queens.copy()
                queens_copy[i] = (queens_copy[i][0], (queens_copy[i][1] + j) % len(self.queens))
                new_value = self.valueCalc(queens_copy)
                if new_value <= value:
                    value = new_value
                    move = (self.queens[i], queens_copy[i])
        # find the best move and the value function after making that move
        return move, value

    def create_board(self):
        board = []
        for i in range(len(self.queens)):
            board.append(list())
            for j in range(len(self.queens)):
                board[i].append("+")
        for i in self.queens:
            board[i[0]][i[1]] = "Q"
        for i in board:
            print(i)


def hill_climbing(pos):
    curr_value = pos.value()
    while True:
        move, new_value = pos.best_move()
        if new_value >= curr_value:
            if curr_value != 0:
                new_pos = NQPosition(pos.num)
                best_pos, best_value = hill_climbing(new_pos)
                pos.iter += new_pos.iter
                return best_pos, best_value
            # no improvement, give up
            return pos, curr_value
        else:
            # position improves, keep searching
            curr_value = new_value
            pos.make_move(move)


if __name__ == '__main__':
    pos = NQPosition(15)  # test with the tiny 4x4 board first
    print("Initial position value", pos.value())
    best_pos, best_value = hill_climbing(pos)
    print("Final value", best_value)
    print("Hill climbing refreshes: " + str(pos.iter) + " times")
    pos.create_board()

    # li = [4, 8, 12, 15, 20, 30]
    # for n in li:
    #     print("for " + str(n) + "x" + str(n) + ": ")
    #     start = time.perf_counter()
    #     iterations = 0
    #     for i in range(100):
    #         test = NQPosition(n)
    #         best_pos, best_value = hill_climbing(test)
    #         iterations += test.iter
    #         if best_value != 0:
    #             print("Something went wrong")
    #
    #     end = time.perf_counter()
    #
    #     if n >= 20:
    #         print("Time took for 100 launches: " + str(format_timespan(round(end - start))))
    #         print("Time took on average for 1 launch: " + str(format_timespan(round((end - start) / 100))))
    #     elif n >= 12:
    #         print("Time took for 100 launches: " + str(format_timespan(round(end - start))))
    #         print("Time took on average for 1 launch: " + str(round((end - start) / 100, 2)) + " seconds")
    #     else:
    #         print("Time took for 100 launches: " + str(round(end - start, 2)) + " seconds")
    #         print("Time took on average for 1 launch: " + str(round((end - start) / 100, 4)) + " seconds")
    #     print("Refreshes on average for 1 launch: " + str(round(iterations / 100, 2)) + " times")
    #     print("____________________________________________________")
