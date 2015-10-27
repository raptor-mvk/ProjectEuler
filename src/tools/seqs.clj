(ns
  ^{:author raptor_MVK}
  tools.seqs
  (:use tools.math))

(defn fib-seq
  "Returns lazy sequence of Fibonnaci numbers"
  [] (let [next-fib (fn [[prev cur]] [cur (+ prev cur)])]
       (map first (iterate next-fib [1 1]))))

(defn prime-seq
  "Returns lazy sequence of prime numbers"
  []
  (let [upd (fn [sieve n prime] (update-in sieve [(+ n prime)] conj prime))
        prime-step (fn ps [sieve prime]
                     (if-let [factors (get sieve prime)]
                       (recur (reduce #(upd %1 prime %2) (dissoc sieve prime) factors)
                         (inc prime))
                       (lazy-seq (cons prime (ps (assoc sieve (* prime prime)
                                                   (list prime)) (inc prime))))))]
    (prime-step {} 2)))

(def prime-seq$ (prime-seq))

(defn min-prime-factor
  "Given N and M, returns minimal prime factor of N, greater or equal than M"
  [n m]
  (first (drop-while #(or (< % m) (> (rem n %) 0)) prime-seq$)))

(defn prime-factors-seq
  "Given N, returns its prime factors"
  [n]
  (let [prime-factors (fn prime-factors [n factors m]
                        (let [next-prime-factor (min-prime-factor n m)]
                          (if (= next-prime-factor n)
                            (conj factors n)
                            (prime-factors (quot n next-prime-factor)
                              (conj factors next-prime-factor) next-prime-factor))))]
    (if (< n 2)
      nil
      (prime-factors n [] 2))))

(defn n-subseqs
  "Given sequence and N, returns all subsequences of length N"
  [coll n]
  (partition n (apply interleave (take n (iterate rest coll)))))

(defn prim-pyth-trip-seq
  "Returns lazy sequence of pythagorean triplets"
  []
  (let [pyth-trip-step (fn pyth-trip-step [m n]
                         (println "pyth" m n)
                         (if (<= m n)
                           (pyth-trip-step (inc m) (if (odd? m) 1 2))
                           (if (= 1 (gcd m n))
                             (let [m2 (sqr m)
                                   n2 (sqr n)]
                               (lazy-cat [[(- m2 n2) (* 2 m n) (+ m2 n2)]]
                                 (pyth-trip-step m (+ n 2))))
                             (pyth-trip-step m (+ n 2)))))]
    (pyth-trip-step 2 1)))

(defn collatz-seq
  "Given N, returns next Collatz sequence, starting from N"
  [n]
  (let [collatz-step (fn colltz-step [n]
                       (cond
                         (= n 1) 0
                         (odd? n) (inc (*' n 3))
                         :else (quot n 2)))]
    (take-while #(> % 0) (iterate collatz-step n))))