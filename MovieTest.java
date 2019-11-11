/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author João Paulo
 */
public class MovieTest {
    
    public MovieTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testStatement() {
        
        Customer customer_1 = new Customer("Bob");
        
        Movie movie_1 = new Movie("Titanic", 0);
        Movie movie_2 = new Movie("Star Wars", 1);
        Movie movie_3 = new Movie("Iron Man", 3);
        
        Rental rental_1 = new Rental(movie_1, 5);
        Rental rental_2 = new Rental(movie_2, 7);
        Rental rental_3 = new Rental(movie_3, 8);

        customer_1.addRental(rental_1);
        customer_1.addRental(rental_2);
        customer_1.addRental(rental_3);
        
        String test_result = "Rental Record for Bob\n";
        test_result += "\tTitanic\t6.5\n";
        test_result += "\tStar Wars\t21.0\n";
        test_result += "\tIron Man\t0.0\n";
        test_result += "Amount owed is 27.5\n";
        test_result += "You earned 4 frequent renter points";
        
        assertEquals(customer_1.statement(), test_result);
        
    }
    
}
