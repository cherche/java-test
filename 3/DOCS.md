# Documentation

## `Function`

An interface that allows the storage of methods in container class instances

`void run()`: calls the contained method

Example:
```java
Function talkToRyan = new Function() {
  public void run() {
    System.out.println("Greetings!");
  }
}
talkToRyan.run(); // => Greetings!
```

## `Action`

Stores a Function alongside some String description

`String name`: the name of the `Action` instance

`Function fn`: the `Function` associated with the `Action` instance

`void run()`: calls `fn.run()`

Example:
```java
Action action = new Action("eat pizza", new Function() {
  public void run() {
    System.out.println("You ate pizza");
  }
});
action.run(); // => You ate pizza
```

## `Person`

Contains an array of Action instances and a name

`String name`: the name of the `Person` instance
`Action[] actions`: the actions of the `Person` instance

## `Room`

`String name`: Contains the name of the `Room` instance

`Action[] actions`: Contains the actions of the `Room` instance

`Set<Room> links`: Contains any `Room` instances connected to this one

`Set<Person> people`: Contains any `People` instances in this `Room` instance
