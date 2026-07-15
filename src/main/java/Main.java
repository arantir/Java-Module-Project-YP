import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Ввод данных
        InputData input = new InputData();
        Car[] cars = input.getCars();
        
        //расчёты
        Calc calc = new Calc();
        Car fastest = calc.findFastest(cars);
        
        //вывод результата
        Output output = new Output();
        output.printResult(fastest);
    }
}

class InputData {
    private Scanner scanner = new Scanner(System.in);
    
    public Car[] getCars() {
        System.out.println("Эта программа - симулятор гонок. В гонках участвует 3 автомобиля.");
        Car[] cars = new Car[3];
        
        for (int i = 0; i < 3; i++) {
            System.out.println("\n--- Автомобиль " + (i + 1) + " ---");
            
            String name;
            boolean validName;
            
            do {
                System.out.print("Введите имя машины " + (i + 1) + ": ");
                name = scanner.nextLine();
                
                // Проверяем, что имя не пустое
                if (name.trim().isEmpty()) {
                    System.out.println("Ошибка! Имя не может быть пустым.");
                    validName = false;
                    continue;
                }
                
                // Проверяем, есть ли уже машина с таким именем
                validName = true;
                for (int j = 0; j < i; j++) {
                    if (cars[j] != null && cars[j].name.equalsIgnoreCase(name)) {
                        System.out.println("Ошибка! Машина с именем '" + name + "' уже существует.");
                        validName = false;
                        break;
                    }
                }
            } while (!validName);
            
            int speed = inputSpeed(i + 1);
            cars[i] = new Car(name, speed);
        }
        
        scanner.close();
        return cars;
    }
    
    //Метод для ввода скорости
    private int inputSpeed(int carNumber) {
        int speed = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print("Введите скорость машины " + carNumber + " (1-250 км/ч): ");
                String input = scanner.nextLine();
                speed = Integer.parseInt(input);
                
                //Проверка диапазона
                if (speed > 0 && speed <= 250) {
                    validInput = true;
                } else {
                    System.out.println("Ошибка! Скорость должна быть в диапазоне от 1 до 250 км/ч.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
            }
        }
        
        return speed;
    }
}

class Calc {
    //Метод для расчёта самой быстрой машины
    public Car findFastest(Car[] cars) {
        Car fastest = null;
        for (Car car : cars) {
            if (fastest == null || car.speed > fastest.speed) {
                fastest = car;
            }
        }
        return fastest;
    }
}

class Output {
    //Вывод
    public void printResult(Car fastest) {
        System.out.println("Самая быстрая машина: " + fastest.name);
    }
}

// Класс машина
class Car {
    // Поля
    String name;            // Название
    int speed;              // Скорость (км/ч)
    int distance;           // Расстояние за 24 часа езды (км)

    // Конструктор
    Car(String carName, int carSpeed) {
        name = carName;
        speed = carSpeed;
        distance = carSpeed * 24;
    }
}