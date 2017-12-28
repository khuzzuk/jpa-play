package pl.silvermedia.jpaplay.service;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.silvermedia.jpaplay.db.Film;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Component
@Scope("prototype")
public class Emitter extends Mono<Collection<Film>>
{
   private CoreSubscriber<? super Collection<Film>> actual;

   @Override
   public void subscribe(CoreSubscriber<? super Collection<Film>> actual)
   {
      this.actual = actual;
   }

   void provide(Collection<Film> content)
   {
      actual.onNext(content);
      actual.onComplete();
   }
}
