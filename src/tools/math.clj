(ns
  ^{:author raptor_MVK}
  tools.math)

(defn divides?
  "Given N, K, returns true, if N is the multiple of K, and false otherwise"
  [n k]
  (= (rem n k) 0))

(defn or-divides?
  "Given N, coll, returns true, if N is the multiple of any element of coll, and false
  otherwise"
  [n coll]
  (reduce #(or %1 %2) (map #(divides? n %) coll)))

(defn and-divides?
  "Given N, coll, returns true, if N is the multiple of every element of coll, and false
  otherwise"
  [n coll]
  (every? #(divides? n %) coll))

(defn sqr
  "Given N, returns N^2"
  [n]
  (* n n))

(defn fact
  "Given N, returns N!"
  [n]
  (if (<= n 1) 1 (*' n (fact (dec n)))))

(defn gcd
  "Given M, N, returns their greater common divisor"
  [m n]
  (println m n)
  (cond
    (= m 0) n
    (= n 0) m
    (< m n) (gcd (rem n m) m)
    :else (gcd (rem m n) n)))

(defn factors
  "Given N, returns its proper factors"
  [n]
  (if (< n 2)
    []
    (concat [1] (filter #(= 0 (rem n %)) (range 2 (inc (quot n 2)))))))

(defn nat-pow
  "Given X, N, returns X^N"
  [x n]
  (reduce *' (repeat n x)))

(defn leap-year?
  "Given N, returns true, if N-th year is leap, and false otherwise"
  [n]
  (or (= 0 (rem n 400)) (and (= 0 (rem n 4)) (> (rem n 100) 0))))

(defn days-in-month
  "Given month and year, returns number of days in this month, months are numbered from
   zero"
  [month year]
  (cond
    (= month 1) (if (leap-year? year) 29 28)
    (some #{month} #{3 5 8 10}) 30
    :else 31))

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

(defn prime-factors
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

(defn factors-sum
  "Given N, returns the sum of its proper factors"
  [n]
  (let [prime-factors (frequencies (prime-factors n))]
    (- (reduce * (map #(/ (dec (nat-pow (key %) (inc (val %)))) (dec (key %)))
                prime-factors)) n)))

(defn perfect?
  "Given N, returns :deficient, if N is deficient, :perfect, if N is perfect, and
  :abundant, if N is abundant"
  [n]
  (first (filter (complement false?)
           (map #(and (%1 (factors-sum n) n) %2)
             [< = >] [:deficient :perfect :abundant]))))
