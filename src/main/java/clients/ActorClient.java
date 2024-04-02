package clients;

import models.AssertCondition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.*;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.lessThan;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ActorClient<T> {

    private Actor actor;
    private Gson gson;
    private Class<T> responseType;

    public ActorClient(Actor actor) {
        this.actor = actor;
        this.gson = new GsonBuilder().create();
    }

    public ActorClient<T> performs(Supplier<Task> task) {
        actor.attemptsTo(task.get());
        return this;
    }

    public ActorClient<T> shouldSeeThat(AssertCondition<T> assertCondition) {
        String jsonResponse = SerenityRest.lastResponse().getBody().asPrettyString();
        T response = gson.fromJson(jsonResponse, assertCondition.getClazz());
        actor.should(seeThat(assertCondition.getMessage(), x -> assertCondition.getPredicate().test(response)));
        return this;
    }

    public ActorClient<T> statusCodeShouldBe(int expectedCode) {
        actor.should(seeThatResponse("Status code should be as expected",
                response -> response.statusCode(expectedCode)));
        return this;
    }

    public ActorClient<T> responseTimeShouldBeWithInSecs(long expectedSeconds) {
        actor.should(seeThatResponse("Response time should be within %s but was %s",
                response -> response.time(lessThan(expectedSeconds), TimeUnit.SECONDS)));
        return this;
    }
}

