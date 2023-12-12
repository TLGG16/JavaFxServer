import condorcet.Models.Entities.Order;
import condorcet.Models.Entities.User;
import condorcet.Services.OrderService;
import condorcet.Services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class TestOrder {

    @Test
    public void TestOrderFindAll() throws SQLException, ClassNotFoundException {

        OrderService orderService = new OrderService();
        List<Order> orderList = new ArrayList<Order>();
        orderList = orderService.findAllEntities();
        Assertions.assertFalse(orderList.isEmpty());

    }

    @Test
    public void TestLogin() throws SQLException, ClassNotFoundException {

        User user = new User();
        user.setLogin("root");
        user.setPassword("root");
        Assertions.assertTrue( new UserService().findAllEntities().stream().anyMatch(x->(x.getPassword().equals(user.getPassword()) && x.getLogin().equals(user.getLogin()))));
    }

    @Test
    public void TestDao() throws SQLException, ClassNotFoundException{
        User user = new User();
        user.setLogin("root2");
        user.setPassword("root2");
        boolean flag = false;
        UserService userService = new UserService();
        List<User> userList = userService.findAllEntities();
        for (User u: userList
             ) {
            if(u.getLogin().equals("root2") && u.getPassword().equals("root2")){
               user.setId(u.getId());
               flag = true;
            }
        }
        if (flag==false){
            userService.saveEntity(user);
            userService.deleteEntity(user);
            flag=true;
        }
        else {
            userService.deleteEntity(user);
        }
        Assertions.assertTrue(flag);


    }

}

