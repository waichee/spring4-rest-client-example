import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wai.client.AsyncRestClient;
import org.wai.client.SyncRestClient;
import org.wai.service.MockRestService;

public class AppMain {

  public static void main(String[] args) {

    //Startup spring context
    AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml", AppMain.class);

    // add a shutdown hook for the spring context...
    applicationContext.registerShutdownHook();

    //Start the mock REST server
    MockRestService mockRestService = applicationContext.getBean("mockRestService", MockRestService.class);
    mockRestService.startServer();

    //Invoke the REST clients synchronously
    SyncRestClient client = applicationContext.getBean("syncRestClient", SyncRestClient.class);
    client.getJsonDataSync();
    client.getStringDataSync();

    //Invoke the REST clients asynchronously
    AsyncRestClient asyncRestClient = applicationContext.getBean("asyncRestClient", AsyncRestClient.class);
    asyncRestClient.getDataJsonAsync();
    asyncRestClient.getDataStringAsync();


  }
}
