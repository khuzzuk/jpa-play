package pl.silvermedia.jpaplay.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Component
@Scope("prototype")
public class Emitter<E> extends Mono<E>
{
   private CoreSubscriber<? super E> actual;

   @Override
   public void subscribe(CoreSubscriber<? super E> actual)
   {
      this.actual = actual;
   }

   public void provide(E content)
   {
      actual.onNext(content);
      actual.onComplete();
   }
}
