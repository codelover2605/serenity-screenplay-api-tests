package clients;

import models.ResponseWrapper;
import net.serenitybdd.screenplay.Actor;

import java.util.function.Supplier;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ActorClient<T> {

    private Actor actor;

    public ActorClient(Actor actor) {
        this.actor = actor;
    }

    public ActorClient<T> performs(Supplier<ResponseWrapper<T>> task) {
        ResponseWrapper<T> response = task.get();
        actor.remember("response", response);
        return this;
    }

    public ActorClient<T> shouldSeeThat(AssertCondition assertCOndition) {
        ResponseWrapper<T> response = actor.recall("response");
        actor.should(seeThat(assertCOndition.getMessage(), actual -> assertCOndition.getPredicate().test(response)));
        return this;
    }

    public ActorClient<T> statusCodeShouldBe(int expectedCode) {
        ResponseWrapper<T> response = actor.recall("response");
        int statusCode = response.getStatusCode();
        actor.should(seeThat(String.format("Status expectedCode should be %s but was %s",
                expectedCode, statusCode), result -> statusCode == expectedCode));
        return this;
    }

    public ActorClient<T> responseTimeShouldBeWithInSecs(long expectedSeconds) {
        ResponseWrapper<T> response = actor.recall("response");
        long responseTime = response.getResponseTime();
        actor.should(seeThat(String.format("Response time should be within %s but was %s",
                expectedSeconds, responseTime / 1000), result -> responseTime < expectedSeconds * 1000));
        return this;
    }
}

