# L I Q U I D
#### github.com/z-100/liquid
### 1. Features
* Library
* Shop
* Userprofile
### 2. How to make the project work
* Intellij
* Visual Studio Code

## Features
* Library
  * All games listed
    * A list with all the video games bought via the store
    * View game desc, title & thumbnail 
    
* Shop
  * List of buyable games in database
  * Search field
    * Search by name
  * Filter
    * By price > set max. price
    * By OS > checkbox
    
* User profile
  * Login system
    * Set username (used for log in, not editable)
    * Set username (used for display, editable)
    * Set password (used for verification, editable)
    
## How to get the project working in...
### (Editors)
#### ...Intellij:
1. New Project (Get from VCS)
2. Install / Reload all Maven modules
3. Edit Run configuration:
    1. Add new config (maven)
    2. Add the following line to the field "command line":
    3. javafx:run
4. Hit the run button

#### ...Visual Studio Code:
1. Read a manual
### (Database)
#### ...MariaDB:
1. Open MariaDB terminal
2. Log in and create a database called 'liquid'
3. Press ctrl + c and type the following command into the terminal 
   - Change -u and -p to your username & password
   - Change path to where your /resources/sql/sqldump.sql is located
4. mariadb -u**** -p**** liquid < PATH
