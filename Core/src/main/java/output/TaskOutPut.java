package output;


import model.Task;

public interface TaskOutPut {

    boolean validateTitle(String title);
    boolean saveTask(Task task);
    boolean deleteTask(String title) throws Exception;

    Task findTask(String title);

}
