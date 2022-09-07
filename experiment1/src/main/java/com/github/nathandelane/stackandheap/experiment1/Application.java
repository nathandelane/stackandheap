package com.github.nathandelane.stackandheap.experiment1;

import java.util.ArrayList;
import java.util.List;

public class Application {

  private List<Integer> multipleOfThreeOrFive;

  private Application(final int maximumNum) {
    multipleOfThreeOrFive = new ArrayList<>(maximumNum / 2);
  }

  public boolean isMultipleOfThree(final int num) {
    return num % 3 == 0;
  }

  public boolean isMultipleOfFive(final int num) {
    return num % 5 == 0;
  }

  public void findAllMultiplesOfThreeAndFive(final int maximumNum) {
    for (int num  = 1; num <= maximumNum; num++) {
      if (isMultipleOfThree(num) || isMultipleOfFive(num))
        multipleOfThreeOrFive.add(num);
    }
  }

  public static void main(final String args[]) throws Exception {
    final int maximumNum = Integer.parseInt(args[0]);
    final Application application = new Application(maximumNum);
    application.findAllMultiplesOfThreeAndFive(maximumNum);

    System.out.format("All numbers between 1 and %d that are multiples of 3 or 5:%n%s%n", maximumNum, application.multipleOfThreeOrFive);

//    System.out.println("Press any key to continue...");
//    System.in.read();
  }

}
