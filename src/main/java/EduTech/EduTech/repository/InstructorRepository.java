package EduTech.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import EduTech.EduTech.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, String> {

}
