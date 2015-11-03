; Topic: Pythagorean triplets

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task9
  (:use clojure.test)
  (:use tools.seqs)
  (:use tools.math)
  (:use tools.factorization))

(defn pyth-triplet-1000
  "Returns product abc for single Pythagorean triplets such, that a + b + c = 1000"
  []
  (let [n 1000
        factor-set (set (conj (factors n) n))
        triplet (first (drop-while #(nil? (some factor-set #{(reduce + %)}))
                   (prim-pyth-trip-seq)))]
    (* (reduce * triplet) (nat-pow (quot 1000 (reduce + triplet)) 3))))

(deftest test1 (is (= (pyth-triplet-1000) 31875000)))

(time (run-tests 'euler.volume_1.task9))