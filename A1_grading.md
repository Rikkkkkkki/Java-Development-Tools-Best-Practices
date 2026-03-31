# Basis

Derived from [`A1_review.md`](A1_review.md) in the same repository.

This grading is based on `A1_review.md` only, per [`../Assignment_1_grading_criteria.md`](../Assignment_1_grading_criteria.md).

# Internal Marks

## Base Internal Mark: 80/100

Category breakdown:

- Core Assignment Compliance: `35/35`
  - `10/10`: the scalable algorithms are benchmarked at `1,000,000`, and Bubble Sort is separated into a smaller documented run
  - `10/10`: all required algorithms are present
  - `10/10`: all required input distributions are present in the review
  - `5/5`: Java code, JMH benchmark classes, and a PDF report are present

- Benchmarking Quality: `20/20`
  - `8/8`: real `JMH` usage is present
  - `4/4`: separate benchmark methods are present
  - `4/4`: warmup and measurement settings are within the required range
  - `4/4`: the benchmark design is methodologically sound in the review

- Algorithm Correctness and Fidelity: `25/25`
  - `5/5`: Bubble Sort includes early exit
  - `5/5`: Quick Sort is in-place
  - `5/5`: Quick Sort pivot strategy is identifiable and documented
  - `10/10`: Radix Sort matches the assignment requirements in the review

- Correctness Verification: `0/20`
  - `0/10`: comparison against `Arrays.sort()` is not evidenced in a tracked execution path
  - `0/10`: explicit sortedness verification is not evidenced in a tracked execution path

## Bonus Internal Mark: 4/10

Reasoning:

- the benchmark architecture, split scope, and report are all stronger than baseline
- however, bonus is not justified while a core assignment requirement, visible correctness verification, is still not evidenced

# Gating Rules Applied

- Rule 1 (`No JMH, No More Than 1/4`): not triggered
- Rule 2 (`Missing Core Benchmark Scope, No More Than 3/4`): not triggered
- Rule 3 (`Broken Correctness Evidence, No More Than 3/4`): triggered
  - the review finds no visible execution evidence for either comparison against `Arrays.sort()` or sortedness checking
  - this caps the regular grade at `3/4`
- Rule 4 (`Bonus Requires Strong Baseline`): triggered
  - the regular grade is below `4/4`
  - the review contains a major defect
  - therefore bonus point cannot be awarded

# Final Grade

- Regular grade: `3/4`
- Bonus grade: `0/1`
- Final grade: `3/4 + 0/1`

# Rationale

This repository is strong on benchmark structure, algorithm coverage, dataset generation, and `JMH` usage, and the documented split benchmark is acceptable under the current Assignment 1 clarification. The score drops because the assignment requires visible correctness verification against `Arrays.sort()` plus sortedness checking, and the review found no tracked execution evidence for either.
