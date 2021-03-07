"""Encode and decode text using Rail-fence Cipher."""

def lists(message2: str, key: int):
    """
    Create a list of lists using message and key.

    :param message2: str
    :param key: int
    :return: list
    """
    dollars = []
    for i in range(key):
        line = []
        for n in range(len(message2)):
            line.append("$")
        dollars.append(line)
    return dollars


def sik_sak(message, key):
    """
    Make a last step of decoding.

    :param message: str
    :param key: int
    :return: str
    """
    down_move = True
    row = 0
    result = ""
    for i in range(len(message[0])):
        result += message[row][i]
        if row == 0:
            row += 1
            down_move = True
        elif row == key - 1:
            row -= 1
            down_move = False
        elif down_move:
            row += 1
        elif not down_move:
            row -= 1
    return result


def encode(message: str, key: int) -> str:
    """
    Encode text using Rail-fence Cipher.

    Replace all spaces with '_'.

    :param message: Text to be encoded.
    :param key: Encryption key.
    :return: Decoded string.
    """
    message2 = message.replace(" ", "_")
    dollars = lists(message2, key)
    index_of_dollar = 0
    a = 1
    nr = -1
    for i in message2:
        nr += 1
        dollars[index_of_dollar].insert(nr, i)
        if key != 1 and nr % (key - 1) == 0 and nr != 0:
            a *= -1
        elif key == 1:
            continue
        if a == 1:
            index_of_dollar += 1
        else:
            index_of_dollar -= 1
    list_without_dollars = sum(dollars, [])
    coded_message = ''.join(list_without_dollars)
    final_string = coded_message.replace('$', '')
    return final_string


def decode(message: str, key: int) -> str:
    """
    Decode text knowing it was encoded using Rail-fence Cipher.

    '_' have to be replaced with spaces.

    :param message: Text to be decoded.
    :param key: Decryption key.
    :return: Decoded string.
    """
    message2 = message.replace("_", " ")
    dollars = lists(message2, key)
    index_of_dollar = 0
    a = 1
    nr = -1
    for i in message2:
        nr += 1
        dollars[index_of_dollar].insert(nr, "*")
        dollars[index_of_dollar].pop(nr + 1)
        if key != 1 and nr % (key - 1) == 0 and nr != 0:
            a *= -1
        elif key == 1:
            continue
        if a == 1:
            index_of_dollar += 1
        else:
            index_of_dollar -= 1
    list_index = 0
    for i in message2:
        if '*' in dollars[list_index]:
            dollars[list_index].insert(dollars[list_index].index('*'), i)
            dollars[list_index].remove('*')
        else:
            list_index += 1
            dollars[list_index].insert(dollars[list_index].index('*'), i)
            dollars[list_index].remove('*')
    list_of_strings = []
    for i in dollars:
        string = ''
        list_of_strings.append(string.join(i))
    if key == 1:
        soome = ''
        return soome.join(list_of_strings)
    return sik_sak(list_of_strings, key)


if __name__ == '__main__':
    print(encode("Mind on vaja krüpteerida", 3))  # => M_v_prido_aaküteiannjred
    print(encode("Mind on", 3))  # => M_idonn
    print(encode("hello", 1))  # => hello
    print(encode("hello", 8))  # => hello
    print(encode("kaks pead", 1))  # => kaks_pead

    print(decode("kaks_pead", 1))  # => kaks pead
    print(decode("M_idonn", 3))  # => Mind on
    print(decode("M_v_prido_aaküteiannjred", 3))  # => Mind on vaja krüpteerida
