# 8 Queens

## Overview
The eight queens puzzle is the problem of placing eight chess queens on an 8×8 chessboard
so that no two queens threaten each other, thus, a solution requires that no two queens
share the same row, column, or diagonal. <br>
In this repository, we implement different algorithms to solve the 8 queens problem starting from any initial state.
## How to contribute
1. clone the repository using the command 

    ```git clone https://github.com/YomnaEldawy/8-queens.git```
2. create a new branch with the name of the algorithm or class you are implementing using the following command (I will provide csp as an example)

    ``` git checkout -b csp```
3. execute the following command

    ``` git remote add origin https://github.com/YomnaEldawy/8-queens.git```
4. when you are done with your class, commit and push the branch to the repo using the following commands

    ```bash
    git add .
    git commit -m "commit message"
    git push origin csp #the name of the branch
    ```
5. If you want to implement a new feature (Either you are finished with the first one or not), execute the following commands: I assume you are now implementing feature1 and want to start working on feature2

    ```bash
    git add .
    git commit -m "commit message"
    git checkout stage
    git checkout -b feature2
    ```
    
