package il.co.fibi.comm.mqbridge.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtoRepository extends MongoRepository<ProtolItem, String> {

}
