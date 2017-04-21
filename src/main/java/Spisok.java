import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 21.04.2017.
 */
public class Spisok {
    private final ArrayList<ToDoList> ar;
    private int ind;
    public Spisok(){
        ar=new ArrayList<ToDoList>();
        ind=1;
    }
    public int add(String mess){
        int id=ind++;
        ar.add(new ToDoList(id,mess));
        return id;

    }
    public void del(int index){
        ar.remove(index);
    }
    List<ToDoList> view(){
        Collections.sort(ar, new Comparator<ToDoList>(){
            public int compare(ToDoList w1, ToDoList w2){
                return Integer.compare(w1.getId(), w2.getId());
//                if (w1.getId()>w2.getId()) return 1;
//                if (w1.getId()<w2.getId()) return -1;
//                return 0;
            }
        });
        return ar;
    }
    public void tOString(){
        for (int i=0; i<ar.size();i++) {
            ToDoList w = ar.get(i);
            System.out.println(w);
        }

    }
}

