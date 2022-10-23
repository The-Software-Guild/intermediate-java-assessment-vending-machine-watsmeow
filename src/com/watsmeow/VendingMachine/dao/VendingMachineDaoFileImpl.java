package com.watsmeow.VendingMachine.dao;

import com.watsmeow.VendingMachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoFileImpl implements VendingMachineDaoFile{

    private static final String DELIMITER = "::";

    public Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        String name = itemTokens[0];
        double cost = Double.parseDouble(itemTokens[1]);
        BigDecimal bigDecInput = new BigDecimal(cost);
        bigDecInput.setScale(2, RoundingMode.HALF_UP);
        int quantity = Integer.parseInt(itemTokens[2]);
        Item itemFromFile = new Item(name, bigDecInput, quantity);
        return itemFromFile;
    }

    // Loads the library.txt file into the hashmap
    public Map<String,Item> loadVendingMachine(String file) throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(file)
                    )
            );
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Could not load library into memory.", e);
        }
        Map<String, Item> items = new HashMap<>();
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName().toLowerCase(), currentItem);
        }

        scanner.close();
        return items;
    }


    public String marshallItem(Item item) {
        String itemAsText = item.getName() + DELIMITER;
        itemAsText += item.getCost() + DELIMITER;
        itemAsText += item.getQuantity();
        return itemAsText;
    }


    public void writeToItemsFile(List<Item> itemList, String file) throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save data.", e);
        }
        String itemAsText;
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

}
