package blackapple.webbook.repositories;

import blackapple.webbook.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
