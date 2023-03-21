# How to: Using git to collaborate on a project

> **Disclaimer**
> There are many approaches and opinions about what an ideal git flow looks like. What I will describe is the approach
> which I use and like. Depending on the team you work with, you may come across different approaches.

Since I primarily work with the git cli, in the following sections I will write down the git commands which I use to
achieve each task. The same can be done via git guis, in case you use one.

## Branches

```bash
git checkout -b feat/my-awesome-feature
```

### Why should you care about branches?

Branches allow us to work on the same codebase in parallel while keeping conflicts to a minimum. You and I can work on
the same files at the same time and if we do not touch
the same lines of code, we can simply merge our different branches together into their common parent branch. In my git
workflow, this common branch is the master branch. The master branch is your repositories "holy grail" and once you get
into professional development, you should make sure that only well tested code gets merged into it. This is because
often times in companies you will have a
[CD](https://en.wikipedia.org/wiki/Continuous_delivery) setup, which automatically deploys anything that is merged into
master to the production environment. Another nice thing about branches is that you can check your changes "in
isolation". When previously working tests fail all of a sudden, you know for a fact that you are the one who broke them,
because only your changes have been added to the up until now functional master state.

## Committing your changes

First, you need to stage your changes:

```bash
git add -A
```

Then, you can commit the added files:

```bash
git commit -m "Your message goes here"
```

Now the changes have been applied to the branch on your local repository. To share it with the world, you need to
publish the changes to the upstream repository (the one hosted on github).

```bash
git push
```

The branch with your changes is now in the remote repository. Go to github, choose pull requests -> new pull request and
select your branch as source and master as the target. This will open a new PR, in which you can see all changes lines
and others can review your changes.

### Writing meaningful commit messages

When you work at a company, you will usually have
a [ticket-system](https://www.atlassian.com/software/jira?&aceid=&adposition=&adgroup=149781617252&campaign=19313890739&creative=642044895830&device=c&keyword=jira&matchtype=e&network=g&placement=&ds_kids=p74602317412&ds_e=GOOGLE&ds_eid=700000001558501&ds_e1=GOOGLE&gclid=CjwKCAjwq-WgBhBMEiwAzKSH6JyOXdqzOTqkeMgP8uXKI7doBTvDalZ8ZffMnsN6rGbPWM9E3wkpOBoC0fwQAvD_BwE&gclsrc=aw.ds)
. In that case, prefix the commit with the corresponding ticket number:
`git commit -m "ABC-13414: some message"`

When I do not have any ticket to use, I like to use explanatory prefixes such as:

- chore: cleanup duties (`git commit -m "chore: remove outdated properties"`)
- refactor: technical adjustment (`git commit -m "refactor: move class ABC to package DEF"`)
- bug: fixing a bug that has no ticket (`git commit -m "bug: no translations shown on chat submit button`)

## Resolving conflicts

If you and another collaborator have changed the same lines in a file, git will not be able to automatically merge your
changes. In that case, a merge conflict appears. This needs to be resolved manually. Whenever I have conflicts, I make
use of intellij's built in git ui. When a conflict occurs, choose git -> resolve conflicts and you will get a neat side
by side comparison of the two versions of the file in question and can easily merge them together. Another reason for
conflicts is if the git histories of your local branch and the remote branch differ. This can happen when you work a lot
with rebasing and force-pushing. I won't go into details here.

## Branch merging strategies

My preferred strategy is rebasing. To get an overview over the differences between merge, squash and merge and rebase,
check [this](https://stackoverflow.com/questions/2427238/what-is-the-difference-between-merge-squash-and-rebase) stack
overflow post.


