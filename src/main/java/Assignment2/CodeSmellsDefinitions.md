# Code Smells Definitions

Source basis:

- *Refactoring: Improving the Design of Existing Code (2nd ed.)* by Martin Fowler, Chapter 3

This file uses short paraphrases of the Chapter 3 smell definitions.

## 1. Mysterious Name

Definition:
Names do not clearly communicate intent, so readers must guess what a variable, function, or class really means.

Why It Is Bad:
It slows comprehension and increases the chance of misuse or wrong changes.

Related OOP Design Principles:
Primary: Abstraction.
Secondary: Clarity of intent, communicative design.

## 2. Duplicated Code

Definition:
The same code structure appears in multiple places.

Why It Is Bad:
Understanding and changing behavior requires touching more than one copy, which raises maintenance cost and creates a risk that copies drift out of sync.

Related OOP Design Principles:
Primary: DRY.
Secondary: High cohesion, single source of truth.

## 3. Long Function

Definition:
A function has grown large enough that it hides intention and mixes concerns.

Why It Is Bad:
Large functions raise cognitive load and make extraction, testing, and localized modification harder.

Related OOP Design Principles:
Primary: Single Responsibility Principle.
Secondary: High cohesion.

## 4. Long Parameter List

Definition:
A function takes so many parameters that the call and the signature become noisy.

Why It Is Bad:
Call sites become harder to read, and the chance of passing arguments incorrectly increases.

Related OOP Design Principles:
Primary: Encapsulation.
Secondary: Information hiding, tell-don't-ask.

## 5. Global Data

Definition:
Data is reachable from many places in the system.

Why It Is Bad:
It becomes difficult to know who can change the data and what side effects follow, which makes bugs hard to trace.

Related OOP Design Principles:
Primary: Encapsulation, information hiding.
Secondary: Low coupling.

## 6. Mutable Data

Definition:
Data changes over time in ways that can affect other parts of the system.

Why It Is Bad:
Shared mutable state makes behavior fragile and bugs harder to predict or diagnose.

Related OOP Design Principles:
Primary: Encapsulation.
Secondary: Information hiding, controlled state change.

## 7. Divergent Change

Definition:
A single module tends to change for different unrelated reasons.

Why It Is Bad:
Unrelated concerns are forced into one place, so each change becomes riskier and harder to isolate.

Related OOP Design Principles:
Primary: Single Responsibility Principle.
Secondary: Separation of concerns, high cohesion.

Note:
`Divergent Change` and `Shotgun Surgery` are opposites.
With `Divergent Change`, many kinds of changes hit one module.
With `Shotgun Surgery`, one change hits many modules.

## 8. Shotgun Surgery

Definition:
A single change forces many small edits across many modules.

Why It Is Bad:
Changes are easy to miss, and even small modifications become expensive and error-prone.

Related OOP Design Principles:
Primary: Low coupling.
Secondary: Information hiding, locality of change.

## 9. Feature Envy

Definition:
A function seems more interested in another object’s data or behavior than in the object that currently owns it.

Why It Is Bad:
It suggests behavior is living in the wrong place, which increases coupling and weakens cohesion.

Related OOP Design Principles:
Primary: High cohesion.
Secondary: Low coupling, tell-don't-ask.

Note:
`Feature Envy` is about behavior living with the wrong data.
`Message Chains` is about callers navigating through too much object structure.
A method can use message chains without feature envy, and vice versa.

## 10. Data Clumps

Definition:
The same small group of data items appears together repeatedly.

Why It Is Bad:
Keeping those values separate causes repetition, noisy APIs, and missed opportunities to attach behavior to the concept they form together.

Related OOP Design Principles:
Primary: Encapsulation.
Secondary: Abstraction, high cohesion.

## 11. Primitive Obsession

Definition:
Domain concepts are represented with primitive types instead of meaningful objects.

Why It Is Bad:
The domain model becomes weaker, and rules and validation get scattered through duplicated checks and ad hoc conditionals.

Related OOP Design Principles:
Primary: Abstraction, encapsulation.
Secondary: Domain modeling.

## 12. Repeated Switches

Definition:
The same switching logic appears in multiple places.

Why It Is Bad:
Adding a new case requires coordinated edits in several modules, which creates duplication and inconsistent behavior risk.

