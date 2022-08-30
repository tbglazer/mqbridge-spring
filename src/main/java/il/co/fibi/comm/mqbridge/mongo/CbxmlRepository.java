package il.co.fibi.comm.mqbridge.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CbxmlRepository extends MongoRepository<CbxmlItem, String> {
	@Query("{_id:'?0'}")
	Optional<CbxmlItem> findItemById(String id);
}
