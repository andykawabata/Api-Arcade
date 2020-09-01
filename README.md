# Api-Arcade
This is going to be the central repository for the Elon Musketeers.

## Workflow after initial clone and branch creation  

We've already cloned the repo and pushed/merged changes in the central repo, so here's the workflow for making changes

### When adding to or editing the code:
1) MAKE SURE YOUR LOCAL MASTER IS UP TO DATE WITH THE REMOTE (GITHUB) MASTER
2) Make sure your local &lt;your name&gt; branch is up to date with local master
3) Make changes to code on YOUR BRANCH
4) Stage and commit your changes
5) Push your local branch to your remote branch
6) Create pull request on github
  
### Commands to accomplish the above steps
1) On your local master branch run `git pull` to make sure your master branch is up to date with remote master
2) Run `git checkout <your name>` to checkout your personal branch
3) Run `git rebase master` to make sure your branch incorperates the changes to master
4) Write ur code and stuff
5) Run `git add .` to stage then `git commit -m"<your name>"` to commit
6) Run `git push origin <your name>` to push your branch to the remote
7) Go to github and create pull request from YOUR BRANCH to MASTER

