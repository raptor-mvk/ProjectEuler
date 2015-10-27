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