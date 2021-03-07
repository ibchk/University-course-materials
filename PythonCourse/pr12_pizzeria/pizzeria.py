"""Make pizzeria."""
from math import pi
from math import floor


class Chef:
    """Chef class."""

    def __init__(self, name: str, experience_level: int):
        """Chef strings."""
        self.name = name
        self.experience_level = experience_level
        self.money = 0

    def cash(self, m):
        """Chef money."""
        self.money += m

    def __repr__(self):
        """Return string."""
        return f'Pizza chef {self.name.capitalize()} with {self.experience_level} XP'


class Pizza:
    """Pizza class."""

    def __init__(self, name: str, diameter: int, toppings: list):
        """Pizza strings."""
        self.name = name
        self.diameter = diameter
        self.toppings = toppings

    def calculate_complexity(self) -> int:
        """Calculate complexity."""
        hard = 0
        for i in self.toppings:
            hard += len(i) // 3
        return hard

    def calculate_price(self) -> int:
        """Calculate price."""
        r = self.diameter / 2
        area = r * r * pi
        return int(floor((area / 40 + len(self.toppings) // 2) * 100))

    def __repr__(self):
        """Return string."""
        toto = self.calculate_price()
        if toto < 100:
            pr = 0
            sr = toto
            if len(str(sr)) == 1:
                sr = '0' + str(sr)
        else:
            pr = toto // 100
            sr = str(toto)[-2:]
        return f'{self.name.capitalize()} pizza with a price of ' \
               f'{pr}.{sr}'


class Pizzeria:
    """Pizzeria class."""

    def __init__(self, name: str, is_fancy: bool, budget: int):
        """Pizza strings."""
        self.name = name
        self.is_fancy = is_fancy
        self.budget = budget
        self.chefs = []
        self.pizzas = []
        self.made_pizzas = {}

    def add_chef(self, chef: Chef) -> Chef or None:
        """Add chef."""
        if chef not in self.chefs and (not self.is_fancy or (self.is_fancy and chef.experience_level > 24)):
            self.chefs.append(chef)
            return chef
        return None

    def remove_chef(self, chef: Chef):
        """Remove chef."""
        if chef in self.chefs:
            self.chefs.remove(chef)

    def add_pizza_to_menu(self, pizza: Pizza):
        """Add pizza."""
        if self.budget - pizza.calculate_price() >= 0 and pizza not in self.pizzas and len(self.chefs) > 0:
            self.pizzas.append(pizza)
            self.budget = self.budget - pizza.calculate_price()

    def remove_pizza_from_menu(self, pizza: Pizza):
        """Remove pizza."""
        if pizza in self.pizzas:
            self.pizzas.remove(pizza)

    def bake_pizza(self, pizza: Pizza) -> Pizza or None:
        """Bake pizza."""
        if pizza in self.pizzas:
            master = 0
            cmplx = pizza.calculate_complexity()
            for i in self.chefs:
                if i.experience_level >= cmplx and (master == 0 or master.experience_level > i.experience_level):
                    master = i
            if master != 0:
                master.experience_level += len(pizza.name) // 2
                profit = floor((pizza.calculate_price() * 4 + len(pizza.name)) / 2)
                master.cash(profit)
                self.budget += profit
                if pizza in self.made_pizzas:
                    self.made_pizzas[pizza] = self.made_pizzas[pizza] + 1
                else:
                    self.made_pizzas[pizza] = 1
                return pizza
        return None

    def get_pizza_menu(self) -> list:
        """Get menu."""
        pop = [i for i in self.pizzas]
        li = []
        for m in range(len(self.pizzas)):
            piz = 0
            for k in pop:
                if piz == 0 or k.calculate_price() > piz.calculate_price():
                    piz = k
            pop.remove(piz)
            li.append(piz)
        return li

    def get_baked_pizzas(self) -> dict:
        """Get baked pizza list."""
        return self.made_pizzas

    def get_chefs(self) -> list:
        """Get list of chefs."""
        prof = [i for i in self.chefs]
        lis1 = []
        for m in range(len(self.chefs)):
            profi1 = 'q'
            for k in prof:
                if profi1 == 'q' or k.experience_level < profi1.experience_level:
                    profi1 = k
            prof.remove(profi1)
            lis1.append(profi1)
        return lis1

    def __repr__(self):
        """Return string."""
        return f'{self.name.capitalize()} with {len(self.chefs)} pizza chef(s).'


if __name__ == '__main__':
    pass
