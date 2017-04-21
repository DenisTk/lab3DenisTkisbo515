
public class ToDoList {
    private int id;
    private String message;
    public ToDoList(int id, String message){
        this.id=id;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id +". "+message;
    }
}
