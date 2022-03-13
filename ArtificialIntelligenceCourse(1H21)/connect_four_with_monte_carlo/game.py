import random
import copy


def print_finish(pos, state):
    """
    Prints a result in the end of the game.

    :param pos: dict with game info
    :param state: game state for the last player
    """
    dump_pos(pos)
    if state == "DRAW":
        print("The game finished with the draw")
    elif pos["to_move"] == "X":
        print("Player won")
    else:
        print("Player lost")


def update_pos(pos):
    """
    Adds blank board to the game. place (0, 0) is on the bottom left.

    :param pos: dict with game info
    :return: pos with game board
    """
    new_pos = pos
    matrix = []
    for i in range(7):
        horizontal = []
        for j in range(6):
            horizontal.append(" ")
        matrix.append(horizontal)
    new_pos["board"] = matrix
    return new_pos


def dump_pos(pos):
    """
    prints the game board

    :param pos: dict with game info
    """
    for i in range(5, -1, -1):
        li = []
        for j in range(7):
            li.append(pos["board"][j][i])
        print(li)
    print(["0", "1", "2", "3", "4", "5", "6"])


def parse_move(pos):
    """
    Asks player for the move

    :param pos: dict with game info
    :return: int for the column to move (0-6)
    """
    while True:
        try:
            move = int(input("Your move? "))
            if 0 <= move <= 6:
                for i in range(6):
                    if pos["board"][move][i] == " ":
                        return move
                print(f"There is no place in {move} column, choose another one...")

            else:
                print("Move must be from 0 to 6")
        except:
            print("Enter a number please!!!")


def make_move(pos, move):
    """
    makes move on board. The place to move is should always free.

    :param pos: dict with game info
    :param move: column index where to make a move
    :return: updated pos and the made move place (x,y)
    """
    for i in range(6):
        if pos["board"][move][i] == " ":
            pos["board"][move][i] = pos["to_move"]
            place = (move, i)
            return pos, place


def over_diag_rl(pos, last_move):
    """
    Looks by right-left diagonal for the win position.
    The win will be detected only around move place.

    :param pos: dict with game info
    :param last_move: last move place (x, y)
    :return: boolean if the win is detected
    """
    player = pos["to_move"]
    board = pos["board"]
    win_row = 0
    for i in range(-3, 4):
        x = last_move[0] - i
        y = last_move[1] + i
        if 0 <= x <= 6 and 0 <= y <= 5:
            if board[x][y] == player:
                win_row += 1
            elif win_row < 4:
                win_row = 0
    return win_row == 4


def over_diag_lr(pos, last_move):
    """
    Looks by left-right diagonal for the win position.
    The win will be detected only around move place.

    :param pos: dict with game info
    :param last_move: last move place (x, y)
    :return: boolean if the win is detected
    """
    player = pos["to_move"]
    board = pos["board"]
    win_row = 0
    for i in range(-3, 4):
        x = last_move[0] + i
        y = last_move[1] + i
        if 0 <= x <= 6 and 0 <= y <= 5:
            if board[x][y] == player:
                win_row += 1
            elif win_row < 4:
                win_row = 0
    return win_row == 4


def over_horizontal(pos, last_move):
    """
    Looks by horizontal for the win position.
    The win will be detected only around move place.

    :param pos: dict with game info
    :param last_move: last move place (x, y)
    :return: boolean if the win is detected
    """
    player = pos["to_move"]
    board = pos["board"]
    win_row = 0
    for i in range(last_move[0] - 3, last_move[0] + 4):
        if 0 <= i <= 6:
            if board[i][last_move[1]] == player:
                win_row += 1
            elif win_row < 4:
                win_row = 0
    return win_row == 4


def over_vertical(pos, last_move):
    """
    Looks by vertical for the win position.
    The win will be detected only around move place.

    :param pos: dict with game info
    :param last_move: last move place (x, y)
    :return: boolean if the win is detected
    """
    player = pos["to_move"]
    board = pos["board"]
    win_row = 0
    for i in range(last_move[1] - 3, last_move[1] + 1):
        if i >= 0 and board[last_move[0]][i] == player:
            win_row += 1
        else:
            win_row = 0
    return win_row == 4


def moves(pos):
    """
    Searches for the columns that are free for move.

    :param pos: dict with game info
    :return: colums that you may make a move
    """
    li = []
    for i in range(7):
        if pos["board"][i][5] == " ":
            li.append(i)
    return li


def over(pos, last_move):
    """
    Using over_vertical(), over_horizontal(), over_diag_lr(),
    over_diag_rl() and moves() checks if the game is over.

    :param pos: dict with game info
    :param last_move: the last move in the game which was made
    :return: win, draw or continue game string
    """
    if len(moves(pos)) > 0:
        answers = [over_vertical(pos, last_move), over_horizontal(pos, last_move), over_diag_lr(pos, last_move),
                   over_diag_rl(pos, last_move)]
        for i in answers:
            if i:
                return "WIN"
        return "CONTINUE"
    return "DRAW"


def simulate(pos):
    """
    Simulates randomly the game till the end.

    :param pos: dict with game info
    :return: state the game finished
    """
    li = moves(pos)
    rand = random.randrange(0, len(li))
    move = li[rand]
    moving = make_move(pos, move)
    state = over(pos, moving[1])
    if state != "CONTINUE":
        return state
    if pos["to_move"] == "X":
        pos["to_move"] = "O"
    else:
        pos["to_move"] = "X"
    return simulate(pos)


def pure_mc(pos, N):
    """
    Monte carlo algorithm.
    function which runs N times the simulation for each column of the board.

    :param pos: dict with game info
    :param N: number of simulations to made for each column
    :return: the best move to make
    """
    my_side = pos["to_move"]
    initial_moves = moves(pos)
    win_counts = dict((move, 0) for move in initial_moves)
    for move in initial_moves:
        for i in range(N):
            if move in moves(pos):
                board = copy.deepcopy(pos["board"])
                make_move(pos, move)
                answer = simulate(pos)
                if answer == "DRAW":
                    win_counts[move] += 15
                elif answer == "WIN" and pos["to_move"] == "O":
                    win_counts[move] += 22
                else:
                    win_counts[move] -= 20
                pos["board"] = board
                pos["to_move"] = my_side
    return list(win_counts.keys())[list(win_counts.values()).index(max(win_counts.values()))]


def play_game(pos, player_side="X", N=1000):
    """
    Game main function. uses monte carlo algorithm in pure_mc(). ends,
    when the winner is known or the game ends with a draw.

    :param pos: dict with game info
    :param player_side: the first player. X - player, O - computer, default X
    :param N: number of simulations to made for each column, default 1000
    """
    pos = update_pos(pos)
    playing = True
    while playing:
        if pos["to_move"] == player_side:
            dump_pos(pos)
            move = parse_move(pos)
        else:
            move = pure_mc(pos, N)

        pos, last_move = make_move(pos, move)
        state = over(pos, last_move)
        if state == "DRAW" or state == "WIN":
            print_finish(pos, state)
            playing = False
        if pos["to_move"] == "X":
            pos["to_move"] = "O"
        else:
            pos["to_move"] = "X"


if __name__ == '__main__':
    starting_pos = {"to_move": "X"}
    play_game(starting_pos)
