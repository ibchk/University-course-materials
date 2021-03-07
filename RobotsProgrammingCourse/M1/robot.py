from PiBot import PiBot


class Robot:
    """
    The three primitives robotic paradigm: sense, plan, act [1].
    Sense - gather information using the sensors
    Plan - create a world model using all the information and plan the next move
    Act - carry out the next step of the plan using actuators
    """

    def __init__(self):
        print("Initializing...")
        self.robot = PiBot()
        self.problem_solved = False
        self.right = self.robot.get_rear_right_side_ir()
        self.left = self.robot.get_rear_left_side_ir()
        self.stop_distance = self.robot.get_rear_right_straight_ir()
        self.stop_distance2 = self.robot.get_rear_right_side_ir()
        self.front_right = self.robot.get_front_right_laser()
        self.front_left = self.robot.get_front_left_laser()
        self.stop = False
        self.r = self.robot.get_rear_right_side_ir()

    # checked
    def robot_wheel_speed(self, left, right):
        self.robot.set_left_wheel_speed(left)
        self.robot.set_right_wheel_speed(right)

    def go_front(self):
        """Go front."""
        print('gooo')
        self.robot_wheel_speed(12, 12)
        self.robot.sleep(5)

    # checked
    def turn_right(self):
        """Turn right."""
        rotation = self.robot.get_rotation()
        while self.robot.get_rotation() > rotation - 85:
            print("TURNING RIGHT!")
            self.robot_wheel_speed(11, -14)
            self.robot.sleep(0.07)
        print('Turn right fuck')

    def drive(self):
        """Drive forward."""
        print(self.robot.get_rear_left_straight_ir())
        print(self.robot.get_rear_right_straight_ir())

        while True:
            self.robot_wheel_speed(-10, -10)
            print('Again')
            self.robot.sleep(0.07)
            n = 0
            while self.robot.get_rear_right_side_ir() < self.r:
                print("Adjusting to left")
                self.robot_wheel_speed(-12, -9)
                self.robot.sleep(0.07)
                print(self.robot.get_rear_right_side_ir())  # 47
                print(self.robot.get_rear_left_side_ir())  # 57
                if self.robot.get_rear_right_straight_ir() > (self.r * 2) \
                        and self.robot.get_rear_left_straight_ir() > (self.r * 2):
                    n = 1
                    print('A wall')
                    self.turn_right()
            while self.robot.get_rear_right_side_ir() > self.r:
                print("Adjusting to right")
                self.robot_wheel_speed(-9, -12)
                self.robot.sleep(0.07)
                print(self.robot.get_rear_right_side_ir())
                print(self.robot.get_rear_left_side_ir())
                if self.robot.get_rear_right_straight_ir() > (self.r * 2) \
                        and self.robot.get_rear_left_straight_ir() > (self.r * 2):
                    n = 1
                    print('A wall')
                    self.turn_right()
            if self.robot.get_rear_right_straight_ir() > (self.r * 2) \
                    and self.robot.get_rear_left_straight_ir() > (self.r * 2) and n == 0:
                print('A wall')
                self.turn_right()

    def plan(self):
        print("Planning...")
        # self.check_if_dead_end()
        self.turn_right()
        self.go_front()
        self.problem_solved = True

    def main(self):
        self.robot.set_grabber_height(99)
        print(self.robot.get_rear_irs())
        while not self.problem_solved:
            self.drive()
            self.plan()
            self.robot.sleep(0.05)  # 20 Hz
        if self.problem_solved:
            print("Solved! Good job, robot!")
        else:
            print("Unable to solve! :(")


robot = Robot()
robot.main()
