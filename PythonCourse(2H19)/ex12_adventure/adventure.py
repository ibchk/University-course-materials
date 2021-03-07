"""Game."""


class Adventurer:
    """An adventurer."""

    def __init__(self, name, class_type, power, experience=0):
        """Adventurer strings."""
        self.name = name
        if class_type == 'Druid' or class_type == 'Wizard' or class_type == 'Paladin':
            self.class_type = class_type
        else:
            self.class_type = 'Fighter'
        self.power = power
        self.experience = experience

    def add_experience(self, exp):
        """Add an ex."""
        self.experience += exp

    def add_power(self, power):
        """Adding power."""
        self.power += power

    def __repr__(self):
        """Return string."""
        return f'{self.name}, the {self.class_type}, Power: {self.power}, Experience: {self.experience}.'


class Monster:
    """Monster maker."""

    def __init__(self, name, mon_type, power=0):
        """Make strings."""
        self.pop = name
        self.mon_type = mon_type
        self.power = power

    @property
    def name(self):
        """Check the name by monster type."""
        if self.mon_type == 'Zombie':
            return 'Undead ' + self.pop
        else:
            return self.pop

    def __repr__(self):
        """Return string."""
        return f'{self.name} of type {self.mon_type}, Power: {self.power}.'


def remove(i: list, li: list):
    """Remove i from list."""
    for n in i:
        li.remove(n)


