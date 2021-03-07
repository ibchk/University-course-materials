"""Minesweeper has to swipe the mines."""
import copy


def find_your_place(field):
    """Find a place of the #."""
    line = 0
    for i in field:
        place = 0
        for j in i:
            if j == '#':
                return [line, place]
            place += 1
        line += 1


def add_x(field1: list, field2: list) -> list:
    """Add x and X from field1 to field 2."""
    list_index = 0
    for i in field1:
        nr = 0
        for j in i:
            if j == 'x' or j == 'X':
                field2[list_index][nr] = j
            nr += 1
        list_index += 1
    return field2


def create_minefield(height: int, width: int) -> list:
    """
    Create and return minefield.

    Minefield must be height high and width wide. Each position must contain single dot (`.`).
    :param height: int
    :param width: int
    :return: list
    """
    field = [['.' for j in range(width)] for i in range(height)]
    return field


def add_mines(minefield: list, mines: list) -> list:
    """
    Add mines to a minefield and return minefield.

    Minefield must be length long and width wide. Each non-mine position must contain single dot.
    If a position is empty ("."), then a small mine is added ("x").
    If a position contains small mine ("x"), a large mine is added ("X").
    Mines are in a list.
    Mine is a list. Each mine has 4 integer parameters in the format [N, S, E, W].
        - N is the distance between area of mines and top of the minefield.
        - S ... area of mines and bottom of the minefield.
        - E ... area of mines and right of the minefield.
        - W ... area of mines and left of the minefield.
    :param minefield: list
    :param mines: list
    :return: list
    """
    new_field = copy.deepcopy(minefield)
    for i in range(len(mines)):
        up = int(mines[i][0])
        down = int(-1 * mines[i][1])
        lines = new_field[up:] if down == 0 else new_field[up: down]
        right = int(mines[i][2])
        left = int(mines[i][3])
        for j in lines:
            for k in range(len(j)):
                if ((len(j) - right) > k >= left) and new_field[up][k] == 'x':
                    new_field[up][k] = 'X'
                elif ((len(j) - right) > k >= left) and new_field[up][k] == 'X':
                    continue
                elif (len(j) - right) > k >= left:
                    new_field[up][k] = 'x'
            up += 1
    return new_field


def get_minefield_string(minefield: list) -> str:
    """
    Return minefield's string representation.

    .....
    .....
    x....
    Xx...

    :param minefield:
    :return:
    """
    return ''.join([''.join(i) + '\n' for i in minefield])


def calculate_mine_count(minefield: list) -> list:
    """
    For each cell in minefield, calculate how many mines are nearby.

    This function cannot modify the original list.
    So, the result should be a new list (or copy of original).

    ....
    ..x.
    X.X.
    x..X

    =>

    0111
    13x2
    X3X3
    x32X

    :param minefield:
    :return:
    """
    new_field = add_x(minefield, [['0' for j in range(len(minefield[0]))] for i in range(len(minefield))])
    string_fr_list, len1 = ''.join([''.join(i) for i in minefield]), len(minefield[0])
    list_places, place_nr = [1, len1, len1 + 1, -1, len1 - 1, len1 * -1, (len1 * -1) + 1, (len1 * -1) - 1], -1
    for place in list_places:
        place_nr += 1
        if len1 == 1 and place_nr != 5 and place_nr != 1:
            continue
        str_index, list_index = -1, 0
        for i in new_field:
            nr = -1
            for j in i:
                nr += 1
                str_index += 1
                try:
                    if j != 'X' and j != 'x' and string_fr_list[place + str_index] != '.' and place + str_index >= 0:
                        if (nr + 1 != len1 and nr != 0) or (nr == 0 and place != list_places[3] and
                                                            + place != list_places[4] and place != list_places[7]):
                            new_field[list_index][nr] = str(int(new_field[list_index][nr]) + 1)
                        elif ((nr + 1 == len1) and (place != list_places[0]) and (place != list_places[2]) and (
                                place != list_places[6])) or \
                                (len1 == 2 and ((nr == 1 and (place_nr == 3 or place_nr == 4)) or
                                                + (nr == 0 and (place_nr == 0 or place_nr == 6)))):
                            new_field[list_index][nr] = str(int(new_field[list_index][nr]) + 1)
                except IndexError:
                    continue
            list_index += 1
    return new_field


