package it.capgemini.esercitazione.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.capgemini.esercitazione.model.Student;
import it.capgemini.esercitazione.response.ResponseHandler;
import it.capgemini.esercitazione.service.StudentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Object> getStudents(@Nullable @RequestParam String id, @Nullable @RequestParam String email) {
	try {
	    List<Student> result = new ArrayList<>();
	    if (id != null) {
		result.add(studentService.getStudentsById(id));
	    } else if (email != null) {
		result.add(studentService.getStudentByEmail(email));
	    } else {
		result = studentService.getAllStudents();
		return ResponseHandler.generateResponse("Ecco la lista!", HttpStatus.OK, result);
	    }

	    return ResponseHandler.generateResponse("Ecco lo studente!", HttpStatus.OK, result);
	} catch (Exception e) {
	    return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NO_CONTENT, null);
	}
    }

    @PostMapping
    public ResponseEntity<Object> insertStudent(@RequestBody Student s) {
	try {
	    Student result = studentService.insertStudents(s);

	    return ResponseHandler.generateResponse("Studente inserito con successo!", HttpStatus.OK, result);
	} catch (Exception e) {
	    return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student s, @PathVariable String id) {
	try {

	    Student result = studentService.updateStudent(s, id);

	    return ResponseHandler.generateResponse("Studente aggiornato con successo", HttpStatus.OK, result);

	} catch (Exception e) {
	    return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	}

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") String id) {
	try {

	    String result = studentService.deleteStudents(id);
	    return ResponseHandler.generateResponse("Studente eliminato!", HttpStatus.OK, result);
	} catch (Exception e) {
	    return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	}
    }
}
