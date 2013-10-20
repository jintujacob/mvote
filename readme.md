
git init
# Sets up the necessary Git files
# Initialized empty Git repository in /MobileVoting

git add readme.md
# Stages your README file, adding it to the list of files to be committed

git commit -m 'description'
# Commits your files, adding the message "first commit"

git remote add origin https://github.com/jintujacob/mvote.git
# Creates a remote named "origin" pointing at your GitHub repository

git push origin master
# Sends your commits in the "master" branch to GitHub

