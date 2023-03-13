package it.capgemini.esercitazione.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.capgemini.esercitazione.model.Student;
import it.capgemini.esercitazione.repository.StudentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
	return studentRepository.findAll();

    }

    public Student getStudentsById(String id) throws Exception {
	Student studentById;

	try {

	    studentById = studentRepository.findById(id).orElseThrow(() -> new Exception("id not found"));
	    return studentById;
	} catch (Exception e) {

	    e.printStackTrace();

	    throw e;
	}

    }

    public Student getStudentByEmail(String email) throws Exception {
	Student studentByEmail;
	try {
	    studentByEmail = studentRepository.findStudentByEmail(email)
		    .orElseThrow(() -> new Exception("email not found"));
	    return studentByEmail;
	} catch (Exception e) {

	    e.printStackTrace();

	    throw e;
	}

    }

    public Student insertStudents(Student s) {

	return studentRepository.insert(s);
    }

    public Student updateStudent(Student s, String idStudent) throws Exception {

	try {
	    Student studentModify = studentRepository.findById(idStudent)
		    .orElseThrow(() -> new Exception("Username not found"));

	    studentModify.setFirstName(s.getFirstName());
	    studentModify.setLastName(s.getLastName());
	    studentModify.setEmail(s.getEmail());
	    studentModify.setGender(s.getGender());
	    studentModify.setAddress(s.getAddress());
	    studentModify.setFavouriteSubjects(s.getFavouriteSubjects());
	    studentModify.setTotalSpentInBooks(s.getTotalSpentInBooks());
	    studentRepository.save(studentModify);

	    return studentModify;
	} catch (Exception e) {

	    e.printStackTrace();

	    throw e;
	}

    }

    public String deleteStudents(String id) {

	String message = null;

	try {

	    Optional<Student> student = studentRepository.findById(id);

	    studentRepository.delete(student.get());
	    message = "Studente eliminato";
	} catch (NullPointerException e) {
	    message = "Studente non trovato";
	}
	return message;
    }

}
