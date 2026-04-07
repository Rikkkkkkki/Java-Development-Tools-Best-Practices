# Assignment 2 - Refactoring Code Smells

**Author:** Tatia Tkeshelashvili  
**Course:** Java Development Tools & Best Practices  
**Soft deadline:** Wednesday, April 8, 2026

---

## Overview

This package contains refactored versions of all 24 bad-smell examples from
`Java2026/src/badsmells`.

Each file preserves the original observable behavior while eliminating the
design flaw identified in the smell comment.

---

## Smell / File / Refactoring Summary

| #  | Smell                                         | File                                                    | Key Refactoring(s)                                                                        |
|----|-----------------------------------------------|---------------------------------------------------------|-------------------------------------------------------------------------------------------|
| 1  | Mysterious Name                               | `MysteriousNameExample.java`                            | Rename method, rename parameters and locals, Extract Method                               |
| 2  | Duplicated Code                               | `DuplicatedCodeExample.java`                            | Extract shared `tax()` and `shippingCost()` helpers                                       |
| 3  | Long Function                                 | `LongFunctionExample.java`                              | Extract Method x5 - one per distinct concern                                              |
| 4  | Long Parameter List                           | `LongParameterListExample.java`                         | Introduce Parameter Object x3 (`Address`, `GuardianContact`, `EnrollmentInfo`)            |
| 5  | Global Data                                   | `GlobalDataExample.java`                                | Extract Class (`AcademicSettings`), Encapsulate Field, dependency injection               |
| 6  | Mutable Data                                  | `MutableDataExample.java`                               | Return `Collections.unmodifiableList`; writes via controlled mutator only                 |
| 7  | Divergent Change                              | `DivergentChangeExample.java`                           | Extract Class x3 - one per reason to change                                               |
| 8  | Shotgun Surgery                               | `ShotgunSurgeryExample.java`                            | Move Method into `CourseTitleFormatter`; single source of truth                           |
| 9  | Feature Envy                                  | `FeatureEnvyExample.java`                               | Move Method to `StudentAccount`; remove envious class                                     |
| 10 | Data Clumps                                   | `DataClumpsExample.java`                                | Introduce Parameter Object `ContactInfo`; Move Method                                     |
| 11 | Primitive Obsession                           | `PrimitiveObsessionExample.java`                        | Replace Primitive with Object x4 (`StudentStatus`, `Age`, `UnpaidBalance`, `CountryCode`) |
| 12 | Repeated Switches                             | `RepeatedSwitchesExample.java`                          | Replace Conditional with Polymorphism using enum with abstract methods                    |
| 13 | Loops                                         | `LoopsExample.java`                                     | Replace Loop with Pipeline (stream filter + map + collect)                                |
| 14 | Lazy Element                                  | `LazyElementExample.java`                               | Inline Class - `StudentNameFormatter` removed                                             |
| 15 | Speculative Generality                        | `SpeculativeGeneralityExample.java`                     | Remove unused parameters from interface and implementation                                |
| 16 | Temporary Field                               | `TemporaryFieldExample.java`                            | Extract Class x2 - `OnsiteExam` and `OnlineExam` each own their field                    |
| 17 | Message Chains                                | `MessageChainsExample.java`                             | Hide Delegate - `University` exposes `getCoordinatorPhoneNumber()`                        |
| 18 | Middle Man                                    | `MiddleManExample.java`                                 | Remove Middle Man - `StudentPortal` inlined; client calls `TranscriptService` directly    |
| 19 | Insider Trading                               | `InsiderTradingExample.java`                            | Encapsulate Field; Move Method `freezeIfOverdrawn()` into `BankAccount`                   |
| 20 | Large Class                                   | `LargeClassExample.java`                                | Extract Class x5 by domain area; thin coordinator retained                                |
| 21 | Alternative Classes with Different Interfaces | `AlternativeClassesWithDifferentInterfacesExample.java` | Extract Interface `VirtualClassroom`; Rename Method; Use Supertype                        |
| 22 | Data Class                                    | `DataClassExample.java`                                 | Move Method x3 into `StudentRecord`; Encapsulate Field; remove external evaluators        |
| 23 | Refused Bequest                               | `RefusedBequestExample.java`                            | Extract Interface `FlyingBird`; Push Down `fly()`; `Penguin` no longer lies               |
| 24 | Comments                                      | `CommentsExample.java`                                  | Extract Method x3 (`applyVipDiscount`, `applyBulkDiscount`, `addTax`); comments removed   |

---

## Design Notes on Difficult Examples

### Repeated Switches
Used an **enum with abstract methods** - each constant owns its own
`tuitionDiscount()` and `dormPriority()` implementation. Adding a new student
type is one enum constant in one place. No existing switch needs touching.

### Refused Bequest
Introduced a `FlyingBird` **interface** separate from `Bird`. `Penguin`
extends `Bird` but does not implement `FlyingBird`, so it never promises to
fly. `Sparrow` and `Eagle` implement both. The LSP is restored: no class
throws `UnsupportedOperationException` for inherited contract.

### Large Class
Split into **five focused classes** by domain area: `EnrollmentOffice`,
`CourseCatalog`, `FinanceOffice`, `FacilitiesService`, and `AdministrationPortal`.
`SchoolAdministration` is kept as a thin wiring coordinator so that the
original `clientCode()` call pattern compiles unchanged.

### Divergent Change
Three completely independent classes extracted. Each has exactly one reason
to change: report wording, SQL schema, or CSV dialect.

---

## Behavioral Preservation

Every `clientCode()` method produces the same output as the original.
Inline and removal refactorings (`LazyElement`, `MiddleMan`) are documented
with the rationale that removing the abstraction is the correct fix, not a
regression.

---

## Package Structure

```
src/
└── assignment2/
    └── badsmells/
        ├── AlternativeClassesWithDifferentInterfacesExample.java
        ├── CommentsExample.java
        ├── DataClassExample.java
        ├── DataClumpsExample.java
        ├── DivergentChangeExample.java
        ├── DuplicatedCodeExample.java
        ├── FeatureEnvyExample.java
        ├── GlobalDataExample.java
        ├── InsiderTradingExample.java
        ├── LargeClassExample.java
        ├── LazyElementExample.java
        ├── LongFunctionExample.java
        ├── LongParameterListExample.java
        ├── LoopsExample.java
        ├── MessageChainsExample.java
        ├── MiddleManExample.java
        ├── MutableDataExample.java
        ├── MysteriousNameExample.java
        ├── PrimitiveObsessionExample.java
        ├── RefusedBequestExample.java
        ├── RepeatedSwitchesExample.java
        ├── ShotgunSurgeryExample.java
        ├── SpeculativeGeneralityExample.java
        └── TemporaryFieldExample.java
```