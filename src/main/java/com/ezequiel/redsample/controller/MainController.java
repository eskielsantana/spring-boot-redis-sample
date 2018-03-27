package com.ezequiel.redsample.controller;


import com.ezequiel.redsample.entity.Person;
import com.ezequiel.redsample.manager.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequestMapping("api/v1")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private PersonRepository personRepository;

    public MainController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }


    @PostMapping("person/add/")
    public ResponseEntity<String> personAdd(@RequestBody Person person) {

        logger.info("Requested a Person Creation");
        personRepository.save(person);
        logger.info("The person was created successfully");
        return new ResponseEntity<>("The person was created successfully", HttpStatus.CREATED);
    }

    @PostMapping("person/update/")
    public ResponseEntity<String> personChange(@RequestBody Person person) {

        logger.info("Requested a Person Update");
        personRepository.update(person);
        logger.info("The person was update successfully");
        return new ResponseEntity<>("The person was update successfully", HttpStatus.CREATED);
    }

    @GetMapping("person/getAll")
    public ResponseEntity<List<Person>> getAllPeople() {

        logger.info("Requested the whole persons list");
        Map<String, Person> personMap = personRepository.findAll();
        logger.info("The persons were retrieved successfully");
        ArrayList result = new ArrayList(personMap.values());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("person/delete/{id}")
    public ResponseEntity<String> personDelete(@PathVariable Integer id) {
        logger.info("Requested a Person Delete");
        personRepository.delete(id);
        logger.info("The person was deleted successfully");
        return new ResponseEntity<>("The person was deleted successfully", HttpStatus.CREATED);
    }


//    @GetMapping("Order/{orderId}")
//    public ResponseEntity<OrderDto> getAllPeople(@RequestHeader String token, @PathVariable("orderId") Integer orderId) {
//
//        OrderDto orderDto;
//        logger.info("Requested a OrderDAO by Id -> Order/" + orderId);
//        try{
//            orderDto = orderBusinessLogicService.getAllPeople(orderId);
//        }catch (Exception e){
//            logger.info("Error during order requests by id process:");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        logger.info("The order request was done successfully");
//        return new ResponseEntity<>(orderDto, HttpStatus.OK);
//    }
//
//    @PostMapping("Orders")
//    public ResponseEntity<String> requestOrders(@RequestHeader String token, @RequestBody OrderListRestDto orderListRestDto) {
//
//        logger.info("Requested a multiple Orders Creation");
//        try{
//            orderBusinessLogicService.requestOrders(orderListRestDto.getOrderRestDtos());
//        }catch (Exception e){
//            e.printStackTrace();
//            logger.info("Error during order requests process: " + e.getMessage());
//            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        logger.info("The order list was requested successfully");
//        return new ResponseEntity<>("The order list was requested successfully", HttpStatus.CREATED);
//    }
}