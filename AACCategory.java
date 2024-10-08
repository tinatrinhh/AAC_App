import java.util.*;

/**
 * Represents the mappings for a single category of items that should
 * be displayed
 * 
 * @author Catie Baker & Tina Trinh
 *
 */
public class AACCategory implements AACPage {
    private String name;
    private HashMap<String, String> itemMap; // Map to store image location and associated text

    /**
     * Creates a new empty category with the given name
     * @param name the name of the category
     */
    public AACCategory(String name) {
        this.name = name;
        this.itemMap = new HashMap<>();
    }

    /**
     * Adds the image location, text pairing to the category
     * @param imageLoc the location of the image
     * @param text the text that image should speak
     */
    @Override
    public void addItem(String imageLoc, String text) {
        itemMap.put(imageLoc, text);
    }

    /**
     * Returns an array of all the images in the category
     * @return the array of image locations; if there are no images,
     * it should return an empty array
     */
    @Override
    public String[] getImageLocs() {
        return itemMap.keySet().toArray(new String[0]);
    }

    /**
     * Returns the name of the category
     * @return the name of the category
     */
    @Override
    public String getCategory() {
        return name;
    }

    /**
     * Returns the text associated with the given image in this category
     * @param imageLoc the location of the image
     * @return the text associated with the image
     * @throws NoSuchElementException if the image provided is not in the current
     *         category
     */
    @Override
    public String select(String imageLoc) {
        if (!itemMap.containsKey(imageLoc)) {
            throw new NoSuchElementException("Image not found in the category");
        }
        return itemMap.get(imageLoc);
    }

    /**
     * Determines if the provided image is stored in the category
     * @param imageLoc the location of the category
     * @return true if it is in the category, false otherwise
     */
    @Override
    public boolean hasImage(String imageLoc) {
        return itemMap.containsKey(imageLoc);
    }
}
