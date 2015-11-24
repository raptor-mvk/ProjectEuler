(ns
  ^{:author raptor_MVK}
  tools.math)

(declare digits-count fact gcd heptagonal? hexagonal? int-power? int? nat-pow nat-pow-mod
  pentagonal? pythagorean-triplet? round-to-fixed sgn solve-sqr-eq sqr square? triangle?)

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

(defn int-power?
  "Given N, returns true, if N is a power of an integer, and false otherwise"
  [n]
  (if (= n 1)
    false
    (loop [i 2]
      (let [res (Math/pow n (/ 1.0 i))]
        (if (int? res)
          true
          (if (< res 2.0)
            false
            (recur (inc i))))))))

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

(defn pentagonal?
  "Given N, returns true, if N is a pentagonal number, and false otherwise"
  [n]
  (let [root (Math/sqrt (inc (* 24 n)))]
    (and (int? root) (= 5 (rem (int root) 6)))))

(defn pythagorean-triplet?
  "Given A, B, C, returns true, if they form a pythagorean triplet, and false otherwise"
  [a b c]
  (= (sqr c) (+ (sqr a) (sqr b))))

(defn round-to-fixed
  "Given X and N, returns X rounded to N decimal places"
  [x n]
  (let [mult (nat-pow 10 n)]
    (double (/ (Math/round (* (double x) mult)) mult))))

(defn sgn
  "Given x, returns signum(x)"
  [x]
  (cond
    (< x -1e-15) -1
    (> x 1e-15) 1
    :else 0))

(defn solve-sqr-eq
  "Given square equation (a, b, c), returns its solution, if there is no solution,
  returns empty vector"
  [[a b c]]
  (let [discr (- (* b b) (* 4 a c))
        solution (fn [oper] (/ (oper (- b) (Math/sqrt discr)) (* 2 a)))]
    (cond
      (or (and (= a 0) (= b 0)) (< discr 0)) []
      (= a 0) (/ (- c) b)
      (> discr 0) (map solution [+ -])
      (= discr 0) (solution +))))

(defn sqr
  "Given N, returns N^2"
  [n]
  (* n n))

(defn square?
  "Given N, returns true, if N is a perfect square, and false otherwise"
  [n]
  (int? (Math/sqrt n)))

(defn triangle?
  "Given N, returns true, if N is a triangle number, and false otherwise"
  [n]
  (int? (Math/sqrt (inc (* 8 n)))))