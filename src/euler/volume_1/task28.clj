; Topic: grids

(ns
  ^{:author "raptor_MVK"}
  euler.volume_1.task28
  (:use tools.core)
  (:use clojure.test))

(defn spiral-diag-sums
  "Given N, returns the sum of the numbers on the diagonals in a 1001 by 1001 spiral
  formed starting with the number 1 and moving to the right in a clockwise direction"
  [n]
  (let [right-top (map #(* % %) (take-nth 2 (rrange+ n)))
        shift-clockwise (fn [coll] (map #(+ %1 (* 2 %2)) coll (rrange+ n)))
        right-bottom (shift-clockwise (butlast right-top))
        left-bottom (shift-clockwise right-bottom)
        left-top (shift-clockwise left-bottom)]
    (reduce + (concat right-top right-bottom left-bottom left-top))))

(deftest test1 (is (= (spiral-diag-sums 5) 101)))

(deftest test2 (is (= (spiral-diag-sums 1001) 669171001)))

(time (run-tests 'euler.volume_1.task28))
