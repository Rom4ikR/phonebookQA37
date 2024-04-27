package utils;

import dto.ContactDTO;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]> addNewContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Paul", "James", "1234567890", "some@gmail.com", "New York", "friend"});
        list.add(new Object[]{"Paul1", "James", "1234567891", "some@gmail.com", "New York", "friend"});
        list.add(new Object[]{"Paul2", "James", "1234567892", "some@gmail.com", "New York", "friend"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> addContactCSVFile() {
        List<Object[]> list = new ArrayList<>();
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    new File("src/test/resources/contacts.csv")));
            line = reader.readLine();

            while (line != null) {
                String [] split = line.split(",");
                list.add(new Object[]{new ContactDTO().setName(split[0]).setLastName(split[1]).setPhone(split[2])
                        .setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
                line = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.iterator();
    }
}
