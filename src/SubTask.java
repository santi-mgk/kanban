public class SubTask extends Task {

    private int idEpic;

    public SubTask(int id, String nameTask, String description, String status, int idEpic) {
        super(id, nameTask, description, status);
        this.idEpic = idEpic;
    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }
}
