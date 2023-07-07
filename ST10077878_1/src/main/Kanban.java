package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Kanban extends JFrame {
    private static int numTasks = 3;
    private static int currentTasks = 0;
    private static Taskform taskform = new Taskform();
    private static List<Task> taskList = new ArrayList<>();

    private JButton addTaskButton;
    private JButton showReportButton;
    private JButton quitButton;

    public Kanban() {
        super("EasyKanban");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        addTaskButton = new JButton("1. Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentTasks < numTasks) {
                    taskform.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Task limit reached.");
                }
            }
        });

        showReportButton = new JButton("2. Show Report");
        showReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String report = Report.generateReport();
                JOptionPane.showMessageDialog(null, report);
            }
        });

        quitButton = new JButton("3. Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // implement the code you want to execute when the Quit button is clicked
                System.exit(0); // this will close the application
            }
        });

        add(addTaskButton);
        add(showReportButton);
        add(quitButton);
    }

    public static void incrementCurrentTasks() {
        currentTasks++;
    }

    public static int getCurrentTasks() {
        return currentTasks;
    }

    public static void addTask(Task task) {
        taskList.add(task);
    }

    // This method will allow you to remove a task from the list
    public static void removeTask(Task task) {
        taskList.remove(task);
    }

    public static List<Task> getTasks() {
        return taskList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Kanban kanban = new Kanban();
                kanban.setVisible(true);
                
            }
        });
    }
}







