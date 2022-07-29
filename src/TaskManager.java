import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private int nextId = 1;

    private HashMap<Integer, SimpleTask> simpleTasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();

    //Создаём задачу
    public void addSimpleTask(SimpleTask task) {
        task.setId(nextId++);
        simpleTasks.put(task.getId(), task);
    }
    public void addSubTask(SubTask task) {
        task.setId(nextId++);
        subTasks.put(task.getId(), task);
        getEpicById(task.getIdEpic()).addSubTasks(task.getId());
        task.setStatus(updateStatusByEpic(task.getId()));
    }
    public void addEpic(Epic task) {
        task.setId(nextId++);
        epics.put(task.getId(), task);
    }

    //Получаем все задачи конкретного типа
    public HashMap<Integer, SimpleTask> getSimpleTasks() {
        return simpleTasks;
    }
    public HashMap<Integer, SubTask> getSubTasks() {
        return subTasks;
    }
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    //Удаляем все задачи конкретного типа
    public void deleteSimpleTasks() {
        simpleTasks.clear();
    }
    public void deleteSubTasks() {
        subTasks.clear();
    }
    public void deleteEpics() {
        epics.clear();
    }

    //Получаем задачу по id
    public SimpleTask getSimpleTaskById(int id) {
        return simpleTasks.get(id);
    }
    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    //Обновляем задачу
    public void updateSimpleTask(SimpleTask task) {
        simpleTasks.put(task.getId(), task);
    }
    public void updateSubTaskTask(SubTask task) {
        subTasks.put(task.getId(), task);
    }
    public void updateEpicTask(Epic task) {
        epics.put(task.getId(), task);
        task.setStatus(updateStatusByEpic(task.getId()));
    }

    //Удаляем задачу по id
    public void deleteSimpleTaskById(int id) {
        simpleTasks.remove(id);
    }
    public void deleteSubTaskById(int id) {
        Epic epic = getEpicById(subTasks.get(id).getIdEpic());
        epic.getIdSubTasks().remove(epic.getIdSubTasks().indexOf(id));
        subTasks.remove(id);
        epic.setStatus(updateStatusByEpic(epic.getId()));
    }
    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    //Получаем все задачи определённого эпика
    public ArrayList<SubTask> getListSubTasksByEpic(int id) {
        ArrayList<SubTask> subTasksByEpic = new ArrayList<>();
        for (int number : epics.get(id).getIdSubTasks()) {
           subTasksByEpic.add(subTasks.get(number));
        }
        return subTasksByEpic;
    }

    public String updateStatusByEpic(int id) {
        ArrayList<SubTask> subTasksByEpic = getListSubTasksByEpic(id);
        if (subTasksByEpic.isEmpty()) {
            return "NEW";
        };
        ArrayList<String> statuses = new ArrayList<>();
        for ( SubTask subTask : subTasksByEpic) {
            statuses.add(subTask.getStatus());
            }
        if (!statuses.contains("IN_PROGRESS") && !statuses.contains("DONE")) {
            return "NEW";
        } else if (!statuses.contains("IN_PROGRESS") && !statuses.contains("NEW")) {
            return "DONE";
        } else {
            return "IN_PROGRESS";
        }

    }
}