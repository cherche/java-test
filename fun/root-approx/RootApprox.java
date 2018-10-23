public class RootApprox {
  private static double EPSILON = 0.000001;
  // Thanks Newton
  private static double x;

  private static double function(double x) {
    // LaTeX:
    // f(x) = cos^3(x) - sin(x)
    return Math.pow(Math.cos(x), 3) - Math.sin(x);
    //   It should be noted that this function has the symmetry
    //         f(x + pi) = -f(x)
    // and is periodic with period 2pi
    //         f(x + 2pi) = f(x).
    //   In other words, finding all zeroes on the interval (0, pi)
    // means that essentially all of the zeroes have been found.
    //   More interestingly, it can be shown that there is only one
    // zero on this interval, and that it actually lies within a
    // smaller interval (pi/6, pi/4).
  }

  // This obviously isn't mathematically accurate, but a numerical
  // approximation of the derivative is good enough for our purposes
  private static double derivative(double x) {
    return (function(x + EPSILON) - function(x)) / EPSILON;
  }

  /*
  private static double derivative(double x) {
    // LaTeX:
    // f'(x) = -cos(x)(frac{3}{2}sin(2x) + 1)
    return -Math.cos(x) * ((3 / 2) * Math.sin(2 * x) + 1);
  }
  */

  public static void main(String[] args) {
    x = IBIO.inputDouble("Initial Guess: ");

    do {
      // LaTeX:
      // x_{n + 1} = x_n - frac{f(x_n)}{f'(x_n)}
      x = x - (function(x) / derivative(x));
      IBIO.output("x = " + x + ", f(" + x + ") = " + function(x));
    } while (IBIO.inputChar("Continue? (y/N) ") == 'y');
  }
}
