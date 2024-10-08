import java.util.*;
import java.io.*;

/**
 * Creates a set of mappings of an AAC that has two levels,
 * one for categories and then within each category, it has
 * images that have associated text to be spoken. This class
 * provides the methods for interacting with the categories
 * and updating the set of images that would be shown and handling
 * an interactions.
 * 
 * @author Catie Baker & Tina Trinh 
 */
public class AACMappings implements AACPage {
    protected HashMap<String, AACCategory> categories; // Map to store category name and associated AACCategory object
    protected AACCategory currentCategory;

    /**
     * Creates a set of mappings for the AAC based on the provided
     * file. The file is read in to create categories and fill each
     * of the categories with initial items. The file is formatted as
     * the text location of the category followed by the text name of the
     * category and then one line per item in the category that starts with
     * > and then has the file name and text of that image
     * 
     * for instance:
     * img/food/plate.png food
     * >img/food/icons8-french-fries-96.png french fries
     * >img/food/icons8-watermelon-96.png watermelon
     * img/clothing/hanger.png clothing
     * >img/clothing/collaredshirt.png collared shirt
     * 
     * represents the file with two categories, food and clothing
     * and food has french fries and watermelon and clothing has a 
     * collared shirt
     * 
     * @param filename the name of the file that stores the mapping information
     */
    public AACMappings(String filename) {

        this.categories = new HashMap<>();
        this.currentCategory = null; // Initialize to null as the home screen
        loadMappingsFromFile(filename);
    }

    /**
     * Given the image location selected, it determines the action to be
     * taken. This can be updating the information that should be displayed
     * or returning text to be spoken. If the image provided is a category, 
     * it updates the AAC's current category to be the category associated 
     * with that image and returns the empty string. If the AAC is currently
     * in a category and the image provided is in that category, it returns
     * the text to be spoken.
     * 
     * @param imageLoc the location where the image is stored
     * @return if there is text to be spoken, it returns that information, otherwise
     *         it returns the empty string
     * @throws NoSuchElementException if the image provided is not in the current 
     *                                category
     */
    public String select(String imageLoc) {
        if (categories.containsKey(imageLoc)) {
            // Switch to the selected category
            currentCategory = categories.get(imageLoc);
            return ""; // Return empty string as it's a category change
        } else if (currentCategory != null && currentCategory.hasImage(imageLoc)) {
            // In a category, return the text associated with the image
            return currentCategory.select(imageLoc);
        } else {
            throw new NoSuchElementException("Image not found in the current category");
        }
    }

    /**
     * Provides an array of all the images in the current category
     * 
     * @return the array of images in the current category; if there are no images,
     *         it should return an empty array
     */
    public String[] getImageLocs() {
        List<String> result = new ArrayList<>();

        if (categories != null) {
            if (currentCategory == null) {
                // Add only category images on the home screen
                result.addAll(categories.keySet());
            } else {
                // Add item images within the current category
                for (String item : currentCategory.getImageLocs()) {
                    if (currentCategory.hasImage(item)) {
                        result.add(item);
                    }
                }
            }
        }

        return result.toArray(new String[0]);
    }

    /**
     * Resets the current category of the AAC back to the default
     * category
     */
    public void reset() {
        currentCategory = null; 
    }

    /**
     * Writes the ACC mappings stored to a file. The file is formatted as
     * the text location of the category followed by the text name of the
     * category and then one line per item in the category that starts with
     * > and then has the file name and text of that image
     * 
     * for instance:
     * img/food/plate.png food
     * >img/food/icons8-french-fries-96.png french fries
     * >img/food/icons8-watermelon-96.png watermelon
     * img/clothing/hanger.png clothing
     * >img/clothing/collaredshirt.png collared shirt
     * 
     * represents the file with two categories, food and clothing
     * and food has french fries and watermelon and clothing has a 
     * collared shirt
     *
     * @param filename the name of the file to write the
     *                 AAC mapping to
     */
    public void writeToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
        	
		/*
		 * For each category, it writes a line to the file with the category name
		 * (twice, separated by a space) followed by a newline character (\n). then
		 * iterates over the images in the category (category.getImageLocs()), and for
		 * each image, it writes a line to the file starting with > followed by the
		 * image location and the associated text. This represents the format specified
		 * for each item in the file.
		 */
    
            for (Map.Entry<String, AACCategory> entry : categories.entrySet()) {
                AACCategory category = entry.getValue();
                writer.write(category.getCategory() + " " + category.getCategory() + "\n");

                for (String imageLoc : category.getImageLocs()) {
                    String text = category.select(imageLoc);
                    writer.write(">" + imageLoc + " " + text + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the mapping to the current category (or the default category if
     * that is the current category)
     * 
     * @param imageLoc the location of the image
     * @param text     the text associated with the image
     */
    public void addItem(String imageLoc, String text) {
        if (currentCategory == null) {
            // Add to the default category (home screen)
            currentCategory = new AACCategory("Home Page");
        }
        currentCategory.addItem(imageLoc, text);
    }

    /**
     * Gets the name of the current category
     * 
     * @return returns the current category or the empty string if 
     *         on the default category
     */
    public String getCategory() {
        if (currentCategory != null) {
            return currentCategory.getCategory();
           
        } else {
            return ""; 
        }
    }

    /**
     * Determines if the provided image is in the set of images that
     * can be displayed and false otherwise
     * 
     * @param imageLoc the location of the category
     * @return true if it is in the set of images that
     *         can be displayed, false otherwise
     */
    public boolean hasImage(String imageLoc) {
        if (currentCategory != null) {
            return currentCategory.hasImage(imageLoc);
        } else {
            return false; 
        }
    }
    private void loadMappingsFromFile(String filename) {
    	// read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);

                if (tokenizer.hasMoreTokens()) {
                    String imageLoc = tokenizer.nextToken();

                    // Check if the line starts with '>'
                    if (imageLoc.startsWith(">")) {
                        // It's an item in the current category
                        imageLoc = imageLoc.substring(1); // Remove '>'
                        String text = tokenizer.nextToken();
                        currentCategory.addItem(imageLoc, text);
                   
                    } else {
                        // It's a new category
                        String categoryName = tokenizer.nextToken();
                        currentCategory = new AACCategory(categoryName);
                        categories.put(imageLoc, currentCategory);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

