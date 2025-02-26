# MiniActv-1 Best Practices in Resource Management

### Default Configuration Resources
- **Default Language:** English  
- **Orientation:** Portrait  
- **Screen Size:** Smartphone  
- **A TextView displaying a welcome message.**  

### Additional Alternative Resources
- **Internationalization:** Three additional languages have been added:
  - Portuguese  
  - Spanish  
  - German  
- **Support for different screen sizes:**
  - Proper adaptation to devices with different resolutions has been verified.  
- **Dropdown for language selection:**
  - A spinner (dropdown) has been implemented to allow users to change the language from the UI.  
  - Flag icons have been added next to each language for easier visual identification.  
  - When changing the language, the UI updates automatically without needing to restart the app.  
- **A button (Button) that displays a Toast when pressed.**  
  - The Toast message is also translated according to the selected language.  
- **Adapted images and icons:**
  - Icons representing the flags of the available languages have been added.  
  - It was verified that background images and visual elements properly adjust to different devices.  

### Resource Folder Structure  
The files and resources are organized into the following folders within the project:  

- **res/values/**  
  - `strings.xml` (contains texts in English)  
  - `values-es/strings.xml` (Spanish translations)  
  - `values-pt/strings.xml` (Portuguese translations)  
  - `values-de/strings.xml` (German translations)  
- **res/drawable/**  
  - Background images and visual elements.  
  - Flag icons for the available languages.  
- **res/layout/**  
  - `activity_main.xml` (Main layout of the application).  
  - `item_language.xml` (Structure of the spinner list item).  
