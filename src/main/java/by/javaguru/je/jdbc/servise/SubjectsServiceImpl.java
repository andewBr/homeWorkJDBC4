package by.javaguru.je.jdbc.servise;

import by.javaguru.je.jdbc.dao.SubjectDAO;
import by.javaguru.je.jdbc.entity.Subjects;

import java.sql.SQLException;
import java.util.List;

public class SubjectsServiceImpl implements GenericService<Subjects, Long> {

    private SubjectDAO subjectDAO = new SubjectDAO();
    private static SubjectsServiceImpl INSTANCE = new SubjectsServiceImpl();

    public static SubjectsServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Subjects findById(Long id) {
        return subjectDAO.findById(id).get();
    }

    @Override
    public List<Subjects> findAll() {
        return subjectDAO.findAll();
    }

    @Override
    public Subjects insert(Subjects schoolSubjects) throws SQLException {
        return subjectDAO.save(schoolSubjects);
    }

    @Override
    public Subjects update(Subjects schoolSubjects, Long id) throws SQLException {
        return subjectDAO.update(schoolSubjects, id);
    }

    @Override
    public Subjects delete(Long id) throws SQLException {
        return subjectDAO.delete(id);
    }
}