"""Robot that finds the object."""
import builtins
from PiBot import PiBot
import time


class Robot:
    """The three primitives robotic paradigm: sense, plan, act."""

    def __init__(self):
        """Initialize."""
        self.robot = PiBot()
        self.list1 = []
        self.distance_from_object = self.robot.get_front_middle_laser()
        self.start_time = time.time()

    def set_robot_wheels_speed(self, right, left):
        """Set your own robot wheel speed."""
        self.robot.set_right_wheel_speed(right)
        self.robot.set_left_wheel_speed(left)

    def spin(self):
        """Spin until major change."""
        while True:
            self.set_robot_wheels_speed(10, -10)
            print("Distance from object:")
            print(self.distance_from_object)
            print("Front laser:")
            print(self.robot.get_front_middle_laser())
            if builtins.float(self.distance_from_object) > builtins.float(self.robot.get_front_middle_laser() + 0.30):
                self.robot.sleep(0.05)
                break
            self.distance_from_object = self.robot.get_front_middle_laser()
            self.robot.sleep(0.05)

    def check(self):
        """Turn back to it."""
        while self.robot.get_front_middle_laser() > self.distance_from_object + 0.1:
            self.set_robot_wheels_speed(-10, 10)
            print(self.robot.get_front_middle_laser())
            self.robot.sleep(0.05)

    def moving(self):
        """Move towards the object."""
        while True:
            if self.distance_from_object + 0.1 > self.robot.get_front_middle_laser():
                self.distance_from_object = self.robot.get_front_middle_laser()
                self.set_robot_wheels_speed(11, 11)
                if self.robot.get_front_middle_laser() < 0.15 or self.robot.get_front_left_laser() < 0.15 or \
                        self.robot.get_front_right_laser() < 0.15:
                    break
            else:
                self.set_robot_wheels_speed(9, -9)
            print(self.robot.get_front_middle_laser())
            self.robot.sleep(0.1)

    def main(self):
        """Main function."""
        self.check()
        self.spin()
        self.set_robot_wheels_speed(0, 0)
        self.moving()
        self.set_robot_wheels_speed(0, 0)


Robot().main()
