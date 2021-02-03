# Spring | Virender Bhargav

This README includes instructions for developers and maintainers for this repository.

# 1. Problem Statement


# 2. Solution Approach
* User: entity to represent any user who can use the system


# 3. Instructions for Developers

## 3.1 Branches & Environments

### 3.1.1 Branches
    There are 3 main branches namely, master, beta and develop. Other then that you must follow git flow to push your code to develop branch.
    So there are some strict rules when it comes to merging. These rules are given below
        a) You can merge "master" into "beta".
        b) You can merge "beta" into "master" and "develop".
        c) You must have to create apull request to merge code into beta and master branch.

## 3.2 Commit Process

### 3.2.1 Commit Format

Use the multi-line format defined below (do not use `-m` and go through editor):

```
[<task-id>] <task-summary>
<blank line>
- <commit description line 1>
- <commit description line 2>
- <commit description line 3>
```

In the header line `<task-id>` is the JIRA task id, and `<task-summary>` is the JIRA task summary.

**NOTE**: The blank line is important. That's what makes git know that you have a header line.

Also, optionally, you may include lines using the [smart commit syntax](https://confluence.atlassian.com/bitbucket/processing-jira-software-issues-with-smart-commit-messages-298979931.html) in your summary.

### 3.2.2 Pre-commit checks

1. Run `mvn clean test` to run unit test cases and ensure no errors
2. Run `cov-check.sh` to check code coverage and ensure it give a pass message

### 3.2.3 commit and Merge

1. `$ git add <your files..>`
2. `$ git commit` to create a commit
3. `$ git pull --rebase` to get changes from repository.
4. In case the above steps gives you any conflicts:
    1. Resolve the conflicted files by manually inspecting
    2. `git add` all conflicted files resolved manually by you
    3. `$ git rebase --continue` to tell git that you have resolved the conflicts. This may ask you to create another commit.

### 3.2.4 Push to remote

```
$ git push
```

## 2.3 Coding Standards

### 2.3.1 Java

#### 2.3.1.1 Coding Standards


# 4. Development Environment Setup

## 4.1 Ubuntu 16.04

### 4.1.1 Download and Installation

### 4.1.2 How to setup new development workspace
    

# 5. Developer:
    a) Virender Bhargav (raif.viren@gmail.com)

# 6 Tech Stack:
    In this project we are using
        a) Spring 2.2.2 with java 1.8
        b) mysql
        c) EC2 of AWS for servers
ge


# 8. Work Flow:


# 9. Postman Collection:
