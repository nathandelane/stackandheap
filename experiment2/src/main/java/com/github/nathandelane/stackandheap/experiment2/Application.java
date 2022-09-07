package com.github.nathandelane.stackandheap.experiment2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {

  private final List<Integer> multiplesOfThreeAndFive;

  private Application(final int maximumNum) {
    multiplesOfThreeAndFive = new ArrayList(maximumNum / 2);
  }

  public boolean isMultipleOfThree(final Integer num) {
    return num % 3 == 0;
  }

  public boolean isMultipleOfFive(final Integer num) {
    return num % 5 == 0;
  }

  public void findAllMultiplesOfThreeAndFive(final Integer maximumNum) {
    for (Integer num  = 1; num <= maximumNum; num++) {
      if (isMultipleOfThree(num) || isMultipleOfFive(num)) {
        multiplesOfThreeAndFive.add(num);
      }
    }

    System.out.format("Multiples of 3 and 5 from 1 to %d: %n%s", maximumNum, multiplesOfThreeAndFive);
    System.out.println();
  }

  public static void main(final String args[]) throws Exception {
    final int maximumNum = Integer.parseInt(args[0]);
    final Application application = new Application(maximumNum);
    application.findAllMultiplesOfThreeAndFive(maximumNum);

//    System.out.println("Press any key to continue...");
//    System.in.read();
  }

}
