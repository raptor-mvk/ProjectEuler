; Topic: graph
; Idea: depth-first search

(ns
  ^{:author raptor_MVK}
  euler.volume_1.task61
  (:use clojure.test)
  (:use tools.core)
  (:use tools.math))

(defn figurate-cycle
  "Returns sum of the only ordered set of six cyclic 4-digit numbers for which each
  polygonal type: triangle, square, pentagonal, hexagonal, heptagonal, and octagonal,
  is represented by a different number in the set"
  []
  (let [octagonal-nums (filter #(> (quot (rem % 100) 10) 0)
                         (take-while #(< % 10000)
                           (drop-while #(< % 1000) (map #(* % (- (* 3 %) 2)) (rrange)))))
        last-num (fn [coll] (+ (quot (first coll) 100)
                              (* (rem (last coll) 100) 100)))
        find-nums (fn [coll test-fn]
                    (let [prefix (* (rem (last coll) 100) 100)]
                      (filter test-fn (map #(+ prefix %) (range 10 100)))))
        get-next (fn get-next [coll test-fns]
                   (if (= 1 (count test-fns))
                     (let [new-num (last-num coll)]
                       (when ((first test-fns) new-num) (concat coll [new-num])))
                     (loop [res []
                            i 0]
                       (if (= i (count test-fns))
                         res
                         (let [cur-fn (nth test-fns i)
                               new-nums (find-nums coll cur-fn)]
                           (recur (apply concat res
                                    (keep #(get-next (concat coll [%])
                                             (but-nth test-fns i)) new-nums))
                             (inc i)))))))]
    (reduce + (first
                (filter (complement empty?)
                  (map #(get-next [%]
                          [triangle? square? pentagonal? hexagonal? heptagonal?])
                    octagonal-nums))))))

  (deftest test1 (is (= (figurate-cycle) 28684)))

  (time (run-tests 'euler.volume_1.task61))
