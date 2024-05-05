package by.javaguru.je.jdbc.servlet;

import by.javaguru.je.jdbc.entity.Subjects;
import by.javaguru.je.jdbc.servise.SubjectsServiceImpl;
import by.javaguru.je.jdbc.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/subjectList")
public class SubjectListServlet extends HttpServlet {

    private final SubjectsServiceImpl subjectService = SubjectsServiceImpl.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subjects> subjects = subjectService.findAll();

        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher(JspHelper.getPath("SubjectList")).forward(request, response);

    }
}
