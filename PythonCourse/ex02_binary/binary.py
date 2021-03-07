"""Converter."""


def dec_to_binary(dec: int) -> str:
    """
    Convert decimal number into binary.

    :param dec: decimal number to convert
    :return: number in binary
    """
    list_of_binary = ""
    while dec < 1:
        return "0"
    number = dec
    for i in range(number):
        list_of_binary += str(number % 2)
        number = number // 2
        if number == 0:
            break
    list = list_of_binary[::-1]
    return list


def binary_to_dec(binary: str) -> int:
    """
    Convert binary number into decimal.

    :param binary: binary number to convert
    :return: number in decimal
    """
    lenght = len(binary)
    list_of_numbers = []
    for i in range(lenght):
        n = int(binary[i])
        dec_element = n * (2 ** (lenght - (i + 1)))
        list_of_numbers.append(dec_element)
    return sum(list_of_numbers)


if __name__ == "__main__":
    print(dec_to_binary(145))  # -> 10010001
    print(dec_to_binary(245))  # -> 11110101
    print(dec_to_binary(255))  # -> 11111111

    print(binary_to_dec("1111"))  # -> 15
    print(binary_to_dec("10101"))  # -> 21
    print(binary_to_dec("10010"))  # -> 18
