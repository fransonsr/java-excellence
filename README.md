# java-excellence
Java Excellence Excercises

## IDE Resources
- [IntelliJ IDEA keymap for Eclipse](https://marketplace.eclipse.org/content/intellij-idea-keymap-eclipse)

## Test-Driven Development

The `tdd` directory contains problem and solution code for the TDD class.

### Refactoring module

The `tdd/refactoring` directory contains projects that you can import into your IDE (Eclipse / Intellij IDEA) for practice or to view the solutions. The projects are set up as standard Maven projects, so as to make them easier to import into any IDE that supports Maven. 

| Directory                                                    | Description                                                                                                                                                 |
|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <code>0.0&#x2011;CollectingParameter&#x2011;Example</code>   | Code for the "Collecting Parameter" example. (This is optional.)                                                                                            |
| <code>1.0&#x2011;AutomatedRefactoring&#x2011;Problem</code>  | Code for the "Automated Refactoring" exercise. (Including "Rename Method", "Rename Field", "Extract Method" and "Pull Up".)                                 |
| <code>1.5&#x2011;AutomatedRefactoring&#x2011;Solution</code> | Solution code for the "Automated Refactoring" exercise.                                                                                                     |
| <code>2.0&#x2011;SmellectionsPart1&#x2011;Problem</code>     | Code for the "Smellections, Part 1" exercise. (Including "Compose Method", "Safe Delete", "Inline Variable" and "Extract Method".)                          |
| <code>2.5&#x2011;SmellectionsPart1&#x2011;Solution</code>    | Solution code for "Smellections, Part 1."                                                                                                                   |
| <code>3.0&#x2011;SmellectionsPart2&#x2011;Problem</code>     | Code for the "Smellections, Part 2" exercise. (Including "Caller Creates", "Caller Swap" and "Encapsulate Field".)                                          |
| <code>3.5&#x2011;SmellectionsPart2&#x2011;Solution</code>    | Solution code for "Smellections, Part 2."                                                                                                                   |
| <code>4.0&#x2011;SmellectionsPart3&#x2011;Problem</code>     | Code for the "Smellections, Part 3" exercise. (Including "Extract Method", "Rename Method", "Pull Up", "Extract Local Variable" and "Rename local Variable" |
| <code>4.5&#x2011;SmellectionsPart3&#x2011;Solution</code>    | Solution code for "Smellections, Part 3."                                                                                                                   |
| <code>5.0&#x2011;SmellectionsPart4&#x2011;Problem</code>     | Code for the "Smellections, Part 4" exercise. (Including "Introduce Scaffolding".)                                                                          |
|  <code>5.5&#x2011;SmellectionsPart4&#x2011;Solution</code>   | Solution code for "Smellections, Part 4."                                                                                                                   |

### Microtesting module

The `tdd/microtesting` directory contains projects you can import into your IDE (Eclipse / IntelliJ IDEA) for the Microtesting module. The projects are standard Maven projects. Note that in some projects, the tests fail intentionally.

| Directory                                             | Description                                                          |
|-------------------------------------------------------|----------------------------------------------------------------------|
 | <code>0.0&#x2011;AllAboutXUnit&#x2011;Example</code>  | Example using xUnit. (Optional.)                                     |
 | <code>1.0&#x2011;SoundCheck&#x2011;Problem</code>     | Sound Check exercise.                                                |
 | <code>2.0&#x2011;SmallIsSuperb&#x2011;Problem</code>  | Small is Superb exercise (refactoring a large test into microtests). |
 | <code>2.5&#x2011;SmallIsSuperb&#x2011;Solution</code> | Instructor solution to the Small is Superb exercise.                 |
 | <code>3.0&#x2011;AssertThat&#x2011;Example            | Example of using AssertJ's assertThat().                             |
 | <code>4.0&#x2011;TailQueuePart1&#x2011;Problem        | Tail queue exercise, part 1. (Basic microtesting.)                   |
 | <code>4.5&#x2011;TailQueuePart1&#x2011;Solution       | Solution to Tail queue exercise, part 1.                             |
 | <code>5.0&#x2011;TailQueuePart3&#x2011;Problem        | Tail queue exercise, part 3. (Complex scenarios.)                    |
 | <code>5.5&#x2011;TailQueuePart3&#x2011;Solution       | Solution to the Tail queue exercise, part 3.                         |
  
(Yes, that is correct; there is no "part 2" of the Tail queue exercise.)
  
### Test Driven Development module

The `tdd/testDrivenDevelopment` directory contains projects you can import into your IDE for the Test Driven Development module. The projects are standard Maven projects.

| Directory                                                       | Description                                                        |
|-----------------------------------------------------------------|--------------------------------------------------------------------|
 | <code>1.0&#x2011;SoundCheckTest&#x2011;Problem</code>           | Sound Check exercise (duplicate of the microtesting project).      |
 | <code>2.0&#x2011;TDDRhythm&#x2011;Problem</code>                | TDD Rhythm problem.                                                |
 | <code>2.5&#x2011;TDDRhythm&#x2011;Solution</code>               | TDD Rhythm solution.                                               |
 | <code>3.0&#x2011;WildWildWireless&#x2011;Problem</code>         | Initial project structure for the Wild West Wireless problem.      |
 | <code>3.5.1&#x2011;WildWestWirelessTask1&#x2011;Solution</code> | Instructor's solution to Task 1 of the Wild West Wireless problem. | 
 | <code>3.5.2&#x2011;WildWestWirelessTask2&#x2011;Solution</code> | Instructor's solution to Task 2 of the Wild West Wireless problem. |
 | <code>3.5.3&#x2011;WildWestWirelessTask3&#x2011;Solution</code> | Instructor's solution to Task 3 of the Wild West Wireless problem. |
 | <code>3.5.4&#x2011;WildWestWirelessTask4&#x2011;Solution</code> | Instructor's solution to Task 4 of the Wild West Wireless problem. |
  
### Faking & Mocking module
The `tdd/fakingAndMocking` directory contains projects you can import into your IDE for the Faking & Mocking module. The projects are standard maven project.
Note that some projects will contain a Maven warning because of the `system` import of a supporting jar file.

| Directory                                      | Description                                          |
|------------------------------------------------|------------------------------------------------------|
| <code>1.0-DiceRollFakeless-Problem</code>      | Using a fake-less to resolve a problem.              |
| <code>1.5-DiceRollFakeless-Solution</code>     | Instructor's solution to using a fake-less solution. |
| <code>2.0-DiceRollFake-Problem</code>          | Using a fake to resolve a problem.                   |     
| <code>2.5-DiceRollFake-Solution</code>         | Instructor's solution to using a fake solution.      |
| <code>3.0-DiceRollAutoMock-Problem</code>      | Using an auto-mock to resolve a problem.             |
| <code>3.5-DiceRollAutoMock-Solution</code>     | Instructor's solution to using an auto-mock.         |
| <code>4.0-Orders-ListeningFake-Problem</code>  | Using a listening fake to resolve a problem.         |
| <code>4.5-Orders-ListeningFake-Solution</code> | Instructor's solution for using a listening fake.    |
| <code>5.0-Orders-Singleton-Problem</code>      | Dealing with a singleton.                            |
| <code>5.5-Orders-Singleton-Solution</code>     | Instructor's solution for dealing with a singleton.  |
| <code>6.0-Orders-AutoMock-Problem</code>       | Using an auto-mock.                                  |
| <code>6.5-Orders-AutoMock-Solution</code>      | Instructor's solution for using an auto-mock.        |
