package it.capgemini.esercitazione;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import it.capgemini.esercitazione.model.Address;
import it.capgemini.esercitazione.model.Gender;
import it.capgemini.esercitazione.model.Student;
import it.capgemini.esercitazione.repository.StudentRepository;

@SpringBootApplication
public class EsercitazioneApplication {

    public static void main(String[] args) {
	SpringApplication.run(EsercitazioneApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTempleate) {
	return args -> {
	    Address address = new Address("Italy", "Montesilvano", "65015");

	    String email = "majk.mone@gmail.com";
	    Student student = new Student("Majk", "Mone", "majk.mone@gmail.com", Gender.MALE, address,
		    List.of("Computer Science"), BigDecimal.TEN, LocalDateTime.now());

	    // UsingMongoTemplateAndQuery(repository, mongoTempleate, email, student);
	    repository.findStudentByEmail(email).ifPresentOrElse(s -> {
		System.out.println(s + "already exists");
	    }, () -> {
		System.out.println("inserting student" + student);
		repository.insert(student);
	    });

	};

    }

    private void UsingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTempleate, String email,
	    Student student) throws IllegalAccessException {
	Query query = new Query();
	query.addCriteria(Criteria.where("email").is(email));

	List<Student> students = mongoTempleate.find(query, Student.class);

	if (students.size() > 1) {
	    throw new IllegalAccessException("found many students whit email" + email);
	}

	if (students.isEmpty()) {
	    System.out.println("inserting student" + student);

	    repository.insert(student);
	} else {
	    System.out.println(student + "already exists");
	}
    }
}
