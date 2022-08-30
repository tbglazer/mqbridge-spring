package il.co.fibi.comm.mqbridge.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProtoRepository extends MongoRepository<ProtolItem, String> {
	@Query("{_id:'?0'}")
	Optional<ProtolItem> findItemById(String id);
}
