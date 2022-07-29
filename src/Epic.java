import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> idSubTasks;

    public Epic( int id, String nameTask, String description, String
        status){
        super(id, nameTask, description, status);
        idSubTasks = new ArrayList<>();
    }

    public void addSubTasks(int subTaskId) {
        this.idSubTasks.add(subTaskId);
    }

    public ArrayList<Integer> getIdSubTasks() {
        return idSubTasks;
    }

    public void setIdSubTasks(ArrayList<Integer> idSubTasks) {
        this.idSubTasks = idSubTasks;
    }
}
