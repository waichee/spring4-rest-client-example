import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wai.RestClient;

public class AppMain {

  public static void main(String[] args) {

    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("applicationContext.xml", AppMain.class);
    RestClient client = applicationContext.getBean("restClient", RestClient.class);
    client.getLvrRanges();
  }
}
