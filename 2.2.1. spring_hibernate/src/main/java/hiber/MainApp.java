package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Toyota", 123);
        Car car2 = new Car("BMW", 456);
        Car car3 = new Car("Audi", 789);
        Car car4 = new Car("Mercedes", 1011);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car Model = " + user.getCar().getModel());
                System.out.println("Car Series = " + user.getCar().getSeries());
            }
            System.out.println();
        }


        String searchModel = "BMW";
        int searchSeries = 789;
        User user = userService.getUserByCar(searchModel, searchSeries);
        if (user != null) {
            System.out.println("Пользователь " + user.getFirstName() + " владеет машиной : " + searchModel );
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        } else {
            System.out.println("Не найден пользователь с моделью: " + searchModel + " серия авто: " + searchSeries);
        }
        context.close();

    }

}
