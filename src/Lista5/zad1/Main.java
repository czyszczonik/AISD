package Lista5.zad1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        PriorityQueue priorityQueue = new PriorityQueue();
        int M = scanner.nextInt();
        String instruction;
        int value, priority;
        for (int i = 1; i <= M; i++) {
            instruction = scanner.next();
            switch (instruction){
                case "insert":
                    value = scanner.nextInt();
                    priority = scanner.nextInt();
                    priorityQueue.insert(value,priority);
                    break;

                case "empty":
                    priorityQueue.Empty();
                    break;

                case "top":
                    priorityQueue.top();
                    break;

                case "pop":
                    priorityQueue.pop();
                    break;

                case "priority":
                    value = scanner.nextInt();
                    priority = scanner.nextInt();
                    priorityQueue.priority(value,priority);
                    break;

                case "print":
                    priorityQueue.print();
                    break;

                default:
                    System.err.println("Wrong!");
                    break;
            }
        }
    }


}
