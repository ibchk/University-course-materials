"""Simple version of rock paper and scissors."""
from random import choice


def normalize_user_name(name: str) -> str:
    """
    Simple function gets player name as input and capitalizes it.

    :param name: name of the player
    :return: A name that is capitalized.
    """
    new_name = name.capitalize()
    return new_name


def reverse_user_name(name: str) -> str:
    """
    Function that takes in name as a parameter and reverses its letters. The name should also be capitalized.

    :param name: name of the player
    :return: A name that is reversed.
    """
    new_name = name[::-1]
    return normalize_user_name(new_name)


def check_user_choice(choice: str) -> str:
    """
    Function that checks user's choice.

    The choice can be uppercase or lowercase string, but the choice must be
    either rock, paper or scissors. If it is, then return a choice that is lowercase.
    Otherwise return 'Sorry, you entered unknown command.'
    :param choice: user choice
    :return: choice or an error message
    """
    lower_choice = choice.lower()
    if lower_choice == 'rock' or lower_choice == 'paper' or lower_choice == 'scissors':
        return lower_choice
    else:
        return "Sorry, you entered unknown command."


def determine_winner(name: str, user_choice: str, computer_choice: str, reverse_name: bool = False) -> str:
    """
    Determine the winner returns a string that has information about who won.

    You should use the functions that you wrote before. You should use check_user_choice function
    to validate the user choice and use normalize_user_name for representing a correct name. If the
    function parameter reverse is true, then you should also reverse the player name.
    NB! Use the previous functions that you have written!

    :param name:player name
    :param user_choice:
    :param computer_choice:
    :param reverse_name:
    :return:
    """
    real_name = normalize_user_name(name)
    choice_user = check_user_choice(user_choice)
    choice_computer = check_user_choice(computer_choice)
    if choice_user == "Sorry, you entered unknown command." or choice_computer == "Sorry, you entered unknown command.":
        return "There is a problem determining the winner."
    if reverse_name is True:
        real_name = reverse_user_name(real_name)
    if choice_user == 'rock' and choice_computer == 'scissors':
        return f"{real_name} had {choice_user} and computer had {choice_computer}, hence {real_name} wins."
    elif choice_user == 'scissors' and choice_computer == 'paper':
        return f"{real_name} had {choice_user} and computer had {choice_computer}, hence {real_name} wins."
    elif choice_user == 'paper' and choice_computer == 'rock':
        return f"{real_name} had {choice_user} and computer had {choice_computer}, hence {real_name} wins."
    elif choice_user == 'paper' and choice_computer == 'scissors':
        return f"{real_name} had {choice_user} and computer had {choice_computer}, hence computer wins."
    elif choice_user == 'scissors' and choice_computer == 'rock':
        return f"{real_name} had {choice_user} and computer had {choice_computer}, hence computer wins."
    elif choice_user == 'rock' and choice_computer == 'paper':
        return f"{real_name} had {choice_user} and computer had {choice_computer}, hence computer wins."
    else:
        return f"{real_name} had {choice_user} and computer had {choice_computer}, hence it is a draw."


def play_game() -> None:
    """
    Enable you to play the game you just created.

    :return:
    """
    user_name = input("What is your name? ")
    play_more = True
    while play_more:
        computer_choice = choice(['rock', 'paper', 'scissors'])
        user_choice = check_user_choice(input("What is your choice? "))
        print(determine_winner(user_name, user_choice, computer_choice))
        play_more = True if input("Do you want to play more ? [Y/N] ").lower() == 'y' else False


if __name__ == "__main__":
    print(normalize_user_name('ago'))  # Ago
    print(normalize_user_name('AGO'))  # Ago
    print(normalize_user_name('MaRtInA'))  # Martina

    print(reverse_user_name('MaRtInA'))  # Anitram
    print(reverse_user_name('AGO'))  # Oga

    print(check_user_choice('rock'))  # rock
    print(check_user_choice('ROCK'))  # rock
    print(check_user_choice('midagi on viltu'))  # Sorry, you entered unknown command.

    # 50% on juba tehtud, tubli töö!

    print(determine_winner('ago', 'rock', 'paper'))  # Ago had rock and computer had paper, hence computer wins.
    print(determine_winner('ago', 'rock', 'paper', True))  # Oga had rock and computer had paper, hence computer wins.
    print(
        determine_winner('loORa', 'SCISSORS', 'paper'))  # Loora had scissors and computer had paper, hence Loora wins.
    print(determine_winner('Shakira', 'waka waka', 'fire'))  # There is a problem determining the winner.
    print(determine_winner('Shakira', 'rock',
                           'sciSSOrs'))  # Shakira had rock and computer had scissors, hence Shakira wins.

    play_game()  # Kommenteeri see rida välja kui kõik funktsioonid on valmis
