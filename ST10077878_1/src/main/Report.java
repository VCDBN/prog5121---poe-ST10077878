package main;

import java.util.ArrayList;
import java.util.List;

public class Report {

    public static String generateReport() {
        StringBuilder report = new StringBuilder();

        List<Task> tasks = Kanban.getTasks();

        for (Task task : tasks) {
            report.append("Task Name: ").append(task.getTaskName()).append("\n");
            report.append("Developer: ").append(task.getDeveloperDetails()).append("\n");
            report.append("Task ID: ").append(task.getTaskID()).append("\n");
            report.append("Task Duration: ").append(task.getTaskDuration()).append("\n");
            report.append("Task Status: ").append(task.getTaskStatus()).append("\n\n");
        }
        return report.toString();
    }

    // 2. a
    public static String getDoneTasks() {
        StringBuilder report = new StringBuilder();
        for (Task task : Kanban.getTasks()) {
            if (task.getTaskStatus().equalsIgnoreCase("done")) {
                report.append("Task Name: ").append(task.getTaskName()).append("\n");
                report.append("Developer: ").append(task.getDeveloperDetails()).append("\n");
                report.append("Task Duration: ").append(task.getTaskDuration()).append("\n\n");
            }
        }
        return report.toString();
    }

    // 2. b
    public static Task getLongestDurationTask() {
        Task longestTask = null;
        for (Task task : Kanban.getTasks()) {
            if (longestTask == null || task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }
        return longestTask;
    }

    // 2. c
    public static Task getTaskByName(String name) {
        for (Task task : Kanban.getTasks()) {
            if (task.getTaskName().equalsIgnoreCase(name)) {
                return task;
            }
        }
        return null;
    }

    // 2. d
    public static List<Task> getTasksByDeveloper(String developer) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : Kanban.getTasks()) {
            if (task.getDeveloperDetails().equalsIgnoreCase(developer)) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    // 2. e
    public static void deleteTaskByName(String name) {
        Task taskToDelete = null;
        for (Task task : Kanban.getTasks()) {
            if (task.getTaskName().equalsIgnoreCase(name)) {
                taskToDelete = task;
                break;
            }
        }
        if (taskToDelete != null) {
            Kanban.getTasks().remove(taskToDelete);
        }
    }
}

