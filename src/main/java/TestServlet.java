import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestServlet extends HttpServlet{
    private Spisok list=new Spisok();

    private Configuration cfg=new Configuration(Configuration.VERSION_2_3_26); {
        try{
            cfg.setTemplateLoader(new FileTemplateLoader(new File(".")));
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");


       /* resp.getWriter().write("" +
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
                "</html>");*/
             try{
                 Template t=cfg.getTemplate("toDo.html");
                 Map<String,Object> map=new HashMap<>();

                 map.put("tasks", list.view());
                 t.process(map,resp.getWriter());
             } catch (Exception e){
                 e.printStackTrace();
                 resp.sendError(500);
             }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String what=req.getParameter("task");
        try {
            list.add(what);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/");
    }

}
