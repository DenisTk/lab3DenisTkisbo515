import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class TestServlet extends HttpServlet{
    private Spisok list=new Spisok();
    {list.add("ovovo");}
    private Configuration cfg=new Configuration(Configuration.VERSION_2_3_26); {
        list.add("Пример");
        try{
            cfg.setTemplateLoader(new FileTemplateLoader(new File(".")));
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buf=new StringBuilder();
        List<ToDoList> items=list.view();
        for(ToDoList i:items){
            buf.append("<li>"+i.getMessage()+"</li>\n");
        }
        resp.setCharacterEncoding("UTF-8");


        resp.getWriter().write("" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Список дел</title>\n" +
                "</head>\n" +
                "<body>\n" +
                " <form method=post>\n" +
                "     Задача:<input name=\"task\">\n" +
                "     <input type=\"submit\" value=\"Добавить\">\n" +
                "     <ol>\n" +
                buf+
                "     </ol>\n" +
                " </form>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String what=req.getParameter("task");
        list.add(what);
        resp.sendRedirect("/");
    }

}
