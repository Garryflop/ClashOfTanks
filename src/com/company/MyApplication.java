package com.company;

import com.company.controllers.interfaces.IUserController;
import com.company.factory.TankType;
import com.company.repositories.interfaces.IUserRepository;
import com.company.singleton.SoundManager;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController controller;
    private final IUserRepository repository;
    private final Scanner scanner = new Scanner(System.in);
//    private Game game = new Game();
    public MyApplication(IUserController controller, IUserRepository repository) {
        this.controller = controller;
        this.repository = repository;
    }

//    private void mainMenu() {
//        System.out.println();
//        System.out.println("Welcome to Clash Of Tanks");
//        System.out.println("Select option:");
//        System.out.println("1. Get all users");
//        System.out.println("2. Get user by id");
//        System.out.println("3. Create user");
//        System.out.println("4. Update user");
//        System.out.println("5. Delete user");
//        System.out.println("0. Exit");
//        System.out.println();
//        System.out.print("Enter option (1-5): ");
//    }
    private void mainMenu(){
        System.out.println();
        System.out.println("Welcome to Clash Of Tanks");
        System.out.println("1. Create New User");
        System.out.println("2. Top Player");
        System.out.println("3. Search Player by Id");
        System.out.println("4. Change nickname");
        System.out.println("5. Delete user");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-5): ");
    }

    private void createUsersMenu(){
        System.out.println("1st Player's nickname:");
        Scanner sc1 = new Scanner(System.in);
        String nickname1 = sc1.nextLine();
        TankType type1 = createChooseTankMenu();

        System.out.println("2nd Player's nickname:");
        Scanner sc2 = new Scanner(System.in);
        String nickname2 = sc2.nextLine();
        TankType type2 = createChooseTankMenu();
        String response = controller.createUser(nickname1);

        //try error
        int id1 = Integer.parseInt(response);
        //try catch
        String response2 = controller.createUser(nickname2);
        int id2 = Integer.parseInt(response2);
        System.out.println("RESPONSE = " + response);
        System.out.println("RESPONSE = " + response2);

        createSession(id1, id2, repository, type1, type2);
    }

    private TankType createChooseTankMenu(){
        System.out.println("Choose your Tank");
        System.out.println("1. Assault");
        System.out.println("2. Defender");
        System.out.println("3. Scout");
        System.out.println();
        System.out.print("Enter option (1-3): ");
        TankType request = null;
        try {
            int option = scanner.nextInt();
            switch (option) {
                case 1: request = TankType.ASSAULT; break;
                case 2: request = TankType.DEFENDER; break;
                case 3: request = TankType.SCOUT; break;
                default: return request;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input must be integer: " + e);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return request;
    }

    public void createSession(int id1, int id2, IUserRepository repository, TankType type1, TankType type2){
        SwingUtilities.invokeLater(() -> new Game(60, id1, id2, repository, type1, type2));
        SoundManager.getInstance().playMusic("com/company/resources/music/background.wav");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1: createUsersMenu(); break;
                    case 2: getAllUsersMenu(); break;
                    case 3: getUserByIdMenu(); break;
                    case 4: updateUserByIdMenu(); break;
                    case 5: deleteUserByIdMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void deleteUserByIdMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();
        String response = controller.deleteUser(id);
        System.out.println(response);
    }

    public void updateUserByIdMenu(){
        System.out.print("Enter user id and new nickname(id name): ");
        Scanner sc1 = new Scanner(System.in);

        String line = sc1.nextLine();
        String[] parts = line.split(" ");
        int userId = Integer.parseInt(parts[0]);
        String nickname = parts[1];
        String response = controller.updateUser(userId, nickname);
        System.out.println(response);
    }

    public void getAllUsersMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);
    }

    public void getUserByIdMenu() {
        System.out.println("Please enter id");

        int id = scanner.nextInt();

        int response = Integer.parseInt(controller.getUser(id));
        System.out.println(response);
    }

    public void createUserMenu() {
        System.out.println("Please enter name");
        String nickname = scanner.next();

        String response = controller.createUser(nickname);
        System.out.println(response);
    }
}