class World:
    """Make world."""

    def __init__(self, pm):
        """Make strings."""
        self.pythom_master = pm
        self.adventurerlist = []
        self.monsterlist = []
        self.graveyard = []
        self.necromancer = False
        self.active_adventurers = []
        self.active_monsters = []

    def get_monsterlist(self):
        """Get list of monsters."""
        return self.monsterlist

    def get_adventurerlist(self):
        """Get list of adventurers."""
        return self.adventurerlist

    def add_adventurer(self, adventurer: Adventurer):
        """Add adventurer."""
        if type(adventurer) == Adventurer:
            self.adventurerlist.append(adventurer)

    def add_monster(self, monster: Monster):
        """Add monster."""
        if type(monster) == Monster:
            self.monsterlist.append(monster)

    def get_graveyard(self):
        """Get graveyard."""
        return self.graveyard

    def get_python_master(self):
        """Get PM."""
        return self.pythom_master

    def change_necromancer(self, tf: bool):
        """Change necromancer."""
        self.necromancer = tf

    def revive_graveyard(self):
        """Make monsters alive."""
        if len(self.graveyard) > 0 and self.necromancer:
            for i in range(len(self.graveyard)):
                io = self.graveyard[i]
                if type(io) == Monster:
                    io.mon_type = "Zombie"
                elif type(io) == Adventurer:
                    io = Monster(f'Undead {io.name}', f'Zombie {io.class_type}', io.power)
                self.add_monster(io)
            self.graveyard = []
            self.change_necromancer(False)

    def add_strongest(self, classt):
        """Find strongest adventurer."""
        strongest_a = 0
        for i in self.adventurerlist:
            if i.class_type == classt and (strongest_a == 0 or strongest_a.power < i.power):
                strongest_a = i
        if strongest_a != 0:
            self.adventurerlist.remove(strongest_a)
            self.active_adventurers.append(strongest_a)

    def add_weakest(self, classt):
        """Find weekest adventurer."""
        weekest_a = 0
        for i in self.adventurerlist:
            if i.class_type == classt and (weekest_a == 0 or weekest_a.power > i.power):
                weekest_a = i
        if weekest_a != 0:
            self.adventurerlist.remove(weekest_a)
            self.active_adventurers.append(weekest_a)

    def add_most_experience(self, classt):
        """Find most experienced adventurer."""
        ex_a = 'a'
        for i in self.adventurerlist:
            if i.class_type == classt and (ex_a == 'a' or ex_a.experience < i.experience):
                ex_a = i
        if ex_a != 'a':
            self.adventurerlist.remove(ex_a)
            self.active_adventurers.append(ex_a)

    def add_least_experience(self, classt):
        """Find least experienced adventurer."""
        ex_a = 'a'
        for i in self.adventurerlist:
            if i.class_type == classt and (ex_a == 'a' or ex_a.experience > i.experience):
                ex_a = i
        if ex_a != 'a':
            self.adventurerlist.remove(ex_a)
            self.active_adventurers.append(ex_a)

    def add_by_name(self, nm):
        """Add adventurer by name."""
        li0 = []
        for i in self.adventurerlist:
            if i.name == nm:
                li0.append(i)
                self.active_adventurers.append(i)
                break
        self.adventurerlist = [x for x in self.adventurerlist if x not in li0]

    def add_all_of_class_type(self, classt):
        """Add by class."""
        li0 = []
        for i in self.adventurerlist:
            if i.class_type == classt:
                li0.append(i)
                self.active_adventurers.append(i)
        self.adventurerlist = [x for x in self.adventurerlist if x not in li0]

    def add_all(self):
        """Add all adventurers."""
        for i in self.adventurerlist:
            self.active_adventurers.append(i)
        self.adventurerlist = []

    def get_active_adventurers(self):
        """Get all active adventures."""
        copy = [i for i in self.active_adventurers]
        li = []
        for i in range(len(self.active_adventurers)):
            adv = 0
            for j in copy:
                if adv == 0 or j.experience > adv.experience:
                    adv = j
            copy.remove(adv)
            li.append(adv)
        return li

    def add_monster_by_name(self, nm):
        """Add monster by name."""
        li0 = []
        for i in self.monsterlist:
            if i.name == nm:
                li0.append(i)
                self.active_monsters.append(i)
                break
        self.monsterlist = [x for x in self.monsterlist if x not in li0]

    def add_strongest_monster(self):
        """Find strongest monster."""
        strongest_a = 0
        for i in self.monsterlist:
            if strongest_a == 0 or strongest_a.power < i.power:
                strongest_a = i
        if strongest_a != 0:
            self.monsterlist.remove(strongest_a)
            self.active_monsters.append(strongest_a)

    def add_weakest_monster(self):
        """Find weekest monster."""
        weekest_a = 0
        for i in self.monsterlist:
            if weekest_a == 0 or weekest_a.power > i.power:
                weekest_a = i
        if weekest_a != 0:
            self.monsterlist.remove(weekest_a)
            self.active_monsters.append(weekest_a)

    def add_all_of_type(self, mnt):
        """Add monster by type."""
        li0 = []
        for i in self.monsterlist:
            if i.mon_type == mnt:
                li0.append(i)
                self.active_monsters.append(i)
        self.monsterlist = [x for x in self.monsterlist if x not in li0]

    def add_all_monsters(self):
        """Add all monsters."""
        for i in self.monsterlist:
            self.active_monsters.append(i)
        self.monsterlist = []

    def get_active_monsters(self):
        """Get all active monsters."""
        copy = [i for i in self.active_monsters]
        li = []
        for i in range(len(self.active_monsters)):
            adv = 0
            for j in copy:
                if adv == 0 or j.power > adv.power:
                    adv = j
            copy.remove(adv)
            li.append(adv)
        return li

    def remove_character(self, nm):
        """Remove a character by name."""
        p = 0
        for i in self.adventurerlist:
            if i.name == nm:
                self.adventurerlist.remove(i)
                p = 1
                break
        if p != 1:
            for i in self.monsterlist:
                if i.name == nm:
                    self.monsterlist.remove(i)
                    p = 1
                    break
        if p != 1:
            for i in self.graveyard:
                if i.name == nm:
                    self.graveyard.remove(i)
                    break

    def calculate_power(self, who):
        """Calculate a sum of power."""
        sum = 0
        if who == 'adv':
            for i in self.active_adventurers:
                sum += i.power
        elif who == 'mon':
            for i in self.active_monsters:
                sum += i.power
        return sum

    def find_wrong_monsters(self):
        """Find wrong."""
        t = 0
        for i in self.active_adventurers:
            if i.class_type == 'Druid':
                t = 1
        if t == 1:
            li0 = []
            for i in self.active_monsters:
                if i.mon_type == 'Animal' or i.mon_type == 'Ent':
                    li0.append(i)
                    self.monsterlist.append(i)
            self.active_monsters = [x for x in self.active_monsters if x not in li0]

    def double_power(self):
        """Double power paladins."""
        t = 1
        for i in self.active_monsters:
            if i.mon_type == 'Zombie Fighter' or i.mon_type == 'Zombie' or i.mon_type == 'Zombie Druid' or \
                    i.mon_type == 'Zombie Paladin' or i.mon_type == 'Zombie Wizard':
                t = 0
        if t == 0:
            for i in self.active_adventurers:
                if i.class_type == 'Paladin':
                    t += i.power
            return t
        return 0

    def adv_win(self, ex: int, deadly: bool):
        """Make things if adventurers win."""
        if not deadly:
            for i in self.active_adventurers:
                i.add_experience(ex)
                self.adventurerlist.append(i)
            for i in self.active_monsters:
                self.monsterlist.append(i)
        elif deadly:
            for i in self.active_adventurers:
                i.add_experience(ex * 2)
                self.adventurerlist.append(i)
            for i in self.active_monsters:
                self.graveyard.append(i)

    def mon_win(self, ex: int, deadly: bool):
        """Make things if monsters win."""
        if not deadly:
            for i in self.active_adventurers:
                self.adventurerlist.append(i)
            for i in self.active_monsters:
                self.monsterlist.append(i)
        elif deadly:
            for i in self.active_adventurers:
                self.graveyard.append(i)
            for i in self.active_monsters:
                self.monsterlist.append(i)

    def draw(self, ex: int, deadly: bool):
        """Make things if draw."""
        if not deadly:
            for i in self.active_adventurers:
                i.add_experience(ex // 2)
                self.adventurerlist.append(i)
            for i in self.active_monsters:
                self.monsterlist.append(i)
        elif deadly:
            for i in self.active_adventurers:
                i.add_experience(ex)
                self.adventurerlist.append(i)
            for i in self.active_monsters:
                self.monsterlist.append(i)

    def go_adventure(self, deadly=False):
        """The main function, which plays the game."""
        self.find_wrong_monsters()
        add = self.double_power()
        adv = self.calculate_power('adv') + add
        mon = self.calculate_power('mon')
        ex = mon // len(self.active_adventurers) if len(self.active_adventurers) != 0 else 0
        if adv > mon:
            self.adv_win(ex, deadly)
        elif mon > adv:
            self.mon_win(ex, deadly)
        else:
            self.draw(ex, deadly)
        self.active_monsters = []
        self.active_adventurers = []


if __name__ == "__main__":
    print("Kord oli maailm.")
    Maailm = World("Sõber")
    Monsu1 = Monster("Goblin Baby", "Goblin", 1)
    Monsu2 = Monster("Goblin Brute", "Goblin", 5)
    Monsu3 = Monster("Goblin Spearman", "Goblin", 10)
    Monsu4 = Monster("Goblin Leader", "Goblin", 15)
    Maailm.add_monster(Monsu1)
    Maailm.add_monster(Monsu2)
    Maailm.add_monster(Monsu3)
    Maailm.add_monster(Monsu4)

    Maailm.add_all_monsters()
    print(Maailm.get_active_monsters())
    print(Maailm.get_active_monsters()[1])
