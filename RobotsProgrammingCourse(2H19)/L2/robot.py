"""Third robot programm."""

from PiBot import PiBot

# Create a robot instance
robot = PiBot()
distance_from_line = robot.get_left_line_sensors()
distance_from_line_2 = robot.get_right_line_sensors()
number_of_colours = len(robot.get_line_sensors())


def cross(nr):
    """Cross drive."""
    if nr == 1:
        nr = 2
        print('Going left')
        robot.set_left_wheel_speed(0)
        robot.set_right_wheel_speed(20)
        robot.sleep(0.20)
        left_laser = robot.get_third_line_sensor_from_left()
        while left_laser > 150:
            robot.set_left_wheel_speed(0)
            robot.set_right_wheel_speed(20)
            robot.sleep(0.10)
            left_laser = robot.get_third_line_sensor_from_left()
    elif nr == 2:
        nr = 3
        print('missing')
        robot.set_left_wheel_speed(20)
        robot.set_right_wheel_speed(20)
        robot.sleep(0.15)
    elif nr == 3:
        nr = 1
        print('Going right')
        robot.set_left_wheel_speed(20)
        robot.set_right_wheel_speed(0)
        robot.sleep(0.20)
        right_laser = robot.get_third_line_sensor_from_right()
        while right_laser > 150:
            robot.set_left_wheel_speed(15)
            robot.set_right_wheel_speed(0)
            robot.sleep(0.10)
            right_laser = robot.get_third_line_sensor_from_right()
    return nr


nr = 1
while True:
    robot.set_wheels_speed(10)
    if (robot.get_second_line_sensor_from_right() < 300 and robot.get_second_line_sensor_from_left() < 300) or \
            (robot.get_rightmost_line_sensor() < 300 and robot.get_second_line_sensor_from_left() < 300) or \
            (robot.get_second_line_sensor_from_right() < 300 and robot.get_leftmost_line_sensor() < 300) or \
            (robot.get_rightmost_line_sensor() < 300 and robot.get_third_line_sensor_from_left() < 300) or \
            (robot.get_third_line_sensor_from_right() < 300 and robot.get_leftmost_line_sensor() < 300):
        print('cross')
        cross(nr)
    elif robot.get_second_line_sensor_from_right() > 300 and robot.get_second_line_sensor_from_left() > 300:
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
