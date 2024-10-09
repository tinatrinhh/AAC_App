# AAC System App Project

# Overview

This project implements an Augmented and Alternative Communication (AAC) system using Java. The AAC system allows users with speech impairments to communicate using graphical symbols or images that correspond to spoken text. The system includes a graphical user interface (GUI) to display categories (e.g., food, clothing), and users can select images to hear the corresponding text spoken aloud using text-to-speech technology.

# Features 
* **Graphical User Interface (GUI)**: Displays categories (e.g., food, clothing) with associated images.
* **Text-to-Speech**: Speaks the selected images using FreeTTS.
* **HashMaps for Data Storage**: Stores mappings of images to text in categories for efficient retrieval.
*  **Custom Categories and Images**: Users can add new categories and images

# Getting Started 
## Prerequistes: 
* Java Development Kit (JDK) version 1.8 or later
* Eclipse IDE (or any preferred Java IDE).
* FreeTTS library for text-to-speech functionality

## Installation 
1. Clone the repository to your local machine:
   
`git@github.com:tinatrinhh/AAC_App.git`

3. Open the project in Eclipse or any Java IDE.
4. Ensure the FreeTTS library is installed and configured:
   * Add FreeTTS to your build path.
   * If using Eclipse:
     * Click on `Build path -> Add Libraries`.
     * Select `User Library`.
     * Check `FreeTTS` and confirm.
    
## Running the Project 
1. Ensure the text-to-speech functionality is working:
   * Run the `TextSpeechDriver` class to verify the TTS output.
2. Run the `AAC` class the launch the GUI. You will be able to interact with the different categories and images.
3. To add custom categories, use the “Add” button in the GUI to upload new images and corresponding text for speech.

# Project Structure
* **AAC Class**: Main GUI class that displays categories and images
* **AACCateogry Class**: Manages individual categories (e.g., food, clothing) and stores image-to-text mappings.
* **AACMapping Class**: Handles the full set of mappings from images to text for all categories.

### Main Classes: 
#### AAC Class:
This class creates the graphical interface for the AAC system. It allows users to interact with various categories and select images to hear the associated text.

#### AACCateogry Class:
This class represents a single category in the AAC system, such as "food" or "clothing". It manages the relationship between image locations and the text that should be spoken.

#### AACMapping Class:
The `AACMapping` class is responsible for maintaining the overall set of image-to-text mappings across categories. It reads a configuration file, which links images to their respective categories and spoken text.

### Interfaces:
#### AACPage
This interface represents a set of information that would be displayed together on an AAC 

#### TextSpeech
This interface is used to convert text to speech


# Acknowledgments

- **Dr. Baker**: For guiding and providing invaluable feedback throughout the project.
- **FreeTTS**: Acknowledgment for the FreeTTS library, which facilitated the text-to-speech functionality in our application. For more information, visit [FreeTTS Website](http://freetts.sourceforge.net/docs/index.php).