def west(new_field, point0, point1, lives):
    """Make a walk in the west."""
    if new_field[point0][point1 - 1] == '.' and point1 - 1 >= 0:
        new_field[point0][point1] = '.'
        point1 -= 1
        new_field[point0][point1] = '#'
    elif new_field[point0][point1 - 1] == 'X' and point1 - 1 >= 0:
        lives -= 1
        if lives < 0:
            return new_field, point0, point1, lives
        new_field[point0][point1] = '.'
        point1 -= 1
        new_field[point0][point1] = '#'
    elif new_field[point0][point1 - 1] == 'x' and point1 - 1 >= 0:
        nr_field = calculate_mine_count(new_field)
        if int(nr_field[point0][point1]) > 4:
            lives -= 1
            if lives < 0:
                return new_field, point0, point1, lives
        new_field[point0][point1 - 1] = '.'
    return new_field, point0, point1, lives


def east(new_field, point0, point1, lives):
    """Make a walk in the east."""
    if point1 + 1 < len(new_field[0]) and new_field[point0][point1 + 1] == '.':
        new_field[point0][point1] = '.'
        point1 += 1
        new_field[point0][point1] = '#'
    elif point1 + 1 < len(new_field[0]) and new_field[point0][point1 + 1] == 'X':
        lives -= 1
        if lives < 0:
            return new_field, point0, point1, lives
        new_field[point0][point1] = '.'
        point1 += 1
        new_field[point0][point1] = '#'
    elif point1 + 1 < len(new_field[0]) and new_field[point0][point1 + 1] == 'x':
        nr_field = calculate_mine_count(new_field)
        if int(nr_field[point0][point1]) > 4:
            lives -= 1
            if lives < 0:
                return new_field, point0, point1, lives
        new_field[point0][point1 + 1] = '.'
    return new_field, point0, point1, lives


def north(new_field, point0, point1, lives):
    """Make a walk in the north."""
    if new_field[point0 - 1][point1] == '.' and point0 - 1 >= 0:
        new_field[point0][point1] = '.'
        point0 -= 1
        new_field[point0][point1] = '#'
    elif new_field[point0 - 1][point1] == 'X' and point0 - 1 >= 0:
        lives -= 1
        if lives < 0:
            return new_field, point0, point1, lives
        new_field[point0][point1] = '.'
        point0 -= 1
        new_field[point0][point1] = '#'
    elif new_field[point0 - 1][point1] == 'x' and point0 - 1 >= 0:
        nr_field = calculate_mine_count(new_field)
        if int(nr_field[point0][point1]) > 4:
            lives -= 1
            if lives < 0:
                return new_field, point0, point1, lives
        new_field[point0 - 1][point1] = '.'
    return new_field, point0, point1, lives


def south(new_field, point0, point1, lives):
    """Make a walk in the south."""
    if point0 + 1 < len(new_field) and new_field[point0 + 1][point1] == '.':
        new_field[point0][point1] = '.'
        point0 += 1
        new_field[point0][point1] = '#'
    elif point0 + 1 < len(new_field) and new_field[point0 + 1][point1] == 'X':
        lives -= 1
        if lives < 0:
            return new_field, point0, point1, lives
        new_field[point0][point1] = '.'
        point0 += 1
        new_field[point0][point1] = '#'
    elif point0 + 1 < len(new_field) and new_field[point0 + 1][point1] == 'x':
        nr_field = calculate_mine_count(new_field)
        if int(nr_field[point0][point1]) > 4:
            lives -= 1
            if lives < 0:
                return new_field, point0, point1, lives
        new_field[point0 + 1][point1] = '.'
    return new_field, point0, point1, lives


def walk(minefield, moves, lives) -> list:
    """
    Make moves on the minefield.

    3) stop, because you would die
    final result:
    ..#XX
    .....

    :param minefield:
    :param moves:
    :param lives:
    :return:
    """
    global data
    new_field = copy.deepcopy(minefield)
    point0 = find_your_place(new_field)[0]
    point1 = find_your_place(new_field)[1]
    for i in moves:
        if i == 'W' and lives > -1:
            data = west(new_field, point0, point1, lives)
        elif i == 'E' and lives > -1:
            data = east(new_field, point0, point1, lives)
        elif i == 'N' and lives > -1:
            data = north(new_field, point0, point1, lives)
        elif i == 'S' and lives > -1:
            data = south(new_field, point0, point1, lives)
        new_field = data[0]
        point0 = data[1]
        point1 = data[2]
        lives = data[3]
    return new_field


if __name__ == '__main__':
    minefield_c = create_minefield(1, 3)
    minefield_c = add_mines(minefield_c, [[0, 0, 2, 0], [0, 0, 0, 2]])
    print(minefield_c)
    print(calculate_mine_count([['x'], ['.']]))
