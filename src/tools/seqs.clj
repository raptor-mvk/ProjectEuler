(ns
  ^{:author raptor_MVK}
  tools.seqs
  (:use tools.core)
  (:use tools.math)
  (:use tools.factorization))

(declare abundants-seq collatz-seq fib-seq prim-pyth-trip-seq)

(defn abundants-seq
  "Given N, returns the sequence of abundant numbers below N"
  [n]
  (loop [nums (rrange n)
         res []]
    (if (empty? nums)
      (sort (distinct res))
      (let [cur (first nums)
            tail (rest nums)
            cur-type (perfect? cur)]
        (if (= cur-type :deficient)
          (recur (rest nums) res)
          (let [multiples (range (condp = cur-type :perfect 2 1) (/ n cur))
                new-abundants (map #(* % cur) multiples)]
            (recur (remove #(= 0 (rem % cur)) nums) (concat res new-abundants))))))))

(defn collatz-seq
  "Given N, returns next Collatz sequence, starting from N"
  [n]
  (let [collatz-step (fn colltz-step [n]
                       (cond
                         (= n 1) 0
                         (odd? n) (inc (*' n 3))
                         :else (quot n 2)))]
    (take-while #(> % 0) (iterate collatz-step n))))

(defn fib-seq
  "Returns lazy sequence of Fibonnaci numbers"
  [] (let [next-fib (fn [[prev cur]] [cur (+' prev cur)])]
       (map first (iterate next-fib [1 1]))))

(defn prim-pyth-trip-seq
  "Returns lazy sequence of pythagorean triplets"
  []
  (let [pyth-trip-step (fn pyth-trip-step [m n]
                         (if (<= m n)
                           (pyth-trip-step (inc m) (if (odd? m) 1 2))
                           (if (= 1 (gcd m n))
                             (let [m2 (sqr m)
                                   n2 (sqr n)]
                               (lazy-cat [[(- m2 n2) (* 2 m n) (+ m2 n2)]]
                                 (pyth-trip-step m (+ n 2))))
                             (pyth-trip-step m (+ n 2)))))]
    (pyth-trip-step 2 1)))