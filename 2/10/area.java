// Fill in the methods at the bottom to make the program run correctly

public class area {
  public static void main(String args[]) {
    new area();
  }

  public area() {
    char more = 'y';

    while (more == 'Y' || more == 'y') {
      printmenu();
      int answer = IBIO.inputInt("Which object do you have? (1-5) ");
      double area = -1;

      switch (answer) {
        case 1:
          area = circle();
          break;
        case 2:
          area = rectangle();
          break;
        case 3:
          area = triangle();
          break;
        case 4:
          area = square();
          break;
        case 5:
          area = sphere();
          break;
      }

      if (area == -1) {
        System.out.println("Please select a number from 1 to 5.");
        System.out.println("");
        continue;
      } else {
        System.out.println("The area is " + area);
      }

      System.out.println("");
      more = IBIO.inputChar("Do you want to find the area of anything else? (y/n) ");
    } // while

    System.out.println("Goodbye!");
  } // constructor

  public void printmenu() {
    System.out.println("1. circle");
    System.out.println("2. rectangle");
    System.out.println("3. triangle");
    System.out.println("4. square");
    System.out.println("5. sphere");
  }

  public double circle() { // Code to get info needed and calculate area of a circle
    double r = IBIO.inputDouble("What is the radius of the circle? ");
    double area = Math.PI * r * r;
    // change the return line to return the area you calculated
    return area;
  } // circle

  public double rectangle() { // Code to get info needed and calculate area of a rectangle
    double length = IBIO.inputDouble("What is the length? ");
    double width = IBIO.inputDouble("What is the width? ");
    double area = length * width;
    // change the return line to return the area you calculated
    return area;
  } // rectangle

  public double triangle() { // Code to get info needed and calculate area of a triangle
    double base = IBIO.inputDouble("What is the base? ");
    double height = IBIO.inputDouble("What is the height? ");
    double area = 0.5 * base * height;
    // change the return line to return the area you calculated
    return area;
  } // triangle

  public double square() { // Code to get info needed and calculate area of a square
    double side = IBIO.inputDouble("What is the side length? ");
    double area = side * side;
    // change the return line to return the area you calculated
    return area;
  } // square

  public double sphere() { // Code to get info needed and calculate surface area of a sphere
    double r = IBIO.inputDouble("What is the radius? ");
    double area = 4 * Math.PI * r * r;
    // change the return line to return the surface area you calculated
    return area;
  } // sphere
} // class
