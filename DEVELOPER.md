# Developer Instructions
[README](README.md) > DEVELOPER

_Work In Progress_

## VMWare - to run the developer image
**TODO Royce (or Kyle if not Royce)** instructions on buying/downloading/installing VMWare player

Royce uploaded the VMWare developer image to OneDrive: https://oncore.sharepoint.com/sites/oncorechhs/_layouts/15/guestaccess.aspx?guestaccesstoken=TkHr6YH3Gbqm60xhSGrKwtlF6tmzN4EcuB1IrlaNTXs%3d&docid=2_0d74d12013bfc4ba1b485e6cfa626811a
_ISSUE_: [Kyle] for Vagrant to use VMWare you have to buy both VMWare $150-$250 and a Vagrant plugin $79. They recommend only using VMWare Workstation Pro, not Player. Vagrant uses VirtualBox by default with no paid plugin.


## Git
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

### cloning the repository - like making a view
```
git clone https://github.com/OncoreLLC/OncoreCHHS.git
```
This will make a subdirectory called OncoreCHHS with the contents from GitHub. It is the full repository with all revisions, not just a view containing only tips.

### Pulling down latest changes - like update
```
git pull
```

Pull does a fetch and merge, whereas `git fetch` only gets the changes.

### Adding a file - makes Git aware of it
```
git add <filename>
```
Or when committing, add a "-a" flag to commit to automatically add all untracked files and then commit them.
```git commit -a -m "adding new files"```

### Commiting
```
git commit
```
This launches your editor of choice to record a commit comment.

Include the Pivotal Tracker #ITEMNUMBER inside [] to automatically add a comment to the story. This adds a comment to the story/chore and will automatically start the story/chore if not already started.
Example: '[#119506207]'
Example more than one ITEMNUMBER: '[#119506207 #119504595]'

Use words like "fixed", "completed", or "finished" in the []s to automatically finish the story/feature or accept the chore in Pivotal.
Example: '[fixed #119506207] fix junits for module'

Or provide the comment on the command line with "-m"
```
git commit -m "implement #119506207"
```
And the best form with automatic adding changes as well
```
git commit -a -m "implement #119506207"
```

### Pushing
Do this after you have committed.
```
git push
```
You will be prompted for GitHub credentials on the first push, provide your github username (kpoland-oncore), and password. The `git config --global credential.helper store` told git to save your credentials for future use.  They are kept plaintext in `.git-credentials` so there is probably a better way.

explain what status does
```git status```

## Working with GitHub in Netbeans
1. First until you get used to using Git, backup frequently
2. It is generally best to follow the following pattern,  Pull->Push
3. So you have opened Netbeans for today’s work,  first thing you should do is right click the top project in the Netbeans navigator, which will be OncoreCHHS, execute a Git Pull command.
4. Do your work…  commit your changes to your local repository by executing a Git→Commit. NOT a Git→Remote→Commit.   This checks in your changes to your local Git.
5. You think you are ready to push you local changes to the rest of the team… 
1. First run another Git→Remote→Pull to sync up any changes to your repository.   If you get prompted to rebase or merge, you want to select the merge option. 
2. Assuming you have already checked in your changes to your local repository (#4 above) then you are ready to push your changes.  Select Git→Remote→Push
