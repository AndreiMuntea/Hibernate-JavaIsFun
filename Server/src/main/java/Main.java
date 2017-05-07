import Repository.DBRepository.TrialDBRepository;
import Server.ServerImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by andrei on 2017-05-07.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");
        ServerImpl serverImpl = factory.getBean(ServerImpl.class);
        Server server = ServerBuilder
                .forPort(50052)
                .addService(serverImpl)
                .build()
                .start();
        server.awaitTermination();

    }
}
