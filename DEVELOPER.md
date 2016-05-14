# Developer Instructions
__Work In Progress__

## Git
[Git getting started guide](https://git-scm.com/book/en/v2/Getting-Started-The-Command-Line)
'''git help <command>'''

### Setup
'''
git config --global user.name "Kyle Poland"
git config --global user.email kyle.poland@oncorellc.com
git config --global core.editor "'C:/Program Files (x86)/Notepad++/notepad++.exe' -multiInst -nosession"
'''
or for the serious
'''
git config --global core.editor vi
'''

### cloning the repository - like making a view
'''
git clone https://github.com/OncoreLLC/OncoreCHHS.git
'''
This will make a subdirectory called OncoreCHHS with the contents from GitHub. It is the full repository, not just a view.

### Pulling down latest changes - like update
'''
git pull
'''

Does a fetch and merge, whereas 'git fetch' only gets the changes.

### Adding a file - makes Git aware of it
'''
git add <filename>
'''

Or when committing, add a "-a" flag to commit to automatically add all untracked files and then commit them.
git commit -a -m "adding new files"

### Commiting
'''
git commit
'''
This launches your editor of choice to record a commit comment. Include the Pivitol Tracker #ITEMNUMBER to automatically add a comment to the story.
Example: 'implement #119506207'

Or all in one line
'''
git commit -m "implement #119506207"
'''

### Pushing
Do this after you have committed.
'''
git push
'''

explain what status does
'''git status'''