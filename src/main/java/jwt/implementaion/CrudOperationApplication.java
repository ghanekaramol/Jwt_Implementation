 package jwt.implementaion;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CrudOperationApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CrudOperationApplication.class, args);
	}
}
