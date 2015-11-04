(ns
  ^{:author raptor_MVK}
  tools.math)

(declare digits-count fact gcd nat-pow sqr pythagorean-triplet?)

(defn digits-count
  "Given natural N, returns number of its digits in decimal number system;
  given natural N and K, returns number of digits in N in K-ary number system"
  ([n]
    (digits-count n 10))
  ([n k]
    (if (= n 0)
      0
      (inc (digits-count (quot n k))))))

(defn fact
  "Given N, returns N!"
  [n]
  (if (<= n 1) 1 (*' n (fact (dec n)))))

(defn gcd
  "Given M, N, returns their greater common divisor"
  [m n]
  (cond
    (= m 0) n
    (= n 0) m
    (< m n) (gcd (rem n m) m)
    :else (gcd (rem m n) n)))

(defn nat-pow
  "Given X, N, returns X^N"
  [x n]
  (reduce *' (repeat n x)))

(defn sqr
  "Given N, returns N^2"
  [n]
  (* n n))

(defn pythagorean-triplet?
  "Given A, B, C, returns true, if they form a pythagorean triplet, and false otherwise"
  [a b c]
  (= (sqr c) (+ (sqr a) (sqr b))))