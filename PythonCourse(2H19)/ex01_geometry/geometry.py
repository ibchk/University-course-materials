"""Ask user a shape and a radius or a side length and calculate the shape area."""

shape = input("Please insert geometric shape: ")
if shape == "circle":
    radius = input(("Please insert radius in cm: "))
    from math import pi
    print(f"The area is {round(float(radius) ** 2 * pi, 2)} cm^2")
elif shape == "triangle":
    triangle_length = input("Please insert side length in cm: ")
    import math
    print(f"The area is {round(float(triangle_length) ** 2 * math.sqrt(3) / 4, 2)} cm^2")
elif shape == "square":
    square_length = input("Please insert side length in cm: ")
    print(f"The area is {round(float(square_length) ** 2, 2)} cm^2")
else:
    print("Shape is not supported.")
