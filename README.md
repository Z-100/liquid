# L I Q U I D
### 1. Features
* Library
* Shop
* Userprofile
* Communitystore (Not enough time)
### 2. How to make the project work
* Intellij
* Visual Studio Code

## Features
* Library
  * All games listed
  * Two modes
    * A list with all the video games bought via the store
    * (All the video games bought via the store as clickable thumbnails)
    
* Shop
  * List of buyable games in database
  * Search field
    * Search by name
  * Filter
    * By price > set max. price
    * (By category) > checkbox
    * (By OS) > checkbox
    
* User profile
  * Login system
    * Set username (used for log in, not editable)
    * Set username (used for display, editable)
    * Set password (used for verification, editable)
  * Profile picture
    * Custom user upload (used for display, editable)
    * Downscaled png image (128 x 128) to save space
  * (Inventory)
    * List (or thumbnails) of items bought from the Community Store
    
* (Community store)
  * Buyable items
    * user > user
    * transaction fees
    * thumbnails
  
## How to
#### Intellij:
1. New Project (Get from VCS)
2. Install / Reload all Maven modules
3. Edit Run configuration:
    1. Add new config (maven)
    2. Add the following line to the field "command line":
    3. javafx:run
4. Hit the run button

#### Visual Studio Code:
1. Read a manual