package esercitazione.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esercitazione.model.Student;
import esercitazione.response.ResponseHandler;
import esercitazione.service.StudentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @GetMapping(value = "/users")
    public ResponseEntity<Object> fetchAllStudents() {
	try {
	    List<Student> result = studentService.getAllStudents();
	    return ResponseHandler.generateResponse("Ecco la lista dei studenti!", HttpStatus.OK, result);
	} catch (Exception e) {
	    return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	}
    }

//    public List<Student> fetchAllStudents() {
//	return studentService.getAllStudents();
//    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getStudentsById(@PathVariable String id) {
	try {
	    Optional<Student> result = studentService.getStudentsById(id);
	    return ResponseHandler.generateResponse("Ecco lo studente!", HttpStatus.OK, result);
	} catch (Exception e) {
	    return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
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

	    Student result = studentService.updateFirstAndLastNameStudents(s, id);

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
