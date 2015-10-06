(ns
  ^{:author raptor_MVK}
  tools.math)

(defn divides?
  "Given N, K, returns true, if N is the multiple of K, and false otherwise"
  [n k] (= (rem n k) 0))

(defn or-divides?
  "Given N, coll, returns true, if N is the multiple of any element of coll, and false
  otherwise"
  [n coll] (reduce #(or %1 %2) (map #(divides? n %) coll)))

(defn and-divides?
  "Given N, coll, returns true, if N is the multiple of every element of coll, and false
  otherwise"
  [n coll] (every? #(divides? n %) coll))
