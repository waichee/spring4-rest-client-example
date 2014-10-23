import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wai.client.AsyncRestClient;
import org.wai.client.SyncRestClient;

public class AppMain {

  public static void main(String[] args) {

    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("applicationContext.xml", AppMain.class);

    SyncRestClient client = applicationContext.getBean("syncRestClient", SyncRestClient.class);
    client.getDataSync();

    AsyncRestClient asyncRestClient = applicationContext.getBean("asyncRestClient", AsyncRestClient.class);
    asyncRestClient.getDataAsync();


  }
}
