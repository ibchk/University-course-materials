"""My testers for solution definitions."""

import solution


def test_students_study():
    """Test to the students study."""
    for i in range(0, 5):
        assert solution.students_study(i, True) is False
        assert solution.students_study(i, False) is False
    for i in range(5, 18):
        assert solution.students_study(i, True) is True
        assert solution.students_study(i, False) is False
    for i in range(18, 25):
        assert solution.students_study(i, True) is True
        assert solution.students_study(i, False) is True


def test_lottery():
    """Test to the lottery."""
    for i in range(-5, 5):
        print(i)
        assert solution.lottery(i, i, i) == 5
    for a in range(6):
        for b in range(6):
            for c in range(6):
                if a != b and a != c:
                    assert solution.lottery(a, b, c) == 1
    assert solution.lottery(5, 5, 5) == 10
    for a in range(6):
        for b in range(6):
            for c in range(6):
                if (a == b or a == c) and c != b:
                    assert solution.lottery(a, b, c) == 0


def test_fruit_order():
    """Test to the fruit order."""
    for i in range(110):
        for j in range(105):
            small_b = i - (5 * j)
            for k in range(110):
                if i == 0:
                    assert solution.fruit_order(k, j, 0) == 0
                elif small_b < 0 and small_b % 5 == 0:
                    assert solution.fruit_order(k, j, i) == 0
                elif small_b < 0 and small_b % 5 != 0 and small_b % 5 <= k:
                    assert solution.fruit_order(k, j, i) == small_b % 5
                elif small_b < 0 and small_b % 5 != 0 and small_b % 5 > k:
                    assert solution.fruit_order(k, j, i) == -1
                elif small_b > k:
                    assert solution.fruit_order(k, j, i) == -1
                elif k >= small_b > -1:
                    assert solution.fruit_order(k, j, i) == small_b
