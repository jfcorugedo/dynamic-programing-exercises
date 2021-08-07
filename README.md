# Dynamic Programing challenges

This repository contains some interesting challenges and exercises solved with Java and dynamic programming techniques.  

## What is dynamic programming?

Dynamic Programming (DP) is an algorithmic technique for solving a bigger and hard problem by breaking it down into simpler sub-problems and utilizing the fact that the optimal solution to the bigger problem depends upon the optimal solution to its smaller sub-problems.

It uses several techniques to avoid O(n^2) complexity, that comes some times when you solve a problem using pure recursion approaches.

## Big O Notation

For any of the challenges and algorithms implemented in this repository there are a lot of ways of solving them.

How do we know which one is the best?

Big O Notation is a way of measure time and space complexity of each solution, so we can catalog and compare them.

Big O Notation has to do with performance in both aspects: Time and space (less or high memory-intensive).

So we can tell that a solution O(n) is better than another one O(n^2), always talking in terms of performance.

It will also help us to use a precise vocabulary when talking about the performance of the code.

### Timing our code

Lets introduce Big O with a simple example:

We want to write a function that computes the sum of all the numbers from 1 to n.

Some examples:

Given 3, then it will return 6 (1 + 2+ 3)
Given 100, then it will return 5050

A first attempt to solve this function could be the following:

```java
private int solution(int n) {
    int total = 0;
    for(int i = 1 ; i <= n ; i++) total += i;
    return total;
}
```

In this approach, we are using an accumulator variable, and a for loop.

Let's take a look at another approach, this time using Zomg Wut description (more info in [SumUpTo](src/test/java/com/jfcorugedo/udemy/javascriptalgorithmsanddatastructures/SumUpTo.java) class) of the problem:

```java
private int solutionZomgWut(int n) {
    return n * (n + 1) /2;
}
```

This time we are not using any accumulator variable, and there isn't any loop either.

Both solutions works properly and compute the right output. However one of them is much better in terms of time.

There is no point in talking about time units. If we run several tests using these solutions, each time we run a test it will give us a different time.
Even using the same hardware. So it is quite difficult, if no imposible, to say "This solution takes 2 seconds less than this other one".

And what worse, for fast algorithms, speed measure may not be precise enough.

Big O Notation can help us with this problem. Instead of counting seconds, which are so varible, let's count the number of simple operations the computer has to perform.

Let's see how many basic operations each solution is using to solve the problem:

In the case of Zomg Wut approach, it uses three basic operations:
* One multiplication
* One addition
* One division

No matter the value of `N`, it always uses only three basic operations to compute the result.

If `N=3` then it uses three basic operations.
If `N=10000000` then it uses three basic operations as well.

This makes this approach very efficient.

Let's go over now the other approach (the one with the for loop):

```java
private int solution(int n) {
    int total = 0;
    for(int i = 1 ; i <= n ; i++) total += i;
    return total;
}
```

Here we have this operations:
* One assignment (`int total = 0;`)
* One assignment (`int i = 1`)
* `N` conditionals (`i <= n`). The loop executes this condition after every iteration, so it will execute it N times.
* `N` addtions and assignments (`i++`). Again, the loop executes this code N times.
* `N` additions and assignments (`total += i`)

So this code is executing around `3N + 2` basic operations to compute the solution.

So if `N=10`, then this approach will need 32 (3*10 + 2) basic operations.
If `N=1000000`, then this approach will need three million and two basic operations.

The important point here is not the exact number of basic operations each approach needs, but how the operations grow with `N`.
In the case of the for loop, the number of operations grows roughly proportionally with `N`.

Given this very basic example we can infer a valuable lesson: **Counting is hard**.

So instead of trying to calculate the exact number of operations, it is more important to calculate the trend.


>Big O Notation is a way of formalize fuzzy counting.

It allows us to talk formally about how the runtime of an algorithm grows as the inputs grow.

