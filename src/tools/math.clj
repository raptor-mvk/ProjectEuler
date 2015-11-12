(ns
  ^{:author raptor_MVK}
  tools.math)

(declare digits-count fact gcd heptagonal? hexagonal? int? nat-pow nat-pow-mod sqr
  square? pentagonal? pythagorean-triplet? triangle?)

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

(defn heptagonal?
  "Given N, returns true, if N is a heptagonal number, and false otherwise"
  [n]
  (let [root (Math/sqrt (+ (* 40 n) 9))]
    (and (int? root) (= 7 (rem (int root) 10)))))

(defn hexagonal?
  "Given N, returns true, if N is a hexagonal number, and false otherwise"
  [n]
  (let [root (Math/sqrt (inc (* 8 n)))]
    (and (int? root) (= 3 (rem (int root) 4)))))

(defn int?
  "Given float X, returns true, if X is integer, and false otherwise"
  [x]
  (= 0.0 (rem x 1)))

(defn nat-pow
  "Given X, N, returns X^N"
  [x n]
  (reduce *' (repeat n x)))

(defn nat-pow-mod
  "Given X, N, K, returns X^N mod K"
  [x n k]
  (reduce #(rem (*' %1 %2) k) 1 (repeat n x)))

(defn sqr
  "Given N, returns N^2"
  [n]
  (* n n))

(defn pentagonal?
  "Given N, returns true, if N is a pentagonal number, and false otherwise"
  [n]
  (let [root (Math/sqrt (inc (* 24 n)))]
    (and (int? root) (= 5 (rem (int root) 6)))))

(defn pythagorean-triplet?
  "Given A, B, C, returns true, if they form a pythagorean triplet, and false otherwise"
  [a b c]
  (= (sqr c) (+ (sqr a) (sqr b))))

(defn square?
  "Given N, returns true, if N is a perfect square, and false otherwise"
  [n]
  (int? (Math/sqrt n)))

(defn triangle?
  "Given N, returns true, if N is a triangle number, and false otherwise"
  [n]
  (int? (Math/sqrt (inc (* 8 n)))))