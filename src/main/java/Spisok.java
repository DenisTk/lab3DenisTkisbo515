import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 21.04.2017.
 */
public class Spisok {
    public static final String JBC_H2_TEST = "jdbc:h2:~/test";
    private final ArrayList<ToDoList> ar;
    private int ind;
    public Spisok(){
        ar=new ArrayList<ToDoList>();
        ind=1;
    }

    public void del(int index){
        ar.remove(index);
    }

    public void add(String text) throws Exception{
        try(Connection c= DriverManager.getConnection(JBC_H2_TEST)){
            try(PreparedStatement ps=c.prepareStatement("insert into todo (text) values (?)")){
                ps.setString(1,text);
                ps.executeUpdate();
            }

        }

    }
    List<ToDoList> view() throws Exception{
        List<ToDoList> list=new ArrayList<>();
        try(Connection c=DriverManager.getConnection(JBC_H2_TEST)){
            try(PreparedStatement ps=c.prepareStatement("select id,text from todo order by id")){
                try(ResultSet ys=ps.executeQuery()){
                    while (ys.next()) {
                        int id = ys.getInt(1);
                        String text = ys.getString(2);
                        list.add(new ToDoList(id, text));
                    }
                }

            }

        }
        return list;
    }
    public void tOString(){
        for (int i=0; i<ar.size();i++) {
            ToDoList w = ar.get(i);
            System.out.println(w);
        }

    }
}

