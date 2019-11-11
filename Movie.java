/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Jo√£o Paulo
 */
public class Movie {
  public static final int  CHILDRENS = 2;
  public static final int  REGULAR = 0;
  public static final int  NEW_RELEASE = 1;

    private String _title;
    private Price _price;

   public Movie(String name, int priceCode) {
      _title = name;
      setPriceCode(priceCode);
   }
    
   public int getPriceCode() {
      return _price.getPriceCode();
   }
   
   public void setPriceCode(int arg) {
      switch (arg) {
         case REGULAR:
            _price = new RegularPrice();
            break;
         case CHILDRENS:
            _price = new ChildrensPrice();
            break;
         case NEW_RELEASE:
            _price = new NewReleasePrice();
            break;
         default:
            throw new IllegalArgumentException("Incorrect Price Code");
      }
   }

  public String getTitle (){
      return _title;
  };
  
  double getCharge(int daysRented) {
      return _price.getCharge(daysRented);
   }
  int getFrequentRenterPoints(int daysRented) {
         return _price.getFrequentRenterPoints(daysRented);
   }
  
}

    class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
      _movie = movie;
      _daysRented = daysRented;
    }
    public int getDaysRented() {
      return _daysRented;
    }
    public Movie getMovie() {
      return _movie;
    }
    
    double getCharge() { // veja que n„o precisa mais de par‚metro
     return _movie.getCharge(_daysRented);
    }
    
    int getFrequentRenterPoints() {
       return _movie.getFrequentRenterPoints(_daysRented);
   }
}

class Customer {
    private String _name;
   private Vector _rentals = new Vector();

   public Customer (String name){
      _name = name;
   };

   public void addRental(Rental arg) {
      _rentals.addElement(arg);
   }
   public String getName (){
      return _name;
   };
 private double getTotalCharge() {
       double result = 0;
       Enumeration rentals = _rentals.elements();
       while (rentals.hasMoreElements()) {
          Rental each = (Rental) rentals.nextElement();
          result += each.getCharge();
       }
       return result;
     }
 
 private int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
           Rental each = (Rental) rentals.nextElement();
           result += each.getFrequentRenterPoints();
        }
        return result;
     }
 
 public String statement() {
   Enumeration rentals = _rentals.elements();
   String result = "Rental Record for " + getName() + "\n";
   while (rentals.hasMoreElements()) {
      Rental each = (Rental) rentals.nextElement();

      // show figures for this rental
      result += "\t" + each.getMovie().getTitle()+ "\t" +
                String.valueOf(each.getCharge()) + "\n";
   }

   // add footer lines
   result +=  "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
   result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) +
                   " frequent renter points";
   return result;
}
 
public String htmlStatement() {
   Enumeration rentals = _rentals.elements();
   String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";
   while (rentals.hasMoreElements()) {
      Rental each = (Rental) rentals.nextElement();
      // show figures for each rental
      result += each.getMovie().getTitle()+ ": " +
                String.valueOf(each.getCharge()) + "<BR>\n";
   }
   
   // add footer lines
   result +=  "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
   result += "On this rental you earned <EM>" +
          String.valueOf(getTotalFrequentRenterPoints()) +
          "</EM> frequent renter points<P>";
   return result;
}

}

abstract class Price {
   abstract int getPriceCode();
   int getFrequentRenterPoints(int daysRented) {
       return 1;
   }
}
 
class ChildrensPrice extends Price {
   int getPriceCode() {
       return Movie.CHILDRENS;
   }
}
 
class NewReleasePrice extends Price {
   int getPriceCode() {
       return Movie.NEW_RELEASE;
   }
}
 
class RegularPrice extends Price {
   int getPriceCode() {
       return Movie.REGULAR;
   }
}
