package clients;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class APITest {
    public static <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public <T> ActorClient<T> actorClient() {
        Actor actor = Actor.named("USD Rate Checker")
                .whoCan(CallAnApi.at(System.getenv("baseurl")));
        return new ActorClient<T>(actor);
    }
}
