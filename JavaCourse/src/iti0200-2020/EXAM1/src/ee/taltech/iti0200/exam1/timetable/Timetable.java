import java.util.*;

public class Timetable {

    Map<Integer, LinkedList<Task>> tasks = new HashMap<>();
    Integer taskNR = 1;

    public Optional<String> addTask(String name, int day, int duration, boolean priority) {
        if (!checkIfCantAdd(name, day, duration, priority)) {
            return Optional.empty();
        } else {
            Task task = new Task(name, day, duration, priority, "T" + taskNR);
            taskNR++;
            if (tasks.containsKey(day)) {
                LinkedList<Task> newList = new LinkedList<>(tasks.get(day));
                newList.add(task);
                tasks.replace(day, newList);
            } else {
                LinkedList<Task> newList = new LinkedList<>();
                newList.add(task);
                tasks.put(day, newList);
            }
            return Optional.of(task.getTaskId());
        }
    }

    public boolean markTaskDone(String taskNumber) {
        for (LinkedList list : tasks.values()) {
            for (Object task : list) {
                Task task1 = (Task) task;
                if (task1.getTaskId().equals(taskNumber) && !task1.isDone()) {
                    for (Task task2: tasks.get(task1.getDay())){
                        if (task2.getTaskId().equals(task1.getTaskId())){
                            task2.setDone(true);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getTasksForDay(int day) {
        LinkedList<String> schedule = new LinkedList<>();
        if (day > 0) {
            if (tasks.containsKey(day)) {
                boolean priority = true;
                for (int n = 0; n < 2; n++) {
                    for (Task task : tasks.get(day)) {
                        if (task.isPriority() == priority) {
                            schedule.add(task.getTaskId() + " " + task.getName());
                        }
                    }
                    priority = false;
                }
            }
        }
        return schedule;
    }

    public boolean checkIfCantAdd(String name, int day, int duration, boolean priority) {
        if (day < 1 || duration > 5 || duration < 1) {
            return false;
        }
        int dayDuration = 0;
        if (tasks.containsKey(day)){
            for (Task task : tasks.get(day)) {
                if (name.equals(task.getName())) {
                    return false;
                }
                if (!task.isDone()) {
                    dayDuration += task.getDuration();
                }
            }
        }
        return dayDuration + duration <= 5;
    }

    public static void main(String[] args) {

        Timetable timetable = new Timetable();
        String task1 = timetable.addTask("wake up1", 1, 1, false).get();
        String task2 = timetable.addTask("wake up2", 1, 1, false).get();
        String task3 = timetable.addTask("wake up3", 1, 1, false).get();
        String task4 = timetable.addTask("wake up4", 1, 1, false).get();
        String task5 = timetable.addTask("wake up5", 1, 1, false).get();
        Optional<String> task6 = timetable.addTask("wake up6", 1, 1, false);
        System.out.println(task6); // Optional.empty(), day already full

        timetable.addTask("swim", 4, 3, true).get();
// cannot have the same task name on the same day (swim), whatever the other parameters are
        System.out.println(timetable.addTask("swim", 4, 1, false)); // Optional.empty
        System.out.println(timetable.getTasksForDay(1));
// [T1 wake up1, T2 wake up2, T3 wake up3, T4 wake up4, T5 wake up5]
        System.out.println(timetable.markTaskDone(task3)); // true
        System.out.println(timetable.markTaskDone(task3)); // false, cannot mark task done twice
        System.out.println(timetable.getTasksForDay(1));
// [T1 wake up1, T2 wake up2, T4 wake up4, T5 wake up5]
// now we can add additional task for day one, priority one!
        String task7 = timetable.addTask("sleep", 1, 1, true).get();
// priority task comes first
        System.out.println(timetable.getTasksForDay(1));
// [T7 sleep, T1 wake up1, T2 wake up2, T4 wake up4, T5 wake up5]

        timetable.addTask("eat", 2, 2, false);
        timetable.addTask("walk", 2, 2, true);
// priority task comes first
        System.out.println(timetable.getTasksForDay(2));
// [T9 walk, T8 eat]

// timetables are independent
        Timetable tt = new Timetable();
// we should not get an error here:
        tt.addTask("wake up1", 1, 1, false).get();

    }
}
