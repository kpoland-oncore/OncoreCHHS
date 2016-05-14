# Developer Instructions
[README](README.md) > DEVELOPER

_Work In Progress_

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
This launches your editor of choice to record a commit comment. Include the Pivitol Tracker #ITEMNUMBER to automatically add a comment to the story.
Example: 'implement #119506207'

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