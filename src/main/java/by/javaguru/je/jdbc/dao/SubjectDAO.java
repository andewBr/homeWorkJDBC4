package by.javaguru.je.jdbc.dao;


import by.javaguru.je.jdbc.entity.Subjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.javaguru.je.jdbc.utils.ConnectionManager.open;


public class SubjectDAO implements DAO<Long, Subjects> {

    private static String SELECT_BY_ID_SQL = "SELECT id, subject FROM subjects WHERE id = ?";
    private static String SELECT_ALL_SQL = "SELECT id, subject FROM subjects";
    private static String SET_SUBJECTS_SQL = "UPDATE subjects set subject = ? where id = ?";
    private static String INSERT_SUBJECTS_SQL = "INSERT INTO subjects (subject) VALUES (?)";
    private static String DELETE_SUBJECTS_SQL = "DELETE FROM subjects WHERE id = ?";

    @Override
    public Subjects update(Subjects subjects, Long id) throws SQLException {
        Subjects schoolSubjects = new Subjects();
        try (Connection connection = open()) {
            PreparedStatement statement = connection.prepareStatement(SET_SUBJECTS_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, subjects.getSubject());
            statement.setLong(2, id);
            int result = statement.executeUpdate();
            if (result == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    schoolSubjects = coverSubject(resultSet);
                }
            }
        }
        return schoolSubjects;
    }

    @Override
    public List<Subjects> findAll() {
        ArrayList<Subjects> subjects = new ArrayList<>();
        try (Connection connection = open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subjects.add(coverSubject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subjects;
    }

    @Override
    public Optional<Subjects> findById(Long id) {
        try (Connection connection = open()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(coverSubject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Subjects save(Subjects subjects) throws SQLException {
        Subjects schoolSubjects = null;
        try (Connection connection = open()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECTS_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, subjects.getSubject());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        schoolSubjects = coverSubject(generatedKeys);
                    }
                }
            }
        }
        return schoolSubjects;
    }

    @Override
    public Subjects delete(Long id) throws SQLException {
        Subjects schoolSubjects = new Subjects();
        try (Connection connection = open()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECTS_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            if (result > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    schoolSubjects.setId(generatedKeys.getLong(1));
                }
            }
            return schoolSubjects;
        }
    }

    public Subjects coverSubject(ResultSet resultSet) throws SQLException {
        return new Subjects(
                resultSet.getLong("id"),
                resultSet.getString("subject"));
    }

    public static void main(String[] args) throws SQLException {
        SubjectDAO subjectDAO = new SubjectDAO();
        Subjects trud = new Subjects("Труд");
        Subjects save = subjectDAO.save(trud);
        System.out.println(save);
    }

}