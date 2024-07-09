package com.example.tacocloud.repositories;

import com.example.tacocloud.dto.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>  {
    TacoOrder save(TacoOrder order);
    // эти методы можно явно не указывать
}

 /*
    Теперь у нас есть два репозитория. Вы могли бы подумать, что далее нам предстоит написать реализации их обоих, включая дюжину
методов, определяемых интерфейсом CrudRepository. Но спешу вас
обрадовать, Spring Data предоставляет готовые реализации этих методов! Когда приложение запускается, Spring Data автоматически
создает необходимые реализации на лету. Это означает, что репозитории немедленно готовы к  использованию. Просто внедрите их
в контроллеры, и все готово.
Более того, поскольку Spring Data автоматически создает реализации интерфейсов во время выполнения, вам больше не нужны явные
реализации в JdbcIngredientRepository и JdbcOrderRepository. Вы можете удалить эти два класса и навсегда забыть про них
     */