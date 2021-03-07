"""Deck."""
from typing import Optional, List
import requests


class Card:
    """Simple dataclass for holding card information."""

    def __init__(self, value: str, suit: str, code: str):
        """Constructor."""
        self.value = value
        self.suit = suit
        self.code = code
        self.top_down = False

    def __str__(self):
        """Str."""
        return self.code if self.top_down is False else '??'

    def __repr__(self) -> str:
        """Repr."""
        return self.code

    def __eq__(self, o) -> bool:
        """Eq."""
        return True if isinstance(o, Card) and o.value == self.value and o.suit == self.suit and o.code == self.code \
            else False


class Deck:
    """Deck."""

    DECK_BASE_API = "https://deckofcardsapi.com/api/deck/"

    def __init__(self, deck_count: int = 1, shuffle: bool = False):
        """Constructor."""
        if shuffle:
            self.reque = requests.get(f'{Deck.DECK_BASE_API}new/shuffle/?deck_count={deck_count}').json()
        else:
            self.reque = requests.get(f'{Deck.DECK_BASE_API}new/?deck_count={deck_count}').json()
        self.deck_count = deck_count
        self.is_shuffled = shuffle
        self._backup_deck = ['AS', '2S', '3S', '4S', '5S', '6S', '7S', '8S', '9S', '0S', 'JS', 'QS', 'KS', 'AD', '2D',
                             '3D', '4D', '5D', '6D', '7D', '8D', '9D', '0D', 'JD', 'QD', 'KD', 'AC', '2C', '3C', '4C',
                             '5C', '6C', '7C', '8C', '9C', '0C', 'JC', 'QC', 'KC', 'AH', '2H', '3H', '4H', '5H', '6H',
                             '7H', '8H', '9H', '0H', 'JH', 'QH', 'KH'] * deck_count
        self.remaining = self.reque.get('remaining')
        self.deck_id = self.reque.get('deck_id')

    def shuffle(self) -> None:
        """Shuffle the deck."""
        id = self.reque.get('deck_id')
        requests.get(Deck.DECK_BASE_API + f"{id}/shuffle/")
        self.is_shuffled = True

    def draw_card(self, top_down: bool = False) -> Optional[Card]:
        """
        Draw card from the deck.

        :return: card instance.
        """
        id = self.reque.get('deck_id')
        print(id)
        try:
            if self.remaining > 0 and top_down:
                i = requests.get(Deck.DECK_BASE_API + f"{id}/draw/?count=1").json()
                self.remaining = i['remaining']
                if i in self._backup_deck:
                    self._backup_deck.remove(i)
                return i
            return None
        except TypeError:
            if top_down:
                for i in self._backup_deck:
                    return i
            return None

    def _request(self, url: str) -> dict:
        """Update deck."""
        return requests.get(url).json()

    @staticmethod
    def _generate_backup_pile(self) -> List[Card]:
        """Generate backup pile."""
        li = []
        st = ['AS', '2S', '3S', '4S', '5S', '6S', '7S', '8S', '9S', '0S', 'JS', 'QS', 'KS', 'AD', '2D',
              '3D', '4D', '5D', '6D', '7D', '8D', '9D', '0D', 'JD', 'QD', 'KD', 'AC', '2C', '3C', '4C',
              '5C', '6C', '7C', '8C', '9C', '0C', 'JC', 'QC', 'KC', 'AH', '2H', '3H', '4H', '5H', '6H',
              '7H', '8H', '9H', '0H', 'JH', 'QH', 'KH']
        for i in self.deck_count:
            for s in st:
                li.append(s)
        return li


if __name__ == '__main__':
    d = Deck(shuffle=True)
    print(d.remaining)  # 52
    card1 = d.draw_card()  # Random card
    print(card1)
    print(card1 in d._backup_deck)  # False
    print(len(d._backup_deck))  # 51 shuffled cards
    d2 = Deck(deck_count=2)
    print(len(d2._backup_deck))  # 104 ordered cards (deck after deck)
