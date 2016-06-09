# Developer Instructions
[README](README.md) > DEVELOPER

## VMWare - Developer Image
A VMWare image is used to quickly get a developer up to productive use with the set of tools. The image includes Ubuntu, NetBeans (including Git tooling), and MySQL. The VMWare developer image can be downloaded from OneDrive: https://oncore-my.sharepoint.com/personal/michael_tsay_oncorellc_com/_layouts/15/guestaccess.aspx?guestaccesstoken=BD046UtB%2fc8cpOFYxXVoJMTE4XjaFR45zunjZTl4uQQ%3d&docid=0e8da6e22ff2443b3992a86f57cb38e4c&expiration=2016-07-13T18%3a41%3a15.000Z  
  
Install this VM image, then follow the developer workstation instructions in the documentation directory to setup your developer workstation.  Tasks such as repository clone, DB setup and datasource configuration are covered in that document.  
  
Note that use of VMWare is not required. If you choose, you can separately install the following products into a Linux desktop image:
-- NetBeans 8.1
-- MySQL 14.14 on a Linux workstation
Once MySql is installed:
1. Clone the GitHub repo under /home/oncore 
2. Create a MySQL database user 'OncoreDba' with a password of 'Passw0rd'
3. Run the SQL scripts located in GitHub under OncoreCHHS/OncoreCHHS-ejb/scripts/ in numeric order. These create the database schema and populate it with reference and test data.
  
## Working with GitHub in Netbeans
1. Backup Netbeans frequently until you get used to using Git within Netbeans
2. Follow a Commit -> Pull -> Push pattern
3. When opening Netbeans first right-click the top project in Netbeans navigator, which is OncoreCHHS, and execute a Git Pull command.
4. Work, make changes. Commit your changes to your local repository by executing a Git→Commit. NOT a Git→Remote→Commit.
5. Then when you are ready to push your local changes to the rest of the team:
    1. First run another Git→Remote→Pull to sync up any changes to your repository.   If you get prompted to rebase or merge, you want to select the merge option. 
    2. Assuming you have already checked in your changes to your local repository (#4 above) then you are ready to push your changes.  Select Git→Remote→Push

## Git Command Line Basics
[Git getting started guide](https://git-scm.com/book/en/v2/Getting-Started-The-Command-Line)
```git help <command>```

### Setup
```
git config --global user.name "Kyle Poland"
git config --global user.email kyle.poland@oncorellc.com
git config --global credential.helper store
git config --global core.editor "'C:/Program Files (x86)/Notepad++/notepad++.exe' -multiInst -nosession"
```
or for the serious
```
git config --global core.editor vi
```

### cloning the repository
```
git clone https://github.com/OncoreLLC/OncoreCHHS.git
```
This will make a subdirectory called OncoreCHHS with the contents from GitHub. It is the full repository with all revisions, not just a view containing only tips.

### Pulling down latest changes others have pushed. Always do this before pushing your changes.
```
git pull
```

Pull does a fetch and merge, whereas `git fetch` only gets the changes.

### Review uncommitted changes in your workspace
```git status```

### Adding a file - makes Git aware of it
```
git add <filename>
```
Or when committing, add a "-a" flag to commit to automatically add all untracked files and then commit them.
```git commit -a -m "adding new files"```

### Commiting and linking to Pivitol Tracker stories and chores
```
git commit
```
This launches your editor of choice to record a commit comment.

Include the Pivotal Tracker #ITEMNUMBER inside [] to automatically add a comment to the story. This adds a comment to the story/chore and will automatically start the story/chore if not already started.
Example: '[#119506207]'
Example more than one ITEMNUMBER: '[#119506207 #119504595]'

Use words like "fixed", "completed", or "finished" in the []s to automatically finish the story/feature or accept the chore in Pivotal.
Example: '[fixed #119506207] fix junits for module'

You can also provide the commit comment on the command line with "-m"
```
git commit -m "implement #119506207"
```
And a complete form of commit which also adds all untracked files in one step
```
git commit -a -m "implement #119506207"
```

### Pushing to Master
Do this after you have pulled, and committed your changes.
```
git push
```
You will be prompted for GitHub credentials on the first push, provide your github username (ex: kpoland-oncore), and password. The `git config --global credential.helper store` will save your credentials for future use.  Credentials are plaintext in `.git-credentials` so ensure that file is readable only by you.
