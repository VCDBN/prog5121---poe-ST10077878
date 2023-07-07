
package main;


import main.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task validTask;
    private Task invalidTask;

    @BeforeEach
    void setUp() {
        validTask = new Task("Login Feature", "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        invalidTask = new Task("Login Feature", "This task description is definitely more than fifty characters long, which is not allowed", "Robyn Harrison", 8, "To Do");
    }

    @Test
    void checkTaskDescriptionSuccess() {
        assertTrue(validTask.checkTaskDescription());
    }
    
    @Test
    void checkTaskDescriptionFailure() {
        assertFalse(invalidTask.checkTaskDescription());
    }

    @Test
    void createTaskID() {
        String taskID = validTask.createTaskID();
        assertEquals("LO:0:SON", taskID);
    }

    @Test
    void printTaskDetails() {
        String expectedDetails = "Task Status: To Do\n" +
                "Developer Details: Robyn Harrison\n" +
                "Task Number: 0\n" +
                "Task Name: Login Feature\n" +
                "Task Description: Create Login to authenticate users\n" +
                "Task ID: LO:0:SON\n" +
                "Duration: 8";

        assertEquals(expectedDetails, validTask.printTaskDetails());
    }

    @Test
    void returnTotalHours() {
        assertEquals(8, validTask.returnTotalHours());
    }
}






    

