; Topic: combinatorics
; Idea: possible combinations are 1-digit * 4-digit = 4-digit and
;       2-digit * 3-digit = 4-digit

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task32
  (:use clojure.test)
  (:use tools.comb)
  (:use tools.conversions))

(defn pandigital-products-count
  "Returns sum of all products whose multiplicand/multiplier/product identity can be
  written as a 1 through 9 pandigital"
  []
  (let [prod-count 4
        perms (all-perms [1 2 3 4 5 6 7 8 9])
        transform (fn [coll mult1-count]
                    (= (seq2num (take-last prod-count coll))
                      (* (seq2num (take mult1-count coll))
                        (seq2num (drop-last prod-count (drop mult1-count coll))))))
        test #(or (transform % 1) (transform % 2))]
    (reduce + (distinct (map #(seq2num (take-last prod-count %))
                          (filter test perms))))))

(deftest test1 (is (= (pandigital-products-count) 45228)))

(time (run-tests 'euler.volume_1.task32))