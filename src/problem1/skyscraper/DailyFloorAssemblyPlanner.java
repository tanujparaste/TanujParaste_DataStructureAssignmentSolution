package problem1.skyscraper;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DailyFloorAssemblyPlanner {
	private static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Enter the total no of floors in the building");
		int numFloors = 1; // at least 1 floor is present
		Queue<Integer> dailyFloorSizes = null;
		boolean isValidSize = true;
		try {
			numFloors = in.nextInt();
			if (numFloors <= 0) {
				throw new Exception("Not valid number of floors, exiting...");
			}

			dailyFloorSizes = new LinkedList<>();

			for (int day = 1; day <= numFloors; day++) {
				System.out.println("Enter the floor size given on day:" + day);

				int factorySize = in.nextInt();

				if (factorySize <= 0) {
					throw new Exception("Invalid floor size " + factorySize + "! Size can't be less than 1");
				} else if (factorySize > numFloors) {
					throw new Exception("Invalid floor size " + factorySize + "! Size can't exceed " + numFloors);
				}

				dailyFloorSizes.offer(factorySize);

			}
		} catch (Exception ex) {
			isValidSize = false;
			System.out.println(ex.getMessage());
		}

		System.out.println();

		if (isValidSize) {
			// #floors = maxSize(floors)
			planDailyFloors(dailyFloorSizes, numFloors, numFloors);
		}

		in.close();
	}

	private static void planDailyFloors(Queue<Integer> floors, int maxSize, int totalFloors) {
		Stack<Integer> unusedFloors = new Stack<>();

		System.out.println("The order of construction is as follows	");

		for (int day = 1; day <= totalFloors; day++) {
			System.out.println("Day:" + day);
			int currentSize = floors.poll();
			if (currentSize == maxSize) {
				System.out.print(currentSize + " ");
				maxSize--;
				// also look for previous floors from the stock
				// make sure it is sorted in ascending order
				sort(unusedFloors);
				while (!unusedFloors.isEmpty()) {
					int eligibleSize = unusedFloors.pop();
					if (eligibleSize >= maxSize) {
						System.out.print(eligibleSize + " ");
						maxSize--;
					} else {
						unusedFloors.push(eligibleSize);
						break;
					}
				}
			} else {
				unusedFloors.push(currentSize);
			}

			System.out.println();
		}
	}

	private static void sort(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}

		int value = stack.pop();
		sort(stack);
		sort(stack, value);
	}

	private static void sort(Stack<Integer> stack, int data) {
		if (stack.isEmpty() || data > stack.peek()) {
			stack.push(data);
			return;
		}

		int temp = stack.pop();
		sort(stack, data);
		stack.push(temp);
	}
}
