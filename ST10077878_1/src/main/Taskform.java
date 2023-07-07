package main;

import javax.swing.*;
import java.awt.*;

public class Taskform extends JFrame {
    private static JTextArea taskNameField;
    private static JTextArea taskDescriptionField;
    private static JTextArea developerDetailsField;
    private static JTextField taskDurationField;
    private static JComboBox<String> taskStatusField;
    private static JLabel taskIDLabel;

    public Taskform() {
        setTitle("Add Task");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        taskNameField = new JTextArea();
        taskDescriptionField = new JTextArea();
        developerDetailsField = new JTextArea();
        taskDurationField = new JTextField();
        String[] statusOptions = {"To Do", "Doing", "Done"};
        taskStatusField = new JComboBox<>(statusOptions);

        // setting line wrap for JTextArea
        taskNameField.setLineWrap(true);
        taskDescriptionField.setLineWrap(true);
        developerDetailsField.setLineWrap(true);

        taskIDLabel = new JLabel();  // initialize the label
        add(new JLabel("Task ID:"));
        add(taskIDLabel);

        add(new JLabel("Task Name:"));
        add(new JScrollPane(taskNameField)); // added JScrollPane to handle scrolling
        add(new JLabel("Task Description:"));
        add(new JScrollPane(taskDescriptionField)); // added JScrollPane to handle scrolling
        add(new JLabel("Developer Details:"));
        add(new JScrollPane(developerDetailsField)); // added JScrollPane to handle scrolling
        add(new JLabel("Task Duration:"));
        add(taskDurationField);
        add(new JLabel("Task Status:"));
        add(taskStatusField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> addTask());

        add(new JLabel());
        add(submitButton);

        this.setVisible(false);
    }

    public void addTask() {
        if (Kanban.getCurrentTasks() < 3) {
            Kanban.incrementCurrentTasks();
            String taskName = taskNameField.getText();
            String taskDescription = taskDescriptionField.getText();
            String developerDetails = developerDetailsField.getText();
            int taskDuration = Integer.parseInt(taskDurationField.getText());
            String taskStatus = (String) taskStatusField.getSelectedItem();

            Task newTask = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
            if (newTask.checkTaskDescription()) {
                JOptionPane.showMessageDialog(null, newTask.printTaskDetails());
                taskIDLabel.setText(newTask.getTaskID());
                Kanban.addTask(newTask);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Task limit reached.");
        }
    }
}



