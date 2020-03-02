package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Note;
import services.NoteService;

/**
 *
 * @author 798419
 */
public class NoteServlet extends HttpServlet {

    private int counter;

    @Override
    public void init() throws ServletException {
        counter = 0;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("guestcount", "<p> you are visitor #" + counter + "!</p>");
        counter++;

        System.out.println("do GET");

        NoteService ns = new NoteService();

        String action = request.getParameter("action");
        System.out.println("=====action is " + action + "==========");

        if (action != null && action.equals("view")) {
            String selectedNote = request.getParameter("selectedNote");
            int noteid = Integer.parseInt(selectedNote);
            try {
                Note note = ns.get(noteid);/////
                request.setAttribute("selectedNote", note);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Note> notes = null;
        try {
            System.out.println("notes loading .........................");
            notes = (List<Note>) ns.getAll();//?????????

            System.out.println("notes loaded .........................");
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        request.getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("guestcount", "<p> you are visitor #" + counter + "!</p>");
        counter++;
        System.out.println("do Post");

        String action = request.getParameter("action");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        String noteid = request.getParameter("noteid");
        String notedatecreated = request.getParameter("notedatecreated");
        String selectedNote = "";
        NoteService ns = new NoteService();

        try {
            switch (action) {
                case "delete":
                    System.out.println("delete a row...before..");
                    selectedNote = request.getParameter("selectedNote");
                    ns.delete(Integer.parseInt(selectedNote));
                    System.out.println("delete a row...after..");
                    break;
                case "edit":
                    System.out.println("edit mode...");
                    selectedNote = request.getParameter("selectedNote");
                    Note note =ns.get(Integer.parseInt(selectedNote));
                    System.out.println("note");
                    request.setAttribute("note", note);
                    break;
                case "add":
                    ns.insert(contents, title);
                    break;
                case "save":
                    System.out.println(title);
                    System.out.println(contents);
                    System.out.println(noteid );
                    System.out.println(notedatecreated);
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy"); 
                    java.util.Date date = format.parse(notedatecreated); 
                    System.out.println("date===== " + date);
                    ns.update(Integer.parseInt(noteid),title,contents, date);
                    System.out.println("Saved........");
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("exception in userServlet....");
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }

        List<Note> notes = null;
        try {
            notes = (List<Note>) ns.getAll();
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);

        request.getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
