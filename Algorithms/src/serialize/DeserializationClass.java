/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author ANMEENA
 */
public class DeserializationClass {

    public static void main(String[] args) {
        Employee emp = null;
        try {
            FileInputStream fileIn = new FileInputStream("./employee.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            emp = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserializing Employee...");
        System.out.println("First Name of Employee: " + emp.firstName);
        System.out.println("Last Name of Employee: " + emp.lastName);
    }
}
