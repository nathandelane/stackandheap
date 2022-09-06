package com.github.nathandelane.stackandheap.experiment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {

  private boolean isMultipleOfThree(final int num) {
    return num % 3 == 0;
  }

  private boolean isMultipleOfFive(final int num) {
    return num % 5 == 0;
  }

  private static void waitForKeypress(final String message) {
    System.out.println(message);

    try {
      System.in.read();
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Integer> findAllMultiplesOfThreeAndFive(final int num) {
    final List<Integer> multiplesOfThreeAndFive = new ArrayList<>();

    if (num <= 0) {
      return multiplesOfThreeAndFive;
    }

    if (isMultipleOfThree(num) || isMultipleOfFive(num)) {
      multiplesOfThreeAndFive.add(num);
    }

    multiplesOfThreeAndFive.addAll(findAllMultiplesOfThreeAndFive(num - 1));

    return multiplesOfThreeAndFive;
  }

  public static void main(final String args[]) throws IOException {
    final int maximumNum = Integer.parseInt(args[0]);
    final Application application = new Application();
    final List<Integer> multipleOfThreeOrFive = application.findAllMultiplesOfThreeAndFive(maximumNum);

    System.out.format("All numbers between 1 and %d that are multiples of 3 or 5:%n%s%n", maximumNum, multipleOfThreeOrFive);

//    waitForKeypress("Press any key to continue...");
  }

}
