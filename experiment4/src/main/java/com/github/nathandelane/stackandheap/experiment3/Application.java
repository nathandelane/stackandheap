package com.github.nathandelane.stackandheap.experiment4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Application {

  private final int numberOfThreads;

  private final int partitionSize;

  private Application(final int numberOfThreads, final int partitionSize) {
    this.numberOfThreads = numberOfThreads;
    this.partitionSize = partitionSize;
  }

  public List<Integer> findAllMultiplesOfThreeAndFive(final int maximumNum) {
    final List<Integer> multiplesOfThreeAndFive = new ArrayList<>();
    final ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
    final List<Future<List<Integer>>> futures = new ArrayList<>();

    for (int n = 1; n <= maximumNum; n += partitionSize) {
      final int rangeStart = n;
      final int rangeEnd = (n + (partitionSize - 1));
      final Future<List<Integer>> future = executor.submit(new FindMultiples(rangeStart, rangeEnd));
      futures.add(future);
    }

    executor.shutdown();
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (final InterruptedException e) {
      throw new RuntimeException(e);
    }

    for (final Future<List<Integer>> f : futures) {
      try {
        multiplesOfThreeAndFive.addAll(f.get());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      }
    }

    return multiplesOfThreeAndFive;
  }

  private static void waitForKeypress(final String message) {
    System.out.println(message);

    try {
      System.in.read();
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(final String args[]) throws IOException {
    final int maximumNum = Integer.parseInt(args[0]);
    final Application application = new Application(10, 100);

    final List<Integer> multipleOfThreeOrFive = application.findAllMultiplesOfThreeAndFive(maximumNum);

    System.out.format("All numbers between 1 and %d that are multiples of 3 or 5:%n%s%n", maximumNum, multipleOfThreeOrFive);

//    waitForKeypress("Press any key to end...");
  }

  public static class FindMultiples implements Callable<List<Integer>> {

    private static boolean isMultipleOfThree(final int num) {
      return num % 3 == 0;
    }

    private static boolean isMultipleOfFive(final int num) {
      return num % 5 == 0;
    }

    private final int rangeStart;

    private final int rangeEnd;

    public FindMultiples(final int rangeStart, final int rangeEnd) {
      this.rangeStart = rangeStart;
      this.rangeEnd = rangeEnd;
    }

    @Override
    public List<Integer> call() throws Exception {
      final List<Integer> multiplesOfThreeAndFive = new ArrayList<>();

      for (int num = rangeStart; num <= rangeEnd; num++) {
        if (isMultipleOfThree(num) || isMultipleOfFive(num)) {
          multiplesOfThreeAndFive.add(num);
        }
      }

      return multiplesOfThreeAndFive;
    }
  }

}
