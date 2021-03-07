"""Second robot programm."""
from PiBot import PiBot

# Create a robot instance
robot = PiBot()

distance_from_line = robot.get_left_line_sensors()
distance_from_line_2 = robot.get_right_line_sensors()
number_of_colours = len(robot.get_line_sensors())

while True:
    robot.set_wheels_speed(10)
    while robot.get_second_line_sensor_from_right() > 300 and robot.get_second_line_sensor_from_left() > 300:
        robot.sleep(0.05)

    robot.set_wheels_speed(0)

    if robot.get_second_line_sensor_from_right() < 300:
        robot.set_left_wheel_speed(10)
        while robot.get_second_line_sensor_from_right() < 300:
            robot.sleep(0.05)
        robot.set_wheels_speed(0)
    elif robot.get_second_line_sensor_from_left() < 300:
        robot.set_right_wheel_speed(10)
        while robot.get_second_line_sensor_from_left() < 300:
            robot.sleep(0.05)
        robot.set_wheels_speed(0)

    robot.set_wheels_speed(0)
