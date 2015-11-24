(ns
  ^{:author raptor_MVK}
  tools.factorization
  (:use tools.core)
  (:use tools.math))

(declare and-divides? divides? factors factors-sum min-prime-factor or-divides? perfect?
  prime-factors prime-seq prime-seq$ prime? probable-prime? totient)

(defn and-divides?
  "Given N, coll, returns true, if N is the multiple of every element of coll, and false
  otherwise"
  [n coll]
  (every? #(divides? n %) coll))

(defn divides?
  "Given N, K, returns true, if N is the multiple of K, and false otherwise"
  [n k]
  (= (rem n k) 0))

(defn factors
  "Given N, returns its proper factors"
  [n]
  (if (< n 2)
    []
    (concat [1] (filter #(= 0 (rem n %)) (range+ 2 (quot n 2))))))

(defn factors-sum
  "Given N, returns the sum of its proper factors"
  [n]
  (let [prime-factors (frequencies (prime-factors n))]
    (- (reduce * (map #(/ (dec (nat-pow (key %) (inc (val %)))) (dec (key %)))
                   prime-factors)) n)))

(defn min-prime-factor
  "Given N and M, returns minimal prime factor of N, greater or equal than M"
  [n m]
  (first (drop-while #(or (< % m) (> (rem n %) 0)) prime-seq$)))

(defn or-divides?
  "Given N, coll, returns true, if N is the multiple of any element of coll, and false
  otherwise"
  [n coll]
  (reduce #(or %1 %2) (map #(divides? n %) coll)))

(defn perfect?
  "Given N, returns :deficient, if N is deficient, :perfect, if N is perfect, and
  :abundant, if N is abundant"
  [n]
  (first (filter (complement false?)
           (map #(and (%1 (factors-sum n) n) %2)
             [< = >] [:deficient :perfect :abundant]))))

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

(defn prime?
  "Given a number N, returns true, if N is prime, and false otherwise"
  [n]
  (if (< n 2)
    false
    (let [limit (int (Math/sqrt n))]
      (every? #(> (rem n %) 0) (take-while #(<= % limit) prime-seq$)))))

(defn probable-prime?
  "Given a number N, returns true, if N is prime with certainty 99.9%, and false
  otherwise"
  [n]
  (.isProbablePrime (BigInteger. (str n)) 10))

(defn totient
  "Given N, returns value of Euler's totient function of N"
  [n]
  (let [factors (distinct (prime-factors n))]
    (*' n (reduce #(*' %1 (-' 1 (/ 1 %2))) 1 factors))))