Related OOP Design Principles:
Primary: Open/Closed Principle, polymorphism.
Secondary: DRY.

## 13. Loops

Definition:
Loop-based collection processing is used where a clearer pipeline-style transformation could express the intent.

Why It Is Bad:
The reader sees control flow details instead of the higher-level data transformation.

Related OOP Design Principles:
Primary: Abstraction.
Secondary: Clarity of intent.

## 14. Lazy Element

Definition:
A function, class, or hierarchy adds too little value to justify its existence.

Why It Is Bad:
Extra structure without benefit makes the design harder to navigate for no payoff.

Related OOP Design Principles:
Primary: Meaningful abstraction.
Secondary: YAGNI, simplicity.

## 15. Speculative Generality

Definition:
The code contains abstractions, hooks, or options for future needs that never arrived.

Why It Is Bad:
The current system becomes harder to understand and maintain for imaginary requirements.

Related OOP Design Principles:
Primary: YAGNI.
Secondary: Simplicity, avoid needless abstraction.

## 16. Temporary Field

Definition:
An object contains fields that are only meaningful in certain situations.

Why It Is Bad:
The object’s state becomes inconsistent and confusing, which leads to fragile logic and awkward conditionals.

Related OOP Design Principles:
Primary: Encapsulation.
Secondary: High cohesion, class invariants.

## 17. Message Chains

Definition:
Client code navigates through a chain of objects to reach behavior or data.

Why It Is Bad:
Callers become coupled to internal structure, so changes to the object graph ripple outward.

Related OOP Design Principles:
Primary: Law of Demeter.
Secondary: Information hiding, low coupling.

## 18. Middle Man

Definition:
A class delegates too much of its interface to another class and adds too little value of its own.

Why It Is Bad:
The extra indirection increases noise without providing enough encapsulation benefit.

Related OOP Design Principles:
Primary: Meaningful abstraction.
Secondary: Appropriate encapsulation, simplicity.

## 19. Insider Trading

Definition:
Two modules know too much about each other’s internals and collaborate too closely.

Why It Is Bad:
Tight knowledge of internals increases coupling and makes both modules harder to change independently.

Related OOP Design Principles:
Primary: Information hiding.
Secondary: Low coupling, encapsulation.

## 20. Large Class

Definition:
A class has accumulated too many fields, methods, or responsibilities.

Why It Is Bad:
Broad classes increase cognitive load and often become breeding grounds for duplication and tangled changes.

Related OOP Design Principles:
Primary: Single Responsibility Principle.
Secondary: High cohesion, separation of concerns.

Note:
`Large Class` is mainly a structural smell: the class has grown too broad.
`Divergent Change` is mainly a change-pattern smell: the same class keeps being modified for different unrelated reasons.

## 21. Alternative Classes with Different Interfaces

Definition:
Classes that serve similar purposes expose different interfaces.

Why It Is Bad:
Substitution becomes harder, client code grows more complex, and translation code starts to accumulate.

Related OOP Design Principles:
Primary: Polymorphism, substitutability.
Secondary: Open/Closed Principle.

## 22. Data Class

Definition:
A class mostly stores data and exposes it through accessors while meaningful behavior lives elsewhere.

Why It Is Bad:
The data stays passive, logic gets pushed into other classes, and cohesion becomes weaker.

Related OOP Design Principles:
Primary: Encapsulation.
Secondary: High cohesion, information hiding.

Note:
`Data Class` is about behavior being missing from the data holder.
`Large Class` is about too much behavior and state being packed into one class.
They are almost opposite failure modes of class design.

## 23. Refused Bequest

Definition:
A subclass inherits behavior or interface that it does not want or cannot properly support.

Why It Is Bad:
The inheritance relationship becomes misleading and can break client expectations.

Related OOP Design Principles:
Primary: Liskov Substitution Principle.
Secondary: Proper inheritance, substitutability.

## 24. Comments

Definition:
Comments are used to explain code that should be made clearer through better names and structure.

Why It Is Bad:
They add maintenance burden without fixing the real design problem, and they can become stale while the code changes.

Related OOP Design Principles:
Primary: Clarity of intent.
Secondary: Abstraction, self-explanatory design.