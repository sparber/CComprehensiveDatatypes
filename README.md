# CComprehensiveDatatypes

**Syntax Extension for the Programming Language C**

The idea is that if there are more possible allocations for one variable – referring a concrete context - you declare the variable as comprehensive and pretend there is only one possible allocation.

The paragraph sign declares a variable as comprehensive
```C
int § a;
```

The initialization of such a variable happens by calling a generator:
```C
int § a = factors(100);
```

A generator’s objective is to calculate all possible solutions for a given problem. The ```factors(int)``` generator could be defined like this:
```C
int factors(int number) generator {
    int factor = 2;
    while (factor <= number) {
        while (number % factor == 0) {
            yield factor;
            number /= factor;
        }
    factor++;
    }
}
```

The split operator | (vertical bar) is for processing a comprehensive variable:
```C
void main() {
    int § compr = factors(100);
    int factor = |compr;
    printf("%d;", factor);
}
```
or in short:
```C
void main() {
    printf("%d;", |factors(100));
}
```

This produces the output ```"2;2;5;5;"```. 

The split operator defines an implicit loop, iterating over all the values represented by the consecutive comprehensive variable. The loop starts at the position of the split operator, reaching till the end of the current scope.


