//package com.example.tacocloud.repositories;
//
//
//import com.example.tacocloud.dto.Ingredient;
//import com.example.tacocloud.dto.IngredientRef;
//import com.example.tacocloud.dto.Taco;
//import com.example.tacocloud.dto.TacoOrder;
//import org.springframework.asm.Type;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class JdbcOrderRepository implements OrderRepository {
//
//    private JdbcOperations jdbcOperations;
//
//    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
//        this.jdbcOperations = jdbcOperations;
//    }
//
//    @Override
//    @Transactional
//    public TacoOrder save(TacoOrder order) {
//        PreparedStatementCreatorFactory pscf =
//                new PreparedStatementCreatorFactory(
//                        "insert into Taco_Order "
//                                + "(delivery_name, delivery_street, delivery_city, "
//                                + "delivery_state, delivery_zip, cc_number, "
//                                + "cc_expiration, cc_cvv, placed_at) "
//                                + "values (?,?,?,?,?,?,?,?,?)",
//                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
//                );
//        pscf.setReturnGeneratedKeys(true);
//        order.setPlacedAt(new Date());
//        PreparedStatementCreator psc =
//                pscf.newPreparedStatementCreator(
//                        Arrays.asList(
//                                order.getDeliveryName(),
//                                order.getDeliveryStreet(),
//                                order.getDeliveryCity(),
//                                order.getDeliveryState(),
//                                order.getDeliveryZip(),
//                                order.getCcNumber(),
//                                order.getCcExpiration(),
//                                order.getCcCVV(),
//                                order.getPlacedAt()));
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        long orderId = keyHolder.getKey().longValue();
//        order.setId(orderId);
//        List<Taco> tacos = order.getTacos();
//        int i=0;
//        for (Taco taco : tacos) {
//            saveTaco(orderId, i++, taco);
//        }
//        return order;
//    }
//
//    private IngredientRef toIngredientRef(Ingredient ingredient) {
//        return new IngredientRef(ingredient.getId()); // предполагая, что IngredientRef имеет конструктор, принимающий ID
//    }
//
//    private long saveTaco(Long orderId, int orderKey, Taco taco) {
//        taco.setCreatedAt(new Date());
//        PreparedStatementCreatorFactory pscf =
//                new PreparedStatementCreatorFactory(
//                        "insert into Taco "
//                                + "(name, created_at, taco_order, taco_order_key) "
//                                + "values (?, ?, ?, ?)",
//                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
//                );
//        pscf.setReturnGeneratedKeys(true);
//        PreparedStatementCreator psc =
//                pscf.newPreparedStatementCreator(
//                        Arrays.asList(
//                                taco.getName(),
//                                taco.getCreatedAt(),
//                                orderId,
//                                orderKey));
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcOperations.update(psc, keyHolder);
//        long tacoId = keyHolder.getKey().longValue();
//        taco.setId(tacoId);
//
//        // Преобразуем список Ingredient в список IngredientRef
//        List<IngredientRef> ingredientRefs = taco.getIngredients().stream()
//                .map(this::toIngredientRef)
//                .collect(Collectors.toList());
//
//        saveIngredientRefs(tacoId, ingredientRefs); // Вызываем метод с преобразованным списком
//        return tacoId;
//    }
//
//    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) {
//        int key = 0;
//        for (IngredientRef ingredientRef : ingredientRefs) {
//            jdbcOperations.update(
//                    "insert into Ingredient_Ref (ingredient, taco, taco_key) "
//                            + "values (?, ?, ?)",
//                    ingredientRef.getIngredient(), tacoId, key++);
//        }
//    }
//
//}