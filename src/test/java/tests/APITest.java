package tests;

import clients.ActorClient;
import net.serenitybdd.screenplay.Actor;

public class APITest {
    public static <T> T client(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public <T> ActorClient<T> rateChecker(Class<T> clazz) {
        Actor actor = Actor.named("USD Rate Checker");
        return new ActorClient<T>(actor);
    }
}
