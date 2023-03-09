package esercitazione.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esercitazione.model.Student;
import esercitazione.repository.StudentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
	return studentRepository.findAll();

    }

    public Optional<Student> getStudentsById(String id) {
	Optional<Student> studentById = studentRepository.findById(id);
	return studentById;

    }

    public Student insertStudents(Student s) {

	return studentRepository.insert(s);
    }

    public Student updateFirstAndLastNameStudents(Student s, String idStudent) {
	Optional<Student> studentModify = studentRepository.findById(idStudent);

	Student studente = null;

	if (studentModify.isPresent()) {

	    studente = studentModify.get();

	    studente.setFirstName(s.getFirstName());
	    studente.setLastName(s.getLastName());
	    studente.setEmail(s.getEmail());
	    studente.setGender(s.getGender());
	    studente.setAddress(s.getAddress());
	    studente.setFavouriteSubjects(s.getFavouriteSubjects());
	    studente.setTotalSpentInBooks(s.getTotalSpentInBooks());
	    studentRepository.save(studente);

	}

	return studente;
    }

    public String deleteStudents(String id) {
	studentRepository.deleteById(id);
	return null;
    }

}
