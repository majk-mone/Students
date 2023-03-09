package esercitazione.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import esercitazione.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findStudentByEmail(String email);

}