We say an algorithm is **O(f(n))** if the number of simple operations a computer has to do is eventually less than a constant times **f(n)**, as **n** increases. 

There are several types, depending on the trend:
* f(n) could be constant: `f(n)=1`
* f(n) could be linear: `f(n)=n`
* f(n) could be quadratic: `f(n)=n^2`
* f(n) could be something entirely different! (i.e: `O(log(N))` or `O(N*log(N))`)

So given that, the approach that uses Zomg Wut definition could be expressed in Big O as:

`f(n) = 3  =>  O(1)`

That means the number of basic operations is constant and it does not have nothing to do with the size of the inputs.

On the other hand, the approach that uses a for loop can be expressed as:

`3N + 2  =>  O(N)`

It is a linear relationship. It does ont matter if it is 3N or 10N, the important thing is the trend.

Let's see another example:

We want a function that counts up and down from 1 to a given value.

Given `N=5` then it will count 1 2 3 4 5 4 3 2 1

Let's see a very basic implementation:

```java
private int solution(int n) {
    //Going up!
    for(int i = 1 ; i <= n ; i++) {
        //Do something
    }
    
    //Going down
    for(int i = n-1 ; i > 0 ; i--) {
    //Do something
    }
}
```

Now let's compute its Big O notation:

Each for loop has a complexity of `O(N)`.

So this solution is a `O(N)` algorithm (`O(N) + O(N)` is equal to `O(N)` when `N` tends to infinite).

Let's see another example, this with a bigger Big O Notation.

```java
private int solution(int n) {
    for(int i = 1 ; i <= n ; i++) {
        for(int j = 1 ; j <= n ; j++) {
           //Do something with i and j
        }
    }
}
```

This time we have a for loop inside another for loop.

The inner for loop has a `O(N)` complexity. Same the other for loop.

Taking both into account, we end up with a quadratic complexity: `O(N*N) = O(N^2)`

This is not, for sure, a very good situation to be involved.

To sum up, there are a set of rules of thumb for Big O expressions:

### Constants does not matter

From the point of view of Big O, these expressions are the same: `O(2N)` and `O(10N)`. 

Both represents a linear trend between simple operations and inputs grow.

So both must be represented as `O(N)`.

Same with these exapmles: `O(3)` and `O(500)`. Both represents constant complexity: `O(1)`.

Always think about the trend when `N` is equal to infinite.

### Smaller terms do not matter

Take into account always the bigger term in the expression. 

Remember that we are thinking about the trend when `N` tends to infinite.

Some examples:
* This expression `O(N + 10)` must be simplified to `O(N)`
* This expression `O(1000N + 500)` must be simplified to `O(N)`
* This expression `O(N^2 + 4N + 500)` must be simplified to `O(N^2)`

### Arithmetic operations are constants

It does not matter the exact values we are using, an addition or a multiplier are constant basic operations.

### Variable assignment is also constant

The computer takes rouhtly the same time to assign the value `3` to a varialble than asigning the value `100000000` to the same variable.

### Accesing elements in an array using index is constant

It does not matter if the array has one million or just 3 elements, accessing an element using the index takes constant time.

`scores[10]` takes aroung the same time than `scores[1000000]`.

### The complexity of a loop is its length

When we analyze loops, then the length matters. From the point of view of Big O, a loop performs `N` times the operations inside the loop.

If the loop only contains constant operations, then the total complexity is `O(N)`. But if the loop has something more complex, then the total complexity will be higher.

### Constants and primitive types have constant Space complexity

From the point of view of memory (auxiliary space complexity is the one used by the algorithm not including space taken by the inputs), a constant or primitive value take constant space.

### Strings requires O(N) space

If the length of the String grows, then the space complexity grows, so it is an `O(N)` trend.

### Arrays and Lists takes O(N) space

If the objects inside arrays and collecitons grow, the space complexity grow.





