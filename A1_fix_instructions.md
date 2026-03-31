# Fix Instructions

These steps are derived from [`A1_review.md`](A1_review.md) and focus only on the remaining issue blocking full compliance.

## 1. Add visible correctness verification

- Keep the existing `SortVerifier` helper, but make its use visible in tracked repository code.
- Add tests or a dedicated verification runner that calls `SortVerifier.assertCorrect(...)` for Bubble Sort, Quick Sort, and LSD Radix Sort.
- Cover the required input distributions so the repository shows correctness verification against the same dataset types used in benchmarking.
- Make sure the verification path clearly exercises both:
  - comparison against `Arrays.sort()`
  - sortedness checking

Primary file:
- [`src/main/java/BenchmarkingAssignment1/util/SortVerifier.java`](src/main/java/BenchmarkingAssignment1/util/SortVerifier.java)

Suggested new locations:
- `src/test/java/...`
- or a tracked verification runner under `src/main/java/...`

## Recommended order

1. Add a visible verification path.
2. Run it on the required input distributions.
3. Mention that verification path briefly in the report or README if you want the evidence to be easier to inspect.
