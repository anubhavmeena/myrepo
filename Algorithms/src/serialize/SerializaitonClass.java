/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author ANMEENA
 */
public class SerializaitonClass {

    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.firstName = "Vivekanand";
        emp.lastName = "Gautam";

        try {
            FileOutputStream fileOut = new FileOutputStream("./employee.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(emp);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in ./employee.txt file");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
