package condorcet.Utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import condorcet.Enums.ResponseStatus;
import condorcet.Models.Entities.*;
import condorcet.Models.TCP.Request;
import condorcet.Models.TCP.Response;
import condorcet.Services.*;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private Request request;
    private Response response;
    private Gson gson;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private UserService userService = new UserService();
    private PersonService personService = new PersonService();
    private ClientService clientService =new ClientService();
    private SupplierService supplierService =new SupplierService();
    private ProductService productService =new ProductService();
    private ReportService reportService = new ReportService();
    private ReviewService reviewService = new ReviewService();
    private  OrderService orderService = new OrderService();
    private FinalOrderService finalOrderService = new FinalOrderService();


    public ClientThread(Socket clientSocket) throws IOException{
        response = new Response();
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new Gson();
        in = new ObjectInputStream(clientSocket.getInputStream());
        out = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try{

            while (clientSocket.isConnected()){
                String message = in.readObject().toString();
                request = gson.fromJson(message, Request.class);
                switch (request.getRequestType()){
                    case LOGINADMIN:{
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        if (userService.findAllEntities().stream().anyMatch(x -> (x.getRole().toLowerCase().equals(requestUser.getRole()))) &&
                                userService.findAllEntities().stream().anyMatch(x -> (x.getLogin().toLowerCase().equals(requestUser.getLogin()))) &&
                                userService.findAllEntities().stream().anyMatch(x -> (x.getPassword().toLowerCase().equals(requestUser.getPassword())))){
                            User user = userService.findAllEntities().stream().filter(x -> (x.getLogin().toLowerCase().equals(requestUser.getLogin()))).collect(Collectors.toList()).get(0);

                            System.out.println("Успешный вход");
                            Type type = new TypeToken<User>(){}.getType();
                            Response response = new Response(ResponseStatus.OK, gson.toJson(user, type));
                            out.writeObject(gson.toJson(response));

                        }else {
                            System.out.println("Данные не совпадают");
                            response = new Response(ResponseStatus.ERROR, ("Данные не совпадают"));
                            out.writeObject(new Gson().toJson(response));

                        }
                        break;
                    }

                    case ADMINMENUACCOUNTSADMINS:{
                        switch (request.getRequestCRUD()){
                            case ADD: {
                                User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                                if (userService.findAllEntities().stream().anyMatch(x -> (x.getLogin().toLowerCase().equals(requestUser.getLogin()))) &&
                                        personService.findAllEntities().stream().anyMatch(x -> (x.getName().toLowerCase().equals(requestUser.getPerson().getName()))) &&
                                        personService.findAllEntities().stream().anyMatch(x -> (x.getSurname().toLowerCase().equals(requestUser.getPerson().getSurname())))
                                ) {
                                    System.out.println("Такой логин существует");
                                    response = new Response(ResponseStatus.ERROR, ("Такой логин существует"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {

                                    personService.saveEntity(requestUser.getPerson());
                                    userService.saveEntity(requestUser);

                                    System.out.println("Сохранение успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));

                                }
                                break;
                            }
                            case SHOWALL:{
                                List<User> list = userService.findAllEntities();
                                Type type = new TypeToken<List<User>>(){}.getType();
                                out.writeObject(gson.toJson(list, type));
                                break;
                            }
                            case DELETE:{
                                User user = userService.findEntity(Integer.parseInt(request.getRequestMessage()));
                                if(user.getRole().equals("admin")) {
                                    userService.deleteEntity(user);
                                    personService.deleteEntity(personService.findEntity((int)user.getPerson().getPerson_id()));
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                else {
                                    response = new Response(ResponseStatus.ERROR, ("ID не админа"));
                                    out.writeObject(new Gson().toJson(response));
                                }

                                break;
                            }

                            case UPDATE:{
                                User requestuser = gson.fromJson(request.getRequestMessage(), User.class);
                                if (userService.findAllEntities().stream().anyMatch(x -> (x.getId() == requestuser.getId()))
                                ) {
                                    requestuser.getPerson().setPerson_id((int)userService.findEntity(requestuser.getId()).getPerson().getPerson_id());
                                    userService.updateEntity(requestuser);
                                    System.out.println("Обновление успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {
                                    System.out.println("Ошибка обновления товара (нету такого ID)");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                break;
                            }
                            case SEARCHBYDATA:{
                                User user = userService.findEntity(Integer.parseInt(request.getRequestMessage()));
                                Type type = new TypeToken<User>(){}.getType();
                                out.writeObject(gson.toJson(user, type));
                                break;
                            }
                        }

                        break;
                    }

                    case ADMINMENUACCOUNTSACCOUNTANTS:{
                        switch (request.getRequestCRUD()){
                            case ADD: {
                                User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                                if (userService.findAllEntities().stream().anyMatch(x -> (x.getLogin().toLowerCase().equals(requestUser.getLogin()))) &&
                                        personService.findAllEntities().stream().anyMatch(x -> (x.getName().toLowerCase().equals(requestUser.getPerson().getName()))) &&
                                        personService.findAllEntities().stream().anyMatch(x -> (x.getSurname().toLowerCase().equals(requestUser.getPerson().getSurname())))
                                ) {
                                    System.out.println("Такой логин существует");
                                    response = new Response(ResponseStatus.ERROR, ("Такой логин существует"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {

                                    personService.saveEntity(requestUser.getPerson());
                                    userService.saveEntity(requestUser);

                                    System.out.println("Сохранение успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));

                                }
                                break;
                            }
                            case SHOWALL:{
                                List<User> list = userService.findAllEntities();
                                Type type = new TypeToken<List<User>>(){}.getType();
                                out.writeObject(gson.toJson(list, type));
                                break;
                            }

                            case DELETE:{
                                User user = userService.findEntity(Integer.parseInt(request.getRequestMessage()));
                                if(user.getRole().equals("accountant")) {
                                    userService.deleteEntity(user);
                                    personService.deleteEntity(personService.findEntity((int)user.getPerson().getPerson_id()));
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                else {
                                    response = new Response(ResponseStatus.ERROR, ("ID не не бухгалтера"));
                                    out.writeObject(new Gson().toJson(response));
                                }

                                break;

                            }

                            case UPDATE:{
                                User requestuser = gson.fromJson(request.getRequestMessage(), User.class);
                                if (userService.findAllEntities().stream().anyMatch(x -> (x.getId() == requestuser.getId()))
                                ) {
                                    requestuser.getPerson().setPerson_id((int)userService.findEntity(requestuser.getId()).getPerson().getPerson_id());
                                    userService.updateEntity(requestuser);
                                    System.out.println("Обновление успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {
                                    System.out.println("Ошибка обновления товара (нету такого ID)");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                break;
                            }
                        }

                        break;
                    }

                    case ADMINMENUACCOUNTSCLIENTS:{
                        switch (request.getRequestCRUD()){
                            case ADD: {
                                Client requestclient = gson.fromJson(request.getRequestMessage(), Client.class);
                                if (clientService.findAllEntities().stream().anyMatch(x -> (x.getLogin().toLowerCase().equals(requestclient.getLogin()))) &&
                                        clientService.findAllEntities().stream().anyMatch(x -> (x.getEmail().toLowerCase().equals(requestclient.getEmail()))) &&
                                        clientService.findAllEntities().stream().anyMatch(x -> (x.getPhonenumber().toLowerCase().equals(requestclient.getPhonenumber())))
                                ) {
                                    System.out.println("Ошибка создания клиента");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {

                                    personService.saveEntity(requestclient.getPerson());
                                    clientService.saveEntity(requestclient);

                                    System.out.println("Сохранение успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));

                                }
                                break;
                            }
                            case SHOWALL:{
                                List<Client> list = clientService.findAllEntities();
                                Type type = new TypeToken<List<Client>>(){}.getType();
                                out.writeObject(gson.toJson(list, type));
                                break;
                            }

                            case UPDATE:{
                                Client requestclient = gson.fromJson(request.getRequestMessage(), Client.class);
                                if (clientService.findAllEntities().stream().anyMatch(x -> (x.getId() == requestclient.getId()))
                                ) {
                                    requestclient.getPerson().setPerson_id((int)clientService.findEntity(requestclient.getId()).getPerson().getPerson_id());

                                    clientService.updateEntity(requestclient);
                                    System.out.println("Обновление успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {
                                    System.out.println("Ошибка обновления клиента (нету такого ID)");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                break;
                            }

                            case DELETE:{
                                Client client = clientService.findEntity(Integer.parseInt(request.getRequestMessage()));

                                clientService.deleteEntity(client);
                                personService.deleteEntity(personService.findEntity((int)client.getPerson().getPerson_id()));
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));

                                break;
                            }
                        }


                        break;
                    }

                    case ADMINMENUSUPPLIERS:{
                        switch (request.getRequestCRUD()){
                            case ADD: {
                                Supplier requestsupplier = gson.fromJson(request.getRequestMessage(), Supplier.class);
                                if (supplierService.findAllEntities().stream().anyMatch(x -> (x.getName().toLowerCase().equals(requestsupplier.getName()))) &&
                                        supplierService.findAllEntities().stream().anyMatch(x -> (x.getAddress().toLowerCase().equals(requestsupplier.getAddress()))) &&
                                        supplierService.findAllEntities().stream().anyMatch(x -> (x.getPhone().toLowerCase().equals(requestsupplier.getPhone())))
                                ) {
                                    System.out.println("Ошибка создания поставщика");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {


                                    supplierService.saveEntity(requestsupplier);

                                    System.out.println("Сохранение успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));

                                }
                                break;
                            }
                            case SHOWALL:{
                                List<Supplier> list = supplierService.findAllEntities();
                                Type type = new TypeToken<List<Supplier>>(){}.getType();
                                out.writeObject(gson.toJson(list, type));
                                break;
                            }

                            case UPDATE:{
                                Supplier requestsupplier = gson.fromJson(request.getRequestMessage(), Supplier.class);
                                if (supplierService.findAllEntities().stream().anyMatch(x -> (x.getId() == requestsupplier.getId()))
                                ) {
                                    supplierService.updateEntity(requestsupplier);
                                    System.out.println("Обновление успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {
                                    System.out.println("Ошибка обновления поставщика (нету такого ID)");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                }


                                break;
                            }

                            case DELETE:{
                                Supplier supplier = supplierService.findEntity(Integer.parseInt(request.getRequestMessage()));

                                supplierService.deleteEntity(supplier);
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));

                                break;
                            }
                        }

                        break;
                    }

                    case ADMINMENUPRODUCTS:{
                        switch (request.getRequestCRUD()){
                            case ADD: {
                                Product requestproduct = gson.fromJson(request.getRequestMessage(), Product.class);
                                if (productService.findAllEntities().stream().anyMatch(x -> (x.getName().toLowerCase().equals(requestproduct.getName())))
                                ) {
                                    System.out.println("Ошибка создания товар");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {

                                    //requestproduct.getSupplier().setId((int)productService.findEntity(requestproduct.getId()).getSupplier().getId());

                                    productService.saveEntity(requestproduct);

                                    System.out.println("Сохранение успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));

                                }
                                break;
                            }
                            case SHOWALL:{
                                List<Product> list = productService.findAllEntities();
                                Type type = new TypeToken<List<Product>>(){}.getType();
                                out.writeObject(gson.toJson(list, type));
                                break;
                            }

                            case UPDATE:{
                                Product requestproduct = gson.fromJson(request.getRequestMessage(), Product.class);
                                if (productService.findAllEntities().stream().anyMatch(x -> (x.getId() == requestproduct.getId()))
                                ) {
                                    requestproduct.getSupplier().setId(productService.findEntity(requestproduct.getId()).getSupplier().getId());
                                    productService.updateEntity(requestproduct);
                                    System.out.println("Обновление успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {
                                    System.out.println("Ошибка обновления товара (нету такого ID)");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                break;
                            }

                            case DELETE:{
                                Product product = productService.findEntity(Integer.parseInt(request.getRequestMessage()));

                                productService.deleteEntity(product);
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));

                                break;
                            }
                        }

                        break;
                    }

                    case ADMINMENUREPORTS:{
                        switch (request.getRequestCRUD()){
                            case ADD: {
                                Report requestreport = gson.fromJson(request.getRequestMessage(), Report.class);
                                reportService.saveEntity(requestreport);
                                System.out.println("Сохранение успешно произошло");
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));
                                break;
                            }
                            case SHOWALL:{
                                List<Report> list = reportService.findAllEntities();
                                Type type = new TypeToken<List<Report>>(){}.getType();
                                out.writeObject(gson.toJson(list, type));
                                break;
                            }

                            case UPDATE:{
                                Report requestreport = gson.fromJson(request.getRequestMessage(), Report.class);
                                if (reportService.findAllEntities().stream().anyMatch(x -> (x.getReport_id() == requestreport.getReport_id()))
                                ) {
                                    requestreport.getUser().setId(reportService.findEntity(requestreport.getReport_id()).getUser().getId());
                                    reportService.updateEntity(requestreport);
                                    System.out.println("Обновление успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {
                                    System.out.println("Ошибка обновления товара (нету такого ID)");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                break;
                            }

                            case DELETE:{
                                Report report = reportService.findEntity(Integer.parseInt(request.getRequestMessage()));

                                reportService.deleteEntity(report);
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));

                                break;
                            }
                        }



                        break;
                    }

                    case ADMINMENUREVIEWS:{
                        switch (request.getRequestCRUD()){
                            case ADD: {
                                Review requestreview = gson.fromJson(request.getRequestMessage(), Review.class);
                                reviewService.saveEntity(requestreview);
                                System.out.println("Сохранение успешно произошло");
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));
                                break;
                            }
                            case SHOWALL:{
                                List<Review> list = reviewService.findAllEntities();
                                Type type = new TypeToken<List<Review>>(){}.getType();
                                out.writeObject(gson.toJson(list, type));
                                break;
                            }

                            case UPDATE:{
                                Review requestreview = gson.fromJson(request.getRequestMessage(), Review.class);
                                if (reviewService.findAllEntities().stream().anyMatch(x -> (x.getId() == requestreview.getId()))
                                ) {
                                    requestreview.getClient().setId(reviewService.findEntity(requestreview.getId()).getClient().getId());
                                    requestreview.getProduct().setId(reviewService.findEntity(requestreview.getId()).getProduct().getId());
                                    reviewService.updateEntity(requestreview);
                                    System.out.println("Обновление успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {
                                    System.out.println("Ошибка обновления товара (нету такого ID)");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                break;
                            }

                            case DELETE:{
                                Review review = reviewService.findEntity(Integer.parseInt(request.getRequestMessage()));

                                reviewService.deleteEntity(review);
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));

                                break;
                            }
                        }
                        break;
                    }

                    case ADMINMENUORDERS:{
                        switch (request.getRequestCRUD()){
                            case ADD: {
                                Order requestedorder = gson.fromJson(request.getRequestMessage(), Order.class);
                                orderService.saveEntity(requestedorder);
                                System.out.println("Сохранение успешно произошло");
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));
                                break;
                            }
                            case SHOWALL:{
                                List<Order> list = orderService.findAllEntities();
                                Type type = new TypeToken<List<Order>>(){}.getType();
                                out.writeObject(gson.toJson(list, type));
                                break;
                            }

                            case UPDATE:{
                                Order requestedorder = gson.fromJson(request.getRequestMessage(), Order.class);
                                if (reviewService.findAllEntities().stream().anyMatch(x -> (x.getId() == requestedorder.getId()))
                                ) {
                                    requestedorder.getClient().setId(orderService.findEntity(requestedorder.getId()).getClient().getId());
                                    //requestedorder.getProduct().setId(orderService.findEntity(requestedorder.getId()).getClient().getId());
                                    orderService.updateEntity(requestedorder);
                                    System.out.println("Обновление успешно произошло");
                                    response = new Response(ResponseStatus.OK, ("OK"));
                                    out.writeObject(new Gson().toJson(response));
                                } else {
                                    System.out.println("Ошибка обновления товара (нету такого ID)");
                                    response = new Response(ResponseStatus.ERROR, ("Ошибка"));
                                    out.writeObject(new Gson().toJson(response));
                                }
                                break;
                            }

                            case DELETE:{
                                Order order = orderService.findEntity(Integer.parseInt(request.getRequestMessage()));

                                orderService.deleteEntity(order);
                                response = new Response(ResponseStatus.OK, ("OK"));
                                out.writeObject(new Gson().toJson(response));

                                break;
                            }
                        }
                        break;
                    }

                    case CLIENTLOGIN:{
                        Client requestclient = gson.fromJson(request.getRequestMessage(), Client.class);
                        if (clientService.findAllEntities().stream().anyMatch(x -> (x.getLogin().toLowerCase().equals(requestclient.getLogin()))) &&
                                clientService.findAllEntities().stream().anyMatch(x -> (x.getPassword().toLowerCase().equals(requestclient.getPassword())))){
                            Client client = clientService.findAllEntities().stream().filter(x -> (x.getLogin().toLowerCase().equals(requestclient.getLogin()))).collect(Collectors.toList()).get(0);

                            System.out.println("Успешный вход");
                            Type type = new TypeToken<Client>(){}.getType();
                            Response response = new Response(ResponseStatus.OK, gson.toJson(client, type));
                            out.writeObject(gson.toJson(response));

                        }else {
                            System.out.println("Данные не совпадают");
                            response = new Response(ResponseStatus.ERROR, ("Данные не совпадают"));
                            out.writeObject(new Gson().toJson(response));

                        }
                        break;
                    }

                    case CLIENTORDER:{


                        SessionData sessionData = gson.fromJson(request.getRequestMessage(), SessionData.class);
                        FinalOrder finalOrder = new FinalOrder();
                        finalOrder.setTotalPrice(-1);
                        finalOrderService.saveEntity(finalOrder);
                        List<FinalOrder> list = finalOrderService.findAllEntities();
                        finalOrder = list.get(list.size()-1);
                        List<Order> orderList = new ArrayList<>();
                        orderService.findAllEntities();
                        float finalprice = 0;
                        for (Product product: sessionData.getProductList()
                             ) {
                            Order order = new Order();
                            order.setFinalOrder(finalOrder);
                            order.setProduct(product);
                            order.setClient(sessionData.getClient());
                            order.getFinalOrder().setDate(LocalDate.now().toString());
                            int delprice =0;

                            File file = new File("D:\\!Учеба\\КП (код)\\Server2\\src\\main\\java\\condorcet\\Utility\\DeliveryPrice.txt");
                            Scanner fileReader = new Scanner(file);
                            String data = fileReader.nextLine();
                            delprice = Integer.parseInt(data);
                            fileReader.close();
                            System.out.println(data);
                            System.out.println(delprice);
                            order.getFinalOrder().setDeliveryprice(delprice);


                            orderService.saveEntity(order);
                            int buf =productService.findEntity(product.getId()).getAmount();
                            buf--;
                            product.setAmount(buf);
                            productService.updateEntity(product);
                            orderList.add(order);
                            finalprice += product.getPrice() + order.getFinalOrder().getDeliveryprice();

                        }

                        finalOrder.setTotalPrice(finalprice);
                        finalOrderService.updateEntity(finalOrder);
                        response = new Response(ResponseStatus.OK, ("OK"));
                        out.writeObject(new Gson().toJson(response));

                        break;
                    }

                    case CLIENTREVIEW:{
                        Review review = gson.fromJson(request.getRequestMessage(), Review.class);
                        Review revForUpdate = new Review();
                        if (reviewService.findAllEntities().stream().anyMatch(x -> (x.getClient().getId() == review.getClient().getId() )) &&
                                reviewService.findAllEntities().stream().anyMatch(x -> (x.getProduct().getId() == review.getProduct().getId()))
                        ) {
                            List<Review> reviewList = reviewService.findAllEntities();
                            for (Review rev:
                                 reviewList ) {
                                if(
                                        (rev.getProduct().getId() ==  review.getProduct().getId()) &&
                                                (rev.getClient().getId() ==  review.getClient().getId())
                                )
                                {
                                    revForUpdate = rev;
                                    revForUpdate.setRate(review.getRate());
                                    revForUpdate.setComment(review.getComment());
                                }
                            }

                            reviewService.updateEntity(revForUpdate);

                            System.out.println("Обзор обновился");
                            response = new Response(ResponseStatus.OK, ("OK"));
                            out.writeObject(new Gson().toJson(response));
                        } else {

                            reviewService.saveEntity(review);

                            System.out.println("Сохранение успешно произошло");
                            response = new Response(ResponseStatus.OK, ("OK"));
                            out.writeObject(new Gson().toJson(response));
                        }


                        break;
                    }

                    case ACCOUNTANTLOGIN:{
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        if (userService.findAllEntities().stream().anyMatch(x -> (x.getRole().toLowerCase().equals(requestUser.getRole()))) &&
                                userService.findAllEntities().stream().anyMatch(x -> (x.getLogin().toLowerCase().equals(requestUser.getLogin()))) &&
                                userService.findAllEntities().stream().anyMatch(x -> (x.getPassword().toLowerCase().equals(requestUser.getPassword())))){
                            User user = userService.findAllEntities().stream().filter(x -> (x.getLogin().toLowerCase().equals(requestUser.getLogin()))).collect(Collectors.toList()).get(0);

                            System.out.println("Успешный вход");
                            Type type = new TypeToken<User>(){}.getType();
                            Response response = new Response(ResponseStatus.OK, gson.toJson(user, type));
                            out.writeObject(gson.toJson(response));

                        }else {
                            System.out.println("Данные не совпадают");
                            response = new Response(ResponseStatus.ERROR, ("Данные не совпадают"));
                            out.writeObject(new Gson().toJson(response));

                        }

                        break;
                    }

                    case ACCOUNTANTVIEWFINALORDERS:{
                        List<FinalOrder> list = finalOrderService.findAllEntities();
                        Type type = new TypeToken<List<FinalOrder>>(){}.getType();
                        out.writeObject(gson.toJson(list, type));
                        break;
                    }

                    case ACCOUNTANTVIEWORDERS:{
                        List<Order> list = orderService.findAllEntities();
                        Type type = new TypeToken<List<Order>>(){}.getType();
                        out.writeObject(gson.toJson(list, type));

                        break;
                    }

                    case DELIVERYPRICE:{
                        int delprice = Integer.parseInt( request.getRequestMessage());

                        File file = new File("D:\\!Учеба\\КП (код)\\Server2\\src\\main\\java\\condorcet\\Utility\\DeliveryPrice.txt");
                        Scanner fileReader = new Scanner(file);
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(Integer.toString(delprice));
                        fileWriter.close();
                        System.out.println("Обзор обновился");
                        response = new Response(ResponseStatus.OK, ("OK"));
                        out.writeObject(new Gson().toJson(response));
                        LocalDate localDate = LocalDate.of(2000, 12, 23);

                        break;
                    }




                    //Старый логин подлежит удалению
                    case LOGIN: {
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        if (userService.findAllEntities().stream().anyMatch(x -> (x.getLogin().toLowerCase().equals(requestUser.getLogin()))) && userService.findAllEntities().stream().anyMatch(x -> (x.getPassword().toLowerCase().equals(requestUser.getPassword())))){
                            User user = userService.findAllEntities().stream().filter(x -> (x.getLogin().toLowerCase().equals(requestUser.getLogin()))).collect(Collectors.toList()).get(0);
                            user = userService.findEntity(user.getId());
                            System.out.println("Успешный вход");
                            response = new Response(ResponseStatus.OK, ("Успешный вход"));
                            out.writeObject(new Gson().toJson(response));
                        }else {
                            System.out.println("Данные не совпадают");
                            response = new Response(ResponseStatus.ERROR, ("Данные не совпадают"));
                            out.writeObject(new Gson().toJson(response));

                        }
                        break;
                    }
                    case REGISTER: {
                        Client requestclient = gson.fromJson(request.getRequestMessage(), Client.class);
                        if (clientService.findAllEntities().stream().anyMatch(x -> (x.getLogin().toLowerCase().equals(requestclient.getLogin()))) &&
                                clientService.findAllEntities().stream().anyMatch(x -> (x.getPerson().getName().toLowerCase().equals(requestclient.getPerson().getName()))) &&
                        clientService.findAllEntities().stream().anyMatch(x -> (x.getPerson().getSurname().toLowerCase().equals(requestclient.getPerson().getSurname())))

                        ){
                            System.out.println("Пользователь существует");
                            response = new Response(ResponseStatus.ERROR, ("Такой пользователь существует"));
                            out.writeObject(new Gson().toJson(response));
                        }else {
                            personService.saveEntity(requestclient.getPerson());
                            clientService.saveEntity(requestclient);

                            System.out.println("Сохранение успешно произошло");
                            response = new Response(ResponseStatus.OK, ("OK"));
                            out.writeObject(new Gson().toJson(response));

                        }
                        break;
                    }
                    default:{
                        System.out.println("НЕ пришло");

                    }
               }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
